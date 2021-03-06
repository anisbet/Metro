/*
 * Metro allows customers from any affiliate library to join any other member library.
 *    Copyright (C) 2019  Edmonton Public Library
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mecard.exception.ConfigurationException;

/**
 * Builds the SQL connection based builder parameters.
 * @author Andrew Nisbet andrew.nisbet@epl.ca
 */
public class SQLConnector
{
    private final String host;
    private final int    port;
    private final String sqlUser;
    private final String sqlPassword;
    private final String sqlDatabase;
    private final String driverName;
    private final String urlProtocol;
    private Connection connection = null;
    private final String url;
    private DriverType driverType;

    public static class Builder
    {
        private final String host;
        private String database;
        private String user;
        private String password;
        private String driver;

        /**
         * Creates builder with minimum constructor arguments.
         *
         * @param host host name of the database.
         * @param driver enum of the driver type. Only MySQL and SQL Server are supported.
         * @param databaseName name of the database to connect with.
         */
        public Builder(String host, String driver, String databaseName)
        {
            this.host     = host;
            this.driver   = driver;
            this.database = databaseName;
        }

        /**
         * Allows the connection to be constructed with a SQL user.
         *
         * @param user
         * @return Connection Builder
         */
        public Builder user(String user)
        {
            if (user != null && user.length() > 0)
            {
                this.user = user;
            }
            return this;
        }

        /**
         * Allows for the use of a SQL user password if one is set.
         *
         * @param password
         * @return Builder
         */
        public Builder password(String password)
        {
            if (password != null && password.length() > 0)
            {
                this.password = password;
            }
            return this;
        }

        /**
         * Builds the connection.
         *
         * @return Connection builder.
         */
        public SQLConnector build()
        {
            return new SQLConnector(this);
        }
    }
    
    public enum DriverType
    {
        MY_SQL,
        SQL_SERVER;
    }

    /**
     * Creates a SIPConnector but not without the help of a builder object.
     * Usage: SIPConnector c = SIPConnector.Builder(host, port).build();
     *
     * @param builder
     */
    private SQLConnector(Builder builder)
    {
        host        = builder.host;
        sqlDatabase = builder.database;
        sqlUser     = builder.user;
        sqlPassword = builder.password;
        switch (builder.driver)
        {
            case "MY_SQL":     // mysql-connector-java-5.1.31-bin.jar
                this.driverName  = "com.mysql.jdbc.Driver";
                this.urlProtocol = "jdbc:mysql://";
                this.port        = 3306;
                this.url         = getMySQL_URL();
                this.driverType  = DriverType.MY_SQL;
                break;
            case "SQL_SERVER": // sqljdbc42.jar for Java 8 compatability, tested.
                this.driverName  = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                this.urlProtocol = "jdbc:sqlserver://";
                this.port        = 1433;
                this.url         = getSQLServer_URL();
                this.driverType  = DriverType.SQL_SERVER;
                break;
            default:
                throw new UnsupportedOperationException(
                        SQLConnector.class.getName() 
                        + " unregistered JDBC driver type. (1024)"); // conspicuous easy to find message.
        }
        System.out.println("==connection layer");
        System.out.println("driverName: "+driverName);
        System.out.println("port: "+port);
        System.out.println("url: "+url);
        System.out.println("driverType: "+driverType);
        System.out.println("==DB layer");
        System.out.println("host: "+host);
        System.out.println("sqlDatabase: "+sqlDatabase);
        System.out.println("sqlUser: "+sqlUser);
        System.out.print("sqlPassword: ");
        for (String c: sqlPassword.split(""))
        {
            System.out.print("*");
//            System.out.print(c);
        }
        System.out.println();

        try
        {
            Class.forName(this.driverName);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("JDBC Driver not found: " +
                    this.driverName + "::" + ex.getMessage());
            throw new ConfigurationException("sql driver error");
        }
        try
        {
            // build the connection for MySQL
            this.connection = DriverManager.getConnection(
                    this.url, this.sqlUser, this.sqlPassword);
        }
        catch (SQLException ex)
        {
            System.err.println("=======> SQL Connection failed: " + ex.getMessage());
            throw new ConfigurationException("sql driver error");
        }
        
        if (connection == null)
        {
            System.out.println("JDBC Failed to make connection.");
            throw new ConfigurationException("sql driver error");
        }
        
        System.out.println("SQL connection succeeded");       
    }
    
    private String getMySQL_URL()
    {
        StringBuilder connectionURL = new StringBuilder();
        connectionURL.append(this.urlProtocol);
        connectionURL.append(this.host);
        connectionURL.append(":");
        connectionURL.append(this.port);
        connectionURL.append("/");
        connectionURL.append(this.sqlDatabase);
        return connectionURL.toString();
    }
    
    private String getSQLServer_URL()
    {
        StringBuilder connectionURL = new StringBuilder();
        connectionURL.append(this.urlProtocol);
        connectionURL.append(this.host);
        connectionURL.append(":");
        connectionURL.append(String.valueOf(this.port));
        connectionURL.append(";");
        connectionURL.append("databaseName=");
        connectionURL.append(this.sqlDatabase);
        connectionURL.append(";");
        // Not required for the current driver version.
//        connectionURL.append("integratedSecurity=true;");
//        connectionURL.append(this.sqlDatabase);
//        connectionURL.append(";");
        System.out.println(">>>>"+connectionURL.toString()+"<<<<");
        return connectionURL.toString();
    }
    
    public DriverType getDriverType()
    {
        return this.driverType;
    }
    
    /**
     * Returns Connection to create SQL statements.
     * @return 
     */
    public Connection getConnection()
    {
        return this.connection;
    }
    
    /**
     * Closes the JDBC connection.
     */
    public void close()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            } catch (SQLException ex)
            {
                System.out.println("**error failed to close connection.");
                throw new ConfigurationException("sql driver error");
            }
        }
    }
    
    @Override
    public String toString()
    {
        return this.url;
    }
}
