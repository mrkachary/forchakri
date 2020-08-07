DROP TABLE IF EXISTS ARTICLE;
CREATE TABLE ARTICLE (
id bigint auto_increment primary key,
slug varchar(255) not null,
title varchar(255) not null,
description varchar(255) not null,
body clob,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
insert into ARTICLE(id,slug,title,description,body,created_at,updated_at)
VALUES(1,'rukmini-learning','Rukmini Learning','Rukmini learns alot','Rukmini learns alot',current_timestamp,current_timestamp);