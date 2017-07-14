package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exception.MyDriveException;

import pt.tecnico.mydrive.exception.*;
import pt.tecnico.mydrive.domain.*;


public class DeleteUserService extends MyDriveService{

	private String username;
	private long token;

	/**
	 * Apaga um utilizador do MyDrive.
	 * @param userToken
	 * @param usernameToDelete
	 */
	public DeleteUserService(long userToken, String usernameToDelete) {
		token = userToken;
		username = usernameToDelete;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		
		MyDrive md = MyDrive.getInstance();
		Session session = md.getSession();
		User user = session.getUserFromToken(token);
		
		if(user == null || !session.inSession(user))
			throw new UserIsNotInSessionException(token);
		
		else if(!user.isRoot()) //Only root can delete users
				throw new UnauthorizedOperationException();
		
		User user_to_delete = md.getUser(username);
		
		if(user_to_delete == null)
			throw new UnauthorizedOperationException();
		
		else if(user_to_delete.isRoot())
				throw new UnauthorizedOperationException();	
		else 
			user_to_delete.delete();
			
	}
}