package api.management.task.application.exception;

/**
 * リソースが見つからない場合のエラー
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8171398301979324192L;

    public ResourceNotFoundException(String messages) {
        super(messages);
    }
}
