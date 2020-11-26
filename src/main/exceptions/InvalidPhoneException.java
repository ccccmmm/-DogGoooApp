package exceptions;

public class InvalidPhoneException extends Exception {
    //public InvalidPhoneException() {}

    public InvalidPhoneException(String msg) {
        super(msg);
    }
}
