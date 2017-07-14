package pt.tecnico.mydrive.exception;

public class UnauthorizedAccessException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedAccessException() {
        super("Unauthorized access to a file");
    }
}
