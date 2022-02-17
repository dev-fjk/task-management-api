-- ユーザー情報テーブル
CREATE TABLE task_user
(
    user_id      bigint       not null auto_increment comment 'ユーザーID',
    last_name    varchar(30)  not null comment '苗字',
    first_name   varchar(30)  not null comment '名前',
    mail_address varchar(256) not null comment 'メールアドレス',
    password     varchar(64)  not null comment 'sha256でハッシュ化されたパスワード',
    created_at   timestamp    not null default current_timestamp comment '作成日時',
    created_src  varchar(30)  not null comment '作成者',
    updated_at   timestamp    not null default current_timestamp comment '更新日時',
    updated_src  varchar(30)  not null comment '更新者',
    PRIMARY KEY (user_id)
);