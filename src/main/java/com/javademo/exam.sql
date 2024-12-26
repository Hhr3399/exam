create table college
(
    id           int auto_increment comment '学院id'
        primary key,
    college_name varchar(15) not null comment '学院名称'
)
    comment '学院表';

create table course
(
    id          int auto_increment comment '课程id'
        primary key,
    course_name varchar(15) not null comment '课程名称'
)
    comment '课程';

create table question
(
    id           int auto_increment comment '题目id'
        primary key,
    content      varchar(188) null comment '题目内容',
    answer       varchar(50)  null,
    single_score int          null comment '单道题目得分',
    course_id    int          null comment '所属课程id'
)
    comment '题目表';

create table student
(
    id           int auto_increment comment '学生表主键id'
        primary key,
    username     varchar(15)                   not null comment '用户名',
    password     varchar(100) default '123456' not null,
    student_name varchar(15)                   not null comment '姓名',
    gender       varchar(5)                    not null comment ' 性别',
    phonenumber  char(11)                      not null comment '电话号码',
    sid          char(13)                      not null comment '学号',
    college_id   int                           null,
    constraint student_college_id_fk
        foreign key (college_id) references college (id),
    constraint check_phonenumber
        check (regexp_like(`phonenumber`, _utf8mb4\'^[0-9]{11}$\')),
    constraint check_sid
        check (regexp_like(`sid`,_utf8mb4\'^[0-9]{13}$\'))
)
    comment ' 学生表 ';

create table student_course
(
    id    int auto_increment comment '中间表id'
        primary key,
    s_id  int      not null,
    c_id  int      not null,
    score int      not null comment '课程分数',
    stime datetime null comment '考试开始时间',
    etime datetime null comment '考试结束时间',
    constraint student_course_course_id_fk
        foreign key (c_id) references course (id),
    constraint student_course_student_id_fk
        foreign key (s_id) references student (id)
);

create table teacher
(
    id          int auto_increment comment '教师id'
        primary key,
    username    varchar(15)                   not null comment '用户名',
    password    varchar(200) default '123456' not null comment '密码',
    name        varchar(15)                   null comment '教师姓名',
    gender      int                           null comment '性别  1：男  2：女',
    phonenumber varchar(15)                   null comment '手机号',
    tid         varchar(15)                   null comment '工号',
    college_id  int                           null comment '学院id',
    course_id   int                           null comment '教授课程id',
    constraint teacher_college_id_fk
        foreign key (college_id) references college (id),
    constraint teacher_course_id_fk
        foreign key (course_id) references course (id),
    constraint check_tphonenumber
        check (regexp_like(`phonenumber`, _utf8mb4\'^[0-9]{11}$\'))
)
    comment ' 教师表 ';

create definer = root@localhost view student_college_view as
select `dbexam`.`student`.`id`           AS `id`,
       `dbexam`.`student`.`student_name` AS `student_name`,
       `dbexam`.`student`.`sid`          AS `sid`,
       `dbexam`.`college`.`college_name` AS `college_name`
from `dbexam`.`student`
         join `dbexam`.`college`
where (`dbexam`.`student`.`college_id` = `dbexam`.`college`.`id`);

-- comment on column student_college_view.id not supported: 学生表主键id

-- comment on column student_college_view.student_name not supported: 姓名

-- comment on column student_college_view.sid not supported: 学号

-- comment on column student_college_view.college_name not supported: 学院名称

create definer = root@localhost view teacher_college_view as0
select `dbexam`.`teacher`.`id`           AS `id`,
       `dbexam`.`teacher`.`name`         AS `name`,
       `dbexam`.`college`.`college_name` AS `college_name`,
       `dbexam`.`course`.`course_name`   AS `course_name`
from `dbexam`.`teacher`
         join `dbexam`.`college`
         join `dbexam`.`course`
where ((`dbexam`.`teacher`.`college_id` = `dbexam`.`college`.`id`) and
       (`dbexam`.`teacher`.`course_id` = `dbexam`.`course`.`id`));

-- comment on column teacher_college_view.id not supported: 教师id

-- comment on column teacher_college_view.name not supported: 教师姓名

-- comment on column teacher_college_view.college_name not supported: 学院名称

-- comment on column teacher_college_view.course_name not supported: 课程名称

