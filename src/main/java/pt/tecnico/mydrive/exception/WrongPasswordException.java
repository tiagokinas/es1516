package pt.tecnico.mydrive.exception;

public class WrongPasswordException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public WrongPasswordException() {
        super("Password wrong please try again");
    }
}