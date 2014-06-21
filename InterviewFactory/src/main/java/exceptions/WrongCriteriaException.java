package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 19.06.14
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class WrongCriteriaException extends InterviewException {

    public WrongCriteriaException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public WrongCriteriaException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
