CREATE TABLE marks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    subject VARCHAR(255) NOT NULL,
    marks INT NOT NULL,
    grade VARCHAR(50),
    CONSTRAINT fk_marks_student
        FOREIGN KEY (student_id)
        REFERENCES student(id)
);
