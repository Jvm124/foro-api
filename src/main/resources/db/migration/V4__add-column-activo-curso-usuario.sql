ALTER TABLE cursos ADD COLUMN activo tinyInt;
ALTER TABLE usuarios ADD COLUMN activo tinyInt;
UPDATE cursos set activo = 1;
UPDATE usuarios set activo = 1;