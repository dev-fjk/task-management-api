package api.management.task.domain.repository;

/**
 * ユーザー情報のリポジトリ
 */
public interface TaskUserRepository {

    /**
     * 有効なユーザーのユーザーIDかチェックする
     *
     * @param userId ユーザーID
     * @return チェック結果 trueで有効なユーザー
     */
    boolean isEnableUserId(long userId);
}
