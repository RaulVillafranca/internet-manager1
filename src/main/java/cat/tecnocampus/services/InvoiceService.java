package cat.tecnocampus.services;

import cat.tecnocampus.domain.Contract;
import cat.tecnocampus.domain.Invoice;
import cat.tecnocampus.exception.InvoiceException;
import cat.tecnocampus.exception.InvoiceStackException;

/**
 * Created by internet-manager on 19/07/2017.
 */
public interface InvoiceService {

    Iterable<Invoice> listAllInvoices();

    Invoice save(Invoice invoice);

    Invoice getInvoiceById(Integer id) throws InvoiceException;

    String createInvoiceStack(Contract contract) throws InvoiceStackException;

    void invoicePay(Invoice invoice);

    Long getPendingInvoices();
}
