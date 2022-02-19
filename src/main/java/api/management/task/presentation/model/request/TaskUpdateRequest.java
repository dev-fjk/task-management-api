package api.management.task.presentation.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description = "タスク更新リクエスト")
public class TaskUpdateRequest extends TaskBaseRequest {
}
