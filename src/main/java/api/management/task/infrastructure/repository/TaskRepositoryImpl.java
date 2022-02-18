package api.management.task.infrastructure.repository;

import api.management.task.domain.repository.TaskRepository;
import api.management.task.infrastructure.entity.TaskDetail;
import api.management.task.infrastructure.mapper.TaskMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * タスクテーブル操作用リポジトリ
 */
@Service
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskMapper taskMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDetail> fetchUserTask(long userId, long taskId) {
        return Optional.ofNullable(taskMapper.fetchUserTaskDetail(userId, taskId));
    }
}
