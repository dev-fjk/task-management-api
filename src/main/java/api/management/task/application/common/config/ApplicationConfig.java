package api.management.task.application.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Clock;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class ApplicationConfig {

    // 日本向けTimeZoneの設定
    private static final String JP_TIME_ZONE = "Asia/Tokyo";

    /**
     * 日本標準時を持つClockのDI生成
     *
     * @return Clock
     */
    @Bean(name = "clock")
    public Clock clock() {
        return Clock.system(ZoneId.of(JP_TIME_ZONE));
    }

    /**
     * ObjectMapperのDI注入
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 空Jsonの作成時に例外を発生させない
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // java8の日付クラスへの変換時に例外を発生させない設定
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
