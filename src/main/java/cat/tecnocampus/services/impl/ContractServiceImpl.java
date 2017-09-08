package cat.tecnocampus.services.impl;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Resident;
import cat.tecnocampus.exception.ContractException;
import cat.tecnocampus.respositories.ContractRepository;
import cat.tecnocampus.respositories.ResidentRepository;
import cat.tecnocampus.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by internet-manager on 10/07/2017.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private ResidentRepository residentRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, ResidentRepository residentRepository) {
        this.contractRepository = contractRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public Iterable<Contract> listAllContracts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return contractRepository.findAll();
            }
        }

        Resident currentResident = residentRepository.findByEmail(currentUser);
        return contractRepository.findByCommunity(currentResident.getCommunity());
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Integer id) throws ContractException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Resident currentResident = residentRepository.findByEmail(currentUser);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Contract contract = contractRepository.findOne(id);
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return contract;
            }
        }

        if (currentResident.getCommunity().equals(contract.getCommunity())) return contract;
        else throw new ContractException("Not allowed, can not access to this contract.");
    }

    @Override
    public Long getContractsCount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("ADMIN")){
                return contractRepository.count();
            }
        }

        Resident currentResident = residentRepository.findByEmail(currentUser);
        return contractRepository.countByCommunity(currentResident.getCommunity());
    }
}
