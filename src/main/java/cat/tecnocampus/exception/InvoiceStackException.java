package cat.tecnocampus.exception;

/**
 * Created by internet-manager on 19/07/2017.
 */
public class InvoiceStackException extends Exception {
    public InvoiceStackException() { super();}
    public InvoiceStackException(String message) { super(message); }

    public InvoiceStackException(String message, Throwable cause) { super(message, cause); }

    public InvoiceStackException(Throwable cause) { super(cause); }
}
