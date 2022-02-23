package api.management.task.domain.service;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskRegister;
import api.management.task.domain.model.task.TaskUpdater;

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

    /**
     * ユーザーのタスク情報一覧を取得する
     *
     * @param selector 検索条件
     * @param offset   取得開始位置
     * @param limit    　取得件数
     * @return {@link TaskResultList}
     */
    TaskResultList fetchUserTaskList(TaskListSelector selector, int offset, int limit);

    /**
     * タスクを新規登録する
     *
     * @param register タスク登録情報
     * @return タスクID
     */
    long registerTask(final TaskRegister register);

    /**
     * タスクを更新する
     *
     * @param updater タスク更新情報
     * @return 更新したタスク情報
     */
    TaskResult updateTask(final TaskUpdater updater);

    /**
     * ユーザーのタスクを削除する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     */
    void deleteTask(long userId, long taskId);
}
