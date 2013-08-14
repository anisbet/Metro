/*
 * Metro allows customers from any affiliate library to join any other member library.
 *    Copyright (C) 2013  Andrew Nisbet
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
package epl;

import mecard.Request;
import json.RequestDeserializer;
import mecard.customer.Customer;
import org.junit.Test;
import static org.junit.Assert.*;
import mecard.site.MeCardPolicy;

/**
 * *** Warning *** These tests don't work unless you have an environment file
 * in the project's root directory.
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class EPLMeCardPolicyTest
{
    private String meta;
    private Customer c;
    public EPLMeCardPolicyTest()
    {
        this.meta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|PCEPL-THREE|PFM|DB$0.00|DM$0.00|AFUser BLOCKED|AY0AZACC6";
        String custReq =
                "{\"code\":\"GET_CUSTOMER\",\"authorityToken\":\"12345678\",\"userId\":\"21221012345678\",\"pin\":\"64058\",\"customer\":\"{\\\"ID\\\":\\\"21221012345678\\\",\\\"PIN\\\":\\\"64058\\\",\\\"NAME\\\":\\\"Billy, Balzac\\\",\\\"STREET\\\":\\\"12345 123 St.\\\",\\\"CITY\\\":\\\"Edmonton\\\",\\\"PROVINCE\\\":\\\"Alberta\\\",\\\"POSTALCODE\\\":\\\"H0H0H0\\\",\\\"GENDER\\\":\\\"M\\\",\\\"EMAIL\\\":\\\"ilsteam@epl.ca\\\",\\\"PHONE\\\":\\\"7804964058\\\",\\\"DOB\\\":\\\"19750822\\\",\\\"PRIVILEGE_EXPIRES\\\":\\\"20140602\\\",\\\"RESERVED\\\":\\\"X\\\",\\\"DEFAULT\\\":\\\"X\\\",\\\"ISVALID\\\":\\\"Y\\\",\\\"ISMINAGE\\\":\\\"Y\\\",\\\"ISRECIPROCAL\\\":\\\"N\\\",\\\"ISRESIDENT\\\":\\\"Y\\\",\\\"ISGOODSTANDING\\\":\\\"Y\\\",\\\"ISLOSTCARD\\\":\\\"N\\\",\\\"FIRSTNAME\\\":\\\"Balzac\\\",\\\"LASTNAME\\\":\\\"Billy\\\"}\"}";
        RequestDeserializer deserializer = new RequestDeserializer();
        Request request = deserializer.getDeserializedRequest(custReq);
        this.c = request.getCustomer();
    }

    /**
     * Test of isResident method, of class EPLPolicy.
     */
    @Test
    public void testIsResident()
    {
        System.out.println("==isResident==");
        MeCardPolicy p = MeCardPolicy.getInstanceOf(false);
        boolean result = p.isResident(c, meta);
        boolean expected= true;
        assertTrue(expected == result);
        String modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-VISITR|"
                + "PFM|DB$0.00|DM$0.00|AFUser BLOCKED|AY0AZACC6";
        
//        c = new Customer(custReq);
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isResident(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
    }

    /**
     * Test of isReciprocal method, of class EPLPolicy.
     */
    @Test
    public void testIsReciprocal()
    {
        System.out.println("==isReciprocal==");
        MeCardPolicy p = MeCardPolicy.getInstanceOf(true);
        boolean result = p.isReciprocal(c, meta);
//        System.out.println(meta);
        boolean expected= false;
        assertTrue(expected == result);
        String modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-RECIP|"
                + "PFM|DB$0.00|DM$0.00|AFUser BLOCKED|AY0AZACC6";
        
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isReciprocal(c, modeMeta);
        expected= true;
        assertTrue(expected == result);
    }

    /**
     * Test of isInGoodStanding method, of class EPLPolicy.
     */
    @Test
    public void testIsInGoodStanding()
    {
        System.out.println("==isInGoodStanding==");
        
        MeCardPolicy p = MeCardPolicy.getInstanceOf(false);
        boolean result = p.isInGoodStanding(c, meta);
        boolean expected= false;
        assertTrue(expected == result);
        String modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-VISITR|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isInGoodStanding(c, modeMeta);
        expected= true;
        assertTrue(expected == result);
    }

    /**
     * Test of isMinimumAge method, of class EPLPolicy.
     */
    @Test
    public void testIsMinimumAge()
    {
        System.out.println("==isMinimumAge==");
        
        MeCardPolicy p = MeCardPolicy.getInstanceOf(false);
        boolean result = p.isMinimumAge(c, meta);
        boolean expected= true;
        assertTrue(expected == result);
        
        String modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUV|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
        
        modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUV01|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
        
        modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUV05|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
        
        modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUV10|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
        
        modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUVNR|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
        
        modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUVGR|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
        
        modeMeta = "64YYYY      Y   00020130606    115820000000000000000100000000AO|"
                + "AA21221012345678|AEBilly, Balzac|AQEPLMNA|BZ0025|CA0041|"
                + "CB0040|BLY|CQY|BV 12.00|BD7 Sir Winston Churchill Square Edmonton, AB T5J 2V4|"
                + "BEilsteam@epl.ca|BHUSD|PA20140321    235900|PD20050303|"
                + "PCEPL-JUVIND|"
                + "PFM|DB$0.00|DM$0.00|AFOk|AY0AZACC6";
        
        p = MeCardPolicy.getInstanceOf(false);
        result = p.isMinimumAge(c, modeMeta);
        expected= false;
        assertTrue(expected == result);
    
    }
    
   
}