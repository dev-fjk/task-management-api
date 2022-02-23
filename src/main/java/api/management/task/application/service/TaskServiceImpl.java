package api.management.task.application.service;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskRegister;
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
    public long register(TaskRegister register) {

        if (!taskUserRepository.isEnableUserId(register.getUserId())) {
            log.error("無効なユーザーIDです : {}", register.getUserId());
            throw new ResourceNotFoundException("ユーザーが見つかりませんでした ユーザーID: " + register.getUserId());
        }
        // DB側で自動採番された taskIdの値を返却
        return taskRepository.register(Task.of(register)).getTaskId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Throwable.class, timeout = 15)
    public void deleteTask(long userId, long taskId) {
        taskRepository.deleteTask(userId, taskId);
    }
}
