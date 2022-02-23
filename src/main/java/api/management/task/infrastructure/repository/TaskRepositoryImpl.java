package api.management.task.infrastructure.repository;

import api.management.task.application.common.utility.DateConverterUtil;
import api.management.task.application.exception.RepositoryControlException;
import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.domain.factory.TaskResultFactory;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskUpdater;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.infrastructure.entity.Task;
import api.management.task.infrastructure.entity.TaskDetail;
import api.management.task.infrastructure.mapper.TaskMapper;
import java.time.Clock;
import java.time.LocalDateTime;
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
    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult fetchUserTask(long userId, long taskId) {
        final TaskDetail taskDetail = taskMapper.fetchUserTaskDetail(userId, taskId).orElseThrow(() -> {
            log.error("ユーザーのタスク情報が見つかりませんでした userId: {}, taskId: {}", userId, taskId);
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
        return taskResultFactory.factory(total, taskMapper.fetchUserTaskDetailList(selector, offset, limit));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task fetchTaskForUpdate(long taskId) {
        return taskMapper.fetchTaskForUpdate(taskId).orElseThrow(() -> {
            log.error("タスクが見つかりませんでした taskId: {}", taskId);
            throw new ResourceNotFoundException("タスク情報が見つかりませんでした タスクID:" + taskId);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task registerTask(Task task) {
        // insert成功時は TaskクラスにDB側で自動採番されたIDが設定される
        int insertCount = taskMapper.register(task);
        if (insertCount == 0) {
            log.error("タスクの新規登録に失敗しました ユーザーID : {}", task.getUserId());
            throw new RepositoryControlException("タスクの登録に失敗しました");
        }
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(TaskUpdater updater) {
        // タスク情報と更新日時をMapperに渡して更新処理を行う
        var task = Task.of(updater);
        var updatedAt = DateConverterUtil.isoDateTime2Str(LocalDateTime.now(clock));
        int updateCount = taskMapper.updateTask(task, updatedAt);
        if (updateCount == 0) {
            log.error("タスクの更新に失敗しました ユーザーID : {}, タスクID : {}", task.getUserId(), task.getTaskId());
            throw new RepositoryControlException("タスクの更新に失敗しました タスクID: " + task.getTaskId());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTask(long userId, long taskId) {
        int deleteCount = taskMapper.deleteTask(userId, taskId);
        if (deleteCount == 0) {
            log.error("タスクの削除に失敗しました ユーザーID : {}, タスクID : {}", userId, taskId);
            throw new RepositoryControlException("タスクの削除に失敗しました タスクID: " + taskId);
        }
    }
}
