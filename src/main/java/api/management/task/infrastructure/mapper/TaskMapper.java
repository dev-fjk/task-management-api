package api.management.task.infrastructure.mapper;

import api.management.task.domain.model.selector.TaskListSelector;
import api.management.task.infrastructure.entity.TaskDetail;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * タスクテーブル向けのMapper IF
 */
@Mapper
public interface TaskMapper {

    /**
     * Userのタスク詳細情報を取得する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     * @return タスク詳細情報
     */
    Optional<TaskDetail> fetchUserTaskDetail(@Param("userId") long userId, @Param("taskId") long taskId);

    /**
     * ユーザーのタスクの総件数を取得する
     *
     * @param userId ユーザーID
     * @return 取得件数
     */
    int userTaskCount(@Param("userId") long userId);

    /**
     * ユーザーのタスクの一覧を取得する
     *
     * @param selector 検索条件
     * @param offset 取得開始位置
     * @param limit  取得件数
     * @return Task一覧
     */
    List<TaskDetail> fetchUserTaskDetailList(
            @Param("selector") TaskListSelector selector,
            @Param("offset") int offset,
            @Param("limit") int limit);
}
