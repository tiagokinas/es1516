package pt.tecnico.mydrive.presentation;
import pt.tecnico.mydrive.dto.FileDTO;
import pt.tecnico.mydrive.service.ListDirectoryService;

public class List extends MdCommand {

	public List(Shell sh) {
		super(sh, "ls", "list files in the directory");
	}
	public void execute(String[] args) {
		ListDirectoryService lds;
		long token = ((MdShell) shell()).getUserToken();
		if (args.length == 0) {
			lds = new ListDirectoryService(token);
		} else {
			lds = new ListDirectoryService(token, args[0]);
		}

		lds.execute();
		for (FileDTO dto: lds.getResult())
			System.out.println(dto.getType() + " " + dto.getUsername() 
			+ " " + dto.getId() + " " + dto.getDate().toString() + " " + dto.getName());
	}
}
