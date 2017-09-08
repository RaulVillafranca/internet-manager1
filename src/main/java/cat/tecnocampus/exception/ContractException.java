package cat.tecnocampus.exception;

/**
 * Created by internet-manager on 15/08/2017.
 */
public class ContractException extends Exception {
    public ContractException() { super();}
    public ContractException(String message) { super(message); }

    public ContractException(String message, Throwable cause) { super(message, cause); }

    public ContractException(Throwable cause) { super(cause); }
}
