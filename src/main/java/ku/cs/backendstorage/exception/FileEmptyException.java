package ku.cs.backendstorage.exception;

public class FileEmptyException  extends Exception{
    public FileEmptyException() {
        super("File is empty");
    }
}
