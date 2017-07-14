package pt.tecnico.mydrive.service;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public abstract class MyDriveService {
	
	protected long userToken; 

	@Atomic
	public final void execute() throws MyDriveException {
			dispatch();
	}

	static MyDrive getMyDrive() {
		return FenixFramework.getDomainRoot().getMydrive();
	}

	protected abstract void dispatch() throws MyDriveException;
}


