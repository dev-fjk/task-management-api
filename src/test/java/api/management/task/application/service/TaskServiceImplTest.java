package api.management.task.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.management.task.application.exception.ResourceNotFoundException;
import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.model.result.TaskResultList;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.model.task.TaskRegister;
import api.management.task.domain.model.task.TaskUpdater;
import api.management.task.domain.repository.TaskRepository;
import api.management.task.domain.repository.TaskUserRepository;
import api.management.task.infrastructure.entity.Task;
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
    public void testInit() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("ユーザーのタスク取得_穴あけ")
    public void 正常系_fetchUserTask() {
        // given
        var userId = 10L;
        var taskId = 100L;

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

    @Test
    @DisplayName("ユーザーのタスク登録_穴あけ")
    public void 正常系_registerTask() {
        // given
        var userId = 10L;
        var expectedTaskId = 100L;
        var register = TaskRegister.builder().userId(10L).build();

        // and
        var task = new Task();
        task.setTaskId(expectedTaskId);

        // mocks
        doReturn(true).when(taskUserRepository).isEnableUserId(userId);
        doReturn(task).when(taskRepository).registerTask(Task.of(register));

        // when-then
        var actual = target.registerTask(register);
        assertEquals(expectedTaskId, actual);
        verify(taskUserRepository, times(1)).isEnableUserId(userId);
        verify(taskRepository, times(1)).registerTask(Task.of(register));
    }

    @Test
    @DisplayName("ユーザーのタスク登録_無効なユーザーの場合 リソースが存在しないエラーが返却")
    public void 異常系_registerTask() {
        // given
        var userId = 10L;
        var register = TaskRegister.builder().userId(10L).build();

        // mocks
        doReturn(false).when(taskUserRepository).isEnableUserId(userId);

        // when-then
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> target.registerTask(register));
        assertEquals(exception.getMessage(), "ユーザーが見つかりませんでした ユーザーID: " + userId);
        verify(taskUserRepository, times(1)).isEnableUserId(userId);
    }

    @Test
    @DisplayName("ユーザーのタスク更新_穴あけ")
    public void 正常系_updateTask() {
        // given
        var userId = 10L;
        var taskId = 100L;
        var updater = TaskUpdater.builder().userId(10L).taskId(taskId).build();

        // and
        var task = new Task();
        task.setUserId(userId);
        task.setTaskId(taskId);

        // mocks
        var taskResult = TaskResult.builder().build();
        doReturn(task).when(taskRepository).fetchTaskForUpdate(taskId);
        doNothing().when(taskRepository).updateTask(updater);
        doReturn(taskResult).when(taskRepository).fetchUserTask(userId, taskId);

        // when-then
        var actual = target.updateTask(updater);
        assertEquals(taskResult, actual);
        verify(taskRepository, times(1)).fetchTaskForUpdate(taskId);
        verify(taskRepository, times(1)).updateTask(updater);
        verify(taskRepository, times(1)).fetchUserTask(userId, taskId);
    }

    @Test
    @DisplayName("ユーザーのタスク削除_穴あけ")
    public void 正常系_deleteTask() {
        // given
        var userId = 10L;
        var taskId = 100L;

        // mocks
        doReturn(true).when(taskUserRepository).isEnableUserId(userId);
        doNothing().when(taskRepository).deleteTask(userId, taskId);

        // when-then
        target.deleteTask(userId, taskId);
        verify(taskUserRepository, times(1)).isEnableUserId(userId);
        verify(taskRepository, times(1)).deleteTask(userId, taskId);
    }

    @Test
    @DisplayName("ユーザーのタスク削除_無効なユーザーの場合 リソースが存在しないエラーが返却")
    public void 異常系_deleteTask() {
        // given
        var userId = 10L;
        var taskId = 100L;

        // mocks
        doReturn(false).when(taskUserRepository).isEnableUserId(userId);

        // when-then
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> target.deleteTask(userId, taskId));
        assertEquals(exception.getMessage(), "ユーザーが見つかりませんでした ユーザーID: " + userId);
        verify(taskUserRepository, times(1)).isEnableUserId(userId);
    }
}