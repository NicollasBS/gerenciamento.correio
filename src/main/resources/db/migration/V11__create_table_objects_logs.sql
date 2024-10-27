CREATE TABLE objects_logs(
    id integer primary key auto_increment not null unique,
    login varchar(100) NOT NULL,
    object_id integer NOT NULL,
    type varchar(15) NOT NULL,
    operation varchar(25) NOT NULL,
    at_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
