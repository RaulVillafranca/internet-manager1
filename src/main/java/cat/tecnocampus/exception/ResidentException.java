package cat.tecnocampus.exception;

/**
 * Created by internet-manager on 15/08/2017.
 */
public class ResidentException extends Exception {
    public ResidentException() { super();}
    public ResidentException(String message) { super(message); }

    public ResidentException(String message, Throwable cause) { super(message, cause); }

    public ResidentException(Throwable cause) { super(cause); }
}
