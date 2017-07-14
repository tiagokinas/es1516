package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class LoginUserTest extends AbstractServiceTest {

	protected static final String USERNAME = "vasconcelos";
	protected static final String PASSWORD = "vasco";
    //TODO adicionado para compilar
    protected static final long TOKEN = new Long("nao sei o que meter aqui");

    protected void populate() {
    	createUserService(TOKEN, USERNAME, PASSWORD, "Joao Vasconcelos");
    }

    private void createUserService(long token, String username, String password, String s) {
        //todo implement
    }


    @Test
    public void success() {
    	LoginUserService service = new LoginUserService(USERNAME, PASSWORD);
        long token = 0; //todo fix this
        service.execute();

       User user = getUserInSession(service, token); //todo fix this. precisa de parametros adicionais
       assertEquals(USERNAME, user.getUsername());
       
    }

    //todo ver se isto deve estar aqui
    private User getUserInSession(LoginUserService service, long token) {
        return service.getUserByToken(token);
        //todo implement

    }

    @Test(expected = UserDoesNotExistException.class)
    public void loginUnknownUser() {
        LoginUserService service = new LoginUserService("joao", "jv");
        service.execute();
    
    }

    @Test(expected = WrongPasswordException.class)
    public void loginUserWithWrongPassword() {
        LoginUserService service = new LoginUserService(USERNAME, "jffv");
        service.execute();
    
	}

}