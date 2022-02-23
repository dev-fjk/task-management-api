package api.management.task.domain.model.task;

import api.management.task.presentation.model.request.TaskAddRequest;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

/**
 * Taskの登録情報を持つクラス
 */
@Data
@Builder
public class TaskRegister {

    private long userId;

    private Integer statusId;

    private Integer priorityId;

    private LocalDate startDate;

    private  LocalDate endDate;

    private LocalDate termDate;

    /**
     * TaskRegisterクラスを生成する
     *
     * @param userId     ユーザーID
     * @param addRequest 　タスク追加リクエスト
     * @return タスク登録情報
     */
    public static TaskRegister of(long userId, TaskAddRequest addRequest) {
        return TaskRegister
                .builder()
                .userId(userId)
                .statusId(addRequest.getStatusId())
                .priorityId(addRequest.getPriorityId())
                .startDate(addRequest.getStartDate())
                .endDate(addRequest.getEndDate())
                .termDate(addRequest.getTermDate())
                .build();
    }
}
