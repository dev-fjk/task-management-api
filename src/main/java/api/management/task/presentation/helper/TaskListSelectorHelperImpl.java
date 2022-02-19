package api.management.task.presentation.helper;

import api.management.task.domain.model.selector.TaskListSelector;
import org.springframework.stereotype.Component;

/**
 * コントローラーの引数からタスク一覧取得情報保持用のBeanへ変換するHelper
 */
@Component
public class TaskListSelectorHelperImpl implements TaskListSelectorHelper {
    /**
     * selectorの作成
     *
     * @param userId ユーザーID
     * @return {@link TaskListSelector}
     */
    @Override
    public TaskListSelector selector(long userId) {
        return TaskListSelector.builder().userId(userId).build();
    }
}
