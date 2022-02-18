package api.management.task.presentation.converter;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.presentation.model.response.UserTaskResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * リポジトリの取得結果から レスポンスへの変換を行うクラス
 */
@Component
public class ResponseConverterImpl implements ResponseConverter {

    /**
     * {@inheritDoc}
     */
    public UserTaskResponse convert(TaskResult taskResult) {
        if(StringUtils.hasText(taskResult))

        return UserTaskResponse.of(taskResult);
    }
}
