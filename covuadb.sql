-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2019 at 12:24 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `covuadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_match`
--

CREATE TABLE `tbl_match` (
  `id` int(11) NOT NULL,
  `time` varchar(50) NOT NULL,
  `user1` varchar(30) NOT NULL,
  `user2` varchar(30) NOT NULL,
  `winner` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_match`
--

INSERT INTO `tbl_match` (`id`, `time`, `user1`, `user2`, `winner`) VALUES
(1, '09:42:37 18-10-2019', 'hieu', 'lanh', 1),
(2, '16:24:34 20-10-2019', 'lanh', 'long', 2),
(3, '17:21:26 20-10-2019', 'long', 'lanh', 1),
(4, '17:45:20 20-10-2019', 'long', 'lanh', 1),
(5, '12:10:08 21-10-2019', 'long', 'lanh', 1),
(6, '12:10:35 21-10-2019', 'lanh', 'long', 2),
(7, '17:10:49 21-10-2019', 'long', 'hieu', 1),
(8, '17:16:18 21-10-2019', 'long', 'hieu', 2),
(9, '17:17:02 21-10-2019', 'lanh', 'long', 2),
(10, '17:17:59 28-10-2019', 'lanh', 'long', 2),
(11, '17:29:29 28-10-2019', 'long', 'hieu', 2),
(12, '17:36:20 28-10-2019', 'hieu', 'long', 1),
(13, '00:07:39 04-11-2019', 'hieu', 'lanh', 1),
(14, '00:10:08 04-11-2019', 'long', 'lanh', 1),
(15, '01:27:23 04-11-2019', 'long', 'hieu', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`username`, `password`, `name`, `score`) VALUES
('cong', '123', 'cong', 0),
('hieu', '123', 'hieu', 49),
('lanh', '1', 'lanh', 0),
('linh', '1234', 'chanh', 0),
('long', '123', 'long', 234);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_match`
--
ALTER TABLE `tbl_match`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user1` (`user1`),
  ADD KEY `user2` (`user2`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_match`
--
ALTER TABLE `tbl_match`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_match`
--
ALTER TABLE `tbl_match`
  ADD CONSTRAINT `tbl_match_ibfk_1` FOREIGN KEY (`user1`) REFERENCES `tbl_user` (`username`),
  ADD CONSTRAINT `tbl_match_ibfk_2` FOREIGN KEY (`user2`) REFERENCES `tbl_user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
