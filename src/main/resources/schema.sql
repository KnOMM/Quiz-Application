
create table question
(
    id              bigint           not null auto_increment primary key,
    topic           varchar(255),
    difficulty_rank int,
    content         varchar(255) not null
);

create table if not exists response
(
    id          bigint           not null auto_increment primary key,
    text        varchar(255) not null,
    is_correct     boolean,
    question_id bigint,
    foreign key (question_id) references question (id) on delete set null
);


