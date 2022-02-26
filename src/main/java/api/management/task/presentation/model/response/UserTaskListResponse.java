package api.management.task.presentation.model.response;

import api.management.task.domain.model.consts.TaskPriority;
import api.management.task.domain.model.consts.TaskStatus;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Value;

/**
 * ユーザーのタスク情報一覧
 */
@Value
@Builder
@Schema(description = "ユーザーのタスク一覧情報")
public class UserTaskListResponse {

    /**
     * 総件数
     */
    @Schema(description = "総件数", example = "100")
    int total;

    /**
     * 取得開始指定位置
     */
    @Schema(description = "取得開始位置", example = "1")
    int offset;

    /**
     * 取得件数
     */
    @Schema(description = "取得件数", example = "20")
    int count;

    /**
     * タスク情報
     */
    @Schema(name = "ユーザーのタスク一覧")
    List<UserTask> taskResultList;

    @Value
    @Builder
    @Schema(name = "UserTaskListResponse.UserTask", description = "ユーザーのタスク")
    public static class UserTask {

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
         * ユーザーのタスク情報を返却する
         *
         * @param taskResult タスク取得結果
         * @return {@link UserTask}
         */
        public static UserTask form(TaskResult taskResult) {
            return UserTask.builder()
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

    /**
     * レスポンスを作成する
     *
     * @param offset     取得開始位置
     * @param resultList タスク一覧取得結果
     * @return {@link UserTaskListResponse}
     */
    public static UserTaskListResponse of(int offset, TaskResultList resultList) {
        return UserTaskListResponse.builder()
                .total(resultList.getTotal())
                .offset(offset)
                .count(resultList.getCount())
                .taskResultList(resultList.getTaskResultList().stream()
                        .map(UserTask::form).collect(Collectors.toList())
                ).build();
    }
}
