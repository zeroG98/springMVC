create table tbl_board(
	bid int not null auto_increment,
	subject varchar(100) not null,
	contents varchar(3000) not null,
	hit int,
	writer varchar(30) not null,
	regDate datetime default now() not null,
	primary key(bid)
);

insert into tbl_board(subject, contents, hit, writer) values('테스트 ~~~~', '테스트', 0, '운영자');

select * from tbl_board;

drop table tbl_board;