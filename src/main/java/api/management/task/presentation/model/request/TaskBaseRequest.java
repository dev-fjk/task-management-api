package api.management.task.presentation.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public abstract class TaskBaseRequest {

    @NotNull
    @Schema(description = "ステータスID", example = "1", required = true)
    private Integer statusId;

    @NotNull
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
}
