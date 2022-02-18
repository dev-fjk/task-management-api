package api.management.task.application.service;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.application.factory.TaskResultFactory;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.domain.service.TaskService;
import api.management.task.infrastructure.entity.TaskDetail;
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
    private final TaskResultFactory taskResultFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult fetchUserTask(long userId, long taskId) {
        final TaskDetail taskDetail = taskRepository.fetchUserTask(userId, taskId).orElseThrow(() -> {
            throw new ResourceNotFoundException("ユーザーのタスク情報が見つかりませんでした");
        });
        return taskResultFactory.factory(taskDetail);
    }
}
