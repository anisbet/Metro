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
package site.strathcona;

import api.SIPCustomerMessage;
import java.util.ArrayList;
import java.util.List;
import mecard.Protocol;
import mecard.customer.Customer;
import mecard.config.CustomerFieldTypes;
import mecard.exception.SIPException;
import site.MeCardPolicy;

/**
 * Implementation of MeCard's restriction policies as interpreted by the 
 * Edmonton Public Library. Every library translates their customer account information
 * into responses to the basic MeCard restriction policies:
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class STRPolicy extends MeCardPolicy
{
    private List<String> nonResidentBTypes;
    private List<String> reciprocalBTypes;
    private List<String> juvenile;
    private final boolean debug;
    private final static String LOST_CARD = "LOSTCARD";
    
    
    public STRPolicy(boolean debug)
    {
        this.debug = debug;
        this.nonResidentBTypes = new ArrayList<String>();
        this.nonResidentBTypes.add("nr");     // Non-resident
        this.nonResidentBTypes.add("nrf");    // Non-resident family
        this.nonResidentBTypes.add("oi");     // Outreach institution
        this.nonResidentBTypes.add("tal");    // TAL MEMBER
        this.nonResidentBTypes.add("tnr");    // visitor
        this.nonResidentBTypes.add("hsr");    // self reg
        
        this.reciprocalBTypes = new ArrayList<String>();
        this.reciprocalBTypes.add("rec");     // RECIPROCAL Edmonton 
        this.reciprocalBTypes.add("recd");    // RECIPROCAL Devon
        this.reciprocalBTypes.add("recsa");   // RECIPROCAL St Albert Lamont
        this.reciprocalBTypes.add("rect");    // RECIPROCAL Tofield Leduc
//        this.reciprocalBTypes.add("dept");  // Department head professional card.
        
        this.juvenile = new ArrayList<String>();
        this.juvenile.add("nrf");            // NON-RESIDENT Family
        
    }

    @Override
    public boolean isResident(Customer customer, String meta, StringBuilder responseError)
    {
        // 64YY        Y   00020130808    150700000000000000000500000000AOst|AA22222001047624|AETEST, 1|AQst|BZ0030|CA0100|CB0200|BLY|BHCAD|BV16.8|CC9.99|BD5 St. Anne St., St. Albert, AB, T8N 3Z9|BEktroppmann@sapl.ca|BF780-459-1537|DH1|DJTEST|PCmra|PE20130824    235900|PS20130824    235900|ZYmra|AF#You are barred from borrowing - Please refer to the circulation desk.|AY0AZ9A5C
        // we can use a SIPMessage object to parse the meta...
        SIPCustomerMessage sipData;
        try
        {
            sipData = new SIPCustomerMessage(meta);
            String bType = sipData.getCustomerProfile();
            for (String s: nonResidentBTypes)
            {
                if (bType.compareTo(s) == 0) // if we match on a non resident bType we aren't a resident.
                {
                    customer.set(CustomerFieldTypes.ISRESIDENT, Protocol.FALSE);
                    responseError.append(failResidencyTest);
                    return false;
                }
            }
        }
        catch (SIPException ex)
        {
            System.out.println(STRPolicy.class.getName() + " isResident test failed: " + ex.getMessage());
            responseError.append(failResidencyTest);
            return false;
        }
        
        customer.set(CustomerFieldTypes.ISRESIDENT, Protocol.TRUE);
        return true;
    }

    @Override
    public boolean isReciprocal(Customer customer, String meta, StringBuilder responseError)
    {
        SIPCustomerMessage sipData;
        try
        {
            sipData = new SIPCustomerMessage(meta);
            String bType = sipData.getCustomerProfile();
            for (String s: this.reciprocalBTypes)
            {
                if (bType.compareTo(s) == 0) // if we match on a non resident bType we aren't a resident.
                {
                    customer.set(CustomerFieldTypes.ISRECIPROCAL, Protocol.TRUE);
                    return true;
                }
            }
        }
        catch (SIPException ex)
        {
            System.out.println(STRPolicy.class.getName() + " isReciprocal test failed: " + ex.getMessage());
            responseError.append(failReciprocalTest);
            return false;
        }
        
        customer.set(CustomerFieldTypes.ISRECIPROCAL, Protocol.FALSE);
        responseError.append(failReciprocalTest);
        return false;
    }

    @Override
    public boolean isInGoodStanding(Customer customer, String meta, StringBuilder responseError)
    {
        // because EPL uses SIP to get customer information we can assume that
        // meta will contain BARRED if the customer is not in good standing.
        SIPCustomerMessage sipData;
        try
        {
            sipData = new SIPCustomerMessage(meta);
            String message = sipData.getMessage();
            if (message.contains("barred")) // if we match on a non resident bType we aren't a resident.
            {
                customer.set(CustomerFieldTypes.ISGOODSTANDING, Protocol.FALSE);
                responseError.append(failGoodstandingTest);
                return false;
            }
        }
        catch (SIPException ex)
        {
            System.out.println(STRPolicy.class.getName() + " isInGoodStanding test failed: " + ex.getMessage());
            responseError.append(failGoodstandingTest);
            return false;
        }

        customer.set(CustomerFieldTypes.ISGOODSTANDING, Protocol.TRUE);
        return true;
    }

    @Override
    public boolean isMinimumAge(Customer customer, String meta, StringBuilder responseError)
    {
        // run through all the juv profile types and if one matches then
        // no can do.
        SIPCustomerMessage sipData;
        try
        {
            sipData = new SIPCustomerMessage(meta);
            String bType = sipData.getCustomerProfile();
            for (String s: this.juvenile)
            {
                if (bType.compareTo(s) == 0) // if we match juv bType we are a minor.
                {
                    customer.set(CustomerFieldTypes.ISMINAGE, Protocol.FALSE);
                    responseError.append(failMinAgeTest);
                    return false;
                }
            }
        }
        catch (SIPException ex)
        {
            System.out.println(STRPolicy.class.getName() + " isMinimumAge test failed: " + ex.getMessage());
            responseError.append(failMinAgeTest);
            return false;
        }
        
        customer.set(CustomerFieldTypes.ISMINAGE, Protocol.TRUE);
        return true;
    }

    @Override
    public boolean isLostCard(Customer customer, String meta, StringBuilder s)
    {
        if (meta.contains(LOST_CARD))
        {
            customer.set(CustomerFieldTypes.ISLOSTCARD, Protocol.TRUE);
            if (debug) System.out.println("passed lost card test");
            return true;
        }
        customer.set(CustomerFieldTypes.ISLOSTCARD, Protocol.FALSE);
        s.append(failLostCardTest);
        return false;
    }

}
