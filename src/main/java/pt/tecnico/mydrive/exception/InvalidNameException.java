package pt.tecnico.mydrive.exception;

public class InvalidNameException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingName;

    public InvalidNameException(String conflictingName) {
        this.conflictingName = conflictingName;
    }

    public String getConflictingName() {
        return conflictingName;

    }

    @Override
    public String getMessage() {
        return "This name " + conflictingName + " is too long";
    }
}
