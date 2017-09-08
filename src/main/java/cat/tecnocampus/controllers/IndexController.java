package cat.tecnocampus.controllers;

import cat.tecnocampus.services.ContractService;
import cat.tecnocampus.services.InvoiceService;
import cat.tecnocampus.services.ProviderService;
import cat.tecnocampus.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by internet-manager on 29/3/17.
 */
@Controller
public class IndexController {
    private ProviderService providerService;
    private ContractService contractService;
    private ResidentService residentService;
    private InvoiceService invoiceService;

    @Autowired
    public IndexController(ProviderService providerService, ContractService contractService, ResidentService residentService, InvoiceService invoiceService) {
        this.providerService = providerService;
        this.contractService = contractService;
        this.residentService = residentService;
        this.invoiceService = invoiceService;
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("providersCount", providerService.getProvidersCount());
        modelAndView.addObject("contractsCount", contractService.getContractsCount());
        modelAndView.addObject("residentsCount", residentService.getResidentCount());
        modelAndView.addObject("pendingInvoicesCount", invoiceService.getPendingInvoices());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
