package pt.tecnico.mydrive.presentation;

public class Key extends MdCommand {

    public Key(Shell sh) {
    	super(sh, "token", "Prints token and current user if no user provided, or changes the current user with the username provided.");
    }
    
    /**
     * Imprime o valor do token e username do utilizador actual,
     * ou altera o utilizador actual e o token activo e imprime 
     * o seu valor quando recebe um argumento username.
     * 
     * token [username]
     */
    public void execute(String[] args) {
    	//TODO verificar se isto esta certo
    	MdShell shell = new MdShell();
    	long token = shell.getUserToken();
    	String username = shell.getUserName();
    	
    	long newToken = Long.parseLong(args[1]);
    	
    	if(args.length == 0)
    		System.out.println("O utilizador actual e: " + username + " com o token: " + token);
    	
    	if(args.length > 1) {
    		shell.setUserName(args[0]);
    		shell.setUserToken(newToken);
    		System.out.println("Alteracao efectuada!");
    		System.out.println("O novo utilizador e: " + args[0] + " com o token " + args[1]);
    	}	
    }
}
