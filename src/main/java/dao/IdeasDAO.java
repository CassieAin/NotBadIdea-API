package dao;

/**
 * Created by user on 02.12.2016.
 */
import java.util.ArrayList;
import java.util.List;

public class IdeasDAO {
    int id;
    String title;
    String author;
    String datetime;
    String description;
    String photo;
    int likesAmount;

    public IdeasDAO(){

    }

    public IdeasDAO(int id, String title, String author, String datetime, String description, String photo, int likesAmount)
    {
        this.id=id;
        this.title = title;
        this.author=author;
        this.datetime = datetime;
        this.description = description;
        this.photo = photo;
        this.likesAmount = likesAmount;
    }

    public int getId()
    {
        return id;
    }


    public String getTitle()
    {
        return title;
    }


    public String getAuthor()
    {
        return author;
    }


    public String getDateTime()
    {
        return datetime;
    }



    public String getDescription()
    {
        return description;
    }



    public int getlikesAmount()
    {
        return likesAmount;
    }



    public String getPhoto()
    {
        return photo;
    }

}
