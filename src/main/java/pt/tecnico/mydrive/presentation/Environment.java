package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.MyToken;
import pt.tecnico.mydrive.domain.Session;
import pt.tecnico.mydrive.service.AddVariableService;

public class Environment extends MdCommand {

	//TODO
	public Environment(Shell sh) {
		super(sh, "enviromnent", "enviromnent comment");
	}

	/**
	 * Cria ou altera a variavel de ambiente.
	 * 
	 * env [name [value]]
	 */
	public void execute(String[] args) {
		long token = ((MdShell) shell()).getUserToken();
		
		MyDrive md = MyDrive.getInstance();
		Session s = md.getSession();
		
		MyToken tk = s.getTokenFromLong(token);

		if(args.length < 2)
			throw new RuntimeException("USAGE: "+name()+" <name> <value>");
		
		new AddVariableService(tk,args[0], args[1]).execute();



	}
}