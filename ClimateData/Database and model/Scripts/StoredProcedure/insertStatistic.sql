USE `ClimateData`;
DROP procedure IF EXISTS `insertStatistic`;

DELIMITER $$
USE `ClimateData`$$
CREATE PROCEDURE `insertStatistic` (pIdClimateVariable int,pIdCountry int, 
									pYearMaxXCountry int, pYearMinXCountry int, 
									pStationMaxXCountry varchar(45), pStationMinXCountry varchar(45), 
									pValueMaxXCountry float, pValueMinXCountry float)
BEGIN
	INSERT INTO MaxMinXCountry(idCountry,idClimateVariable,  yearMaxXCountry, yearMinXCountry,
								stationMaxXCountry, stationMinXCountry, isMaxXContinent, isMinXContinent, 
								ValueMaxXCountry, ValueMinXCountry)


	VALUES (pIdCountry, pIdClimateVariable,  pYearMaxXCountry, pYearMinXCountry,
			pStationMaxXCountry, pStationMinXCountry, 0, 0, pValueMaxXCountry, pValueMinXCountry);
END$$

DELIMITER ;