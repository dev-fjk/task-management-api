package api.management.task.presentation.controller;

import api.management.task.application.common.constant.OpenApiConstant;
import api.management.task.domain.service.TaskService;
import api.management.task.presentation.converter.ResponseConverter;
import api.management.task.presentation.model.response.UserTaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = TaskController.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = TaskController.BASE_PATH, description = "タスクテーブルの操作用コントローラー")
public class TaskController {

    public static final String BASE_PATH = "/task/v1/";

    private final TaskService taskService;
    private final ResponseConverter responseConverter;

    @GetMapping(path = "/users/{user-id}/tasks/{task-id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ユーザーのタスクを取得")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ユーザーのタスク情報 情報が見つからない場合は空レスポンスを返す", content = @Content(
                    schema = @Schema(implementation = UserTaskResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.TASK_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> fetchUserTask(@PathVariable("user-id") @Min(1) long userId,
                                           @PathVariable("task-id") @Min(1) long taskId) {
        return ResponseEntity.ok(responseConverter.convert(taskService.fetchUserTask(userId, taskId)));
    }
}