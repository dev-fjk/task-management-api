package api.management.task.infrastructure.entity;

import api.management.task.domain.model.consts.TaskPriority;
import api.management.task.domain.model.consts.TaskStatus;
import lombok.Getter;

/**
 * タスクテーブル詳細Entity
 * マスタのデータ名を追加したTask
 */
@Getter
public class TaskDetail extends Task {

    private TaskStatus status;

    private TaskPriority priority;
}
