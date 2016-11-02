USE `ClimateData`;
DROP procedure IF EXISTS `insertStatistic`;

DELIMITER $$
USE `ClimateData`$$
CREATE PROCEDURE `insertStatistic` (pIdCountry int, pIdClimateVariable int ,
									pYearMaxXCountry int, pYearMinXCountry int, 
									pStationMaxXCountry varchar(45), pStationMinXCountry varchar(45))
BEGIN
	INSERT INTO MaxMinXCountry(idClimateVariable, idCountry, yearMaxXCountry, yearMinXCountry,
								stationMaxXCountry, stationMinXCountry, isMaxXContinent, isMinXContinent)
	VALUES (pIdClimateVariable, pIdCountry, pYearMaxXCountry, pYearMinXCountry,
			pStationMaxXCountry, pStationMinXCountry, 0, 0);
END$$

DELIMITER ;