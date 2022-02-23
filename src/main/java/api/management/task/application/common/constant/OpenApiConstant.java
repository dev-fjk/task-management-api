package api.management.task.application.common.constant;

/**
 * Swagger uiの定義に使用するキー値を格納するクラス
 */
public class OpenApiConstant {

    // エラー系レスポンスのコンテンツのキー値
    public static final String BAD_REQUEST = "badRequest";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String FORBIDDEN = "forbidden";
    public static final String TASK_NOT_FOUND = "taskNotFound";
    public static final String USER_NOT_FOUND = "userNotFound";
    public static final String INTERNAL_SERVER_ERROR = "internalServerError";

    // リソース更新系のキー値
    public static final String TASK_INSERTED_SUCCESS = "taskInsertedSuccessfully";
    public static final String TASK_UPDATED_SUCCESS = "taskUpdatedSuccessfully";
    public static final String TASK_DELETED_SUCCESS = "taskDeletedSuccessfully";

    // ヘッダーのサンプル値取得用キー
    public static final String TASK_INSERT_HEADER_EXAMPLE_KEY = "taskInsertHeaderExample";
}