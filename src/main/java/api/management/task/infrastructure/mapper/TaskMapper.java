package api.management.task.infrastructure.mapper;

import api.management.task.infrastructure.entity.TaskDetail;
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
     * @return {@link TaskDetail}
     */
    TaskDetail fetchUserTaskDetail(@Param("userId") long userId, @Param("taskId") long taskId);
}
