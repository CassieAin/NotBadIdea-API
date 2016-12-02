package config;

/**
 * Created by user on 30.11.16.
 */
import dao.IdeaDAO;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DBConnection;

@RestController
@RequestMapping("/")
public class MainController {

    @RequestMapping("/test")
    public DBConnection testRequest() {

        return new DBConnection();
    }

    @RequestMapping(value="/getIdea", method= RequestMethod.POST)
    public IdeaDAO getIdea(RequestObject req) {
        req.getVar();
        return new IdeaDAO();
    }


}
