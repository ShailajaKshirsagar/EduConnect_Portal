

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    deletedat TIMESTAMP NULL,
    role VARCHAR(50),
    profilephotopath VARCHAR(500)
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fname VARCHAR(100),
    lname VARCHAR(100),
    year VARCHAR(20),
    age INT,
    department VARCHAR(100),
    user_id BIGINT,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT,
    price DOUBLE
);

CREATE TABLE fee_payment (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    amount DOUBLE,
    paymentdate DATE,
    paymentmode VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE blog_post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT,
    created_at TIMESTAMP NULL,
    updated_at TIMESTAMP NULL,
    CONSTRAINT fk_blogpost_user FOREIGN KEY (author_id) REFERENCES user(id)
);

CREATE TABLE blog_comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    user_id BIGINT,
    post_id BIGINT,
    createdat TIMESTAMP NULL,
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES blog_post(post_id)
);
