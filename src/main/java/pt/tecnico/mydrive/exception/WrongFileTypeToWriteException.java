package pt.tecnico.mydrive.exception;

public class WrongFileTypeToWriteException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public WrongFileTypeToWriteException() {
        super("Can only write to files of type PlainFile");
    }
}