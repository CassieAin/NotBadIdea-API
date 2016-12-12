package dao;

import java.util.ArrayList;

/**
 * Created by Nikolia on 12/12/2016.
 * Who deer to delete it??????!?!?
 */
public class IdeasListDAO {

    ArrayList<IdeaDAO> data = new ArrayList<IdeaDAO>();

    public IdeasListDAO()
    {

    }

    public IdeasListDAO(ArrayList<IdeaDAO> data)
    {
        this.data=data;
    }

    public ArrayList<IdeaDAO> getData()
    {
        return data;
    }

    public void setData(ArrayList<IdeaDAO> data)
    {
        this.data=data;
    }
}
