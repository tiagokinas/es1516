package pt.tecnico.mydrive.domain;

import org.jdom2.Element;

import pt.tecnico.mydrive.exception.*;

import java.util.Random;
import java.util.TreeMap;

public class User extends User_Base {

	private long myToken;

	private TreeMap<String, String> fileAssociations;

	public User() {
		super();
	}

	public User(MyDrive md, String username, String name, String password) throws InvalidUsernameException {
		super();

		try {
			if (!checkPasswordLenght(password)) {
				throw new WrongPasswordLengthException();
			}

			if (username.length() < 3 || !isAlphaNumeric(username)) {
				throw new InvalidUsernameException(username);
			}
			this.fileAssociations = new TreeMap<String, String>();
			setUsername(username);
			setName(name);
			setPassword(password);
		}
		catch(WrongPasswordLengthException wple){
			wple.toString();
		}
	}

	public User(MyDrive md, Element xml) {
		super();
		// xmlImport(xml);
		setMydrive(md);
	}

	public TreeMap<String, String> getFileAssociations() {
		return fileAssociations;
	}

	public void setFileAssociations(TreeMap<String, String> fileAssociations) {
		this.fileAssociations = fileAssociations;
	}

	@Override
	public void setMydrive(MyDrive md) {
		if (md == null)
			super.setMydrive(null);
		else
			md.addUser(this);
	}

	public boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		if (s.matches(pattern)) {
			return true;
		}
		return false;
	}

	@Override
	public void addFile(File fileToBeAdded) throws FileNameAlreadyExistsException {
		String dot = ".";
		if (hasFile(fileToBeAdded.getName()))
			throw new FileNameAlreadyExistsException(fileToBeAdded.getName());

		super.addFile(fileToBeAdded);
		//put k,v into map -> k is file extension, v is filename
		this.getFileAssociations().put(dot+fileToBeAdded.getName().split("\\.")[1], fileToBeAdded.getName());
	}

	// ATENCAO aqui talvez seja por ID em vez de ser por nome
	public File getFileByName(String name) {
		for (File file : getFileSet())
			if (file.getName().equals(name))
				return file;
		return null;
	}

	public boolean hasFile(String fileName) {
		return getFileByName(fileName) != null;
	}
	
	public boolean checkPasswordLenght(String password){
		if(password.length() >= 8)
			return true;

		//this.setPassword(newRandomPassword());
		return false;

	}

	public static String newRandomPassword(){
		Random random = new Random();
		String string = Long.toString(Math.abs(random.nextLong()));
		return string.substring(0, 8);
	}

	// HANDLES DIRECTORY
	public Directory getDirectoryByPath(String path) {
		for (Directory directory : getDirectorySet()) {
			if (directory.getPath().equals(path)) {
				return directory;
			}
		}
		return null;
	}

	public boolean hasDirectory(String path) {
		return getDirectoryByPath(path) != null;
	}

	public void addDirectory(Directory directoryToBeAdded) throws DirectoryNameAlreadyExistsException {
		if (hasDirectory(directoryToBeAdded.getPath()))
			throw new DirectoryNameAlreadyExistsException(directoryToBeAdded.getPath());

		super.addDirectory(directoryToBeAdded);
	}

	public void removeFile(File f) {
		f.delete();
	}
	
	

	// HANDLES PLAINFILE
	public PlainFile getPlainFileByPath(String path) {
		for (PlainFile plainFile : getPlainfileSet()) {
			if (plainFile.getPath().equals(path)) {
				return plainFile;
			}
		}
		return null;
	}

	public boolean hasPlainFile(String path) {
		return getPlainFileByPath(path) != null;
	}

	// JOAO ARRANJA UMA EXCEPCAO MELHOR
	public void addPlainFile(PlainFile plainFileToBeAdded) throws FileNameAlreadyExistsException {
		if (hasPlainFile(plainFileToBeAdded.getPath()))
			throw new FileNameAlreadyExistsException(plainFileToBeAdded.getPath());

		super.addPlainfile(plainFileToBeAdded);
	}

	public void removePlainFile(String path) {
		PlainFile plainFileToRemove = getPlainFileByPath(path);
		if (plainFileToRemove == null) {
			throw new FileDoesNotExistException(path);
		}
		plainFileToRemove.delete();
	}

	// ATENCAO ver se e preciso eliminar os ficheiros antes de eliminar o User
	public void remove() {
		/*
		 * for (Permission permission : getPermissionSet()) {
		 * permission.remove(); }
		 */
		setMydrive(null);
		deleteDomainObject();
	}


	public void delete() {
		//apagar o que ta pra tras
		if(getMytoken() != null)
			getMytoken().delete();
		setMydrive(null);
		deleteDomainObject();
	}
	  

	 
	  public Boolean isInSession() { 
		  if (getSession() != null) { 
			  return true; 
		  }
		  else return false; 
	  }
	 

	public boolean isRoot() {
		return getUsername().equals("root");
	}

	//TODO isto retorna um long, mas faz mais sentido retornar o proprio token
	public long getMyToken() {
		return myToken;
	}

	public void setMyToken(long myToken) {
		this.myToken = myToken;
	}
	
}
