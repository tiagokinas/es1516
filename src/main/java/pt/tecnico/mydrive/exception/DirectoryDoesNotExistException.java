package pt.tecnico.mydrive.exception;

public class DirectoryDoesNotExistException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String directoryName;

    public DirectoryDoesNotExistException(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    @Override
    public String getMessage() {
        return "Directory " + directoryName + " does not exist";
    }
}
