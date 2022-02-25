package api.management.task.presentation.helper;

import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskRegister;
import api.management.task.domain.model.task.TaskUpdater;
import api.management.task.presentation.model.request.TaskAddRequest;
import api.management.task.presentation.model.request.TaskUpdateRequest;
import org.springframework.stereotype.Component;

/**
 * コントローラーの引数からタスク一覧取得情報保持用のBeanへ変換するHelper
 */
@Component
public class TaskHelperImpl implements TaskHelper {
    /**
     * selectorの作成
     *
     * @param userId ユーザーID
     * @return {@link TaskListSelector}
     */
    @Override
    public TaskListSelector toSelector(long userId) {
        return TaskListSelector.builder().userId(userId).build();
    }

    /**
     * TaskRegisterクラスを生成する
     *
     * @param userId     ユーザーID
     * @param addRequest 　タスク追加リクエスト
     * @return タスク登録情報
     */
    @Override
    public TaskRegister toRegister(long userId, TaskAddRequest addRequest) {
        return TaskRegister.builder()
                .userId(userId)
                .statusId(addRequest.getStatusId())
                .priorityId(addRequest.getPriorityId())
                .startDate(addRequest.getStartDate())
                .endDate(addRequest.getEndDate())
                .termDate(addRequest.getTermDate())
                .build();
    }

    /**
     * TaskUpdaterクラスを生成する
     *
     * @param userId        ユーザーID
     * @param taskId        タスクID
     * @param updateRequest 　タスク更新リクエスト
     * @return タスク更新情報
     */
    @Override
    public TaskUpdater toUpdater(long userId, long taskId, TaskUpdateRequest updateRequest) {
        return TaskUpdater.builder()
                .taskId(taskId)
                .userId(userId)
                .statusId(updateRequest.getStatusId())
                .priorityId(updateRequest.getPriorityId())
                .startDate(updateRequest.getStartDate())
                .endDate(updateRequest.getEndDate())
                .termDate(updateRequest.getTermDate())
                .build();
    }
}
