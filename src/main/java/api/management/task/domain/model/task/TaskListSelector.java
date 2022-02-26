package api.management.task.domain.model.task;

import lombok.Builder;
import lombok.Data;

/**
 * タスク一覧の検索条件を保持するクラス
 */
@Data
@Builder
public class TaskListSelector {
    private Long userId;
}
