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
package api;

import com.google.gson.Gson;
import mecard.ResponseTypes;
import mecard.customer.Customer;

/**
 * Simple object to order responses.
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class Response //extends ProtocolPayload
{
    protected ResponseTypes code;
    protected String responseMessage;
    protected Customer customer;
    
    public Response()
    {
        code = ResponseTypes.INIT;
    }
    
    public Response(ResponseTypes rt)
    {
        code = rt;
    }
    
    public void setResponse(String s)
    {
        this.responseMessage = s;
    }
    
    public void setCustomer(Customer c)
    {
        this.customer = c;
    }

    public void setCode(ResponseTypes code) 
    {
        this.code = code;
    }
    
    @Override
    public String toString()
    {
        Gson gson = new Gson();
        return gson.toJson(this, Response.class);
    }

    public ResponseTypes getCode()
    {
        return this.code;
    }
}
