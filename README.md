# dynamic-datasource-boot-sample

```mysql
CREATE DATABASE test_0;
CREATE DATABASE test_1;
CREATE DATABASE test_2;

CREATE TABLE `test_0`.`hibernate_sequences`
(
    `sequence_name` varchar(255) DEFAULT NULL,
    `next_val`      int(11)      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `test_0`.`t_user`
(
    `id`   int(0)       NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `test_0`.`t_admin`
(
    `id`      int(0)       NOT NULL,
    `name`    varchar(255) NOT NULL DEFAULT '',
    `address` varchar(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `test_1`.`t_user`
(
    `id`   int(0)       NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `test_2`.`t_user`
(
    `id`   int(0)       NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
```

```shell
curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/create'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/get'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/createAdmin'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/getAdmin'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/datasource/add1'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/datasource/add2'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/create1'

curl -H 'content-type: application/json, charset=utf-8' 'http://localhost:8080/create2'
```