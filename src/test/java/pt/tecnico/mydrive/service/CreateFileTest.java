package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class CreateFileTest extends AbstractServiceTest {
	
	private long userToken;
	private String name = "ola";
	private String content = "ola mundo";
	private String path = "./../ola";
	private PlainFile f;
	private Application app;
	private Link l;
	private Directory d;
	private MyDrive md;
	private User u;
	
	
    protected void populate() {
    	md = MyDrive.getInstance();
    	f = new PlainFile(md,name,content);
    	app = new Application (content);
    	l = new Link (content);
    	d = new Directory(path, name);
    	u = new User(md,"joao","feneja","123456");
    	userToken = u.getMyToken();
    	f.setOwner(u);
    	app.setOwner(u);
    	l.setOwner(u);
    	d.setOwner(u);
    }

    @Test
    public void createPlainFile() {
    	CreateFileService service = new CreateFileService(userToken, name, "Plain File", content); 
    	service.execute();
    	
    	assertEquals(u, f.getOwner());
    	assertEquals(name, f.getName());
    	assertEquals("Plain File", f.getType());
    	assertEquals(content, f.getContent());
    	assertEquals(userToken, f.getOwner().getMyToken());
    }
    
    @Test
    public void createApp() {
    	CreateFileService service = new CreateFileService(userToken, name, "Application", content); 
    	service.execute();
    	
    	assertEquals(u, app.getOwner());
    	assertEquals("Application", app.getType());
    	assertEquals(content, app.getContent());
    	assertEquals(userToken, app.getOwner().getMyToken());
    }
   
    @Test
    public void createLink() {
    	CreateFileService service = new CreateFileService(userToken, name, "Link", content); 
    	service.execute();
    	
    	assertEquals(u, l.getOwner());
    	assertEquals("Link", l.getType());
    	assertEquals(content, l.getContent());
    	assertEquals(userToken, l.getOwner().getMyToken());
    }
    
    @Test
    public void createDirectory() {
    	CreateFileService service = new CreateFileService(userToken, name, "Directory", content); 
    	service.execute();
    	
    	assertEquals(u, d.getOwner());
    	assertEquals(name, d.getName());
    	assertEquals(path, d.getPath());
    	assertEquals("Directory", l.getType());
    	assertEquals(content, l.getContent());
    	assertEquals(userToken, l.getOwner().getMyToken());
    }
   
    @Test(expected = FileNameAlreadyExistsException.class)
    public void fileNameAlreadyExists() {
        CreateFileService service = new CreateFileService(userToken, name, "Plain File", content); 
        service.execute();
    }
    
    @Test(expected = PlainFileNameAlreadyExistsException.class)
    public void plainfileNameAlreadyExists() {
        CreateFileService service = new CreateFileService(userToken, name, "Plain File", content); 
        service.execute();
    }
    
    @Test(expected = FileAlreadyExistsException.class)
    public void unauthorizedFileCreation() {
        CreateFileService service = new CreateFileService(userToken, name, "Plain File", content); 
        service.execute();
    }
    
    @Test(expected = InvalidFileTypeException.class)
    public void invalidType() {
        CreateFileService service = new CreateFileService(userToken, name, "App", content); 
        service.execute();
    }
    
    @Test(expected = InvalidTokenException.class)
    public void invalidToken() {
    	int token =10;
        CreateFileService service = new CreateFileService(token, name, "Plain File", content); 
        service.execute();
    }
    
    @Test(expected = InvalidNameException.class)
    public void nameToLong() {
    	String invName = "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
        CreateFileService service = new CreateFileService(userToken, invName, "Plain File", content); 
        service.execute();
    }
}
