package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;


public class ChangeUserService extends MyDriveService{
	
	private String username;
	
	public ChangeUserService() {
	}
	
	public ChangeUserService(String user) {
		this.username = user;
	}
	
	@Override
	protected void dispatch() {
		MyDrive md = MyDrive.getInstance();
		
		md.getSession();
		
	}
	
	
}