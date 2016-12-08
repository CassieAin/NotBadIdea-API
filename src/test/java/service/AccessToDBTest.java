package service;

import controllerUnderService.IdeasRequestToDB;
import dao.IdeasDAO;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolia on 29.11.2016.
 * Perform one-by-one to check connection and rights
 * first checks connection
 * second checks inserts rights
 * third fails anyway, returns result and check rights of select (there will be difference)
 * fourth checks update rights
 * fifth checks delete rights
 */
public class AccessToDBTest {

    @Test
    public void ConnectionTest()
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        boolean OpenConnectionValue = dbConnection.OpenConnection();
        assertEquals(true,OpenConnectionValue);
        boolean CloseConnectionValue = dbConnection.CloseConnection();
        assertEquals(true,CloseConnectionValue);
    }

    @Test
    public void InsertTest()
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        boolean OpenConnectionValue = dbConnection.OpenConnection();
        assertEquals(true,OpenConnectionValue);
        try {
            Statement st = dbConnection.getConnection().createStatement();
            st.execute( " INSERT INTO `ideaservice`.`status` (`status`)" +
                    " VALUES                             ('Not Ready');");
        }
        catch (SQLException e) {
            assertEquals("Fails if here, either pass", e.getMessage());
        }
        dbConnection.CloseConnection();
    }

    @Test
    // Must fails anyway
    public void SelectTest()
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query = "SELECT     `status`.`statusPK`," +
                    "           `status`.`status`" +
                    "FROM       `ideaservice`.`status`";
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    assertEquals("Must fails anyway", rs.getInt(1) + " " + rs.getString(2));
                }
            }
        }
        catch (SQLException e) {
            assertEquals("If fails here, there is rights trouble", e.getMessage());
        }
        dbConnection.CloseConnection();
    }

    @Test
    public void UpdateTest()
    {

        DBConnection dbConnection;
        dbConnection = new DBConnection();
        boolean OpenConnectionValue = dbConnection.OpenConnection();
        assertEquals(true,OpenConnectionValue);
        try {
            Statement st = dbConnection.getConnection().createStatement();
            st.execute( "UPDATE `ideaservice`.`status`" +
                    "SET    `status` = 'Ready'" +
                    "WHERE  `statusPK` = 'Not Ready';");
        }
        catch (SQLException e) {
            assertEquals("Fails if here, either pass", e.getMessage());
        }
        dbConnection.CloseConnection();
    }

    @Test
    public void DeleteTest()
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        boolean OpenConnectionValue = dbConnection.OpenConnection();
        assertEquals(true,OpenConnectionValue);
        try {
            Statement st = dbConnection.getConnection().createStatement();
            st.execute( " DELETE FROM   `ideaservice`.`status`" +
                    " WHERE          `status`='Ready';");
        }
        catch (SQLException e) {
            assertEquals("Fails if here, either pass", e.getMessage());
        }
        boolean CloseConnectionValue = dbConnection.CloseConnection();
        assertEquals(true,CloseConnectionValue);
    }

    @Test
    public void TestSelection()
    {
        ArrayList<IdeasDAO> obj = new IdeasRequestToDB().getIdeasList();
        assertEquals(obj.get(0).getId(), 12);
    }

}

