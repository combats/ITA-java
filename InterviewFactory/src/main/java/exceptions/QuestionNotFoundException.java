package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 08.07.14
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public class QuestionNotFoundException extends Exception {

    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
