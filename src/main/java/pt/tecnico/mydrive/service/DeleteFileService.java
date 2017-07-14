package pt.tecnico.mydrive.service;


import pt.tecnico.mydrive.exception.*;
import pt.tecnico.mydrive.domain.*;


public class DeleteFileService extends MyDriveService{

	private String filename;
	
	/**
	 * Apaga um ficheiro do MyDrive.
	 * @param token
	 * @param filename
	 */
	public DeleteFileService(Long token, String filename){
		super.userToken = token;
		this.filename = filename;
	}
	
	@Override
	protected void dispatch() throws MyDriveException {
		// Confirmem se está certo sff
		
		MyDrive md = MyDrive.getInstance();
		User u = md.getUserByToken(userToken);
		File f = u.getFileByName(filename);
			u.removeFile(f);
	}
}	