package pt.tecnico.mydrive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import pt.tecnico.mydrive.domain.*;

public class ListUsersService extends MyDriveService{

	private List<String> registeredUsers;

	/**
	 * Devolve uma lista com os nomes
	 * de utilizadores registados.
	 */
    public ListUsersService() {
    }

    public final void dispatch() {
        MyDrive md = getMyDrive();
        registeredUsers = new ArrayList<String>();

        for (User u : md.getUserSet()) {
            registeredUsers.add(u.getName());
        }

        Collections.sort(registeredUsers);
    }

    public final List<String> result() {
        return registeredUsers;
    }
}