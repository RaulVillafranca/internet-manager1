package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.exception.CommunityException;
import cat.tecnocampus.services.CityService;
import cat.tecnocampus.services.CommunityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by internet-manager on 11/04/17.
 */
@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CommunityController {
    private CommunityService communityService;
    private CityService cityService;
    private Logger log = Logger.getLogger(CommunityController.class);

    /* La inyección de dependencias hacen que entre las capas no se acople el código, el FW ya se encarga de inyectar el
     * servicio que toca en cada caso, seguimos la D de los principios SOLID, donde inyectamos interfaces, no implementaciones.*/
    @Autowired
    public CommunityController(CommunityService communityService, CityService cityService) {
        this.communityService = communityService;
        this.cityService = cityService;
    }

    /* RequestMapping indica la uri(path) que invoca este método y de que [tipo] debe ser */
    @RequestMapping(value = "/communities", method = RequestMethod.GET)
    @Secured("ROLE_PRESIDENT")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("communities", communityService.listAllCommunity());
        modelAndView.setViewName("communities");
        log.info("Returning communities.");
        return modelAndView;
    }

    /* PathVariable indica que Spring va a coger del path un valor, en este caso el id */
    @RequestMapping("community/{id}")
    @Secured("ROLE_PRESIDENT")
    public String showProduct(@PathVariable Integer id, Model model) throws CommunityException {
        model.addAttribute("community", communityService.getCommunityById(id));
        log.info("Returning community: " + id);
        return "communityshow";
    }

    @RequestMapping(value = "community/new")
    @Secured("ROLE_ADMIN")
    public String newInvoice(Model model){
        model.addAttribute("community", new Community());
        model.addAttribute("cities", cityService.listAllCity());
        return "communityform";
    }

    @RequestMapping(value = "community/edit/{id}")
    @Secured("ROLE_PRESIDENT")
    public String edit(@PathVariable Integer id, Model model) throws CommunityException {
        model.addAttribute("community", communityService.getCommunityById(id));
        model.addAttribute("cities", cityService.listAllCity());
        return "communityform";
    }

    @RequestMapping(value = "community", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(Community community){
        communityService.save(community);
        return "redirect:/community/" + community.getId();
    }
}
