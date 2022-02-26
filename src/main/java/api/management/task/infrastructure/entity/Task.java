package api.management.task.infrastructure.entity;

import api.management.task.domain.model.task.TaskRegister;
import api.management.task.domain.model.task.TaskUpdater;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * タスクテーブル Entity
 */
@Data
public class Task {

    private Long taskId;

    private Long userId;

    private Integer statusId;

    private Integer priorityId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate termDate;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private LocalDateTime deletedAt;

    /**
     * 追加に使用するtaskを生成
     *
     * @param register タスク登録情報
     * @return 登録用のTask
     */
    public static Task of(TaskRegister register) {
        final Task task = new Task();
        task.userId = register.getUserId();
        task.statusId = register.getStatusId();
        task.priorityId = register.getPriorityId();
        task.startDate = register.getStartDate();
        task.endDate = register.getEndDate();
        task.termDate = register.getTermDate();
        task.createdBy = String.valueOf(register.getUserId());
        task.updatedBy = String.valueOf(register.getUserId());
        return task;
    }

    /**
     * 更新に使用するtaskを生成
     *
     * @param updater タスク更新情報
     * @return 更新用のTask
     */
    public static Task of(TaskUpdater updater) {
        final Task task = new Task();
        task.taskId = updater.getTaskId();
        task.userId = updater.getUserId();
        task.statusId = updater.getStatusId();
        task.priorityId = updater.getPriorityId();
        task.startDate = updater.getStartDate();
        task.endDate = updater.getEndDate();
        task.termDate = updater.getTermDate();
        task.updatedBy = String.valueOf(updater.getUserId());
        return task;
    }
}
