package pt.tecnico.mydrive;

import java.io.*;

import pt.ist.fenixframework.Atomic;

import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.service.*;


public class SetupDomain {
	@Atomic
	public static void main(String[] args) {
		try {
			populateDomain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void populateDomain() throws IOException {
		MyDrive md = MyDrive.getInstance();
		
		/*
		SuperUser root = new SuperUser();
		md.addUser(root);*/
		
		// Cria um utilizador root
		User root = new User(md, "root", "Super User", "rootroot");
		md.addUser(root);
		
		// User root faz login na MyDrive
		LoginUserService rootLogin = new LoginUserService("root", "rootroot");
		rootLogin.execute();
		//long rootToken = rootLogin.getToken();


		
		
	}
	
	
}
