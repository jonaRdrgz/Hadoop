USE `ClimateData`;
DROP procedure IF EXISTS `getYearMinXCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getYearMinXCountry`(pIdCountry int)
BEGIN
	SELECT MM.idClimateVariable, MM.yearMinXCountry, MM.valueMinXCountry
    FROM MaxMinXCountry AS MM
    WHERE MM.idCountry = pIdCountry
    ORDER BY MM.idClimateVariable;
END$$

DELIMITER ;

