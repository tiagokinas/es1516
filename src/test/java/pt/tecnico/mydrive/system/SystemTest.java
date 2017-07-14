package pt.tecnico.mydrive.system;

import org.junit.Test;

import pt.tecnico.mydrive.service.AbstractServiceTest ;
import pt.tecnico.mydrive.presentation.*;

public class SystemTest extends AbstractServiceTest {

	private MdShell sh;

	protected void populate() {
		sh = new MdShell();
	}

	@Test
	public void success() {
		//new Import(sh).execute(new String[] { "other.xml" } );

		//new Login(sh).execute(new String[] {} )
		new Login(sh).execute(new String[] { "Miguel", "password" } );
		//new Key(sh).execute(new String[] { } );
		new Key(sh).execute(new String[] { "Joao", "iloveist" } );
		//new ChangeWorkingDirectory(sh).execute(new String[] {} )
		new ChangeWorkingDirectory(sh).execute(new String[] { "/home/test2" } );
		//new List(sh).execute(new String[] { } );
		new List(sh).execute(new String[] {"/home/test"} );
		//new Write(sh).execute(new String[] { } );
		new Write(sh).execute(new String[] { "/home/test/file.txt"} );
		new Write(sh).execute(new String[] { "/home/test/file.txt", "Hello World" } );
		//new Environment(sh).execute(new String[] { } );
		new Environment(sh).execute(new String[] {"cenas","/home/test/"} );
		//new Environment(sh).execute(new String[] { "cenas" } );
		//new Execute(sh).execute(new String[] {} );
		new Execute(sh).execute(new String[] {"/home/test/","cd"} );

	}
}