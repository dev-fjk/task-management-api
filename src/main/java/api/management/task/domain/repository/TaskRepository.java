package api.management.task.domain.repository;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.infrastructure.entity.Task;
import api.management.task.infrastructure.entity.TaskDetail;

/**
 * タスク情報のリポジトリ
 */
public interface TaskRepository {

    /**
     * ユーザーのタスクを取得する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     * @return {@link TaskDetail}
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
     * @param task DBに登録するタスク情報
     * @return DB登録されたタスク情報
     */
    Task register(final Task task);
}
