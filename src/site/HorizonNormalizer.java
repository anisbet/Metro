/*
 * Metro allows customers from any affiliate library to join any other member library.
 *    Copyright (C) 2021  Edmonton Public Library
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
package site;

import java.util.Date;
import java.util.HashMap;
import mecard.Response;
import mecard.ResponseTypes;
import mecard.config.BImportTableTypes;
import mecard.config.CustomerFieldTypes;
import mecard.customer.horizon.BImportTable;
import mecard.customer.Customer;
import mecard.customer.FormattedCustomer;
import mecard.customer.FormattedTable;
import mecard.util.Text;

/**
 * Horizon common normalization algorithms. This includes things like making
 * sure users' pins are 4 digits.
 * @author Andrew Nisbet andrew.nisbet@epl.ca andrew@dev-ils.com
 */
public abstract class HorizonNormalizer extends CustomerLoadNormalizer
{
    public final static int MAXIMUM_PIN_WIDTH = 4;
    public final static int SENIOR = 65;
    
    protected HorizonNormalizer(boolean debug)
    {
        super(debug);
    }
    
    @Override
    public ResponseTypes normalize(Customer customer, StringBuilder responseStringBuilder)
    {
        ResponseTypes rType = ResponseTypes.SUCCESS;
        return rType;
    }
    
    /**
     * This called during the createCustomer directly and indirectly by the 
     * updateCustomer command.
     * @param customer
     * @param formattedCustomer
     * @param response 
     */
    @Override
    public void finalize(Customer customer, FormattedCustomer formattedCustomer, Response response)
    {  
        /*** Be sure to call super.finalize() in inherited classes. **/
        ResponseTypes rType = ResponseTypes.SUCCESS;
        String pin = customer.get(CustomerFieldTypes.PIN);
        if (Text.isUpToMaxDigits(pin, MAXIMUM_PIN_WIDTH) == false)
        {
            // Get the hash of the current password instead of random digits.
            String newPin = Text.getNew4DigitPin(pin);
            response.setResponse(newPin);
            customer.set(CustomerFieldTypes.PIN, newPin);
            System.out.println(new Date() 
                + " Customer's PIN was not 4 digits as required by Horizon. "
                + " HN.finalize() set to: '" 
                + newPin + "'.");
            rType = ResponseTypes.PIN_CHANGE_REQUIRED;
        }
        response.setCode(rType);
    }
    
    /**
     * For Horizon libraries, if the customer's password is not a 4-digit PIN
     * a new one is generated for them. The generated PIN is a 4-digit hash of 
     * their password.
     * @param customer Customer object.
     * @param response - Response object to be returned once the 
     * request is fulfilled.
     */
    @Override
    public void normalizeOnCreate(Customer customer, Response response)
    {
        ResponseTypes rType = ResponseTypes.SUCCESS;
        String pin = customer.get(CustomerFieldTypes.PIN);
        if (Text.isUpToMaxDigits(pin, MAXIMUM_PIN_WIDTH) == false)
        {
            // Get the hash of the current password instead of random digits.
            String newPin = Text.getNew4DigitPin(pin);
            response.setResponse(newPin);
            customer.set(CustomerFieldTypes.PIN, newPin);
            System.out.println(new Date() 
                    + " Customer's PIN was not 4 digits as required by Horizon. Set to: '" 
                    + newPin + "'.");
            rType = ResponseTypes.PIN_CHANGE_REQUIRED;
        }
        response.setCode(rType);
    }
    
    /**
     * Manages the user's PIN at the time of update. If the customer has a real
     * PIN of 4-digits, fine. If not the password is hashed and added to the 
     * account for update. If the customer is using the same password as before
     * MeCard will create the same hash. If not the new hash will update the 
     * account, and the customer will be emailed their new PIN.
     * @param customer
     * @param response 
     */
    @Override
    public void normalizeOnUpdate(Customer customer, Response response)
    {
        ResponseTypes rType = ResponseTypes.SUCCESS;
        String pin = customer.get(CustomerFieldTypes.PIN);
        if (Text.isUpToMaxDigits(pin, MAXIMUM_PIN_WIDTH) == false)
        {
            // Get the hash of the current password instead of random digits.
            String newPin = Text.getNew4DigitPin(pin);
            response.setResponse(newPin);
            customer.set(CustomerFieldTypes.PIN, newPin);
            System.out.println(new Date() 
                    + " Customer's update data included a PIN that was not"
                    + " 4 digits as required by Horizon. Set to: '" 
                    + newPin + "'.");
            rType = ResponseTypes.PIN_CHANGE_REQUIRED;
        }
        response.setCode(rType);
    }
    
    /**
     * Creates and inserts a new table entry into the bimport file.
     * @param formattedCustomer
     * @param value 
     */
    protected void addBStatTable(FormattedCustomer formattedCustomer, String value)
    {
        FormattedTable table = BImportTable.getInstanceOf(
                BImportTableTypes.BORROWER_BSTAT, new HashMap<String, String>());
        table.setValue("bstat", value);
        formattedCustomer.insertTable(table, 99); // insert at the end.
    }
}
