USE `ClimateData`;
DROP procedure IF EXISTS `insertTopMaxCountryXClimateVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTopMaxCountryXClimateVariable`(pIdClimateVariable int, pIdCountry int,
												pValueTopMaxCountryXClimateVariable float)
BEGIN
	INSERT INTO TopMaxCountryXClimateVariable(idClimateVariable, idCountry, valueTopMaxCountryXClimateVariable)
    VALUES (pIdClimateVariable, pIdCountry, pValueTopMaxCountryXClimateVariable);
END$$

DELIMITER ;

