package dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.12.16.
 */
public class IdeaDAO {

    private String name = new String("ff");
    private List<IdeaDAO> l = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
