package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exception.*;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.mydrive.domain.*;


public class ExecuteFile extends MyDriveService{

	private long userToken;	
	private String path;
	private List<String> args;

	/**
	 * Executa um ficheiro de texto.
	 * @param userToken
	 * @param path
	 * @param args
	 */
	public ExecuteFile(long userToken, String path, List<String> args) {
		this.userToken = userToken;
		this.path = path;
		this.args = args;	
	}


	@Override
	protected void dispatch() throws MyDriveException {
		args = new ArrayList<String>();
		MyDrive md = MyDrive.getInstance();
		User u = md.getUserByToken(userToken);
		File f = md.getFileByPath(path);
		f.execute(u,args);
	}
}