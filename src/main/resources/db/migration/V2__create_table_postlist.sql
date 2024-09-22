create table post_list_itens(
    id integer primary key auto_increment not null unique ,
    sender varchar(30) not null,
    recipient varchar(30) not null,
    tracking_code varchar(20) unique not null ,
    om varchar(12) not null ,
    arrival_day date not null,
    departure_day date,
    out_verify boolean not null
);