package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.City;
import cat.tecnocampus.services.CityService;
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
public class CityController {
    private CityService cityService;
    private Logger log = Logger.getLogger(CityController.class);

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    @Secured("ROLE_PRESIDENT")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("cities", cityService.listAllCity());
        modelAndView.setViewName("cities");
        log.info("Returning cities.");
        return modelAndView;
    }

    @RequestMapping("city/{id}")
    @Secured("ROLE_PRESIDENT")
    public String showCity(@PathVariable Integer id, Model model) {
        model.addAttribute("city", cityService.getCityById(id));
        log.info("Returning city: " + id);
        return "cityshow";
    }

    @RequestMapping(value = "city/new")
    @Secured("ROLE_PRESIDENT")
    public String newInvoice(Model model){
        model.addAttribute("city", new City());
        return "cityform";
    }

    @RequestMapping(value = "city/edit/{id}")
    @Secured("ROLE_PRESIDENT")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("city", cityService.getCityById(id));
        model.addAttribute("cities", cityService.listAllCity());
        return "cityform";
    }

    @RequestMapping(value = "city", method = RequestMethod.POST)
    @Secured("ROLE_PRESIDENT")
    public String create(City city){
        cityService.save(city);
        return "redirect:/city/" + city.getId();
    }
}
