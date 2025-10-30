CREATE TABLE activity_logs(

    logid BIGINT AUTO_INCREMENT PRIMARY KEY,

    userid BIGINT ,

    username VARCHAR(255),

    httpmethod VARCHAR(255) ,

    endpoint VARCHAR(255) ,

    ipaddress VARCHAR(255),

    timestamp TIMESTAMP NULL,

    executiontime VARCHAR(255)

);