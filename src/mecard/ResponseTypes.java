/*
 * Copyright (C) 2013 metro
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mecard;

/**
 *
 * @author metro
 */
public enum ResponseTypes
{
    ERROR("RA9"), // Command was received but failed to execute
    // either it was malformed, empty (null), or not supported.
    INIT("RA0"),
    OK("RA1"),
    BUSY("RA2"),
    UNAVAILABLE("RA3"),
    SUCCESS("RA4"),
    FAIL("RA5"),
    UNAUTHORIZED("RA6");
    
    private String type;
    
    private ResponseTypes(String s)
    {
        this.type = s;
    }
    
    @Override
    public String toString()
    {
        return this.type;
    }
}
