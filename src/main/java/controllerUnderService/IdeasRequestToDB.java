package controllerUnderService;

import dao.IdeasDAO;
import service.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikolia on 02.12.2016.
 */
public class IdeasRequestToDB {

    public ArrayList<IdeasDAO> getIdeasList() {
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
            ArrayList<IdeasDAO> ideasdaoList = null;
            ideasdaoList = new ArrayList<IdeasDAO>();
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
                    ideasdaoList.add(new IdeasDAO(id, title, name + " " + surname, time, description, photo, likes));
                }
            }
            dbConnection.CloseConnection();
            return ideasdaoList;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }
}
