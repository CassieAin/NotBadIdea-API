package controllerUnderService;

import dao.IdeaDAO;
import service.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikolia on 02.12.2016.
 */
public class IdeasRequestToDB {

    public ArrayList<IdeaDAO> getIdeasList() {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT 	 `ideas`.`ideaPK`," +
                            "            `ideas`.`title`," +
                            "            `ideas`.`description`," +
                            "            `ideas`.`likes`," +
                            "            `users`.`name`," +
                            "            `users`.`surname`," +
                            "            `ideas`.`time`," +
                            "            `ideas`.`photo`" +
                            "FROM 		`ideaservice`.`ideas`" +
                            "            JOIN `ideaservice`.`users` ON userFK=usPK" +
                            " ORDER BY  `ideas`.`time` DESC;";
            PreparedStatement ps = null;
            ps = dbConnection.getConnection().prepareStatement(query);
            ResultSet rs = null;
            rs = ps.executeQuery();
            ArrayList<IdeaDAO> ideasdaoList = null;
            ideasdaoList = new ArrayList<IdeaDAO>();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("ideaPK");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int likes = rs.getInt("likes");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String time = rs.getString("time");
                    String photo = rs.getString("photo");
                    ideasdaoList.add(new IdeaDAO(id, title, name + " " + surname, time, description, photo, likes));
                }
            }
            dbConnection.CloseConnection();
            return ideasdaoList;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }

    public IdeaDAO getIdeaByID(int id)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT `ideas`.`ideaPK`," +
                            "       `ideas`.`title`," +
                            "       `ideas`.`shortdescription`," +
                            "       `ideas`.`description`," +
                            "       `status`.`status`," +
                            "       `ideas`.`likes`," +
                            "       `users`.`name`," +
                            "       `users`.`surname`," +
                            "       `ideas`.`time`," +
                            "       `ideas`.`photo`" +
                            "FROM   `ideaservice`.`ideas`" +
                            "        JOIN `status` ON `statusFK`=`statusPK`" +
                            "        JOIN `users` ON `userFK`=`usPK`" +
                            "WHERE  `ideas`.`ideaPK`=?;";
            PreparedStatement ps = null;
            ps = dbConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = null;
            rs = ps.executeQuery();
            IdeaDAO ideaobj = null;
            if (rs != null) {
                while (rs.next()) {
                    int id_res = rs.getInt("ideaPK");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int likes = rs.getInt("likes");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String time = rs.getString("time");
                    String photo = rs.getString("photo");
                    ideaobj = new IdeaDAO(id_res, title, name + " " + surname, time, description, photo, likes);
                }
            }
            dbConnection.CloseConnection();
            return ideaobj;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }

    public boolean InsertData(String name, String surname, String title, String description, int likesAmount)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            //String selectstatusquery = "SELECT `statusPK` FROM `status` WHERE `status`= ?";
            String selectusersquery = "SELECT `usPK` FROM `users` WHERE `users`.`Name`= ? AND `users`.`Surname`= ?";
            String insertquery =  "INSERT INTO `ideaservice`.`ideas` (`title`, `description`, `likes`, `userFK`, `statusFK`)" +
                                  " VALUES (?, ?, ?, ?, 21);";


            // Get status
            /*PreparedStatement psstatus = null;
            psstatus = dbConnection.getConnection().prepareStatement(selectstatusquery);
            psstatus.setString(1, status);
            ResultSet rsstatus = null;
            rsstatus = psstatus.executeQuery();
            int statusquery=0;
            if(rsstatus.next())
                statusquery=rsstatus.getInt("statusPK");*/

            // Get userPK
            PreparedStatement psquery = null;
            psquery = dbConnection.getConnection().prepareStatement(selectusersquery);
            psquery.setString(1, name);
            psquery.setString(2, surname);
            ResultSet rsuser = null;
            rsuser = psquery.executeQuery();
            int usersquery=0;
            if (rsuser != null)
                if(rsuser.next())
                    usersquery=rsuser.getInt("usPK");

            // Insert query
            PreparedStatement psinsertquery = null;
            psinsertquery = dbConnection.getConnection().prepareStatement(insertquery);
            psinsertquery.setString(1, title);
            psinsertquery.setString(2, description);
            psinsertquery.setInt(3, likesAmount);
            psinsertquery.setInt(4, usersquery);
            psinsertquery.executeUpdate();
            psinsertquery.close();
            return true;
        }
        catch (SQLException e) {
            dbConnection.CloseConnection();
            return false;
        }
    }
}
