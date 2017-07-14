package pt.tecnico.mydrive.exception;

public class InvalidTokenException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private long token;

    public InvalidTokenException(long token) {
        this.token = token;
    }

    public long getToken() {
        return token;

    }

    @Override
    public String getMessage() {
        return "This token " + token + " is invalid.";
    }
}
