package api.management.task.domain.model.result;

import api.management.task.domain.model.consts.TaskPriority;
import api.management.task.domain.model.consts.TaskStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

/**
 * タスクの取得結果保持クラス
 */
@Data
@Builder
public class TaskResult {

    private long taskId;

    private long userId;

    private int statusId;

    private TaskStatus status;

    private int priorityId;

    private TaskPriority priority;

    private String startDate;

    private String endDate;

    private String termDate;
}
