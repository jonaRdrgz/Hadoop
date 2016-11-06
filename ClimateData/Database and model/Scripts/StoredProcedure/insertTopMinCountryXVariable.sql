USE `ClimateData`;
DROP procedure IF EXISTS `insertTopMinCountryXVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTopMinCountryXVariable`(pIdClimateVariable int, pIdCountry int,
												pValueTopMinCountryXVariable float)
BEGIN
	INSERT INTO TopMinCountryXVariable(idClimateVariable, idCountry, valueTopMinCountryXVariable)
    VALUES (pIdClimateVariable, pIdCountry, pValueTopMinCountryXVariable);
END$$

DELIMITER ;
