USE `ClimateData`;
DROP procedure IF EXISTS `getDescriptionClimateVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDescriptionClimateVariable`(pIdClimateVariable int)
BEGIN
	SELECT descriptionClimateVariable
    FROM ClimateVariable
    WHERE idClimateVariable = pIdClimateVariable;
END$$

DELIMITER ;