package api.management.task.infrastructure.mapper;

import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.infrastructure.entity.Task;
import api.management.task.infrastructure.entity.TaskDetail;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * タスクテーブル向けのMapper IF
 */
@Mapper
public interface TaskMapper {

    String TASK_INSERT_TEMPLATE = "insert into task(user_id, status_id, priority_id," +
            " start_date, end_date, term_date, created_by, updated_by) ";

    /**
     * Userのタスク詳細情報を取得する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     * @return タスク詳細情報
     */
    Optional<TaskDetail> fetchUserTaskDetail(@Param("userId") long userId, @Param("taskId") long taskId);

    /**
     * ユーザーのタスク一覧の件数を取得する
     *
     * @param selector 検索条件
     * @return 取得件数
     */
    int userTaskCount(@Param("selector") TaskListSelector selector);

    /**
     * ユーザーのタスクの一覧を取得する
     *
     * @param selector 検索条件
     * @param offset   取得開始位置
     * @param limit    取得件数
     * @return Task一覧
     */
    List<TaskDetail> fetchUserTaskDetailList(
            @Param("selector") TaskListSelector selector,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    /**
     * タスクを新規登録する
     *
     * @param task 登録するタスク情報
     * @return DBへのデータ登録件数
     */
    @Insert(TASK_INSERT_TEMPLATE + "values (#{task.userId}, #{task.statusId}, #{task.priorityId}, #{task.startDate},"
            + " #{task.endDate}, #{task.termDate}, #{task.createdBy}, #{task.updatedBy})")
    @Options(useGeneratedKeys = true, keyProperty = "task.taskId", keyColumn = "task_id")
    int register(@Param("task") Task task);
}
