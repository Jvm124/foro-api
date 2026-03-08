create table perfiles(
                       id bigint not null auto_increment,
                       nombre varchar(100) not null,

                       primary key(id)
);

create table usuarios(
                        id bigint not null auto_increment,
                        nombre varchar(100) not null,
                        correo_electronico varchar(100) not null unique,
                        contrasenia varchar(255) not null,
                        perfil_id bigint not null,

                        primary key(id),
                        constraint fk_usuarios_perfil_id foreign key(perfil_id) references perfiles(id)
);

create table cursos(
                      id bigint not null auto_increment,
                      nombre varchar(100) not null,
                      categoria varchar(100) not null,

                      primary key(id)
);

create table topicos(
                       id bigint not null auto_increment,
                       titulo varchar(100) not null unique,
                       mensaje varchar(255) not null unique,
                       fecha_creacion datetime not null,
                       status tinyInt,
                       autor_id bigint not null,
                       curso_id bigint not null,

                       primary key(id),
                       constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id),
                       constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
);

create table respuestas(
                          id bigint not null auto_increment,
                          mensaje varchar(255) not null,
                          fecha_creacion datetime not null,
                          solucion varchar(100) not null,
                          topico_id bigint not null,
                          autor_id bigint not null,

                          primary key(id),
                          constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
                          constraint fk_respuestas_autor_id foreign key(autor_id) references usuarios(id)
);