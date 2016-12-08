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

    @RequestMapping(value = "/getIdeasList", method = RequestMethod.POST)
    public ArrayList<IdeasDAO> getIdeasList(IdeasDAO obj){
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        return objIdeas.getIdeasList();
    }

}
