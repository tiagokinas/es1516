package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.domain.MyToken;
import pt.tecnico.mydrive.service.ChangeDirectoryService;
import pt.tecnico.mydrive.service.LoginUserService;


public class ChangeWorkingDirectory extends MdCommand {

	public ChangeWorkingDirectory(Shell sh) {
		super(sh, "changedir", "change working directory");
	}

	/**
	 * Altera a directoria corrente  e imprime o caminho
	 * para a nova directoria corrente _path_
	 * 
	 * cwd [path]
	 */
	public void execute(String[] args) {
		String path = args[0];

		MyToken token = ((MdShell) shell()).getMyToken();     

		if(args.length < 1)
			throw new RuntimeException("USAGE: "+name()+" <path> ");
		new ChangeDirectoryService(token, args[0]).execute();;

		System.out.println("A directoria actual passa a ser: " + path);
	}
}
