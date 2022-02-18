package api.management.task.presentation.handler;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.presentation.converter.ProblemConverter;
import api.management.task.presentation.model.response.ProblemResponse;
import java.nio.file.AccessDeniedException;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 例外発生時のハンドラー
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ProblemConverter problemConverter;

    /**
     * リクエストオブジェクトのバリデーションエラー
     *
     * @param exception {@link BindException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemResponse> handleBindException(BindException exception) {
        return this.errorResponses(HttpStatus.BAD_REQUEST, problemConverter.convert(exception));
    }

    /**
     * リクエストオブジェクトのバリデーションエラー
     *
     * @param exception {@link ConstraintViolationException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        return this.errorResponses(HttpStatus.BAD_REQUEST, problemConverter.convert(exception));
    }

    /**
     * 権限がない時のエラー
     *
     * @param exception {@link AccessDeniedException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return this.errorResponses(HttpStatus.FORBIDDEN, problemConverter.convert(exception));
    }

    /**
     * リソースが見つからない場合のエラー
     *
     * @param exception {@link ResourceNotFoundException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return this.errorResponses(HttpStatus.NOT_FOUND, problemConverter.convert(exception));
    }

    /**
     * その他エラー
     *
     * @param exception {@link RuntimeException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemResponse> handleRuntimeException(RuntimeException exception) {
        return this.errorResponses(HttpStatus.INTERNAL_SERVER_ERROR, problemConverter.convert(exception));
    }

    /**
     * エラーレスポンスを作成する
     *
     * @param status : HTTPステータス
     * @param body   : HTTPボディ
     * @return エラーレスポンス
     */
    private ResponseEntity<ProblemResponse> errorResponses(HttpStatus status, ProblemResponse body) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(body);
    }

}
