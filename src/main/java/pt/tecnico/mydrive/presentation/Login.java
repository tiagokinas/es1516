package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.LoginUserService;

public class Login extends MdCommand {

	public Login(Shell sh) {
		super(sh, "login", "login user into MyDrive application");
	}

	/**
	 * Realiza o servico de login.
	 * 
	 * login username [password]
	 */
	public void execute(String[] args) {
		MdShell shell = new MdShell();

		long token = Long.parseLong(args[1]);

		if(args.length < 2)
			throw new RuntimeException("USAGE: " +name()+" <username> <password>");
		new LoginUserService(args[0], args[1]).execute();

		shell.setUserName(args[0]);
		shell.setUserToken(token);
	}
}
