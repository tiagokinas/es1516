package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exception.*;
import pt.tecnico.mydrive.domain.*;

public class AddVariableService extends MyDriveService{

	private MyToken userToken;	
	private String varName;
	private String varValue;

	/**
	 * Adiciona uma variavel de ambiente ou redefine 
	 * uma que ja exista.
	 * 
	 * @param userToken
	 * @param varName
	 * @param varValue
	 */
	public AddVariableService(MyToken tk, String varName, String varValue) {
		this.userToken = tk;
		this.varName = varName;
		this.varValue = varValue;
	}
	
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();
		if(userToken.isValid()) {
			md.getSession().updateVar(userToken, varName, varValue);
			java.util.ArrayList<String> variables = md.getSession().getVars(userToken);
			
			for(String s : variables)
				System.out.println(s);
		}
		else {
			System.out.println("The token " + userToken.getToken() + " is valid.");
			throw new UnauthorizedAccessException();
		}
		
			
		}
	
	}
