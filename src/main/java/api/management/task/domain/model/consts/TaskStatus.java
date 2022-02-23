package api.management.task.domain.model.consts;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * タスクステータスマスタの定数一覧
 */
@Getter
@Schema(enumAsRef = true, description = "タスクのステータス一覧: \n"
        + "* `1` - TODO(着手前)\n"
        + "* `2` - PROGRESS(進行中)\n"
        + "* `3` - WAIT(停止中)\n"
        + "* `4` - RESOLVED(解決済み)\n"
)
public enum TaskStatus {

    TODO(1),
    PROGRESS(2),
    WAIT(3),
    RESOLVED(4);

    private final int id;

    TaskStatus(int id) {
        this.id = id;
    }

    /**
     * ID一覧を返す
     *
     * @return ID一覧
     */
    public static List<Integer> getIdList() {
        return Arrays.stream(TaskStatus.values())
                .map(TaskStatus::getId)
                .collect(Collectors.toList());
    }
}
