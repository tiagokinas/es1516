package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exception.MyDriveException;

import pt.tecnico.mydrive.domain.*;


public class LoginUserService extends MyDriveService{

	private long token;
	private String username;
	private String password;
	private MyToken mt;

	/**
	 * Cria uma sessao para um utilizador.
	 * @param username
	 * @param password
	 */
	public LoginUserService(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	protected void dispatch() throws MyDriveException {	
		MyDrive md = MyDrive.getInstance();
		Session session = md.getSession();
		session.removeInactives();

		//if(md.getUser(this.username).checkPasswordLenght(this.password)); //changes the password if it has less than 8 characters
		//preventing the user from logging in

		mt = session.createSession(this.username, this.password);
		String tokenString = Long.toString(mt.getToken());
		this.username = tokenString;
		//TU nem sequer mexes no token. a ultima linha que te deixei embaixo cria te um token para a sessao
		// para que servem estas duas? 
		//String tokenString = Long.toString(mt.getToken());
		//this.username = tokenString;


		//this.token = session.createToken();
	}

	public final long getToken() {
		return token;
	}

	//Feneja nao me venha chatear com isto que preciso dele
	public final MyToken getMyToken() {
		return mt;
	}

	//todo ver se isto pertence aqui ou nao
	public User getUserByToken(long token) {
		//todo implement and fix
		return this.getUserByToken(token);
	}
}