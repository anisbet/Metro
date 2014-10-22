/*
 * Metro allows customers from any affiliate library to join any other member library.
 *    Copyright (C) 2013  Edmonton Public Library
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 */
package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import mecard.Protocol;
import mecard.config.ConfigFileTypes;
import mecard.config.MessagesTypes;
import mecard.config.PropertyReader;
import mecard.exception.ConfigurationException;
import mecard.exception.SIPException;

/**
 * Helps with the interpretation of SIP2 messages.
 * recv:98YYYYYN60000320130424    1135112.00AOEPLMNA|AMEPLMNA|BXYYYYYYYYYYYNNYYY|ANSIPCHK|AY1AZE80C
   St. Albert returns
   recv:98YYYYNN01000320130808    1509002.00AOst|AMSt. Albert Public Library|BXYYYYYYYYYYYYYYYY|ANUnassigned|VNSIP 2.00.106 SirsiDynix Inc. 7.5.074.40|AY1AZD3FA
   We need to check that the values at position 2 (online status) 
   and 56 (Patron Information) are both 'Y'
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class SIPMessage
{
    public enum IlsType
    {
        SIRSI_DYNIX("SIRSI_DYNIX"),
        POLARIS("POLARIS");
        
        private final String type;

        private IlsType(String s)
        {
            this.type = s;
        }

        @Override
        public String toString()
        {
            return this.type;
        }
    }
    protected HashMap<String, String> fields;
    protected final String originalMessage;
    protected final String code;
    protected final String codeBits;
    protected Properties messageProperties;
    protected final IlsType ilsType;
    public final static String ILS_TYPE_TAG = "ils-type";  // optional field to adapt to new customerMessage type.
    
    /**
     * Constructor
     * @param sipMessage SIP2 message string.
     * @throws SIPException 
     */
    public SIPMessage(String sipMessage)
            throws SIPException
    {
        Properties sip2Properties = PropertyReader.getProperties(ConfigFileTypes.SIP2);
        // get the value from the properties file, but if not found assume SIRSI_DYNIX 
        // which will set the customerMessage type to a standard SIPCustomerMessage().
        String whichIls = sip2Properties.getProperty(ILS_TYPE_TAG, IlsType.SIRSI_DYNIX.toString());
        // whichIls can still be empty if a tag was entered but it was empty.
        if (whichIls.isEmpty()) whichIls = IlsType.SIRSI_DYNIX.toString();
        try
        {
            this.ilsType = IlsType.valueOf(whichIls);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ConfigurationException(" **Invalid option '" + whichIls + "' in sip2.properties."
                    + " Either remove the tag for default of SIRSI_DYNIX or enter a valid ILS type.");
        }
        // If the message we get is broken, it is most likely that the ILS is down.
        this.messageProperties = PropertyReader.getProperties(ConfigFileTypes.MESSAGES);
        this.originalMessage = sipMessage;
        String[] stringFields;
        if (sipMessage.contains("|"))
        {
            // Split the fields
            stringFields = sipMessage.split("\\|");
        }
        else
        {
            // The login response contains no pipe, just the code, result bit, and checksum
            if (sipMessage.contains("AY"))
            {
                stringFields = sipMessage.split("AY");
            }
            else // not a sip message.
            {
                throw new SIPException(this.messageProperties.getProperty(
                    MessagesTypes.UNAVAILABLE_SERVICE.toString()));
            }
        }
        this.fields = new HashMap<>();
        try
        {
            this.code = stringFields[0].substring(0, 2);
            this.codeBits = stringFields[0].substring(2);
        }
        catch (IndexOutOfBoundsException ex)
        {
            throw new SIPException(" Index was out of bounds while parsing "
                    + "SIP2 code section. This may not be a SIP2 message, or it "
                    + "be malformed: '" + sipMessage + "'");
        }
        for (int i = 1; i < stringFields.length; i++)
        {
            String key; // 2 letter code like BX
            String value; // The rest of the message.
            try
            {
                key = stringFields[i].substring(0, 2); // 2 letter code like BX
                value = stringFields[i].substring(2); // reset of the message
            }
            catch (IndexOutOfBoundsException ex)
            {
                throw new SIPException(SIPMessage.class.getName()
                        + " Index was out of bounds while parsing "
                        + "SIP2 message. This may not be a SIP2 message, or it "
                        + "be malformed: '" + sipMessage + "'");
            }
            // put it in the hash
            this.fields.put(key, value);
        }
    }
    
    /**
     * 
     * @return String version of the initial SIP2 code.
     */
    public final String getCode()
    {
        return this.code;
    }
    
    /**
     * 
     * @return String of the bit field associated with the code field.
     */
    public final String getCodeMessage()
    {
        return this.codeBits;
    }

    /**
     *
     * @return List of names of fields in the message.
     */
    public final List<String> getFieldNames()
    {
        List<String> myListOfKeys = new ArrayList<>();
        Set<String> keySet = this.fields.keySet();
        for (String s : keySet)
        {
            myListOfKeys.add(s);
        }
        return myListOfKeys;
    }

    /**
     * 
     * @param which the code of the field you want.
     * @return the contents of that field
     */
    public final String getField(String which)
    {
        String returnValue = this.fields.get(which.toUpperCase()); // which could return null
        if (returnValue == null)
        {
            return Protocol.DEFAULT_FIELD_VALUE;
        }
        return returnValue;
    }
    
    /**
     * @param field name as String like 'PA'.
     * @return true if the field contains {@link Protocol.DEFAULT_FIELD_VALUE}
     * and false otherwise.
     */
    public boolean isEmpty(String field)
    {
        return getField(field).compareTo(Protocol.DEFAULT_FIELD_VALUE) == 0;
    }
    
   @Override
    public String toString()
    {
        return this.originalMessage;
    }
    
    /** 
     * returns the type of ILS from the sip2.properties file.
     * @return SIRSI_DYNIX by default if 'ils-type' element is not an entry in 
     * the properties file. Options are SIRSI_DYNIX, POLARIS or no entry. Don't use
     * an empty tag.
     */
    public SIPMessage.IlsType getILSType()
    {
        return this.ilsType;
    }
}
