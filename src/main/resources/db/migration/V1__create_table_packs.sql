create table packs(
    id integer primary key auto_increment not null unique ,
    sender varchar(30) not null,
    recipient varchar(30) not null,
    om varchar(12) not null ,
    tracking_code varchar(20) unique not null ,
    arrival_day date not null,
    delivery_day date,
    delivered_to varchar(30),
    delivered boolean not null
);