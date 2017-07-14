package pt.tecnico.mydrive.exception;

public class DirectoryNameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingDirectoryname;

    public DirectoryNameAlreadyExistsException(String conflictingDirectoryname) {
        this.conflictingDirectoryname = conflictingDirectoryname;
    }

    public String getConflictingDirectoryname() {
        return conflictingDirectoryname;

    }

    @Override
    public String getMessage() {
        return "This Directory name " + conflictingDirectoryname + " is already being used";
    }
}
