-- MySQL Workbench Synchronization
-- Generated: 2016-10-30 22:58
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Izcar - ITCR

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `ClimateData` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `ClimateData`.`Continent` (
  `idContinent` INT(11) NOT NULL AUTO_INCREMENT,
  `descriptionContinent` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idContinent`),
  UNIQUE INDEX `idContinent_UNIQUE` (`idContinent` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ClimateData`.`Country` (
  `idCountry` INT(11) NOT NULL AUTO_INCREMENT,
  `descriptionCountry` VARCHAR(45) NOT NULL,
  `idContinentCountry` INT(11) NOT NULL,
  PRIMARY KEY (`idCountry`),
  UNIQUE INDEX `idCountry_UNIQUE` (`idCountry` ASC),
  INDEX `fk_Country_Continent_idx` (`idContinentCountry` ASC),
  CONSTRAINT `fk_Country_Continent`
    FOREIGN KEY (`idContinentCountry`)
    REFERENCES `ClimateData`.`Continent` (`idContinent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ClimateData`.`ClimateVariable` (
  `idClimateVariable` INT(11) NOT NULL AUTO_INCREMENT,
  `descriptionClimateVariable` VARCHAR(45) NOT NULL,
  `abbreviationClimateVariable` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `idClimateVariables_UNIQUE` (`idClimateVariable` ASC),
  PRIMARY KEY (`idClimateVariable`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `ClimateData`.`MaxMinXCountry` (
  `idClimateVariable` INT(11) NOT NULL,
  `idCountry` INT(11) NOT NULL,
  `yearMaxXCountry` INT(11) NOT NULL,
  `yearMinXCountry` INT(11) NOT NULL,
  `stationMaxXCountry` VARCHAR(45) NOT NULL,
  `stationMinXCountry` VARCHAR(45) NOT NULL,
  `isMaxXContinent` INT(11) NOT NULL,
  `isMinXContinent` INT(11) NOT NULL,
  PRIMARY KEY (`idClimateVariable`, `idCountry`),
  INDEX `fk__Country1_idx` (`idCountry` ASC),
  INDEX `fk__ClimateVariables1_idx` (`idClimateVariable` ASC),
  CONSTRAINT `fk__ClimateVariables1`
    FOREIGN KEY (`idClimateVariable`)
    REFERENCES `ClimateData`.`ClimateVariable` (`idClimateVariable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk__Country1`
    FOREIGN KEY (`idCountry`)
    REFERENCES `ClimateData`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;