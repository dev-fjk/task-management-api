package api.management.task.application.common.config;

import api.management.task.application.common.constant.OpenApiConstant;
import api.management.task.presentation.model.response.ProblemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * SpringDocで生成される RestApi仕様書に関する設定
 */
@Configuration
public class OpenApiConfig {

    /**
     * ジェネレートされたOpenApi定義の拡張設定
     *
     * @param objectMapper : objectMapper
     * @return OpenApiCustomiser
     */
    @Bean
    public OpenApiCustomiser openApiCustomiser(ObjectMapper objectMapper) {
        return openApi -> {
            addSchemas(openApi.getComponents());
            addResponses(openApi.getComponents(), addHeaders(), objectMapper);
        };
    }

    /**
     * Schema定義の追加
     *
     * @param components : コンポーネント
     */
    private void addSchemas(Components components) {
        var schemas = ModelConverters.getInstance().read(ProblemResponse.class);
        schemas.forEach(components::addSchemas);
    }

    /**
     * ヘッダー定義
     *
     * @return ヘッダー取得用のキー値とヘッダーの組み合わせ
     */
    private Map<String, Header> addHeaders() {

        // ヘッダー情報一覧を定義
        Map<String, HeaderInfo> headers = new HashMap<>();
        headers.put(OpenApiConstant.TASK_INSERT_HEADER_EXAMPLE_KEY,
                HeaderInfo.builder().example("task/v1/users/10/tasks/100")
                        .description("登録したタスクを取得するURI情報")
                        .type("string").build()
        );

        return headers.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {
            Header header = new Header();
            header.description(e.getValue().getDescription());
            header.setSchema(this.headerSchema(e.getValue()));
            return header;
        }));
    }

    /**
     * ヘッダーに設定するスキーマの作成
     *
     * @param headerInfo ヘッダー情報
     * @return ヘッダーに設定するスキーマ
     */
    private Schema<?> headerSchema(HeaderInfo headerInfo) {
        Schema<?> schema = new Schema<>();
        schema.setType(headerInfo.getType());
        schema.setExample(headerInfo.getExample());
        schema.description(headerInfo.getDescription());
        return schema;
    }

    /**
     * レスポンス定義を追加する
     *
     * @param components   : コンポーネント
     * @param objectMapper : ObjectMapper
     */
    private void addResponses(Components components, Map<String, Header> headers, ObjectMapper objectMapper) {

        var badRequestContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたパラメータは正しくありません")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail("userIdは必須項目です")
                .build()
        );
        var unauthorizedContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("認証に失敗しました")
                .status(HttpStatus.UNAUTHORIZED.value())
                .detail("認証情報が付与されていません")
                .build()
        );
        var forbiddenContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("アクセスが拒否されました")
                .status(HttpStatus.FORBIDDEN.value())
                .detail("アクセスする権限がありません")
                .build()
        );
        var taskNotFound = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたリソースは見つかりませんでした")
                .status(HttpStatus.NOT_FOUND.value())
                .detail("タスク情報が見つかりませんでした")
                .build()
        );
        var userNotFound = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたリソースは見つかりませんでした")
                .status(HttpStatus.NOT_FOUND.value())
                .detail("ユーザーが見つかりませんでした ユーザーID: " + 10)
                .build()
        );
        var internalServerErrorContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされた操作を完了できませんでした")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .detail("データベースでエラーが発生しました")
                .build()
        );

        components.addResponses(OpenApiConstant.BAD_REQUEST, new ApiResponse()
                        .description("リクエストパラメータが不正").content(badRequestContent))
                .addResponses(OpenApiConstant.UNAUTHORIZED, new ApiResponse()
                        .description("認証情報がリクエストに付与されていない").content(unauthorizedContent))
                .addResponses(OpenApiConstant.FORBIDDEN, new ApiResponse()
                        .description("許可されていないアクセス").content(forbiddenContent))
                .addResponses(OpenApiConstant.TASK_NOT_FOUND, new ApiResponse()
                        .description("タスクが見つからない").content(taskNotFound))
                .addResponses(OpenApiConstant.USER_NOT_FOUND, new ApiResponse()
                        .description("ユーザーが見つからない").content(userNotFound))
                .addResponses(OpenApiConstant.TASK_INSERTED_SUCCESS, new ApiResponse()
                        .description("タスクが正常に追加できた")
                        .headers(Map.of("location", headers.get(OpenApiConstant.TASK_INSERT_HEADER_EXAMPLE_KEY)))
                ).addResponses(OpenApiConstant.TASK_UPDATED_SUCCESS, new ApiResponse()
                        .description("正常に更新できた"))
                .addResponses(OpenApiConstant.TASK_DELETED_SUCCESS, new ApiResponse()
                        .description("正常に削除できた"))
                .addResponses(OpenApiConstant.INTERNAL_SERVER_ERROR, new ApiResponse()
                        .description("処理が正常に終了しなかった").content(internalServerErrorContent));
    }

    /**
     * ProblemResponseのコンテンツを作成する
     *
     * @param objectMapper : ObjectMapper
     * @param response     : 異常時のレスポンス定義クラス
     * @return Content
     */
    private Content problemContent(ObjectMapper objectMapper, ProblemResponse response) {
        try {
            final String example = objectMapper.writeValueAsString(response);
            var schema = new Schema<ProblemResponse>().$ref("ProblemResponse");
            return new Content().addMediaType(MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    new io.swagger.v3.oas.models.media.MediaType().schema(schema).example(example));
        } catch (Exception exception) {
            throw new IllegalStateException("Swaggerのコンテンツ作成に失敗しました", exception);
        }
    }

    /**
     * ヘッダー情報の保持用
     */
    @Data
    @Builder
    public static class HeaderInfo {
        private String example;
        private String description;
        private String type;
    }
}
