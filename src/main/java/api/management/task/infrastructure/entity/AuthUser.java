package api.management.task.infrastructure.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * 認証テーブル エンティティ
 */
@Getter
@ToString
public class AuthUser {

    private String loginId;

    private String password;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
