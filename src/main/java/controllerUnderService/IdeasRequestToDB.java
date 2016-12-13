package controllerUnderService;

import dao.IdeaDAO;
import dao.LoginRequest;
import dao.LoginResponseObj;
import dao.RegisterRequest;
import service.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikolia on 02.12.2016.
 * getIdeasListUsingID - getting ideas list, if login
 * getIdeasList - get ideas list, if guest
 * getCategoryId - get address of some category
 * getCategoryName - get category by address
 * getIdeaByID - get concrete idea, for now without comments
 * getIdeaByID4Auth - get concrete idea, if login, for now without comments
 * getIsLikedByID - check, if user likes idea
 * SelectUserIDByLogin - get id of user by login
 * InsertData - inserts new posts
 * ReturnIDByLogin - login function
 * Registration - do registration
 * TODO: add comments selecting
 *
 */
public class IdeasRequestToDB {

    public ArrayList<IdeaDAO> getIdeasListUsingID(int userId)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT 	`ideas`.`ideaPK`, " +
                            "           `ideas`.`title`, " +
                            "           `ideas`.`description`, " +
                            "           `ideas`.`likes`, " +
                            "           `users`.`name`, " +
                            "           `users`.`surname`, " +
                            "           `ideas`.`userFK`, " +
                            "           `ideas`.`time`, " +
                            "           `ideas`.`photo`, " +
                            "           `ideas`.`CategoryFK` " +
                            "FROM 		`ideaservice`.`ideas` " +
                            "            JOIN `ideaservice`.`users` ON userFK=usPK " +
                            "ORDER BY   `ideas`.`time` ASC limit 9;";
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
                    int categoryFK = rs.getInt("CategoryFK");
                    String isLiked = String.valueOf(getIsLikedByID(userId,rs.getInt("ideaPK")));
                    ideasdaoList.add(new IdeaDAO(id, title, name + " " + surname, time, description, photo, likes, getCategoryName(categoryFK), isLiked));
                }
            }
            dbConnection.CloseConnection();
            return ideasdaoList;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }

    public ArrayList<IdeaDAO> getIdeasList()
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT 	`ideas`.`ideaPK`, " +
                    "           `ideas`.`title`, " +
                    "           `ideas`.`description`, " +
                    "           `ideas`.`likes`, " +
                    "           `users`.`name`, " +
                    "           `users`.`surname`, " +
                    "           `ideas`.`time`, " +
                    "           `ideas`.`photo`, " +
                    "           `ideas`.`CategoryFK` " +
                    "FROM 		`ideaservice`.`ideas` " +
                    "            JOIN `ideaservice`.`users` ON userFK=usPK " +
                    "ORDER BY   `ideas`.`time` ASC limit 9;";
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
                    int categoryFK = rs.getInt("CategoryFK");
                    ideasdaoList.add(new IdeaDAO(id, title, name + " " + surname, time, description, photo, likes, getCategoryName(categoryFK), "false"));
                }
            }
            dbConnection.CloseConnection();
            return ideasdaoList;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }

    public int getCategoryID(String category)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT `category`.`CategoryPK` " +
                            "FROM `ideaservice`.`category` " +
                            "WHERE `category`.`Category` = ?;";
            PreparedStatement ps = null;
            ps = dbConnection.getConnection().prepareStatement(query);
            ps.setString(1, category);
            ResultSet rs = null;
            rs = ps.executeQuery();
            int categoryName=0;
            if (rs != null) {
                while (rs.next()) {
                    categoryName = rs.getInt("CategoryPK");
                }
            }
            dbConnection.CloseConnection();
            return categoryName;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return 0;
        }
    }


    public String getCategoryName(int CategoryID)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT `category`.`Category` " +
                    "FROM `ideaservice`.`category` " +
                    "WHERE `category`.`CategoryPK` = ?;";
            PreparedStatement ps = null;
            ps = dbConnection.getConnection().prepareStatement(query);
            ps.setInt(1, CategoryID);
            ResultSet rs = null;
            rs = ps.executeQuery();
            String categoryName="";
            if (rs != null) {
                while (rs.next()) {
                    categoryName = rs.getString("Category");
                }
            }
            dbConnection.CloseConnection();
            return categoryName;
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
            String query =  "SELECT `ideas`.`ideaPK`, " +
                            "       `ideas`.`title`, " +
                            "       `ideas`.`description`, " +
                            "       `ideas`.`likes`, " +
                            "       `users`.`name`, " +
                            "       `users`.`surname`, " +
                            "       `ideas`.`userFK`, " +
                            "       `ideas`.`time`, " +
                            "       `ideas`.`photo`, " +
                            "       `ideas`.`CategoryFK`" +
                            "FROM   `ideaservice`.`ideas` " +
                            "        JOIN `users` ON `userFK`=`usPK` " +
                            "WHERE  `ideas`.`ideaPK`=?; ";
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
                    int categoryFK = rs.getInt("CategoryFK");
                    String isLiked = String.valueOf(getIsLikedByID(rs.getInt("userFK"),rs.getInt("ideaPK")));
                    ideaobj = new IdeaDAO(id_res, title, name + " " + surname, time, description, photo, likes, getCategoryName(categoryFK), "false");
                }
            }
            dbConnection.CloseConnection();
            return ideaobj;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }

    public IdeaDAO getIdeaByID4Auth(int Ideaid, int UserId)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT `ideas`.`ideaPK`, " +
                    "       `ideas`.`title`, " +
                    "       `ideas`.`description`, " +
                    "       `ideas`.`likes`, " +
                    "       `users`.`name`, " +
                    "       `users`.`surname`, " +
                    "       `ideas`.`userFK`, " +
                    "       `ideas`.`time`, " +
                    "       `ideas`.`photo`, " +
                    "       `ideas`.`CategoryFK`" +
                    "FROM   `ideaservice`.`ideas` " +
                    "        JOIN `users` ON `userFK`=`usPK` " +
                    "WHERE  `ideas`.`ideaPK`=?; ";
            PreparedStatement ps = null;
            ps = dbConnection.getConnection().prepareStatement(query);
            ps.setInt(1, Ideaid);
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
                    int categoryFK = rs.getInt("CategoryFK");
                    String isLiked = String.valueOf(getIsLikedByID(UserId,rs.getInt("ideaPK")));
                    ideaobj = new IdeaDAO(id_res, title, name + " " + surname, time, description, photo, likes, getCategoryName(categoryFK), isLiked);
                }
            }
            dbConnection.CloseConnection();
            return ideaobj;
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return null;
        }
    }

    public boolean getIsLikedByID(int userId, int ideaId)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try {
            String query =  "SELECT `alreadyliked`.`LikePK`" +
                            "FROM   `ideaservice`.`alreadyliked`" +
                            "WHERE  `ideaservice`.`alreadyliked`.`UserFK` = ? " +
                            "       AND `ideaservice`.`alreadyliked`.`IdeaFK` = ?;";
            PreparedStatement ps = null;
            ps = dbConnection.getConnection().prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, ideaId);
            ResultSet rs = null;
            rs = ps.executeQuery();
            int isLiked = -1;
            if (rs != null) {
                isLiked = rs.getInt("LikePK");
            }
            dbConnection.CloseConnection();
            if(isLiked==-1) {
                return false;
            }
            else {
                return true;
            }
        } catch (SQLException e) {
            dbConnection.CloseConnection();
            return false;
        }
    }

    public int SelectUserIDByLogin(String login)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try
        {
            String selectusersquery =   "SELECT `usPK` " +
                                        "FROM `users` " +
                                        "WHERE  `users`.`login`=?";
            PreparedStatement psquery = null;
            psquery = dbConnection.getConnection().prepareStatement(selectusersquery);
            psquery.setString(1, login);
            ResultSet rsuser = null;
            rsuser = psquery.executeQuery();
            int usersquery=0;
            if (rsuser != null)
                if(rsuser.next())
                    usersquery=rsuser.getInt("usPK");
            return usersquery;
        }
        catch (SQLException e) {
            dbConnection.CloseConnection();
            return 0;
        }
    }
    /* PROBLEM HERE */
    public boolean InsertData(IdeaDAO idea)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try
        {
            String insertquery =    "INSERT INTO `ideaservice`.`ideas`" +
                                    " (`title`,`description`,`likes`,`userFK`,`time`,`photo`, `CategoryFK`)" +
                                    "VALUES" +
                                    " (?, ?, ?, ?, ?, ?, ?);";
            // Insert query
            PreparedStatement psinsertquery = null;
            psinsertquery = dbConnection.getConnection().prepareStatement(insertquery);
            psinsertquery.setString(1, idea.getTitle());
            psinsertquery.setString(2, idea.getDescription());
            psinsertquery.setInt(3, idea.getlikesAmount());
            psinsertquery.setInt(4, SelectUserIDByLogin(idea.getAuthor()));
            psinsertquery.setString(5, idea.getDateTime());
            psinsertquery.setString(6, idea.getPhoto());
            psinsertquery.setInt(7, getCategoryID(idea.getCategory()));
            psinsertquery.executeUpdate();
            psinsertquery.close();
            return true;
        }
        catch (SQLException e) {
            dbConnection.CloseConnection();
            return false;
        }
    }

    public LoginResponseObj ReturnIDByLogin(LoginRequest obj)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try
        {
            String selectusersquery =   "SELECT     `users`.`usPK`, " +
                                        "FROM       `ideaservice`.`users` " +
                                        "WHERE      `users`.`login` = '"+obj.getLogin()+"' " +
                                        "            AND `users`.`password` = '"+obj.getPassword()+"';";
            PreparedStatement psquery = null;
            psquery = dbConnection.getConnection().prepareStatement(selectusersquery);
            psquery.setString(1, obj.getLogin());
            psquery.setString(2, obj.getPassword());
            ResultSet rsuser = null;
            rsuser = psquery.executeQuery();
            LoginResponseObj loginresponseobj;
            loginresponseobj =  new LoginResponseObj(-1);
            if (rsuser != null)
                if(rsuser.next())
                    loginresponseobj = new LoginResponseObj(rsuser.getInt("usPK"));
            return loginresponseobj;
        }
        catch (SQLException e) {
            dbConnection.CloseConnection();
            return new LoginResponseObj(-1);
        }
    }

    public boolean Registration(RegisterRequest obj)
    {
        DBConnection dbConnection;
        dbConnection = new DBConnection();
        dbConnection.OpenConnection();
        try
        {
            String insertquery =    "INSERT INTO `ideaservice`.`users`" +
                                    "(`login`, `email`, `password`, `name`, `surname`)" +
                                    "VALUES" +
                                    "(?, ?, ?, ?, ?);";
            // Insert query
            PreparedStatement psinsertquery = null;
            psinsertquery = dbConnection.getConnection().prepareStatement(insertquery);
            psinsertquery.setString(1, obj.getLogin());
            psinsertquery.setString(2, obj.getEmail());
            psinsertquery.setString(3, obj.getPassword());
            psinsertquery.setString(4, obj.getName());
            psinsertquery.setString(5, obj.getSurname());
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
