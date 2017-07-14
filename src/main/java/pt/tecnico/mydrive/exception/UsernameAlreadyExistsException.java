package pt.tecnico.mydrive.exception;

public class UsernameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingUsername;

    public UsernameAlreadyExistsException(String conflictingUsername) {
        this.conflictingUsername = conflictingUsername;
    }

    public String getConflictingUsername() {
        return conflictingUsername;

    }

    @Override
    public String getMessage() {
        return "This Username " + conflictingUsername + " is already being used";
    }
}
