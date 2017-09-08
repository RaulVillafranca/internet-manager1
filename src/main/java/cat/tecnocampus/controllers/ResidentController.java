package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Community;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.exception.CommunityException;
import cat.tecnocampus.exception.ResidentException;
import cat.tecnocampus.services.CommunityService;
import cat.tecnocampus.services.ResidentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by internet-manager on 11/04/17.
 */
@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResidentController {
    private ResidentService residentService;
    private CommunityService communityService;

    private Logger log = Logger.getLogger(ResidentController.class);

    /* La inyección de dependencias hacen que entre las capas no se acople el código, el FW ya se encarga de inyectar el
     * servicio que toca en cada caso, seguimos la D de los principios SOLID, donde inyectamos interfaces, no implementaciones.*/
    @Autowired
    public ResidentController(ResidentService residentService, CommunityService communityService) {
        this.residentService = residentService;
        this.communityService = communityService;
    }

    /* RequestMapping indica la uri(path) que invoca este método y de que [tipo] debe ser */
    @RequestMapping(value = "/residents", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("residents", residentService.listAllResident());
        modelAndView.setViewName("residents");
        log.info("Returning residents.");
        return modelAndView;
    }

    /* PathVariable indica que Spring va a coger del path un valor, en este caso el id */
    @RequestMapping("resident/{id}")
    public String showProduct(@PathVariable Integer id, Model model) throws CommunityException, ResidentException {
        model.addAttribute("resident", residentService.getResidentById(id));
        model.addAttribute("community", communityService.getCommunityById(residentService.getResidentById(id).getCommunity().getId()));
        log.info("Returning resident: " + id);
        return "residentshow";
    }

    @RequestMapping(value = "resident/new")
    @Secured("ROLE_PRESIDENT")
    public String newInvoice(Model model){
        model.addAttribute("resident",  new Resident());
        model.addAttribute("communities", communityService.listAllCommunity());

        return "residentform";
    }

    @RequestMapping(value = "resident/edit/{id}")
    @Secured("ROLE_PRESIDENT")
    public String edit(@PathVariable Integer id, Model model) throws ResidentException {
        model.addAttribute("resident", residentService.getResidentById(id));
        model.addAttribute("communities", communityService.listAllCommunity());

        return "residentform";
    }

    @RequestMapping(value = "resident", method = RequestMethod.POST )
    @Secured("ROLE_PRESIDENT")
    public String create(Resident resident){
        residentService.save(resident);

        return "redirect:/resident/" + resident.getId();
    }

    @RequestMapping(value = "residents/count/community/{id}")
    @ResponseBody
    public String countByCommunity(@PathVariable Integer id) throws CommunityException {
        return residentService.getResidentCountByCommunity(id).toString();
    }
}
