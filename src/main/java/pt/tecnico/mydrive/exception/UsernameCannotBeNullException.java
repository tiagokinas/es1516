package pt.tecnico.mydrive.exception;

public class UsernameCannotBeNullException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public UsernameCannotBeNullException() {
        super("Error username cannot be Null");
    }
}
