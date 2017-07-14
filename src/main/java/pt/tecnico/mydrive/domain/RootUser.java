package pt.tecnico.mydrive.domain;

public class RootUser extends RootUser_Base {
    
    public RootUser() {
        super();
        setUsername("root");
        setName("Super User");
        setPassword("***");
        setMask("rwxdr-x-");
    }
}
