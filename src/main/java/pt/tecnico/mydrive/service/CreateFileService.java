package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;


public class CreateFileService extends MyDriveService{
	
	//private int id;
	private String name;
	private String type;
	private String content;

	/**
	 * Cria um ficheiro na directoria corrente.
	 * Analisa os argumentos recebidos e executa 
	 * o tipo de ficheiro especifico.
	 * @param userToken
	 * @param name
	 * @param type
	 * @param content
	 */
	public CreateFileService(long userToken, String name, String type, String content) {
		this.userToken = userToken;
		this.name = name;
		this.type = type;
		this.content = content;
	}

	
	@Override
	protected void dispatch() throws FileAlreadyExistsException, DirectoryDoesNotExistException, UserIsNotInSessionException, InvalidFileTypeException {
		try {
			MyDrive md = MyDrive.getInstance();
			User user = md.getSession().getUserFromToken(userToken);
			if(user == null || !md.getSession().inSession(user)) {
				//user a null por isso no test lança null pointer exception
				throw new UserIsNotInSessionException(user.getMyToken());
			}

			String tempPath = md.getDirectory().getPath();
			if(tempPath != null)
				throw new DirectoryDoesNotExistException(name);

			File temp = md.getPlainFileByName(name);
			if(temp != null)
				throw new FileNameAlreadyExistsException(name);


			switch (type) {
				case "Plain File":
					PlainFile plainFile = new PlainFile(md, name, content);
					md.addFile(plainFile);
					break;
				case "Link":
					Link link = new Link(content);
					md.addFile(link);
					break;
				case "Directory":
					Directory dir = new Directory(content, name);
					md.addFile(dir);
					break;
				case "Application":
					Application app = new Application(content);
					md.addFile(app);
					break;
				default:  throw new InvalidFileTypeException(type);
			}

		}
		catch (FileAlreadyExistsException faee) {
			faee.getMessage();
		}
		catch (DirectoryDoesNotExistException ddne) {
			ddne.getMessage();
		}
		catch (InvalidFileTypeException ifte) {
			ifte.getMessage();
		}
		catch (UserIsNotInSessionException uinise) {
			uinise.getMessage();
		}
	}
	
	
}