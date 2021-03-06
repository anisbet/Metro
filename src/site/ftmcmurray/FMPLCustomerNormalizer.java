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
package site.ftmcmurray;

import mecard.Response;
import mecard.customer.Customer;
import site.HorizonNormalizer;

/**
 * Normalizes the customer's data before loading into the local library's ILS.
 * The local library may require certain modifications to a customer account
 * such as minimum PIN width, or application of a computed bStat value.
 * @author Andrew Nisbet andrew.nisbet@epl.ca andrew@dev-ils.com
 */
public final class FMPLCustomerNormalizer extends HorizonNormalizer
{    
    public FMPLCustomerNormalizer(boolean debug)
    {
        super(debug);
    }

    @Override
    public void normalizeOnCreate(Customer customer, Response response) {
        // Nothing to do.
    }

    @Override
    public void normalizeOnUpdate(Customer customer, Response response) {
        // Nothing to do.
    }
}
