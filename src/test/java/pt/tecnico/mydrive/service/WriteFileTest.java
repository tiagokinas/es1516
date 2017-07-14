package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class WriteFileTest extends AbstractServiceTest {

	private long userToken;
	private String filename = "ola";
	private String type = "Plain File";
	private String content = "ola mundo";
	private PlainFile f;
	private Directory d;
	private MyDrive md;
	private User u;
	private Session s;
	
    protected void populate() {
    	md = MyDrive.getInstance();
    	f = new PlainFile(md, filename, content);
    	//u = md.getUserByToken(userToken);
    	//dir = new Directory();
    	u = new User(md,"miguel","miguel","12345678");
    	s = u.getSession();
    	d = s.getWorkDir();
    }

   

    @Test
    public void WriteFile() {
    	WriteFileService service = new WriteFileService(userToken, filename, content); 
    	service.execute();
    	
    	assertEquals(d, f.getDir());
    	assertEquals(u, f.getOwner());
    	assertEquals(filename, f.getName());
    	assertEquals(type, f.getType());
    	assertEquals(content, f.getContent());
    }
    
    @Test(expected = FileDoesNotExistException.class)
    public void FileDoesNotExist() {
    	WriteFileService service = new WriteFileService(userToken, "FICHEIRINHO", content); 
        service.execute();
    }
    
    @Test(expected = WrongFileTypeToWriteException.class)
    public void WrongFileTypeToRead() {
    	WriteFileService service = new WriteFileService(userToken, d.getName(), content); 
        service.execute();
    }
    
    @Test(expected = UnauthorizedOperationException.class)
    public void UnauthorizedOperation() {
    	WriteFileService service = new WriteFileService(userToken, filename, content); 
        service.execute();
    }
    
    @Test(expected = ContentNeedsToBeStringException.class)
    public void ContentNeedsToBeStringException() {
    	WriteFileService service = new WriteFileService(userToken, filename, content); 
        service.execute();
    }
}