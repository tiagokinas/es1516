package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exception.*;
import pt.tecnico.mydrive.domain.*;


public class CreateUserService extends MyDriveService{

	private long _userToken;	
	private String _newUser;
	private String _name;
	private String _password;

	/**
	 * Cria e adiciona um utilizador ao MyDrive.
	 * @param userToken
	 * @param newUsername
	 * @param name
	 * @param password
	 */
	public CreateUserService(long userToken, String newUsername, String name, String password) {
		_userToken = userToken;
		_newUser = newUsername;
		_name = name;
		_password = password;
	}

	//VASCONCELOS ADICIONA ESSAS EXCEPCOES E APAGA ESTE COMENTARIO
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive mydrive = MyDrive.getInstance();
		User user = mydrive.getSession().getUserFromToken(_userToken);
		if(user == null || !user.getMytoken().getSession().inSession(user)) {
			//throw new UserNotInSessionException();	
		}
		if(!user.isRoot()) {
			//throw new UnathorizedOperationException();
		}
		mydrive.addUser(new User(mydrive, _newUser, _name, _password));
	}
}