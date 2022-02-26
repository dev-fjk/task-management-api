package api.management.task.presentation.model.response;

import api.management.task.domain.model.consts.TaskPriority;
import api.management.task.domain.model.consts.TaskStatus;
import api.management.task.domain.model.result.TaskResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * ユーザーのタスク情報レスポンス
 */
@Value
@Builder
@Schema(description = "ユーザーのタスク情報")
public class UserTaskResponse {

    @Schema(description = "タスクID", example = "10", required = true)
    Long taskId;

    @Schema(description = "ユーザーID", example = "100", required = true)
    Long userId;

    @Schema(description = "ステータスID", example = "1", required = true)
    Integer statusId;

    @Schema(description = "ステータス", example = "TODO", required = true)
    TaskStatus status;

    @Schema(description = "優先度ID", example = "11", required = true)
    Integer priorityId;

    @Schema(description = "優先度", example = "LOW", required = true)
    TaskPriority priority;

    @Schema(description = "開始日", example = "2022-02-15")
    String startDate;

    @Schema(description = "終了日", example = "2022-02-22")
    String endDate;

    @Schema(description = "期限日", example = "2022-02-25")
    String termDate;

    /**
     * Task情報の取得結果から レスポンス情報を作成し返す
     *
     * @param taskResult タスク取得結果
     * @return レスポンス
     */
    public static UserTaskResponse of(TaskResult taskResult) {
        return UserTaskResponse.builder()
                .taskId(taskResult.getTaskId())
                .userId(taskResult.getUserId())
                .statusId(taskResult.getStatusId())
                .status(taskResult.getStatus())
                .priorityId(taskResult.getPriorityId())
                .priority(taskResult.getPriority())
                .startDate(taskResult.getStartDate())
                .endDate(taskResult.getEndDate())
                .termDate(taskResult.getTermDate())
                .build();
    }
}
