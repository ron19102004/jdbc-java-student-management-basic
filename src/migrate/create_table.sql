create table grades(
    id bigint primary key auto_increment,
    name varchar(255) not null
);

create table students(
    id bigint primary key auto_increment,
    name varchar(255) not null,
    phone varchar(10) unique not null ,
    address text,
    age int,
    gender int,
    grade_id bigint not null,
    foreign key(grade_id) references grades(id)
)