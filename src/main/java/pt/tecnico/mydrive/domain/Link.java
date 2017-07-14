package pt.tecnico.mydrive.domain;

import java.util.List;

import pt.tecnico.mydrive.exception.UserIsNotInSessionException;

public class Link extends Link_Base {
    
    public Link() {
        super();
    }

    public Link(String content){
        super();
        this.setContent(content);
    }
    
    public void delete(){
		setOwner(null);
		setMydrive(null);
		deleteDomainObject();
        }
    
    @Override
    public void execute(User u, List<String> args){
    	if (!u.isInSession()) {
			throw new UserIsNotInSessionException(u.getMyToken());
		}
    	MyDrive md = MyDrive.getInstance();
    	if(getContent()!=null){
    		File f = md.getFileByPath(getContent());
    		f.execute(u,args);
    	}
    }

    
    @Override
    public String getType(){
    	return "Link";
    }
}
