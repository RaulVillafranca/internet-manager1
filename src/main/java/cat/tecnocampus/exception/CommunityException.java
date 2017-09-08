package cat.tecnocampus.exception;

/**
 * Created by internet-manager on 15/08/2017.
 */
public class CommunityException extends Exception {
    public CommunityException() { super();}
    public CommunityException(String message) { super(message); }

    public CommunityException(String message, Throwable cause) { super(message, cause); }

    public CommunityException(Throwable cause) { super(cause); }
}
