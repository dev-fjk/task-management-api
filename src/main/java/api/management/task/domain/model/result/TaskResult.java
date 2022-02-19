package api.management.task.domain.model.result;

import api.management.task.domain.model.consts.TaskPriority;
import api.management.task.domain.model.consts.TaskStatus;
import lombok.Builder;
import lombok.Value;

/**
 * タスクの取得結果保持クラス
 */
@Value
@Builder
public class TaskResult {

    long taskId;

    long userId;

    int statusId;

    TaskStatus status;

    int priorityId;

    TaskPriority priority;

    String startDate;

    String endDate;

    String termDate;
}
