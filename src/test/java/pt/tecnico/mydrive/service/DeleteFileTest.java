package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class DeleteFileTest extends AbstractServiceTest {

	private long userToken;
	
	private User user;
	private File f;
	private Directory dir;
	
	private static final int FILE_ID = 20;
	private static final String FILE_NAME = "Test";
	private static final String FILE_NAMES = "Dir";
	private static final String FILE_PATH = "x/y/z";
	
	private static final String USER_NAME = "Antonio";
	private static final String USER_USERNAME = "Tonio";
	private static final String USER_PASSWORD = "xpto";
	
	private static final String FILE_CONTENT = "O meu cao comeu o TPC";

	
	
    protected void populate() {
    	MyDrive md = MyDrive.getInstance();
    	user = new User(md, USER_USERNAME, USER_NAME, USER_PASSWORD);
    	f = new PlainFile(md, FILE_NAME, FILE_CONTENT); 
    	dir = new Directory(FILE_PATH, FILE_NAMES);
    	
    }

   

    @Test
    public void success() {
       DeleteFileService service = new DeleteFileService(userToken, FILE_NAME);
       service.execute();
    
       assertNull(f.getFile(FILE_ID));    
    }
    
    @Test
    public void successdir() {
       DeleteFileService service = new DeleteFileService(userToken, FILE_NAMES);
       service.execute();
    
       assertNull(dir.getFileByPath(FILE_PATH));    
    } 
    
    @Test(expected = DirectoryDoesNotExistException.class)
    public void directorymissing() {
        DeleteFileService service = new DeleteFileService(userToken, FILE_NAMES);
        service.execute();
    
    }
  
    @Test(expected = FileDoesNotExistException.class)
    public void filemissing() {
        DeleteFileService service = new DeleteFileService(userToken, FILE_NAME);
        service.execute();
    
    }
    
    @Test(expected = UnauthorizedOperationException.class)
    public void notauthorized() {
        DeleteFileService service = new DeleteFileService(userToken, FILE_NAME);
        service.execute();
    
    }
    
    
    

}