package pt.tecnico.mydrive.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import pt.tecnico.mydrive.domain.*; // Mockup
import pt.tecnico.mydrive.service.*;
import pt.tecnico.mydrive.dto.*;
import pt.tecnico.mydrive.exception.*;


@RunWith(JMockit.class)

public class IntegrationTest extends AbstractServiceTest {
	private static final List<String> names = new ArrayList<String>();
	private static final List<String> args = new ArrayList<String>();
	private static final String u1 = "Miguel", u2 = "Vasconcelos", u3 = "Feneja", u4 = "Silva", u5 = "Kinas", u6 = "Gomes";
	private static final String n1 = "MiguelF", n2 = "JVasconcelos", n3 = "JFeneja", n4 = "GSilva", n5 = "TKinas", n6 = "GGomes";
	private static final String p1 = "MiguelFonseca", p2 = "JoaoVasconcelos", p3 = "JoaoFeneja", p4 = "GilSilva", p5 = "TiagoKinas", p6 = "GilGomes";
	private static final String varN = "ls";
	private static final String varV = "/home/test";
	private static final long userToken = 1L;
	private static final String pathdir = "/home/test";
	private static final String pathtxt = "/home/test/teste.txt";
	private static final String type1 = "PlainFile";
	private static final String type2 = "Directory";
	private static final String content1 = "ls";
	private static final String content2 = "/home/test";
	private static final String content3 = "/home/$user/profile";
	private static final String filename = "teste";
	private static final String filename2 = "teste2";
	private static final String variable = "$user";
	private static final int users = 2;
	
	//private static final String importFile = "other.xml";



protected void populate() { // populate mockup
	names.add(u1);
	names.add(u2);
	names.add(u3);
	names.add(u4);
	names.add(u5);
	names.add(u6);
	
	args.add(content1);
	args.add(content2);

	
	
}

@Test
public void success() throws Exception {

    new CreateUserService(userToken,u1,n1,p1).execute();
    new CreateUserService(userToken,u2,n2,p2).execute();
    new CreateUserService(userToken,u3,n3,p3).execute();
    new CreateUserService(userToken,u4,n4,p4).execute();
    new CreateUserService(userToken,u5,n5,p5).execute();
    new CreateUserService(userToken,u6,n6,p6).execute();
    
    new CreateFileService(userToken, filename, type1, content1).execute();
    new CreateFileService(userToken, filename2, type2, content2).execute();
    
    ListUsersService lu = new ListUsersService();
	lu.execute();
	System.out.print("Users:");
	for (String name: lu.result())
	    System.out.println("\t" + name);
	System.out.println(".");
	assertEquals(lu.result().size(), 6);// MiguelF, JVasconcelos, JFeneja, GSilva, TKinas, GGomes
	
	
	ListDirectoryService lds = new ListDirectoryService(userToken);
	lds.execute();
	for (FileDTO dto: lds.getResult())
		System.out.println(dto.getType() + " " + dto.getUsername() 
		+ " " + dto.getId() + " " + dto.getDate().toString() + " " + dto.getName());
	assertEquals(lds.getResult().size(), 2);
	
	new DeleteFileService(userToken, filename).execute();
	lds = new ListDirectoryService(userToken);
	lds.execute();
	assertEquals(lds.getResult().size(), 1);

    new DeleteUserService(userToken,u1).execute();
	lu = new ListUsersService();
	lu.execute();
	assertEquals(lu.result().size(), 5); // JVasconcelos, JFeneja, GSilva, TKinas, GGomes
	
	//O servico tem de ser corrigido
	//WriteFileService w = new WriteFileService(userToken,pathtxt,content1);
	//w.execute();
	//assertEquals(w.getContent(), "ls");*/
	
	ReadFileService w = new ReadFileService(userToken,filename);
	w.execute();
	assertEquals(w.getContent(), "ls");
	
	//O servico tem de ser corrigido
	//AddVariableService add = new AddVariableService(userToken,varN,varV);
	//add.execute();
	//assertEquals(add.getvarN(), "ls");
	//assertEquals(add.getvarV(), "/home/test");
	
	
	//TODO MOCK-UPS
	
	//O servico addvariable tem de estar a funcionar 
	//no Link tem de se tem um metodo para ir buscar a variavel de ambiente
	/*new MockUp<Link>() {
		  @Mock
		  String getContent() { return variable; }
		};
	        Link l = new Link(content3);
	        MyDrive md=l.getMydrive();
	        User u=new User(md,u2,n2,p2);
	        l.execute(u,args);
	        assertEquals(l.result(), variable);
	 */
	
	new MockUp<MyDrive>() {
		@Mock
		 int removeUser(String u1) { return users;}
		};
    DeleteUserService dus = new DeleteUserService(userToken,u5);
    dus.execute();
   	lu = new ListUsersService();
   	lu.execute();
    assertEquals(lu.result(), users);
	 
	
	

	
	

}






}
    
    
    
    
    
