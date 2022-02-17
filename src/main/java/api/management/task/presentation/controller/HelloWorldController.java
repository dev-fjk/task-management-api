package api.management.task.presentation.controller;

import api.management.task.application.common.constant.OpenApiConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = HelloWorldController.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = HelloWorldController.BASE_PATH, description = "動作チェック用コントローラ")
public class HelloWorldController {


    public static final String BASE_PATH = "mock/v1/";

    @GetMapping(path = "/hello")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "動作確認要エンドポイント")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Hello world",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE)
            ),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public String get() {
        return "Hello World";
    }

}
