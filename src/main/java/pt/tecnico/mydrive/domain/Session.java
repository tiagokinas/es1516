package pt.tecnico.mydrive.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.joda.time.*;
import java.util.TreeMap;

import pt.tecnico.mydrive.exception.*;


public class Session extends Session_Base {

	//ir para o dml
	private Map<String, String> env;

	/**
	 * Classe Session faz o papel de Login.
	 * 
	 */
	public Session() {
		super();
		env = new HashMap<String, String>();
	}    

	/**
	 * Funcao que verifica se a sessao e valida, ao verificar se a
	 * data de inicio do TokenAcesso dado esta compreendida no intervalo
	 * da data imediata (agora) e o tempo respectivo para cada tipo de utilizador
	 * Utilizador root: 10 minutos
	 * Utilizador guest: sem tempo definido
	 * Qualquer outro utilizador: 2 horas
	 * 
	 * @param token
	 * @return
	 */
	public boolean validSession(MyToken token){
		DateTime now = new DateTime();

		User user = token.getUser();
		String username = user.getUsername();

		if(username == "root") {
			if (token.getInitialDate().isAfter(now.minusMinutes(10)))	
				return true;
			else
				return false;
		} else if (username == "nobody") {
			return true;
		} else if (token.getInitialDate().isAfter(now.minusHours(2)))
			return true;
		else
			return false;
	}

	/**
	 * Funcao que verifica se um determinado utilizador, dado o seu token,
	 * esta ainda em sessao, verificando se esta registado na sessao e se 
	 * nao passaram 2 horas desde o seu ultimo acesso
	 * @param token
	 */
	public boolean inSessao(long token)
	{
		for(MyToken mt : getMytokenSet())
		{
			if(mt.getToken() == (token))
			{
				if (validSession(mt))
				{
					mt.setInitialDate(new DateTime());
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}


	/**
	 * Funcao que verifica se um determinado utilizador,
	 * tem a sessao valida, verificando se esta registado na sessao e se 
	 * nao passaram 2 horas desde o seu ultimo acesso
	 * @param username
	 */
	public boolean inSession(User user) {
		MyToken mt = user.getMytoken();
		return validSession(mt);
	}

	/*
   	public String getUsersInSession() {
   		for(User user : getUserInSessionSet()) {

   		}
   	}*/

	public long createToken() {
		return new BigInteger(64, new Random()).longValue();
	}


	/**
	 * Cria uma sessao caso o username e a password estejam corretas.
	 * @param username, password
	 * @return
	 */
	public MyToken createSession(String username, String password) throws WrongPasswordException {
		removeInactives();
		User user = getMyDrive().getUser(username);

		if(user.getPassword().equals(password)) {
			long token = createToken();
			MyToken mt = new MyToken(token, user);
			addMytoken(mt);
			env.put("user", getUserFromToken(token).getName());
			return mt;
		}
		else
		{
			throw new WrongPasswordException();
		}
	}

	/**
	 * Dado um token, elimina a sessao correspondente a esse token.
	 * @param token
	 */
	public void removeSession(long token) {
		for(MyToken mt : getMytokenSet()) {
			if(mt.getToken() == token) {
				mt.delete();
				break;
			}
		}
	}

	/**
	 * Esta funcao serve para eliminar da sessao todos os utilizadores 
	 * que estao inactivos ha mais de duas horas
	 */
	public void removeInactives() {
		for(MyToken mt : getMytokenSet()) {
			if(!validSession(mt)) {
				mt.delete();
			}
		}
	}

	/**
	 * Retorna um utilizador a partir do token correspondente a sua sessao. 
	 * @param token
	 * @return
	 */
	public User getUserFromToken(long token) {
		for(MyToken mt : getMytokenSet()) {
			if(mt.getToken() == token && validSession(mt))
				return mt.getUser();
		}
		return null;
	}

	/**
	 * Retorna a directoria que esta actualmente em sessao.
	 * @return
	 */
	public Directory getWorkDir(){ 
		Directory dir = this.getWorkingdirectory();
		return dir;
	}

	/**
	 * Retorna uma directoria a partir de uma String de path.
	 * @param path
	 * @return
	 */
	public Directory getDirectoryByPath(String path){ 
		return getUserInSession().getMydrive().getDirectory().getDirectoryByPath(path);
	}


	/**
	 * Cria um Mapa para as variaveis de ambiente
	 */
	private TreeMap<Session.EnvKey, String> environmentVars = new TreeMap<Session.EnvKey, String>(new Comparator<Session.EnvKey>(){
		@Override
		public int compare(Session.EnvKey t1, Session.EnvKey t2){
			Long token1 = new Long(t1.getToken());
			Long token2 = new Long(t2.getToken());
			int result = token1.compareTo(token2);

			if(result == 0)
				return t1.getName().compareTo(t2.getName());
			else 
				return result;
		}
	});

	/**
	 * Classe para variavel de ambiente
	 * @author Miguel
	 *
	 */
	protected final class EnvKey{
		private final long token;
		private final String name;

		protected EnvKey(long token, String name){
			this.token = token;
			this.name = name;
		}

		public String getName(){
			return name;
		}

		public long getToken(){
			return token;
		}

		@Override
		public boolean equals(Object o){
			return (o instanceof EnvKey) && 
					((EnvKey)o).getName().equals(name) && ((EnvKey)o).getToken() == getToken();
		}
	}

	/**
	 * Recebe um identificador do token
	 * e devolve o objecto correspondente
	 * @param token
	 * @return
	 */
	public MyToken getTokenFromLong(long token) {
		for(MyToken tkn: getMytokenSet()){
			if(tkn.getToken() == token){
				return tkn;
			}
		}
		throw new InvalidTokenException(token);
	}

	public void updateVar(MyToken tkn, String name, String value) throws UnauthorizedAccessException{
		//geTokenFromLong()

		if(tkn.isValid()){
			environmentVars.put(new Session.EnvKey(tkn.getToken(), name), value);
		}
	}


	public ArrayList<String> getVars(MyToken tkn) throws UnauthorizedAccessException{
		ArrayList<String> vars= new ArrayList<String>();

		for(java.util.Map.Entry<Session.EnvKey, String> entry : environmentVars.entrySet()){
			if(entry.getKey().getToken() == tkn.getToken())
				vars.add(entry.getValue());
		}

		return vars; 
	} 

}


