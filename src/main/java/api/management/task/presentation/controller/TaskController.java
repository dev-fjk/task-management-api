package api.management.task.presentation.controller;

import api.management.task.application.common.constant.OpenApiConstant;
import api.management.task.domain.model.task.TaskListSelector;
import api.management.task.domain.service.TaskService;
import api.management.task.presentation.converter.ResponseConverter;
import api.management.task.presentation.helper.TaskHelper;
import api.management.task.presentation.model.request.TaskAddRequest;
import api.management.task.presentation.model.request.TaskUpdateRequest;
import api.management.task.presentation.model.response.UserTaskListResponse;
import api.management.task.presentation.model.response.UserTaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * タスクテーブル コントローラークラス
 */
@Validated
@RestController
@RequestMapping(path = TaskController.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = TaskController.BASE_PATH, description = "タスクテーブルの操作用コントローラー")
public class TaskController {

    public static final String BASE_PATH = "/task/v1/";
    public static final String TASK_LOCATION_URI = "task/v1/users/{user-id}/tasks/{task-id}";

    private final TaskService taskService;
    private final ResponseConverter responseConverter;
    private final TaskHelper taskHelper;

    /**
     * ユーザーのタスク情報を取得する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     * @return ユーザーのタスク情報
     */
    @GetMapping(path = "/users/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ユーザーのタスクを取得")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ユーザーのタスク情報", content = @Content(
                    schema = @Schema(implementation = UserTaskResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.TASK_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> fetchUserTask(@PathVariable("userId") @Min(1) long userId,
                                           @PathVariable("taskId") @Min(1) long taskId) {
        return ResponseEntity.ok(responseConverter.convert(
                taskService.fetchUserTask(userId, taskId))
        );
    }

    /**
     * ユーザーのタスク情報一覧を取得する
     *
     * @param userId ユーザーID
     * @param offset 取得開始位置
     * @param limit  取得件数
     * @return ユーザーのタスク情報一覧
     */
    @GetMapping(path = "/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ユーザーのタスク一覧を取得")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ユーザーのタスク情報一覧", content = @Content(
                    schema = @Schema(implementation = UserTaskListResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> fetchUserTaskList(
            @PathVariable("userId") @Min(1) long userId,
            @RequestParam(name = "offset", required = false, defaultValue = "1") @Min(1) int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "20") @Range(min = 1, max = 50) int limit) {
        // GETのため 検索条件はクエリパラメータからバラバラの引数として受け取り helperで検索条件をマージしている
        // 後々 検索条件がかなり複雑になりそうであれば FacadeService経由で変換用のサービスを切ってもいいかもしれない(現状はユーザーIDだけ)
        final TaskListSelector selector = taskHelper.toSelector(userId);
        return ResponseEntity.ok(
                // DBへの検索用にServiceには取得開始位置を -1 して渡す
                responseConverter.convert(offset, taskService.fetchUserTaskList(selector, (offset - 1), limit))
        );
    }

    /**
     * タスクを追加する
     *
     * @param userId     ユーザーID
     * @param addRequest タスク追加リクエスト
     * @return Locationヘッダー に追加したタスクを取得するURIを設定し返却
     */
    @PostMapping(path = "/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "ユーザーのタスクを追加する")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = TaskAddRequest.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", ref = OpenApiConstant.TASK_INSERTED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.USER_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> registerTask(@PathVariable("userId") @Min(1) long userId,
                                          @Validated @RequestBody TaskAddRequest addRequest) {
        // タスクを登録し、登録したタスクの取得に使用するURIをLocationヘッダーに詰めて返却する
        return ResponseEntity.created(
                this.taskLocationUri(userId, taskService.registerTask(taskHelper.toRegister(userId, addRequest)))
        ).build();
    }

    /**
     * ユーザーのタスク情報を更新する
     *
     * @param userId        ユーザーID
     * @param taskId        タスクID
     * @param updateRequest タスク更新リクエスト
     * @return 更新したタスク情報
     */
    @PutMapping(path = "/users/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ユーザーのタスクを更新")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "更新したユーザーのタスク情報", content = @Content(
                    schema = @Schema(implementation = UserTaskResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.TASK_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> updateUserTask(@PathVariable("userId") @Min(1) long userId,
                                            @PathVariable("taskId") @Min(1) long taskId,
                                            @Validated @RequestBody TaskUpdateRequest updateRequest) {
        // 更新したタスクの詳細情報を返却する
        return ResponseEntity.ok(responseConverter.convert(
                taskService.updateTask(taskHelper.toUpdater(userId, taskId, updateRequest)))
        );
    }

    /**
     * ユーザーのタスク情報を削除する
     *
     * @param userId ユーザーID
     * @param taskId タスクID
     * @return 空レスポンス
     */
    @DeleteMapping(path = "/users/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "ユーザーのタスクを削除")
    @ApiResponses({
            @ApiResponse(responseCode = "204", ref = OpenApiConstant.TASK_DELETED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> delete(@PathVariable("userId") @Min(1) long userId,
                                    @PathVariable("taskId") @Min(1) long taskId) {
        taskService.deleteTask(userId, taskId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 登録したタスクのLocationURIを返却する
     *
     * @param userId ユーザーID
     * @param taskId 更新した タスクID
     * @return LocationURI
     */
    private URI taskLocationUri(long userId, long taskId) {
        return UriComponentsBuilder.newInstance().path(TASK_LOCATION_URI)
                .buildAndExpand(userId, taskId)
                .toUri();
    }
}
