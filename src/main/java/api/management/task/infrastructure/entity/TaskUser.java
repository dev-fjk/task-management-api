package api.management.task.infrastructure.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * タスクユーザーテーブルEntity
 */
@Getter
@ToString
public class TaskUser {

    private long userId;

    private String lastName;

    private String firstName;

    private String mailAddress;

    private String password;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
