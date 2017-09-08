package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Provider;
import cat.tecnocampus.services.ProviderService;
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
public class ProviderController {
    private ProviderService providerService;
    private Logger log = Logger.getLogger(ProviderController.class);

    @Autowired
    public ProviderController(ProviderService ProviderService) {
        this.providerService = ProviderService;
    }

    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    @Secured("ROLE_PRESIDENT")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("providers", providerService.listAllProvider());
        modelAndView.setViewName("providers");
        log.info("Returning providers.");
        return modelAndView;
    }

    @RequestMapping("provider/{id}")
    @Secured("ROLE_PRESIDENT")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("provider", providerService.getProviderById(id));
        log.info("Returning Provider: " + id);
        return "providershow";
    }

    @RequestMapping(value = "provider/new")
    @Secured("ROLE_PRESIDENT")
    public String newInvoice(Model model){
        model.addAttribute("provider", new Provider());
        model.addAttribute("providerTypes", providerService.getProviderTypes());
        return "providerform";
    }

    @RequestMapping(value = "provider/edit/{id}")
    @Secured("ROLE_PRESIDENT")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("provider", providerService.getProviderById(id));
        model.addAttribute("providerTypes", providerService.getProviderTypes());
        return "providerform";
    }

    @RequestMapping(value = "provider", method = RequestMethod.POST)
    @Secured("ROLE_PRESIDENT")
    public String create(Provider Provider){
        providerService.save(Provider);
        return "redirect:/provider/" + Provider.getId();
    }
}
