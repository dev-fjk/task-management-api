package api.management.task.domain.model.consts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class TaskPriorityTest {

    @Test
    @DisplayName("EnumのID一覧確認")
    public void getIdListTest() {
        List<Integer> expectedList = Arrays.asList(11, 12, 13);
        assertEquals(expectedList, TaskPriority.getIdList());
    }
}