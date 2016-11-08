USE `ClimateData`;
DROP procedure IF EXISTS `getStationMinXCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStationMinXCountry`(pIdCountry int)
BEGIN
	SELECT MM.idClimateVariable, MM.stationMinXCountry, MM.valueMinXCountry
    FROM MaxMinXCountry AS MM
    WHERE MM.idCountry = pIdCountry
    ORDER BY MM.idClimateVariable;
END$$

DELIMITER ;
