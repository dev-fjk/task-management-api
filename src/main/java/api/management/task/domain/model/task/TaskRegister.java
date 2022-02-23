package api.management.task.domain.model.task;

import api.management.task.presentation.model.request.TaskAddRequest;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

/**
 * Taskの登録情報を持つクラス
 */
@Value
@Builder(access = AccessLevel.PRIVATE)
public class TaskRegister {

    long userId;

    Integer statusId;

    Integer priorityId;

    LocalDate startDate;

    LocalDate endDate;

    LocalDate termDate;

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
