CREATE
    TABLE user_users
(
    id                    UUID                           NOT NULL,
    date                  TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    email                 VARCHAR(255)                   NOT NULL,
    password_hash         VARCHAR(255)                   NOT NULL,
    status                VARCHAR(16)                    NOT NULL,
    confirm_token_token   VARCHAR(255)                   DEFAULT NULL,
    confirm_token_expires TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,

    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX UNIQ_USER_USERS_EMAIL ON user_users (email);