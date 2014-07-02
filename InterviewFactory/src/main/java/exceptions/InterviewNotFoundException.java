package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class InterviewNotFoundException extends InterviewException {
    public InterviewNotFoundException() {
    }

    public InterviewNotFoundException(String message) {
        super(message);
    }
}
