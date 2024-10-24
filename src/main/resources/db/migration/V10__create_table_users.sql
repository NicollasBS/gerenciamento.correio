CREATE TABLE users(
    id integer primary key auto_increment not null unique,
    login varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    role varchar(15) NOT NULL
);
