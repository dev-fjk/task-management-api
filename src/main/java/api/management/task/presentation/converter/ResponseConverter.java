package api.management.task.presentation.converter;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.presentation.model.response.UserTaskListResponse;
import api.management.task.presentation.model.response.UserTaskResponse;

/**
 * レスポンスへの変換を行うインターフェース
 */
public interface ResponseConverter {

    /**
     * ユーザーのタスク情報を返却する
     *
     * @param taskResult タスク取得結果
     * @return {@link UserTaskResponse}
     */
    UserTaskResponse convert(TaskResult taskResult);

    /**
     * ユーザーのタスク情報一覧を返却する
     *
     * @param offset         取得開始位置
     * @param taskResultList タスク取得結果一覧
     * @return {@link UserTaskListResponse}
     */
    UserTaskListResponse convert(int offset, TaskResultList taskResultList);

}
