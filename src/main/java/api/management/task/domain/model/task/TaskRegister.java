package api.management.task.domain.model.task;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

/**
 * Taskの登録情報を持つクラス
 */
@Data
@Builder
public class TaskRegister {

    private Long userId;

    private Integer statusId;

    private Integer priorityId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate termDate;
}
