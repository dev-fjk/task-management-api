package api.management.task.domain.factory;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.infrastructure.entity.TaskDetail;
import java.util.List;

/**
 * タスクの取得結果作成用のファクトリIF
 */
public interface TaskResultFactory {

    /**
     * タスクの詳細情報取得結果を作成
     *
     * @param detail DBのタスク詳細情報
     * @return {@link TaskResult}
     */
    TaskResult factory(TaskDetail detail);

    /**
     * タスク一覧取得結果の返却
     *
     * @param total      取得総件数
     * @param detailList DBのタスク詳細情報
     * @return {@link TaskResultList}
     */
    TaskResultList factory(int total, List<TaskDetail> detailList);
}
