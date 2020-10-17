-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.16-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for hospital
CREATE DATABASE IF NOT EXISTS `hospital` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hospital`;


-- Dumping structure for table hospital.medication
DROP TABLE IF EXISTS `medication`;
CREATE TABLE IF NOT EXISTS `medication` (
  `id` int(1) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table hospital.medication: ~12 rows (approximately)
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` (`id`, `category`, `name`, `price`) VALUES
	(1, 'A', 'VND 1', '150'),
	(2, 'A', 'XXV 2', '150'),
	(3, 'A', 'HNF 232', '150'),
	(4, 'A', 'GB334', '150'),
	(5, 'B', 'X34', '100'),
	(6, 'B', 'HH5', '100'),
	(7, 'B', 'DDF23', '100'),
	(8, 'B', 'JHH7', '100'),
	(9, 'C', '543H', '70'),
	(10, 'C', '344BB', '70'),
	(11, 'C', 'JUY9', '70'),
	(12, 'C', '232B', '70');
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;


-- Dumping structure for table hospital.patient
DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(50) NOT NULL,
  `meds` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `call_records` varchar(50) DEFAULT NULL,
  `notes` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Dumping data for table hospital.patient: ~8 rows (approximately)
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` (`id`, `firstname`, `lastname`, `dob`, `address`, `phone`, `meds`, `price`, `call_records`, `notes`, `date`, `time`) VALUES
	(1, 'tobs', 'aram', '2222-12-22', '90 In Road', '0852136714', 'CA:VND 1, CB:N/A, CC:N/A', 150, 'I LOVE ITS SSS', 'hello', '', ''),
	(19, 'mike ', 'bake', '2020-12-20', '15 In Road', '0852136715', 'CA:VND 1, CB:catb, CC:344BB', 320, NULL, 'NO', 'haha', 'uaua'),
	(21, 'thando', 'thando', '2020-12-13', 'thando', 'thando', 'CA:N/A, CB:N/A, CC:N/A', 0, NULL, 'rece rece rece', NULL, NULL),
	(24, 'make', 'make', '2214-12-12', 'over there', '0852136716', 'CA:N/A, CB:N/A, CC:543H', 0, NULL, 'thasre', NULL, NULL),
	(25, 'ggggggggg', 'ggggggggg', '2222-12-12', 'lllllll', '22', 'CA:N/A, CB:N/A, CC:N/A', 0, NULL, 'ths guys lobin it', 'daet', 'tiime'),
	(26, 'thando', 'thando', '1212-12-12', 'thando', 'sdfas', 'CA:N/A, CB:catb, CC:N/A', NULL, NULL, NULL, '', ''),
	(27, 'NEW GUY', 'NEW ', '1212-12-12', '89 CHUCH', '085213677', 'CA:N/A, CB:catb, CC:N/A', NULL, NULL, NULL, NULL, NULL),
	(28, 'asf', 'asdf', '1212-12-12', 'sdfa', '222', 'CA:N/A, CB:catb, CC:N/A', NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;


-- Dumping structure for table hospital.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `type` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table hospital.users: ~6 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `type`) VALUES
	(1, 'doc', '1', 'doctor'),
	(2, 'pat', 'pat', 'patient'),
	(3, 'bill', '3', 'biller'),
	(4, 'rec', '4', 'receptionist'),
	(5, 'dup', 'dup', 'rec'),
	(6, 'dup', 'dup', 'rec');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
