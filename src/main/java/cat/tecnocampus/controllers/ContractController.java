package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.exception.ContractException;
import cat.tecnocampus.services.CommunityService;
import cat.tecnocampus.services.ContractService;
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

import java.security.Provider;

/**
 * Created by internet-manager on 11/04/17.
 */
@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ContractController {
    private ContractService contractService;
    private CommunityService communityService;
    private ProviderService providerService;

    private Logger log = Logger.getLogger(ContractController.class);

    /* La inyección de dependencias hacen que entre las capas no se acople el código, el FW ya se encarga de inyectar el
     * servicio que toca en cada caso, seguimos la D de los principios SOLID, donde inyectamos interfaces, no implementaciones.*/
    @Autowired
    public ContractController(ContractService contractService, CommunityService communityService, ProviderService providerService) {
        this.contractService = contractService;
        this.communityService = communityService;
        this.providerService = providerService;
    }

    /* RequestMapping indica la uri(path) que invoca este método y de que [tipo] debe ser */
    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("contracts", contractService.listAllContracts());
        modelAndView.setViewName("contracts");
        log.info("Returning contracts.");
        return modelAndView;
    }

    /* PathVariable indica que Spring va a coger del path un valor, en este caso el id */
    @RequestMapping("contract/{id}")
    public String showProduct(@PathVariable Integer id, Model model) throws ContractException {
        model.addAttribute("contract", contractService.getContractById(id));
        log.info("Returning contract: " + id);
        return "contractshow";
    }

    @RequestMapping(value = "contract/new")
    @Secured("ROLE_PRESIDENT")
    public String newInvoice(Model model){
        model.addAttribute("contract",  new Contract());
        model.addAttribute("communities", communityService.listAllCommunity());
        model.addAttribute("providers", providerService.listAllProvider());

        return "contractform";
    }

    @RequestMapping(value = "contract/edit/{id}")
    @Secured("ROLE_PRESIDENT")
    public String edit(@PathVariable Integer id, Model model) throws ContractException {
        model.addAttribute("contract", contractService.getContractById(id));
        model.addAttribute("communities", communityService.listAllCommunity());
        model.addAttribute("providers", providerService.listAllProvider());

        return "contractform";
    }

    @RequestMapping(value = "contract", method = RequestMethod.POST )
    @Secured("ROLE_PRESIDENT")
    public String create(Contract contract){
        contractService.save(contract);

        return "redirect:/contract/" + contract.getId();
    }
}
