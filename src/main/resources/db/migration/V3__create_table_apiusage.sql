CREATE TABLE api_usage (
    apiusageid BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid BIGINT,
    apirequestcount BIGINT NOT NULL,
    usagedate DATE,
    createdat TIMESTAMP,
    updatedat TIMESTAMP,
    CONSTRAINT fk_api_usage_user FOREIGN KEY (userid) REFERENCES user(id)
);
