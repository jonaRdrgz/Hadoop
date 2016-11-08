USE `ClimateData`;
DROP procedure IF EXISTS `getClimateVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getClimateVariable`()
BEGIN
	SELECT idClimateVariable, descriptionClimateVariable
    FROM ClimateVariable ;
END$$

DELIMITER ;