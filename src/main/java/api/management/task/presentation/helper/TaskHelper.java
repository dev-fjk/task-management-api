package api.management.task.presentation.helper;

import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskRegister;
import api.management.task.domain.model.task.TaskUpdater;
import api.management.task.presentation.model.request.TaskAddRequest;
import api.management.task.presentation.model.request.TaskUpdateRequest;

/**
 * コントローラーの引数からタスク一覧取得情報保持用のBeanへ変換するHelper
 */
public interface TaskHelper {

    /**
     * ユーザーIDのみを持つ selectorの作成
     *
     * @param userId ユーザーID
     * @return {@link TaskListSelector}
     */
    TaskListSelector toSelector(final long userId);

    /**
     * TaskRegisterクラスを生成する
     *
     * @param userId     ユーザーID
     * @param addRequest 　タスク追加リクエスト
     * @return タスク登録情報
     */
    TaskRegister toRegister(long userId, TaskAddRequest addRequest);

    /**
     * TaskUpdaterクラスを生成する
     *
     * @param userId        ユーザーID
     * @param taskId        タスクID
     * @param updateRequest 　タスク更新リクエスト
     * @return タスク更新情報
     */
    TaskUpdater toUpdater(long userId, long taskId, TaskUpdateRequest updateRequest);

}
