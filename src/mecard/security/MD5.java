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
package mecard.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author anisbet
 *
 */
public class MD5
{

    public static String getHash(String customerData)
    {
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e)
        {
            System.out.println("error: " + e.getMessage());
        }
        messageDigest.update(customerData.getBytes());
        byte[] data = messageDigest.digest();
        //convert the byte to hex format
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < data.length; i++)
        {
            String hex = Integer.toHexString(0xff & data[i]);
            // prepend a zero to pad to 32 characters.
            if (hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
