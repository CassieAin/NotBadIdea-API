package dao;

/**
 * Created by Nikolia on 12/13/2016.
 * Come on! We need separately class
 */
public class LoginRequest {
    public String login;
    public String password;

    public LoginRequest(String login, String password)
    {
        this.login=login;
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }
}
