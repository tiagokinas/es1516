package pt.tecnico.mydrive.exception;

public class FileNameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingFilename;

    public FileNameAlreadyExistsException(String conflictingFilename) {
        this.conflictingFilename = conflictingFilename;
    }

    public String getConflictingFilename() {
        return conflictingFilename;

    }

    @Override
    public String getMessage() {
        return "This File name " + conflictingFilename + " is already being used";
    }
}
