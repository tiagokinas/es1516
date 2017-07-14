package pt.tecnico.mydrive.exception;

public class InvalidFileTypeException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String type;

    public InvalidFileTypeException(String type) {
        this.type = type;
    }

    public String getType() {
        return type;

    }

    @Override
    public String getMessage() {
        return "This type " + type + " is invalid.";
    }
}
