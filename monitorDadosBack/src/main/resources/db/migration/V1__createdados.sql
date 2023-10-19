create table dados (
                       id int auto_increment not null primary key,
                       momento timestamp not null,
                       tipo varchar(50) not null,
                       valor varchar(50) not null
)