package pt.tecnico.mydrive.exception;

public class InvalidUsernameException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingInvalidusername;

    public InvalidUsernameException(String conflictingInvalidusername) {
        this.conflictingInvalidusername = conflictingInvalidusername;
    }

    public String getConflictingInvalidUsername() {
        return conflictingInvalidusername;

    }

    @Override
    public String getMessage() {
        return "This Username " + conflictingInvalidusername + " is invalid.";
    }
}
