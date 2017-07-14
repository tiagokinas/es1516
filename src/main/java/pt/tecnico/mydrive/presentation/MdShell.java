package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.domain.MyToken;

public class MdShell extends Shell {

	private long userToken;
	private String userName;
	private MyToken token; 
	
	
	public static void main(String[] args) throws Exception {
		MdShell sh = new MdShell();
		sh.execute();
	}

	public MdShell() { // add commands here
		super("MyDrive");
		new Login(this);
		new ChangeWorkingDirectory(this);
		new List(this);
		new Execute(this);
		new Write(this);
		new Environment(this);
		new Key(this);
	}
	
	public long getUserToken() {
		return userToken;
	}
	
	public MyToken getMyToken() {
		return token; 
	}

	public void setUserToken(long userToken) {
		this.userToken = userToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
