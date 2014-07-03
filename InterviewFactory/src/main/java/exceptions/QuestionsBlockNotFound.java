package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 02.07.14
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsBlockNotFound extends InterviewException {

    public QuestionsBlockNotFound() {
    }

    public QuestionsBlockNotFound(String message) {
        super(message);
    }
}
