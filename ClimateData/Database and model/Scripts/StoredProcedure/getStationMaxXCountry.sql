USE `ClimateData`;
DROP procedure IF EXISTS `getStationMaxXCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStationMaxXCountry`(pIdCountry int)
BEGIN
	SELECT MM.idClimateVariable, MM.stationMaxXCountry, MM.valueMaxXCountry
    FROM MaxMinXCountry AS MM
    WHERE MM.idCountry = pIdCountry
    ORDER BY MM.idClimateVariable;
END$$

DELIMITER ;