"# spring-procedure" 

Database mysql;
```
mysql -u root -p
```

1. Create database
```
CREATE DATABASE test_api;
```
1. Create user (if it doesn't exist)
```
CREATE USER spring@localhost IDENTIFIED BY 'secret';
GRANT ALL PRIVILEGES ON *.* TO 'spring'@'localhost' WITH GRANT OPTION;

##

SELECT * from user;

drop procedure find_user_by_name;

call FIND_USER_BY_NAME("sri");

DELIMITER $$
CREATE DEFINER=`spring`@`localhost` PROCEDURE `FIND_USER_BY_NAME`(IN u_name char(10))
  begin
    SELECT ID, name, email
    FROM user
    WHERE name = u_name;
  end
$$
DELIMITER ;