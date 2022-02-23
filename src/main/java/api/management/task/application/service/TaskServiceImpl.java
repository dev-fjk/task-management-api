package api.management.task.application.service;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskRegister;
import api.management.task.domain.model.task.TaskUpdater;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.domain.repository.TaskUserRepository;
import api.management.task.domain.service.TaskService;
import api.management.task.infrastructure.entity.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスクテーブル操作用サービス
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskUserRepository taskUserRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult fetchUserTask(long userId, long taskId) {
        return taskRepository.fetchUserTask(userId, taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResultList fetchUserTaskList(TaskListSelector selector, int offset, int limit) {
        return taskRepository.fetchUserTaskList(selector, offset, limit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Throwable.class, timeout = 15)
    public long registerTask(TaskRegister register) {
        checkEnableUserId(register.getUserId());
        return taskRepository.registerTask(Task.of(register)).getTaskId(); // DBで自動採番されたタスクIDを返却
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Throwable.class, timeout = 15)
    public TaskResult updateTask(TaskUpdater updater) {
        var task = taskRepository.fetchTaskForUpdate(updater.getTaskId());
        taskRepository.updateTask(updater);
        return taskRepository.fetchUserTask(task.getUserId(), task.getTaskId()); // 更新したタスクの詳細情報を返却
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Throwable.class, timeout = 15)
    public void deleteTask(long userId, long taskId) {
        checkEnableUserId(userId);
        taskRepository.deleteTask(userId, taskId);
    }

    /**
     * ユーザーが存在するかチェックする　無効なIDの場合404エラーを返す
     *
     * @param userId ユーザーId
     */
    private void checkEnableUserId(long userId) {
        if (!taskUserRepository.isEnableUserId(userId)) {
            log.error("無効なユーザーIDです : {}", userId);
            throw new ResourceNotFoundException("ユーザーが見つかりませんでした ユーザーID: " + userId);
        }
    }
}
