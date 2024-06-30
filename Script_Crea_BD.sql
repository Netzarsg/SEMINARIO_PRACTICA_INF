-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema controlprodbd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema controlprodbd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `controlprodbd` DEFAULT CHARACTER SET utf8mb4 ;
USE `controlprodbd` ;

-- -----------------------------------------------------
-- Table `controlprodbd`.`insumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlprodbd`.`insumo` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `controlprodbd`.`ordentrabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlprodbd`.`ordentrabajo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `NumeroOrden` INT(11) NOT NULL,
  `Descripcion` VARCHAR(255) NOT NULL,
  `FechaInicio` DATE NOT NULL,
  `FechaFin` DATE NULL DEFAULT NULL,
  `usuario` VARCHAR(50) NOT NULL,
  `estado` VARCHAR(50) NOT NULL DEFAULT 'Abierta',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `controlprodbd`.`tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlprodbd`.`tarea` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `OrdenTrabajoId` INT(11) NOT NULL,
  `NumeroOrdenTarea` INT(11) NOT NULL,
  `Descripcion` VARCHAR(255) NOT NULL,
  `FechaInicio` DATE NOT NULL,
  `FechaCierre` DATE NULL DEFAULT NULL,
  `Completada` TINYINT(1) NOT NULL,
  `UsuarioResponsable` VARCHAR(50) NULL DEFAULT NULL,
  `Insumo1Id` INT(11) NULL DEFAULT NULL,
  `Insumo2Id` INT(11) NULL DEFAULT NULL,
  `CantidadInsumo1` INT(11) NULL DEFAULT NULL,
  `CantidadInsumo2` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `OrdenTrabajoId` (`OrdenTrabajoId` ASC) VISIBLE,
  INDEX `Insumo1Id` (`Insumo1Id` ASC) VISIBLE,
  INDEX `Insumo2Id` (`Insumo2Id` ASC) VISIBLE,
  CONSTRAINT `tarea_ibfk_1`
    FOREIGN KEY (`OrdenTrabajoId`)
    REFERENCES `controlprodbd`.`ordentrabajo` (`id`),
  CONSTRAINT `tarea_ibfk_2`
    FOREIGN KEY (`Insumo1Id`)
    REFERENCES `controlprodbd`.`insumo` (`Id`),
  CONSTRAINT `tarea_ibfk_3`
    FOREIGN KEY (`Insumo2Id`)
    REFERENCES `controlprodbd`.`insumo` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `controlprodbd`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlprodbd`.`usuario` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` VARCHAR(50) NOT NULL,
  `contrasena` VARCHAR(50) NOT NULL,
  `rol` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `nombreUsuario` (`nombreUsuario` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
