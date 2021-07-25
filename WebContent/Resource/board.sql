
create table board(
	no int primary key auto_increment,
    writer varchar(20),
    email varchar(50),
    subject varchar(50),
    password varchar(10),
    reg_date date,
    ref int,
    re_step int,
    re_level int,
    readcount int,
    content varchar(500)
    );