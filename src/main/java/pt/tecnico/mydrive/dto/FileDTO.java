package pt.tecnico.mydrive.dto;

import org.joda.time.DateTime;

public class FileDTO {

	private String type;
	private String username;
	private int id;
	private DateTime date;
	private String name;
	//private int length;
	//private String permissions;
	
	public FileDTO(String type, String username, int id, DateTime date, String name/*, int length, String permissions*/) {
		this.type = type;
		this.username = username;
		this.id = id;
		this.date = date;
		this.name = name;
		//this.length = length;
		//this.permissions = permissions;
	}

	public String getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public DateTime getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	/*public int getLength() {
		return length;
	}

	public String getPermissions() {
		return permissions;
	}*/
}
