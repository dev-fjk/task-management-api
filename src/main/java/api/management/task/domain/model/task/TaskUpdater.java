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

    private Long taskId;

    private Long userId;

    private Integer statusId;

    private Integer priorityId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate termDate;
}
