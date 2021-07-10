-- создаем модели образовательного центра
create table teacher
(
    id serial primary key,
    firstName varchar(20),
    lastName varchar(20),
    experience integer
);

create table course
(
    id serial primary key,
    name varchar(20),
    date varchar(21) not null,
    teacher integer,
    foreign key (teacher) references teacher(id)
);

create table coursesForTeacher
(
    teacherId integer,
    courseId integer,
    foreign key (teacherId) references teacher(id),
    foreign key (courseId) references course(id)
);

create table lesson
(
    id serial primary key,
    subject varchar(20),
    dayOfWeek varchar(9),
    course integer,
    foreign key (course) references course(id)
);

create table student
(
    id serial primary key,
    firstName varchar(20),
    lastName varchar(20),
    academicGroup varchar(20)
);

create table studentsOnCourses
(
    studentId integer,
    courseId integer,
    foreign key (studentId) references student(id),
    foreign key (courseId) references course(id)
);


-- вводим учителей в базу
insert into teacher(firstName, lastName, experience)
values('Марсель', 'Сидиков', 9);
insert into teacher(firstName, lastName, experience)
values('Михаил', 'Абрамский', 13);
insert into teacher(firstName, lastName, experience)
values('Владислава', 'Кугуракова', 20);

-- вводим курсы в базу
insert into course (name, date, teacher)
values ('Java', '01.09.2021/01.06.2022', 1);
insert into course (name, date, teacher)
values ('JavaLab', '01.09.2021/01.06.2022', 1);
insert into course (name, date, teacher)
values ('Python', '01.10.2021/31.05.2022', 2);
insert into course (name, date, teacher)
values ('DML', '01.12.2021/01.01.2022', 3);

-- делаем связь курсов и преподавателей
insert into coursesForTeacher (teacherId, courseId)
values (1, 1);
insert into coursesForTeacher (teacherId, courseId)
values (1, 2);
insert into coursesForTeacher (teacherId, courseId)
values (2, 3);
insert into coursesForTeacher (teacherId, courseId)
values (3, 4);

-- вводим занятия курсов в базу
insert into lesson (subject, dayOfWeek, course)
values ('java practice', 'monday', 1);
insert into lesson (subject, dayOfWeek, course)
values ('algorithms', 'friday', 1);
insert into lesson (subject, dayOfWeek, course)
values ('java hard', 'tuesday', 2);
insert into lesson (subject, dayOfWeek, course)
values ('database practice', 'sunday', 2);
insert into lesson (subject, dayOfWeek, course)
values ('python practice', 'monday', 3);
insert into lesson (subject, dayOfWeek, course)
values ('python practice', 'friday', 3);
insert into lesson (subject, dayOfWeek, course)
values ('watch Twitch', 'tuesday', 4);
insert into lesson (subject, dayOfWeek, course)
values ('watch Youtube', 'sunday', 4);

-- вводим учащихся в базу
insert into student (firstName, lastName, academicGroup)
values ('Даниял', 'Салахов', '11-001');
insert into student (firstName, lastName, academicGroup)
values ('Эмиль', 'Мифтахов', '11-005');
insert into student (firstName, lastName, academicGroup)
values ('Софья', 'Кургускина', '11-004');
insert into student (firstName, lastName, academicGroup)
values ('Василий', 'Пупкин', '11-028');
insert into student (firstName, lastName, academicGroup)
values ('Диляра', 'Сахибгареева', '11-010');
insert into student (firstName, lastName, academicGroup)
values ('Олег', 'Лупкин', '11-042');

-- делаем связь студентов и курсов
insert into studentsOnCourses (studentId, courseId)
values (1,1);
insert into studentsOnCourses (studentId, courseId)
values (1,2);
insert into studentsOnCourses (studentId, courseId)
values (2,1);
insert into studentsOnCourses (studentId, courseId)
values (2,2);
insert into studentsOnCourses (studentId, courseId)
values (3,3);
insert into studentsOnCourses (studentId, courseId)
values (4,3);
insert into studentsOnCourses (studentId, courseId)
values (5,4);
insert into studentsOnCourses (studentId, courseId)
values (6,4);


