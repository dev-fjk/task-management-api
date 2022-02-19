package api.management.task.presentation.helper;

import api.management.task.domain.model.selector.TaskListSelector;

/**
 * コントローラーの引数からタスク一覧取得情報保持用のBeanへ変換するHelper
 */
public interface TaskListSelectorHelper {

    /**
     * ユーザーIDのみを持つ selectorの作成
     *
     * @param userId ユーザーID
     * @return {@link TaskListSelector}
     */
    TaskListSelector selector(final long userId);

}
