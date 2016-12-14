package dao;

/**
 * Created by Nikolia on 12/14/2016.
 */
public class RegisterRequest {

    String request;

    public RegisterRequest()
    {}

    public RegisterRequest(String request)
    {
        this.request=request;
    }

    public String getRequest() {
        return request;
    }
}
