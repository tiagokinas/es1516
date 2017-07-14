package pt.tecnico.mydrive.domain;

import pt.tecnico.mydrive.exception.FileDoesNotExistException;
import pt.tecnico.mydrive.exception.UserIsNotInSessionException;

import java.util.ArrayList;
import java.util.List;




public class Directory extends Directory_Base {


	public Directory() {
		super();
	}

	public Directory(String path, String name)/* throws PathTooLongException */{
		super();
		String pathString = path.toString();
		//VASCONCELOS ADDICIONA EXCEPCAO
		if (pathString.length() > 1024) {
			//throw new PathTooLongException(path);
		}
		setPath(path);
		setName(name);
	}

	/*public Directory(User user, Element xml) {
    	super();
    	//xmlImport();
    	setUser(user);
    }*/

	/*@Override
    public void setUser(User user) {
        if (user == null)
            super.setUser(null);
        else
            user.addDirectory(this);
    }*/


	public void delete(){
		String path = getPath();
		Directory directoryToRemove = getDirectoryByPath(path);
		for (File f : directoryToRemove.getOwnedSet()){
			f.delete();
			}
		setOwner(null);
		setMydrive(null);
		deleteDomainObject();
	    }

	public String getPath(){
		return getDir().getPath() + "/" + getName();
	}


	//Corrigir isto: verificar se estou a por dir ou files

	public List<String> listAllFiles() {
		List<String> files = new ArrayList<>();
		for(File f: getOwnedSet()){
			String result = f.getName();
			files.add(result);
			/* Nao esquecer o get content e o permission
    		System.out.print(f.getPermissions());
    		System.out.print(f.getContent());*/
		}
		return files;
	}

	public File getFileByName(String fileName) {
		for(File f: getOwnedSet()){
			if(f.getName().equals(fileName)){
				return f;
			}
		}
		throw new FileDoesNotExistException(fileName);

	}

	public boolean fileExists(int id){
		MyDrive md = MyDrive.getInstance();
		pt.tecnico.mydrive.domain.File file = md.getFile(id);
		return (file != null);
	}

	public File getFileByPath(String path) {
		for(pt.tecnico.mydrive.domain.File file : getOwnedSet()){
			if(file.getPath().equals(path)){
				return (Directory) file;
			}
		}
		return null;
	}

	//CORRIGIR ISTO E POR BEM; ESTA AQUI SO PARA NAO ATORFIAR A MALTA
	public Directory getDirectoryByPath(String path){ 
		for(File f : getOwnedSet()){
			if(f.getPath().equals(path))
				if(f.getType().equals("Directory"))
					return (Directory) f;
		}
		return null;
	}

	public void execute(User u, List<String> args){
		//NOTHING TO DO
	}

	@Override
	public String getType(){
		return "Directory";
	}
}
