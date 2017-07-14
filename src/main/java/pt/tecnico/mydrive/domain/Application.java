package pt.tecnico.mydrive.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import pt.tecnico.mydrive.exception.UserIsNotInSessionException;

public class Application extends Application_Base {

	public Application() {
		super();
	}

	public Application(String content){
		super();
		this.setContent(content);
	}

	public void delete(){
		setOwner(null);
		setMydrive(null);
		deleteDomainObject();
    }

	public void doExecute(User u, String args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if (!u.isInSession()) {
			throw new UserIsNotInSessionException(u.getMyToken());
		}
		Class<?> cls;
		Method meth;
		try { // name is a class: call main()
			cls = Class.forName(args);
			meth = cls.getMethod("main", String[].class);
		} catch (ClassNotFoundException cnfe) { // name is a method
			int pos;
			if ((pos = args.lastIndexOf('.')) < 0) 
				throw cnfe;
			cls = Class.forName(args.substring(0, pos));
			meth = cls.getMethod(args.substring(pos+1), String[].class);
		}
		meth.invoke(null, (Object)args); // static method (ignore return)
	}

	@Override
	public void execute(User u, List<String> args) {
		if (getContent() != null) {
			String arguments = getContent();
			try {
				doExecute(u, arguments);
			} 
			catch (NoSuchMethodException e) {e.printStackTrace();}
			catch (SecurityException e) {e.printStackTrace();}
			catch (ClassNotFoundException e) {e.printStackTrace();}
			catch (IllegalAccessException e) {e.printStackTrace();} 
			catch (IllegalArgumentException e) {e.printStackTrace();} 
			catch (InvocationTargetException e) {e.printStackTrace();}
		}
	}



	@Override
	public String getType(){
		return "Application";
	}
}
