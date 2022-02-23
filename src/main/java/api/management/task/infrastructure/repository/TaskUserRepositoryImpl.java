package api.management.task.infrastructure.repository;

import api.management.task.domain.repository.TaskUserRepository;
import api.management.task.infrastructure.mapper.TaskUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskUserRepositoryImpl implements TaskUserRepository {

    private final TaskUserMapper taskUserMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnableUserId(long userId) {
        return taskUserMapper.fetchUser(userId).isPresent();
    }
}
