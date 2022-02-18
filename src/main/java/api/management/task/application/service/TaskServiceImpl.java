package api.management.task.application.service;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.domain.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * タスクテーブル操作用サービス
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult fetchUserTask(long userId, long taskId) {
        return TaskResult.builder().build();
    }
}
