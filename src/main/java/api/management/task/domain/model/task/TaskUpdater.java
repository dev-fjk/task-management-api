package api.management.task.domain.model.task;

import api.management.task.presentation.model.request.TaskUpdateRequest;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

/**
 * Taskの更新情報を持つクラス
 */
@Data
@Builder
public class TaskUpdater {

    private long taskId;

    private long userId;

    private Integer statusId;

    private Integer priorityId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate termDate;

    /**
     * TaskUpdaterクラスを生成する
     *
     * @param userId        ユーザーID
     * @param taskId        タスクID
     * @param updateRequest 　タスク更新リクエスト
     * @return タスク更新情報
     */
    public static TaskUpdater of(long userId, long taskId, TaskUpdateRequest updateRequest) {
        return TaskUpdater
                .builder()
                .taskId(taskId)
                .userId(userId)
                .statusId(updateRequest.getStatusId())
                .priorityId(updateRequest.getPriorityId())
                .startDate(updateRequest.getStartDate())
                .endDate(updateRequest.getEndDate())
                .termDate(updateRequest.getTermDate())
                .build();
    }
}
