package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.exception.InvoiceException;
import cat.tecnocampus.exception.InvoiceStackException;
import cat.tecnocampus.respositories.InvoiceRepository;
import cat.tecnocampus.respositories.ResidentRepository;
import cat.tecnocampus.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by internet-manager on 19/07/2017.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
    private InvoiceRepository invoiceRepository;
    private ResidentRepository residentRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ResidentRepository residentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public Iterable<Invoice> listAllInvoices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return invoiceRepository.findAll();
            }
        }

        Resident currentResident = residentRepository.findByEmail(currentUser);

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("PRESIDENT")){
                List<Invoice> invoices = new ArrayList<>();
                for (Resident resident : currentResident.getCommunity().getResidentList()) {
                    invoices.addAll(invoiceRepository.findByResident(resident));
                }
                return invoices;
            }
        }

        return invoiceRepository.findByResident(currentResident);
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Integer id) throws InvoiceException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Resident currentResident = residentRepository.findByEmail(currentUser);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Invoice invoice = invoiceRepository.findOne(id);
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return invoice;
            }
        }

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("PRESIDENT")){
                if (currentResident.getCommunity().equals(invoice.getResident().getCommunity())) return invoice;
                else throw new InvoiceException("Not allowed, can not access to this invoice.");
            }
        }

        if (currentResident.getCommunity().equals(invoice.getResident().getCommunity())) return invoice;
        else throw new InvoiceException("Not allowed, can not access to this invoice.");
    }

    @Override
    public String createInvoiceStack(Contract contract) throws InvoiceStackException {
        Boolean warnings = true;
        if (contract.getActive()== false){throw new InvoiceStackException("No invoices to generate. This contract is not active");}
        List<Invoice> invoiceList = new ArrayList<>();
        for (Resident resident : contract.getCommunity().getResidentList()) {
            if(!isInvoiceCreated(contract, resident)&& resident.getActive()==true){
                Date date = new Date(Calendar.getInstance().getTime().getTime());
                Invoice newInvoice = new Invoice(date, contract, resident, contract.getMonthlyPrice()/contract.getCommunity().getResidentList().size(), 21.0);
                invoiceList.add(newInvoice);
                warnings = false;
            }
        }

        if(warnings){
            throw new InvoiceStackException("No invoices to generate. This monthly invoices have been already generated.");
        }else {
            invoiceRepository.save(invoiceList);
            return "New invoices have been created successfully.";
        }
    }

    @Override
    public void invoicePay(Invoice invoice) {
        invoice.setPayed(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public Long getPendingInvoices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return invoiceRepository.countByPayed(false);
            }
        }

        Resident currentResident = residentRepository.findByEmail(currentUser);

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("PRESIDENT")){
                Long totalInvoicesPending = 0l;
                for (Resident resident : currentResident.getCommunity().getResidentList()) {
                    totalInvoicesPending =  totalInvoicesPending + invoiceRepository.countByResidentAndPayed(resident, false);
                }
                return totalInvoicesPending;
            }
        }

        return invoiceRepository.countByResidentAndPayed(currentResident, false);
    }

    private Boolean isInvoiceCreated(Contract contract, Resident resident){
        List<Invoice> invoicesByContractAndResident = invoiceRepository.findInvoicesByContractAndResident(contract, resident);

        Date actualDate = new Date(Calendar.getInstance().getTime().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(actualDate);
        int actualMonth = cal.get(Calendar.MONTH);
        int actualYear = cal.get(Calendar.YEAR);

        for (Invoice invoice : invoicesByContractAndResident) {
            cal.setTime(invoice.getDate());
            int invoiceMonth = cal.get(Calendar.MONTH);
            int invoiceYear = cal.get(Calendar.YEAR);

            if((actualMonth == invoiceMonth) && (actualYear == invoiceYear)){
                return true;
            }
        }

        return false;
    }
}
