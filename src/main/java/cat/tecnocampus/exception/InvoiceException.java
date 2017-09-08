package cat.tecnocampus.exception;

/**
 * Created by internet-manager on 15/08/2017.
 */
public class InvoiceException extends Exception {
    public InvoiceException() { super();}
    public InvoiceException(String message) { super(message); }

    public InvoiceException(String message, Throwable cause) { super(message, cause); }

    public InvoiceException(Throwable cause) { super(cause); }
}
