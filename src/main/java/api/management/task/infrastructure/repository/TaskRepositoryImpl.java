package api.management.task.infrastructure.repository;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.domain.factory.TaskResultFactory;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.selector.TaskListSelector;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.infrastructure.entity.TaskDetail;
import api.management.task.infrastructure.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * タスクテーブル操作用リポジトリ
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskMapper taskMapper;
    private final TaskResultFactory taskResultFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult fetchUserTask(long userId, long taskId) {
        final TaskDetail taskDetail = taskMapper.fetchUserTaskDetail(userId, taskId).orElseThrow(() -> {
            log.error("user task not found userId: {}, taskId: {}", userId, taskId);
            throw new ResourceNotFoundException("ユーザーのタスク情報が見つかりませんでした");
        });
        return taskResultFactory.factory(taskDetail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResultList fetchUserTaskList(TaskListSelector selector, int offset, int limit) {
        // タスクの総件数を取得 後続のタスク情報一覧を取得する際と同じ条件をwhere句に指定する
        final int total = taskMapper.userTaskCount(selector);
        if (total == 0) {
            return TaskResultList.empty();
        }
        return taskResultFactory.factory(
                total, taskMapper.fetchUserTaskDetailList(selector, offset, limit)
        );
    }
}
