USE `ClimateData`;
DROP procedure IF EXISTS `insertTopMinCountryXClimateVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTopMinCountryXClimateVariable`(pIdClimateVariable int, pIdCountry int,
												pValueTopMinCountryXClimateVariable float)
BEGIN
	INSERT INTO TopMinCountryXClimateVariable(idClimateVariable, idCountry, valueTopMinCountryXClimateVariable)
    VALUES (pIdClimateVariable, pIdCountry, pValueTopMinCountryXClimateVariable);
END$$

DELIMITER ;
