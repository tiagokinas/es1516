package pt.tecnico.mydrive.exception;

public class WrongFileTypeToReadException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public WrongFileTypeToReadException() {
        super("Can only read files of type PlainFile");
    }
}