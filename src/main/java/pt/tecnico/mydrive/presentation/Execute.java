package pt.tecnico.mydrive.presentation;

import java.util.Arrays;
import java.util.List;

import pt.tecnico.mydrive.service.ExecuteFile;

public class Execute extends MdCommand {

	//TODO
	public Execute(Shell sh) {
		super(sh, "execute", "execute comment");
	}

	/**
	 * Executa o ficheiro indicado no _path_, 
	 * com os argumentos _args_
	 * 
	 * do path [args]
	 */
	public void execute(String[] args) {

		//TODO verificar se isto esta certo
		long token = ((MdShell) shell()).getUserToken();

		String [] items = args[1].split(",");
		List<String> container = Arrays.asList(items);

		if(args.length < 2)
			throw new RuntimeException("USAGE: "+name()+" <path> <args>");
		new ExecuteFile(token,args[0], container).execute();

	}
}
