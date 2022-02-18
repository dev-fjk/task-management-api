package api.management.task.domain.service;

import api.management.task.domain.model.result.TaskResult;

/**
 * タスク情報のサービス
 */
public interface TaskService {

    /**
     * ユーザーのタスクを取得する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     * @return {@link TaskResult}
     */
    TaskResult fetchUserTask(long userId, long taskId);

}
