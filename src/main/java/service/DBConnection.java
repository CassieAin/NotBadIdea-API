package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by user on 01.12.16.
 */
public class DBConnection {
    /* Connection and it's data*/
    Connection connection = null;
    String ConnectionData = "dfdfgdgfb";

    public DBConnection()
    {
        ConnectionData= "jdbc:mysql://"+ DBCredentials.ip+
                ":"+DBCredentials.port+
                "/"+DBCredentials.database+"?" +
                "user="+DBCredentials.username+
                "&password="+DBCredentials.password+"";
    }


    // connects to DB and returns state of connection
    public boolean OpenConnection()
    {
        try {
            connection = DriverManager.getConnection(ConnectionData);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    // Close connection and return state of action
    public boolean CloseConnection()
    {
        try {
            connection.close();
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
    }
}
