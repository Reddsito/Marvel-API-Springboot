-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2023 a las 07:23:02
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `devmarveldb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `granted_permission`
--

CREATE TABLE `granted_permission` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `granted_permission`
--

INSERT INTO `granted_permission` (`id`, `permission_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 1, 2),
(7, 2, 2),
(8, 3, 2),
(9, 4, 2),
(10, 5, 2),
(11, 6, 2),
(12, 7, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permission`
--

CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permission`
--

INSERT INTO `permission` (`id`, `name`) VALUES
(1, 'character:read-all'),
(2, 'character:read-detail'),
(3, 'comic:read-all'),
(4, 'comic:read-by-id'),
(5, 'user-interaction:read-my-interactions'),
(6, 'user-interaction:read-all'),
(7, 'user-interaction:read-by-username');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` enum('ADMIN','CUSTOMER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'CUSTOMER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`account_expired`, `account_locked`, `credentials_expired`, `enabled`, `id`, `role_id`, `password`, `username`) VALUES
(b'0', b'0', b'0', b'1', 1, 1, '$2a$10$ZK9Y2UfgLivxy37YPiAQWOLMsujY3XJHkRXGYz4Cv7trCZLPLqudi', 'Enrique'),
(b'0', b'0', b'0', b'1', 2, 2, '$2a$10$juOXaule5VGy1KogEFCu5eFBSmZ54Wv0x1iIbaN7TpcouueD1epKy', 'jossd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_interaction_log`
--

CREATE TABLE `user_interaction_log` (
  `id` bigint(20) NOT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `http_method` varchar(255) DEFAULT NULL,
  `remote_address` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_interaction_log`
--

INSERT INTO `user_interaction_log` (`id`, `timestamp`, `url`, `http_method`, `remote_address`, `username`) VALUES
(1, '2023-11-14 00:22:11.000000', 'http://localhost:8080/api/v1/characters/1017100', 'GET', '0:0:0:0:0:0:0:1', 'Enrique');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `granted_permission`
--
ALTER TABLE `granted_permission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKevk2nql3oc417rlr4mt8gsjpx` (`permission_id`),
  ADD KEY `FKm4v6hvxf7972y0liwhbfe7x6a` (`role_id`);

--
-- Indices de la tabla `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- Indices de la tabla `user_interaction_log`
--
ALTER TABLE `user_interaction_log`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `granted_permission`
--
ALTER TABLE `granted_permission`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `permission`
--
ALTER TABLE `permission`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user_interaction_log`
--
ALTER TABLE `user_interaction_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `granted_permission`
--
ALTER TABLE `granted_permission`
  ADD CONSTRAINT `FKevk2nql3oc417rlr4mt8gsjpx` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  ADD CONSTRAINT `FKm4v6hvxf7972y0liwhbfe7x6a` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
