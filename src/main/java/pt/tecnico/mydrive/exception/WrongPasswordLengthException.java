package pt.tecnico.mydrive.exception;

public class WrongPasswordLengthException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public WrongPasswordLengthException() {
        super("Please use 8 or more characters when defining a password.");
    }
}