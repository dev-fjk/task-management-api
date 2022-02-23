package api.management.task.infrastructure.mapper;

import api.management.task.infrastructure.entity.TaskUser;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ユーザーテーブル向けのMapper IF
 */
@Mapper
public interface TaskUserMapper {

    /**
     * ユーザー情報を取得する
     *
     * @param userId ユーザーID
     * @return ユーザー情報
     */
    Optional<TaskUser> fetchUser(@Param("userId") long userId);

}
