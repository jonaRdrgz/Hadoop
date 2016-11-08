USE `ClimateData`;
DROP procedure IF EXISTS `getYearMaxXCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getYearMaxXCountry`(pIdCountry int)
BEGIN
	SELECT MM.idClimateVariable, MM.yearMaxXCountry, MM.valueMaxXCountry
    FROM MaxMinXCountry AS MM
    WHERE MM.idCountry = pIdCountry
    ORDER BY MM.idClimateVariable;
END$$

DELIMITER ;

