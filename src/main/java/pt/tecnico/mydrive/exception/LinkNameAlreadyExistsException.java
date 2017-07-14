package pt.tecnico.mydrive.exception;

public class LinkNameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingLinkname;

    public LinkNameAlreadyExistsException(String conflictingLinkname) {
        this.conflictingLinkname = conflictingLinkname;
    }

    public String getConflictingLinkname() {
        return conflictingLinkname;

    }

    @Override
    public String getMessage() {
        return "This Link name " + conflictingLinkname + " is already being used";
    }
}
