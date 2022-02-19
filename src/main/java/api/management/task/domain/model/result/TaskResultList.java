package api.management.task.domain.model.result;

import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.collections4.CollectionUtils;

/**
 * タスク一覧の取得結果保持クラス
 */
@Value
@Builder
public class TaskResultList {

    /**
     * 総件数
     */
    int total;

    /**
     * タスク情報取得結果一覧
     */
    List<TaskResult> taskResultList;

    /**
     * 取得件数を返す
     *
     * @return 取得したタスクの件数
     */
    public int getCount() {
        if (CollectionUtils.isEmpty(this.taskResultList)) {
            return 0;
        }
        return taskResultList.size();
    }

    /**
     * 空のタスク情報一覧返却
     *
     * @return タスク情報が空の取得結果一覧
     */
    public static TaskResultList empty() {
        return TaskResultList.builder()
                .total(0)
                .taskResultList(Collections.emptyList())
                .build();
    }
}
