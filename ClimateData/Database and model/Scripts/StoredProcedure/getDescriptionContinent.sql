USE `ClimateData`;
DROP procedure IF EXISTS `getDescriptionContinent`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDescriptionContinent`(pIdContinent int)
BEGIN
	SELECT descriptionContinent
    FROM Continent
    WHERE idContinent = pIdContinent ;
END$$

DELIMITER ;