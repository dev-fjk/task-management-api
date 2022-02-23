package api.management.task.domain.model.consts;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * タスク優先度マスタの定数一覧
 */
@Getter
@Schema(enumAsRef = true, description = "タスクの優先度一覧: \n"
        + "* `11` - LOW(低)\n"
        + "* `12` - MIDDLE(中)\n"
        + "* `13` - HIGH(高)\n"
)
public enum TaskPriority {

    LOW(11),
    MIDDLE(12),
    HIGH(13);

    private final int id;

    TaskPriority(int id) {
        this.id = id;
    }

    /**
     * ID一覧を返す
     *
     * @return ID一覧
     */
    public static List<Integer> getIdList() {
        return Arrays.stream(TaskPriority.values())
                .map(TaskPriority::getId)
                .collect(Collectors.toList());
    }
}
