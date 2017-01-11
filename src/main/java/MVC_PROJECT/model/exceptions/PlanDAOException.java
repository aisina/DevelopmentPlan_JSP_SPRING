package MVC_PROJECT.model.exceptions;


/**
 * Created by innopolis on 11.01.2017.
 */
public class PlanDAOException extends Exception {

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }
}
