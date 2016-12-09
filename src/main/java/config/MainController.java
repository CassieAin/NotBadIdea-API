package config;

import dao.IdeaDAO;
import controllerUnderService.IdeasRequestToDB;
import dao.IdeasListDAO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/getIdeasList", method = RequestMethod.GET)
    public IdeasListDAO getIdeasList(){
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        IdeasListDAO obj = new IdeasListDAO();
        obj.setData(objIdeas.getIdeasList());
        return obj;
    }

    @RequestMapping(value = "/getIdea", method = RequestMethod.GET)
    public IdeaDAO getIdea(@RequestParam ("ideaId") int id) {
        IdeasRequestToDB objIdeas = new IdeasRequestToDB();
        return objIdeas.getIdeaByID(id);
    }

    @RequestMapping(value = "/postIdea", method = RequestMethod.POST)
    public void postIdea(@RequestBody IdeaDAO idea) {
        String author [] = idea.getAuthor().split(" ");
        new IdeasRequestToDB().InsertData(author[0], author[1], idea.getTitle(),idea.getDescription(), idea.getlikesAmount());
        }

    }

