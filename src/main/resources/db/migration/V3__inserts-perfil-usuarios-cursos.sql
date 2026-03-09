
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO perfiles (id, nombre) VALUES
                                      (1, 'ESTUDIANTE'),
                                      (2, 'INSTRUCTOR'),
                                      (3, 'MODERADOR'),
                                      (4, 'ADMINISTRADOR')
    ON DUPLICATE KEY UPDATE nombre=VALUES(nombre);


INSERT INTO usuarios (id, nombre, correo_electronico, contrasenia, perfil_id) VALUES
                                                                                  (1, 'Jose Alberto', 'jose.alberto@email.com', '$2a$12$12daVK4W8Oeo5K4srTXgyu/QzE2K54Gm36inpN9uDJ8y7CO5x46wy', 1),
                                                                                  (2, 'Maria Garcia', 'm.garcia@forohub.com', '$2a$12$axCVzKo7DTgwIeXxfIH1z.LDfHIG/ysGHQd26WdM1mstYR712K8wq', 2),
                                                                                  (3, 'Admin General', 'admin@forohub.com', '$2a$12$esHIThgM.Iks6W1ePssUrO22WNSL5FsxM0Q77gtb4K507o9/2QDIu', 4),
                                                                                  (4, 'Carlos Perez', 'carlos.p@clases.com', '$2a$12$9aUWcqP552RQgaGoIO9npO7e1TlXfokbpeIwQoes8nft7UXiWtt/.', 1)
    ON DUPLICATE KEY UPDATE correo_electronico=VALUES(correo_electronico);

INSERT INTO cursos (nombre, categoria) VALUES
                                           ('Java Orientado a Objetos', 'Programación'),
                                           ('Spring Boot 3', 'Frameworks'),
                                           ('MySQL Avanzado', 'Bases de Datos'),
                                           ('HTML5 y CSS3', 'Front-end'),
                                           ('Soft Skills en IT', 'Habilidades Blandas');

SET FOREIGN_KEY_CHECKS = 1;