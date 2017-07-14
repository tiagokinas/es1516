package pt.tecnico.mydrive.exception;

public class PlainFileNameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingPlainfilename;

    public PlainFileNameAlreadyExistsException(String conflictingPlainfilename) {
        this.conflictingPlainfilename = conflictingPlainfilename;
    }

    public String getConflictingPlainfilename() {
        return conflictingPlainfilename;

    }

    @Override
    public String getMessage() {
        return "This Plain File name " + conflictingPlainfilename + " is already being used";
    }
}
