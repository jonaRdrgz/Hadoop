USE `ClimateData`;
DROP procedure IF EXISTS `getIdContinent`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdContinent`(pDescriptionContinent varchar (45))
BEGIN
	SELECT idContinent
	FROM Continent
	WHERE descriptionContinent = pDescriptionContinent;
END$$

DELIMITER ;