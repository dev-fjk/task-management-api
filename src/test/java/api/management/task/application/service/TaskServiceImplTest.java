package api.management.task.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.management.task.domain.model.result.TaskResult;
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
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("ユーザーのタスク取得_穴あけ")
    public void 正常系_fetchUserTask() {
        // given
        long userId = 10L;
        long taskId = 100L;

        // mocks
        var taskResult = TaskResult.builder().build();
        doReturn(taskResult).when(taskRepository).fetchUserTask(userId, taskId);

        // when-then
        var expected = target.fetchUserTask(userId, taskId);
        assertEquals(taskResult, expected);
        verify(taskRepository, times(1)).fetchUserTask(userId, taskId);
    }

    @Test
    @DisplayName("ユーザーのタスク一覧取得_穴あけ")
    public void 正常系_fetchUserTaskList() {
        // given
        TaskListSelector selector = TaskListSelector.builder().build();
        int offSet = 1;
        int limit = 20;

        // mocks
        TaskResultList taskResultList = TaskResultList.empty();
        doReturn(taskResultList).when(taskRepository).fetchUserTaskList(selector, offSet, limit);

        // when-then
        var expected = target.fetchUserTaskList(selector, offSet, limit);
        assertEquals(taskResultList, expected);
        verify(taskRepository, times(1)).fetchUserTaskList(selector, offSet, limit);
    }
}