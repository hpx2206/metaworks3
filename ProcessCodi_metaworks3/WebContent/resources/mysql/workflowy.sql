CREATE TABLE `workflowy` (
	`id` VARCHAR(20) NOT NULL,
	`name` VARCHAR(500) NULL DEFAULT NULL,
	`linkedInstId` INT(11) NULL DEFAULT NULL,
	`parentId` VARCHAR(20) NOT NULL,
	`no` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `parentId` (`parentId`, `no`)
)