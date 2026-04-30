DROP DATABASE IF EXISTS `TasksMaster`;
CREATE DATABASE `TasksMaster`;
USE `TasksMaster`;

CREATE TABLE `Estados` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('Pendiente', 'En Progreso', 'Completada', 'Cancelada') NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE `Usuarios` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE `Categorias` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE `Tareas` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_limite DATETIME,
    estado_id INT NOT NULL,
    usuario_propietario_id INT NOT NULL,
    categoria_id INT NOT NULL,
    observaciones TEXT,
    FOREIGN KEY (estado_id) REFERENCES `Estados`(id),
    FOREIGN KEY (usuario_propietario_id) REFERENCES `Usuarios`(id),
    FOREIGN KEY (categoria_id) REFERENCES `Categorias`(id)
);

-- --------------------------------------------------------
-- 1. Insertar Estados
-- --------------------------------------------------------
INSERT INTO `Estados` (`nombre`, `descripcion`) VALUES
('Pendiente', 'La tarea ha sido creada pero aún no se ha empezado a trabajar en ella.'),
('En Progreso', 'Alguien está trabajando activamente en esta tarea.'),
('Completada', 'La tarea ha sido finalizada con éxito.'),
('Cancelada', 'La tarea fue descartada y ya no se realizará.');

-- --------------------------------------------------------
-- 2. Insertar Usuarios
-- --------------------------------------------------------
INSERT INTO `Usuarios` (`nombre`, `email`, `password`) VALUES
('Ana Martínez', 'ana.martinez@email.com', '$2y$10$simulatedhashforana12345'),
('Carlos Gómez', 'carlos.gomez@email.com', '$2y$10$simulatedhashforcarlos67'),
('Laura Torres', 'laura.torres@email.com', '$2y$10$simulatedhashforlaura890');

-- --------------------------------------------------------
-- 3. Insertar Categorías
-- --------------------------------------------------------
INSERT INTO `Categorias` (`nombre`, `descripcion`) VALUES
('Trabajo', 'Tareas relacionadas con el entorno laboral y proyectos profesionales.'),
('Hogar', 'Mantenimiento del hogar, compras y organización doméstica.'),
('Desarrollo Personal', 'Cursos, lectura, ejercicio y hobbies.'),
('Finanzas', 'Pagos de facturas, impuestos y gestión del presupuesto.');

-- --------------------------------------------------------
-- 4. Insertar Tareas
-- --------------------------------------------------------
INSERT INTO `Tareas` (`titulo`, `descripcion`, `fecha_limite`, `estado_id`, `usuario_propietario_id`, `categoria_id`, `observaciones`) VALUES
('Preparar presentación mensual', 'Crear las diapositivas para la reunión de resultados de ventas.', '2026-03-05 10:00:00', 2, 1, 1, 'Pedirle a marketing los gráficos actualizados.'),
('Hacer la compra de la semana', 'Comprar verduras, frutas, leche y productos de limpieza.', '2026-02-28 18:00:00', 1, 2, 2, 'No olvidar comprar el detergente para ropa oscura.'),
('Terminar curso de SQL', 'Completar el módulo de subconsultas y JOINs avanzados.', NULL, 3, 3, 3, 'Conseguido el certificado con nota máxima.'),
('Pagar el seguro del coche', 'Hacer la transferencia para la renovación anual del seguro.', '2026-03-01 23:59:59', 4, 1, 4, 'Cancelada porque cambié de compañía aseguradora.'),
('Revisar código de la nueva app', 'Hacer code review de la rama feature/login.', '2026-02-27 15:30:00', 1, 2, 1, NULL);
