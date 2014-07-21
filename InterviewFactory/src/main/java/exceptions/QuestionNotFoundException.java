package exceptions;


public class QuestionNotFoundException extends Exception {

    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
