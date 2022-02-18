package api.management.task.presentation.converter;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.presentation.model.response.UserTaskResponse;

/**
 * レスポンスへの変換を行うインターフェース
 */
public interface ResponseConverter {

    /**
     * ユーザーのタスク情報を返却する
     *
     * @param taskResult タスク取得結果
     * @return ユーザーのタスク情報レスポンス
     */
    UserTaskResponse convert(TaskResult taskResult);
}
