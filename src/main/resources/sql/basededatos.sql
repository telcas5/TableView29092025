CREATE TABLE cliente (
    id INT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    cumpleaños DATE
);

INSERT INTO cliente (id, nombre, apellido, cumpleaños) VALUES
(1, 'Ana', 'González', '1990-05-12'),
(2, 'Luis', 'Martínez', '1985-11-23'),
(3, 'Carla', 'Pérez', '1992-07-04'),
(4, 'Jorge', 'Ramírez', '1988-02-18');

DELETE FROM cliente WHERE id = ?;

INSERT INTO cliente (id, nombre, apellido, cumpleaños) VALUES (?, ?, ?, ?);