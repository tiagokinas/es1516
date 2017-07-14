package pt.tecnico.mydrive.service;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.mydrive.exception.*;
import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.dto.FileDTO;


public class ListDirectoryService extends MyDriveService{
	
	private List<FileDTO> result;
	private String path;
	
	/**
	 * Efetua a listagem completa 
	 * da diretoria corrente.
	 * @param token
	 * @param path
	 */
	public ListDirectoryService(long token, String path) {
		super.userToken = token;
		this.path = path;
		result = new ArrayList<>();
	}
	public ListDirectoryService(long token) {
		this(token, null);
	}
	
	public List<FileDTO> getResult(){
		return result;
	}
	
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();
		User u = md.getUserByToken(userToken);
		Session s = u.getSession();
		Directory d;
		if(path == null){
			d = s.getWorkDir();
		}
		else{
			d = s.getDirectoryByPath(path);
		}
		//ver se esta em sessao:DOMINIOOOOOO
		if(u.isInSession()){
			result.add(new FileDTO(d.getType(), d.getOwner().getUsername(), d.getId(), d.getDatamod(), "."));
			result.add(new FileDTO(d.getDir().getType(), d.getDir().getOwner().getUsername(), d.getDir().getId(), d.getDir().getDatamod(), "..")); 
			for(File f: d.getOwnedSet()){
				FileDTO dto= new FileDTO(f.getType(), f.getOwner().getUsername(), f.getId(), f.getDatamod(), f.getName());
				result.add(dto);
			}
		}
		
	}
}