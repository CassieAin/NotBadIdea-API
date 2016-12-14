package dao;

/**
 * Created by user on 13.12.2016.
 * Nikolia 13/12/2016: HERE THO? COME ON. Initialization constructor and getters r necessary
 */
public class RegisterResponse {
    String login;
    String password;
    String email;
    String name;
    String surname;

    public RegisterResponse()
    {

    }
    public RegisterResponse(String login, String password, String email, String name, String surname)
    {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
