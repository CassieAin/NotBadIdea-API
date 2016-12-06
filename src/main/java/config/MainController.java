package config;

import dao.IdeasDAO;
import service.DBConnection;
import controllerUnderService.IdeasRequestToDB;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class MainController {

    @RequestMapping("/test")
    public DBConnection testRequest() {

        return new DBConnection();
    }

    @RequestMapping(value = "/getIdeasListParam", method = RequestMethod.POST)
    public IdeasDAO getIdeasList(RequestObject req, @RequestParam("id") int id, @RequestParam("title")  String title,
                                 @RequestParam("author")  String author, @RequestParam("datetime")  String datetime,
                                 @RequestParam("description")  String description, @RequestParam("photo")  String photo,
                                 @RequestParam("likesAmount")  int likesAmount){
        req.getVar();
        return new IdeasDAO(id,title, author, datetime, description, photo,likesAmount);
    }

    @RequestMapping(value = "/getIdeasList", method = RequestMethod.POST)
    public IdeasDAO getIdeasList(RequestObject req){
        req.getVar();
        return new IdeasDAO();
    }
}
