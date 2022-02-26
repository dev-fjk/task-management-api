package api.management.task.domain.model.consts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TaskStatusTest {

    @Test
    @DisplayName("EnumのID一覧確認")
    public void getIdListTest() {
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4);
        assertEquals(expectedList, TaskStatus.getIdList());
    }

}