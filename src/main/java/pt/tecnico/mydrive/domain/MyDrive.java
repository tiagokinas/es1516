package pt.tecnico.mydrive.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;

import pt.tecnico.mydrive.exception.*;

public class MyDrive extends MyDrive_Base {
	static final Logger log = LogManager.getRootLogger();

	public static MyDrive getInstance() {
		MyDrive md = FenixFramework.getDomainRoot().getMydrive();
		if (md != null)
			return md;

		log.trace("new MyDrive");
		return new MyDrive();
	}

	private MyDrive() {
		setRoot(FenixFramework.getDomainRoot());
		setNumberOfFiles(new Integer(0));
		setSession(new Session());
	}

	public int getFileCount() {
		return getNumberOfFiles();
	}

	public int incNumberOfFiles(int inc) {
		setNumberOfFiles(new Integer(getNumberOfFiles() + inc));
		return getFileCount();
	}

	public int incNumberOfFiles() {
		return incNumberOfFiles(1);
	}

	public void incNumberOfUsers() {
		int users = getNumberOfUsers();
		users++;
		setNumberOfUsers(users);
	}

	public User getUserByToken(long token) throws UserIsNotInSessionException {
		for (User user : getUserSet()) {
			if (user.isInSession() && user.getMytoken().getToken() == token) {
				return user;
			}
		}
		return null;
	}

	public User getUser(String str) {
		for(User exist : getUserSet()) {
			if(exist.getUsername().equals(str))
				return exist;
		}
		return null;
	}


	public boolean hasUser(String username) {
		return getUser(username) != null;
	}

	// @Override
	public void addUser(User userToBeAdded) throws UsernameAlreadyExistsException {

		if(userToBeAdded.getUsername().isEmpty())  
			throw new EmptyUsernameException(); 

		if (hasUser(userToBeAdded.getUsername()))
			throw new UsernameAlreadyExistsException(userToBeAdded.getUsername());

		/*
		 * //JOAO VE ESTA EXCEPCAO if (getUserByUsername(username) != null) {
		 * throw new DuplicateUsernameException(username); }
		 * 
		 * 
		 * //JOAO ADICIONA ESTA EXCEPCAO if (username.equals("root")) { throw
		 * new UnauthorizedOperationException(); }
		 */
		super.addUser(userToBeAdded);
	}

	public void removeUser(String username) {
		if (username.equals("root")) {
			throw new UnauthorizedOperationException();
		} else {
			User userToRemove = getUser(username);
			if (userToRemove == null) {
				throw new UserDoesNotExistException(username);
			}
			// Ainda nao e necessario para esta entrega
			/*
			 * for (Permission permission : userToRemove.getPermissionSet()) {
			 * if (permission.getAccess() == Access.OWNS) { File file =
			 * permission.getFile(); file.remove(); } }
			 */
			userToRemove.remove();
		}
	}

	public void cleanup() {
		for (User u : getUserSet())
			u.remove();
	}

	public pt.tecnico.mydrive.domain.File getFile(int id) {
		for(pt.tecnico.mydrive.domain.File file : getFileSet()) {
			if(file.getId()==id)
				return file;
		}
		return null;
	}

	public PlainFile getPlainFileByName(String name){
		for (File f : getFileSet()){
			if(f.getType().equals("Plain File"))
				if(f.getName().equals(name))
					return (PlainFile) f;
		}
		throw new FileDoesNotExistException(name);
	}


	public File getFileByPath(String path) {
		for(File file : getFileSet()){
			if(file.getPath().equals(path)){
				return file;
			}
		}
		return null;
	}



	//TODO SETPERMISSIONS !!!

	public void addPlainFile(PlainFile plainFile) {
		//TODO
	}

	public void removeDirectory(String pathToDelete) {
		//TODO
	}

	public void removePlainFile(String plainFileToDelete) {
		//TODO
	}
}
