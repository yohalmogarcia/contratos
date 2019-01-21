-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema contrato
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema contrato
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `contrato` DEFAULT CHARACTER SET utf8 ;
USE `contrato` ;

-- -----------------------------------------------------
-- Table `contrato`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contrato`.`clientes` (
  `id_cli` INT NOT NULL,
  `nombre_cli` VARCHAR(15) NOT NULL,
  `apellido_cli` VARCHAR(15) NOT NULL,
  `direccion_cli` VARCHAR(55) NOT NULL,
  `pago_cli` DOUBLE NOT NULL,
  `cancelado_cli` INT NULL DEFAULT 0,
  PRIMARY KEY (`id_cli`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contrato`.`servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contrato`.`servicios` (
  `id_ser` INT NOT NULL,
  `nombre_ser` VARCHAR(40) NOT NULL,
  `precio_ser` DOUBLE NOT NULL,
  `fecha_ser` DATE NOT NULL,
  `descuento_ser` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id_ser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contrato`.`detalles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contrato`.`detalles` (
  `id_det` INT NOT NULL,
  `total_det` DOUBLE NOT NULL,
  `clientes_id_cli` INT NOT NULL,
  `servicios_id_ser` INT NOT NULL,
  PRIMARY KEY (`id_det`, `clientes_id_cli`, `servicios_id_ser`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
