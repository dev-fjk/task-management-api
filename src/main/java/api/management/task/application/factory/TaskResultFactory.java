package api.management.task.application.factory;

import api.management.task.application.common.utility.DateConverterUtil;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.infrastructure.entity.TaskDetail;
import org.springframework.stereotype.Component;

/**
 * タスクの取得結果作成用のファクトリ
 */
@Component
public class TaskResultFactory {

    /**
     * タスクの詳細情報取得結果を作成
     *
     * @param detail DBのタスク詳細情報
     * @return {@link TaskResult}
     */
    public TaskResult factory(TaskDetail detail) {
        return TaskResult.builder()
                .taskId(detail.getTaskId())
                .userId(detail.getUserId())
                .statusId(detail.getStatusId())
                .status(detail.getStatus())
                .priorityId(detail.getPriorityId())
                .priority(detail.getPriority())
                .startDate(DateConverterUtil.isoDate2Str(detail.getStartDate()))
                .endDate(DateConverterUtil.isoDate2Str(detail.getEndDate()))
                .termDate(DateConverterUtil.isoDate2Str(detail.getTermDate()))
                .build();
    }
}
