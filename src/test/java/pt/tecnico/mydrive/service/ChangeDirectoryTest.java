package pt.tecnico.mydrive.service;

import org.junit.Test;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class ChangeDirectoryTest extends AbstractServiceTest {
	
	
	private MyToken token;
	private ChangeDirectoryService service;
	
	private static final String BAD_PATH = "\badjoras";
	//TODO confirmar se a directoria e a criada no inicio do programa		
	private static final String GOOD_PATH = "\root";

    protected void populate() {
    	MyDrive md = MyDrive.getInstance();
    	
    	User user = new User(md,"miguel", "fonseca", "fonseca");
    	md.addUser(user);
    	
    	LoginUserService rootLogin = new LoginUserService("miguel", "miguel");
    	rootLogin.execute();
    	
    	token = rootLogin.getMyToken();
    }

    @Test
    public void success() throws DirectoryDoesNotExistException {
    	service = new ChangeDirectoryService(token, GOOD_PATH);
    	service.execute();
    }
    
    @Test(expected = DirectoryDoesNotExistException.class)
    public void directorymissing() throws DirectoryDoesNotExistException {
    	ChangeDirectoryService service = new ChangeDirectoryService(token, BAD_PATH);
        service.execute();
    
    }
}