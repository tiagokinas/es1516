package pt.tecnico.mydrive.exception;

public class UserIsNotInSessionException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private long userToken;

    public UserIsNotInSessionException(long userToken) {
        this.userToken = userToken;
    }

    public long getUserToken() {
        return userToken;
    }

    @Override
    public String getMessage() {
        return "User " + userToken + "not in session";
    }
}
