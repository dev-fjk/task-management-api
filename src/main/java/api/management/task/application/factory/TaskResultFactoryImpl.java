package api.management.task.application.factory;

import api.management.task.application.common.utility.DateConverterUtil;
import api.management.task.domain.factory.TaskResultFactory;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.infrastructure.entity.TaskDetail;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * タスクの取得結果作成用のファクトリ
 */
@Component
public class TaskResultFactoryImpl implements TaskResultFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResult factory(TaskDetail detail) {
        return this.mapToResult(detail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResultList factory(int total, List<TaskDetail> detailList) {
        return TaskResultList.builder()
                .total(total)
                .taskResultList(detailList.stream()
                        .map(this::mapToResult).collect(Collectors.toList())
                ).build();
    }

    /**
     * TaskDetailからTaskResultを生成する
     *
     * @param detail タスク詳細情報
     * @return タスク取得結果クラス
     */
    private TaskResult mapToResult(TaskDetail detail) {
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
