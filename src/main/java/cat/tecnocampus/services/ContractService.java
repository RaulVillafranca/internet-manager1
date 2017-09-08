package cat.tecnocampus.services;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.exception.ContractException;

/**
 * Created by internet-manager on 10/07/2017.
 */
public interface ContractService {
    Iterable<Contract> listAllContracts();

    Contract save(Contract contract);

    Contract getContractById(Integer id) throws ContractException;

    Long getContractsCount();
}
