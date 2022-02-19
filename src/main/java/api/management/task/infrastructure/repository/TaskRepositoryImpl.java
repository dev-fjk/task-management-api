package api.management.task.infrastructure.repository;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.domain.factory.TaskResultFactory;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.infrastructure.entity.TaskDetail;
import api.management.task.infrastructure.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * タスクテーブル操作用リポジトリ
 */
@Service
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
            throw new ResourceNotFoundException("ユーザーのタスク情報が見つかりませんでした");
        });
        return taskResultFactory.factory(taskDetail);
    }

    /**
     * ユーザーのタスク情報一覧を取得する
     *
     * @param userId ユーザーID
     * @param offset 取得開始位置
     * @param limit  　取得件数
     * @return {@link TaskResultList}d
     */
    @Override
    public TaskResultList fetchUserTaskList(long userId, int offset, int limit) {
        return null;
    }
}
