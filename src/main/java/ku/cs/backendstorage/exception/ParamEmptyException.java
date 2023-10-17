package ku.cs.backendstorage.exception;

public class ParamEmptyException extends Exception{
    public ParamEmptyException() {
        super("Parameter is empty");
    }
}
