package pt.tecnico.mydrive.exception;

public class UserDoesNotExistException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String userUsername;

    public UserDoesNotExistException(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserUsername() {
        return userUsername;
    }

    @Override
    public String getMessage() {
        return "User " + userUsername + " does not exist";
    }
}
