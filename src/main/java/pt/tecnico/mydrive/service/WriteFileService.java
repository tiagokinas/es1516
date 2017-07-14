package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class WriteFileService extends MyDriveService{

	private String type;
	private String content;
	private String path;

	/**
	 * Altera o conteudo de um ficheiro de texto, 
	 * nao de uma directoria.
	 * @param userToken
	 * @param name
	 * @param content
	 */
	public WriteFileService(long userToken, String path, String content) {
		this.userToken = userToken;
		this.path = path;
		this.content = content;
	}

	protected void dispatch() throws FileAlreadyExistsException, DirectoryDoesNotExistException, UserIsNotInSessionException, InvalidFileTypeException {
		try {
			MyDrive md = MyDrive.getInstance();
			User user = md.getSession().getUserFromToken(userToken);
			
			String filenameWithPath = path;
			String[] tokens = filenameWithPath.split("[\\\\|/]"); 
			String name = tokens[tokens.length - 1];
			
			if(user == null || !md.getSession().inSession(user)) {
				throw new UserIsNotInSessionException(userToken);
			}

			String tempPath = md.getDirectory().getPath();
			if(tempPath != null)
				throw new DirectoryDoesNotExistException(name);

			File file = user.getFileByName(name);


			//hmmm not the best approach...
			PlainFile pf = md.getPlainFileByName(name);
			String filetype = pf.getType();

			switch (filetype) {
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

			user.removeFile(file);


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