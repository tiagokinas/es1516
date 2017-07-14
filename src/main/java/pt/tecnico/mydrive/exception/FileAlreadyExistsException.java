package pt.tecnico.mydrive.exception;

public class FileAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private int conflictingId;

    public FileAlreadyExistsException(int conflictingId) {
        this.conflictingId= conflictingId;
    }

    public int getConflictingId() {
        return conflictingId;

    }

    @Override
    public String getMessage() {
        return "This File name " + conflictingId + " is already being used";
    }
}
