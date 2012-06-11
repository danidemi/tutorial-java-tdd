CREATE TABLE TDD_USER(
	id BIGINT IDENTITY,
	username VARCHAR(32) UNIQUE,
	password VARCHAR(32),
	is_locked BOOLEAN
)

DROP TABLE TDD_USER; 

INSERT INTO TDD_USER (username,password,is_locked)
VALUES('Zak', 'pwd', true);

UPDATE TDD_USER SET username='',password='',is_locked=true WHERE id=1;

SELECT * FROM TDD_USER;

SELECT * FROM TDD_USER WHERE username='John';

{call IDENTITY};