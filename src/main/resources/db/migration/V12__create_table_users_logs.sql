CREATE TABLE users_logs(
    id integer primary key auto_increment not null unique,
    login varchar(100) NOT NULL,
    operation varchar(100) NOT NULL,
    login_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
