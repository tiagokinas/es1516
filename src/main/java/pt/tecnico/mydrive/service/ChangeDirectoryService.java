package pt.tecnico.mydrive.service;


import pt.tecnico.mydrive.domain.*;

import pt.tecnico.mydrive.exception.*;

public class ChangeDirectoryService extends MyDriveService{
   
   private MyToken token;
   private String path;
   private String newPath;
   
   /**
    * Altera a directoria corrente da sessao.
    * @param token
    * @param path
    */
   public ChangeDirectoryService(MyToken token, String path){
    		this.token = token;
    		this.path = path;
    }
   
   @Override 
   public void dispatch() throws DirectoryDoesNotExistException {
	 	
	 Directory dir = token.getCurrentDirectory();   
	 if(token.isValid() == true){
   		token.dateReset();

   		if(dir.fileExists( dir.getFileByPath(path).getId())== true){

   			if(path.equals(".")){
   				newPath=dir.getPath() + dir.getName();
   			}
   			else{
   				Directory newDir =(Directory) dir.getFileByPath(path);
   				token.setCurrentDirectory(newDir);
   				newPath=newDir.getPath() + "/" + newDir.getName();
   			}
   		}
   		else{
   			throw new DirectoryDoesNotExistException(path);
   		}
	}
		
  }
   public String changeDir(){
	  
	   return newPath;
   }
   
}
