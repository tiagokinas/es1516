package pt.tecnico.mydrive.exception;

public class PathTooLongException extends MyDriveException {

	private static final long serialVersionUID = 1L; 
	
	public PathTooLongException() {
		super("Path too long");
	}

}
