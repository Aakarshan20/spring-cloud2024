create database db2024;

use db2024;

create table payment(
    id bigint(20) unsigned not null auto_increment primary key comment 'ID',
    serial varchar(200) DEFAULT '',
    updated_at datetime default current_timestamp,
    created_at datetime default current_timestamp
)engine = innoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8


