package api.management.task.infrastructure.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * タスクテーブル Entity
 */
@Getter
@ToString
public class Task {

    private long taskId;

    private long userId;

    private int statusId;

    private int priorityId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate termDate;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private LocalDateTime deletedAt;

    private String deletedBy;

    private boolean isDeleted;
}
