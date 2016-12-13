package dao;

/**
 * Created by user on 13.12.2016.
 * Nikolia 13/12/2016: Without constructor and getter? Really?
 */
public class LoginResponseObj {
    int userId;

    public LoginResponseObj(int userId)
    {
        this.userId = userId;
    }

    public int getUserId()
    {
        return userId;
    }
}
