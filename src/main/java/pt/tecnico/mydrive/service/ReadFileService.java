package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exception.*;
import pt.tecnico.mydrive.domain.*;


public class ReadFileService extends MyDriveService{

	private String name;
	private String content;
	
	/**
	 * Le o conteudo de um ficheiro de texto, 
	 * nao de uma directoria.
	 * @param userToken
	 * @param name
	 */
	public ReadFileService(long userToken, String name){
		super.userToken = userToken;
		this.name = name;
	
	}
	
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();
		PlainFile pf = md.getPlainFileByName(name);
		content = pf.getContent();
	}
	public final String getContent(){
		return content; 
	}
	
}