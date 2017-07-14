package pt.tecnico.mydrive.exception;

public class EmptyUsernameException extends MyDriveException {
	
	private static final long serialVersionUID = 1L; 
	
	public EmptyUsernameException() {
		
		super("Username is empty");
	}

}

