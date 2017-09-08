package cat.tecnocampus.respositories;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;
import cat.tecnocampus.domain.Resident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by internet-manager on 19/07/2017.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
    @Query("SELECT i FROM Invoice i WHERE i.contract = ?1 AND i.resident = ?2")
    List<Invoice> findInvoicesByContractAndResident(Contract contract, Resident resident);

    List<Invoice> findByResident(Resident currentResident);

    Long countByResidentAndPayed(Resident resident, Boolean payed);

    Long countByPayed(boolean payed);
}
