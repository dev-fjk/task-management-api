package api.management.task.presentation.model.request;

import api.management.task.domain.model.consts.TaskPriority;
import api.management.task.domain.model.consts.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public abstract class TaskBaseRequest {

    @NotNull(message = "必須項目です")
    @Schema(description = "ステータスID", example = "1", required = true)
    private Integer statusId;

    @NotNull(message = "必須項目です")
    @Schema(description = "優先度ID", example = "11", required = true)
    private Integer priorityId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "開始日", example = "2022-02-15")
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "終了日", example = "2022-02-22")
    private LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "期限日", example = "2022-02-25")
    private LocalDate termDate;

    @AssertTrue(message = "{validation.status-enabled}")
    private boolean isEnableStatusId() {
        return TaskStatus.getIdList().contains(this.statusId);
    }

    @AssertTrue(message = "{validation.priority-enabled}")
    private boolean isEnableTaskPriority() {
        return TaskPriority.getIdList().contains(this.priorityId);
    }
}
