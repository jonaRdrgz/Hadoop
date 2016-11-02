USE `ClimateData`;
DROP procedure IF EXISTS `getIdCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE PROCEDURE `getIdCountry` (pDescriptionCountry varchar (45))
BEGIN
	SELECT idCountry
	FROM Country
	WHERE descriptionCountry = pDescriptionCountry;
END$$

DELIMITER ;
