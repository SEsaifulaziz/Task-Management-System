-- Baseline: the tasks table as defined by TaskEntity.
CREATE TABLE tasks (
    id          BIGINT        NOT NULL AUTO_INCREMENT,
    title       VARCHAR(150)  NOT NULL,
    description VARCHAR(2000),
    status      VARCHAR(20),
    priority    VARCHAR(20),
    due_date    DATE,
    created_at  DATETIME(6)   NOT NULL,
    PRIMARY KEY (id)
);
