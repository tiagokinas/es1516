package pt.tecnico.mydrive.exception;

public class ApplicationNameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingApplicationname;

    public ApplicationNameAlreadyExistsException(String conflictingApplicationname) {
        this.conflictingApplicationname = conflictingApplicationname;
    }

    public String getConflictingApplicationname() {
        return conflictingApplicationname;

    }

    @Override
    public String getMessage() {
        return "This Application name " + conflictingApplicationname + " is already being used";
    }
}
