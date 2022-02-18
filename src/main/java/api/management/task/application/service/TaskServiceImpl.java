package api.management.task.application.service;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.domain.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * タスクテーブル操作用サービス
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult fetchUserTask(long userId, long taskId) {
        log.info("repository result : {}", taskRepository.fetchUserTask(userId, taskId));
        return TaskResult.builder().build();
    }
}
