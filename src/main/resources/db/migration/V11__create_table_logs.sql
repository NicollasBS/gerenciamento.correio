CREATE TABLE logs(
    id integer primary key auto_increment not null unique,
    login varchar(100) NOT NULL UNIQUE,
    object_id integer NOT NULL,
    type varchar(15) NOT NULL,
    operation varchar(25) NOT NULL,
    FOREIGN KEY (object_id) REFERENCES users(id)
);
