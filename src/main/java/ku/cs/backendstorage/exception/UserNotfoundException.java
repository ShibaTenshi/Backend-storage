package ku.cs.backendstorage.exception;

public class UserNotfoundException extends Exception{
    public UserNotfoundException() {
        super("User not found");
    }
}
