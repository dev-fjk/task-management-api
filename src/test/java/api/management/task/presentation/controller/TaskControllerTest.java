package api.management.task.presentation.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.management.task.domain.model.result.TaskResult;
import api.management.task.domain.service.TaskService;
import api.management.task.presentation.converter.ResponseConverter;
import api.management.task.presentation.helper.TaskHelper;
import api.management.task.presentation.model.response.UserTaskResponse;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    TaskService taskService;
    @MockBean
    ResponseConverter responseConverter;
    @MockBean
    TaskHelper taskHelper;

    @BeforeEach
    public void initTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("タスク取得API 正常リクエスト")
    public void 正常系_fetchUserTask_穴あけ() {
        try {
            // given
            var userId = 1;
            var taskId = 1;

            // and URIを作成
            var path = TaskController.BASE_PATH + "users/{userId}/tasks/{taskId}";
            var uriString = UriComponentsBuilder.fromUriString(path)
                    .buildAndExpand(Map.of("userId", userId, "taskId", taskId))
                    .encode().toUriString();

            // mocks
            var taskResult = TaskResult.builder().build();
            var userTaskResponse = UserTaskResponse.builder().build();
            doReturn(taskResult).when(taskService).fetchUserTask(userId, taskId);
            doReturn(userTaskResponse).when(responseConverter).convert(taskResult);

            // when-then
            mockMvc.perform(get(uriString)).andExpect(status().isOk());
            verify(taskService, times(1)).fetchUserTask(userId, taskId);
            verify(responseConverter, times(1)).convert(taskResult);

        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("タスク取得API ユーザーIDが最小値未満")
    public void 異常系_fetchUserTask_userIdが最小値未満() {
        try {
            // when-then
            mockMvc.perform(get(TaskController.BASE_PATH + "users/0/tasks/1"))
                    .andExpect(status().isBadRequest());

        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("タスク取得API タスクIDが最小値未満")
    public void 異常系_fetchUserTask_taskIdが最小値未満() {
        try {
            // when-then
            mockMvc.perform(get(TaskController.BASE_PATH + "users/1/tasks/0"))
                    .andExpect(status().isBadRequest());

        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }
}