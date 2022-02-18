package api.management.task.domain.repository;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.infrastructure.entity.TaskDetail;
import java.util.Optional;

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
    TaskDetail fetchUserTask(long userId, long taskId);
}
