USE `ClimateData`;
DROP procedure IF EXISTS `getDescriptionCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDescriptionCountry`(pIdCountry int)
BEGIN
	SELECT descriptionCountry
    FROM Country
    WHERE idCountry = pIdCountry ;
END$$

DELIMITER ;