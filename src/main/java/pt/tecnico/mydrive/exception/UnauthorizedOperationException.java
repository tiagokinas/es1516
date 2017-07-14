package pt.tecnico.mydrive.exception;

public class UnauthorizedOperationException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedOperationException() {
        super("Unauthorized operation");
    }
}
