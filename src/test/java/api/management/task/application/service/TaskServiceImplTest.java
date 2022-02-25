package api.management.task.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.domain.repository.TaskUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl target;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskUserRepository taskUserRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("ユーザーのタスク取得_穴あけ")
    public void 正常系_fetchUserTask() {
        TaskListSelector selector = TaskListSelector.builder().build();
        int offSet = 1;
        int limit = 20;

        TaskResultList taskResultList = TaskResultList.empty();
        doReturn(taskResultList).when(taskRepository).fetchUserTaskList(selector, offSet, limit);
        assertEquals(taskResultList, target.fetchUserTaskList(selector, offSet, limit));
    }
}