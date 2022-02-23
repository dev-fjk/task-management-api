package api.management.task.application.exception;

/**
 * データの更新に失敗した場合の例外
 */
public class RepositoryControlException extends RuntimeException {

    private static final long serialVersionUID = -5013210732331927791L;

    public RepositoryControlException(String message) {
        super(message);
    }
}
