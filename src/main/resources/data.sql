DROP TABLE IF EXISTS `control_elements`;
DROP TABLE IF EXISTS `control_devices`;
DROP TABLE IF EXISTS `control_history`;

CREATE TABLE `control_devices` (
                                   `device_id` VARCHAR(255) PRIMARY KEY,
                                   `name` VARCHAR(255)
);

CREATE TABLE `control_elements` (
                                    `element_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    `place` VARCHAR(255),
                                    `device` VARCHAR(255),
                                    `event` VARCHAR(255),
                                    `device_id` VARCHAR(255),
                                    FOREIGN KEY (`device_id`) REFERENCES `control_devices`(`device_id`)
);

CREATE TABLE `control_history` (
                                   `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                                   `eui` VARCHAR(255),
                                   `time` TIMESTAMP,
                                   `message` VARCHAR(255)
);

INSERT INTO `control_devices` (`device_id`, `name`) VALUES ('24E124445C408915', 'classA 에어컨 디바이스');
INSERT INTO `control_devices` (`device_id`, `name`) VALUES ('24E124445C408914', 'classA 조명 디바이스');
INSERT INTO `control_devices` (`device_id`, `name`) VALUES ('24E124445C408916', '');

INSERT INTO `control_elements` (`place`, `device`, `event`, `device_id`) VALUES ('class_a', 'ws301', 'magnet_status', '24E124445C408915');
INSERT INTO `control_elements` (`place`, `device`, `event`, `device_id`) VALUES ('class_a', '24e124128c067999', 'illumination', '24E124445C408914');
INSERT INTO `control_elements` (`place`, `device`, `event`, `device_id`) VALUES ('class_a', 'vs121', 'occupancy', '24E124445C408916');
