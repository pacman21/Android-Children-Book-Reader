-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 27, 2013 at 09:15 AM
-- Server version: 5.5.33-31.1
-- PHP Version: 5.3.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pacman21_recordabook`
--

-- --------------------------------------------------------

--
-- Table structure for table `Authors`
--

CREATE TABLE IF NOT EXISTS `Authors` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bio` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `BookAuthors`
--

CREATE TABLE IF NOT EXISTS `BookAuthors` (
  `book_author_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`book_author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `BookKeywords`
--

CREATE TABLE IF NOT EXISTS `BookKeywords` (
  `book_keyword_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `keyword` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`book_keyword_id`),
  KEY `INDEX` (`keyword`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `BookPages`
--

CREATE TABLE IF NOT EXISTS `BookPages` (
  `book_page_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_id` int(255) NOT NULL,
  `page_num` int(255) NOT NULL,
  `page_url` varchar(555) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`book_page_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `BookPages`
--

INSERT INTO `BookPages` (`book_page_id`, `book_id`, `page_num`, `page_url`) VALUES
('1-1', 1, 1, 'Books/1-1.jpg'),
('1-2', 1, 2, 'Books/1-2.jpg'),
('1-3', 1, 3, 'Books/1-3.jpg'),
('2-1', 2, 1, 'Books/2-1.pdf'),
('2-2', 2, 2, 'Books/2-2.pdf'),
('2-3', 2, 3, 'Books/2-3.pdf'),
('2-4', 2, 4, 'Books/2-4.pdf'),
('2-5', 2, 5, 'Books/2-5.pdf'),
('2-6', 2, 6, 'Books/2-6.pdf'),
('2-7', 2, 7, 'Books/2-7.pdf'),
('2-8', 2, 8, 'Books/2-8.pdf'),
('2-9', 2, 9, 'Books/2-9.pdf'),
('2-10', 2, 10, 'Books/2-10.pdf'),
('2-11', 2, 11, 'Books/2-11.pdf'),
('2-12', 2, 12, 'Books/2-12.pdf'),
('2-13', 2, 13, 'Books/2-13.pdf'),
('2-14', 2, 14, 'Books/2-14.pdf'),
('2-15', 2, 15, 'Books/2-15.pdf'),
('2-16', 2, 16, 'Books/2-16.pdf'),
('2-17', 2, 17, 'Books/2-17.pdf'),
('2-18', 2, 18, 'Books/2-18.pdf'),
('2-19', 2, 19, 'Books/2-19.pdf'),
('4-27', 4, 27, 'Books/4-27.pdf'),
('4-26', 4, 26, 'Books/4-26.pdf'),
('4-25', 4, 25, 'Books/4-25.pdf'),
('4-24', 4, 24, 'Books/4-24.pdf'),
('4-23', 4, 23, 'Books/4-23.pdf'),
('4-22', 4, 22, 'Books/4-22.pdf'),
('4-21', 4, 21, 'Books/4-21.pdf'),
('4-20', 4, 20, 'Books/4-20.pdf'),
('4-19', 4, 19, 'Books/4-19.pdf'),
('4-18', 4, 18, 'Books/4-18.pdf'),
('4-17', 4, 17, 'Books/4-17.pdf'),
('4-16', 4, 16, 'Books/4-16.pdf'),
('4-15', 4, 15, 'Books/4-15.pdf'),
('4-14', 4, 14, 'Books/4-14.pdf'),
('4-13', 4, 13, 'Books/4-13.pdf'),
('4-12', 4, 12, 'Books/4-12.pdf'),
('4-11', 4, 11, 'Books/4-11.pdf'),
('4-10', 4, 10, 'Books/4-10.pdf'),
('4-9', 4, 9, 'Books/4-9.pdf'),
('4-8', 4, 8, 'Books/4-8.pdf'),
('4-7', 4, 7, 'Books/4-7.pdf'),
('4-6', 4, 6, 'Books/4-6.pdf'),
('4-5', 4, 5, 'Books/4-5.pdf'),
('4-4', 4, 4, 'Books/4-4.pdf'),
('4-3', 4, 3, 'Books/4-3.pdf'),
('4-2', 4, 2, 'Books/4-2.pdf'),
('4-1', 4, 1, 'Books/4-1.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `Books`
--

CREATE TABLE IF NOT EXISTS `Books` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `page_count` int(11) NOT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `current_cost` float NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `book_cover` varchar(555) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `Books`
--

INSERT INTO `Books` (`book_id`, `title`, `page_count`, `publisher_id`, `current_cost`, `description`, `book_cover`, `status`) VALUES
(1, 'Title Test', 3, 0, 0, 'Best book everrr', '', 1),
(2, 'Bed Making Blues', 19, 0, 0, 'This is a great book about bed making blues.', NULL, 1),
(4, 'The King of Custard Castle', 27, 0, 0, 'The book is probably about the king of ice cream.', NULL, 1),
(12, 'The Golden Goose', 11, 0, 0, 'This is a book about a golden goose.', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Publishers`
--

CREATE TABLE IF NOT EXISTS `Publishers` (
  `publisher_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `RecordingInvites`
--

CREATE TABLE IF NOT EXISTS `RecordingInvites` (
  `recording_invite_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_invited` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_id` int(255) NOT NULL,
  `invite_sent_by` int(255) NOT NULL,
  `acceptance_status` tinyint(4) NOT NULL,
  `date_invited` datetime NOT NULL,
  `date_accepted` datetime DEFAULT NULL,
  PRIMARY KEY (`recording_invite_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `RecordingInvites`
--

INSERT INTO `RecordingInvites` (`recording_invite_id`, `user_invited`, `book_id`, `invite_sent_by`, `acceptance_status`, `date_invited`, `date_accepted`) VALUES
('Xz32jlKg5AtRSvnkaPi6hMWs6NHUijhgSlibG3sMDVDwr1QBR91383455504', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-03 00:11:44', '2013-11-04 23:58:58'),
('UyB1Zh0fd2ADS82IFhlKVc08ERrkv8DqGesFwsVKuvonDq5jIq1383928954', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-08 10:42:34', '2013-11-08 10:43:57'),
('kHWLO7zyZ15Wr9wUO6I6b19Q050A5lPq2MbRTLpTMvQeEm8ttQ1384109694', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-10 12:54:54', '2013-11-10 12:57:45'),
('Eussw1Rwp7', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-10 23:43:15', '2013-11-10 23:49:16'),
('UVM7QbwhrW', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-13 23:24:20', '2013-11-13 23:25:57'),
('9JA4uSeC9r', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-13 23:37:31', '2013-11-13 23:39:00'),
('z1eUgC41zt', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-15 23:07:06', '2013-11-15 23:07:42'),
('KRtLhzph19', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-15 23:23:04', '2013-11-15 23:23:37'),
('cazXtxhR3M', 'dapacc@yahoo.com', 2, 1, 0, '2013-11-15 23:27:04', NULL),
('mEhhI3SwKf', 'dapacc@yahoo.com', 2, 1, 1, '2013-11-15 23:27:32', '2013-11-15 23:28:31');

-- --------------------------------------------------------

--
-- Table structure for table `UserBooks`
--

CREATE TABLE IF NOT EXISTS `UserBooks` (
  `user_book_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `date_purchased` datetime NOT NULL,
  `purchased_cost` float NOT NULL,
  PRIMARY KEY (`user_book_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `UserBooks`
--

INSERT INTO `UserBooks` (`user_book_id`, `user_id`, `book_id`, `date_purchased`, `purchased_cost`) VALUES
(1, 1, 1, '2013-10-06 00:37:16', 0),
(2, 1, 2, '2013-10-20 22:35:23', 0),
(3, 2, 2, '2013-11-13 23:30:08', 0),
(7, 1, 12, '2013-11-24 15:35:42', 0);

-- --------------------------------------------------------

--
-- Table structure for table `UserPages`
--

CREATE TABLE IF NOT EXISTS `UserPages` (
  `user_page_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `book_page_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(255) NOT NULL,
  `audio_url` varchar(555) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recorded_by` int(255) DEFAULT NULL,
  `date_recorded` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_page_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `UserPages`
--

INSERT INTO `UserPages` (`user_page_id`, `book_page_id`, `user_id`, `audio_url`, `recorded_by`, `date_recorded`, `status`) VALUES
('1-3-1', '1-3', 1, NULL, NULL, NULL, 1),
('1-2-1', '1-2', 1, NULL, NULL, NULL, 1),
('1-1-1', '1-1', 1, NULL, NULL, NULL, 1),
('2-1-1', '2-1', 1, '2-1-1.3gp', NULL, NULL, 1),
('2-2-1', '2-2', 1, '2-2-1.3gp', NULL, NULL, 1),
('2-3-1', '2-3', 1, '2-3-1.3gp', NULL, NULL, 1),
('2-4-1', '2-4', 1, '2-4-1.3gp', NULL, NULL, 1),
('2-5-1', '2-5', 1, '2-5-1.3gp', NULL, NULL, 1),
('2-6-1', '2-6', 1, NULL, NULL, NULL, 1),
('2-7-1', '2-7', 1, NULL, NULL, NULL, 1),
('2-8-1', '2-8', 1, NULL, NULL, NULL, 1),
('2-9-1', '2-9', 1, NULL, NULL, NULL, 1),
('2-10-1', '2-10', 1, NULL, NULL, NULL, 1),
('2-11-1', '2-11', 1, NULL, NULL, NULL, 1),
('2-12-1', '2-12', 1, NULL, NULL, NULL, 1),
('2-13-1', '2-13', 1, NULL, NULL, NULL, 1),
('2-14-1', '2-14', 1, NULL, NULL, NULL, 1),
('2-15-1', '2-15', 1, NULL, NULL, NULL, 1),
('2-16-1', '2-16', 1, NULL, NULL, NULL, 1),
('2-17-1', '2-17', 1, NULL, NULL, NULL, 1),
('2-18-1', '2-18', 1, NULL, NULL, NULL, 1),
('2-19-1', '2-19', 1, NULL, NULL, NULL, 1),
('2-1-2', '2-1', 2, NULL, NULL, NULL, 1),
('2-2-2', '2-2', 2, NULL, NULL, NULL, 1),
('2-3-2', '2-3', 2, NULL, NULL, NULL, 1),
('2-4-2', '2-4', 2, NULL, NULL, NULL, 1),
('2-5-2', '2-5', 2, NULL, NULL, NULL, 1),
('2-6-2', '2-6', 2, NULL, NULL, NULL, 1),
('2-7-2', '2-7', 2, NULL, NULL, NULL, 1),
('2-8-2', '2-8', 2, NULL, NULL, NULL, 1),
('2-9-2', '2-9', 2, NULL, NULL, NULL, 1),
('2-10-2', '2-10', 2, NULL, NULL, NULL, 1),
('2-11-2', '2-11', 2, NULL, NULL, NULL, 1),
('2-12-2', '2-12', 2, NULL, NULL, NULL, 1),
('2-13-2', '2-13', 2, NULL, NULL, NULL, 1),
('2-14-2', '2-14', 2, NULL, NULL, NULL, 1),
('2-15-2', '2-15', 2, NULL, NULL, NULL, 1),
('2-16-2', '2-16', 2, NULL, NULL, NULL, 1),
('2-17-2', '2-17', 2, NULL, NULL, NULL, 1),
('2-18-2', '2-18', 2, NULL, NULL, NULL, 1),
('2-19-2', '2-19', 2, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`user_id`, `email`, `password`, `salt`, `status`) VALUES
(1, 'dapacc@yahoo.com', '6d1e87d6737bdf25f1eda9e7e81e1177', 'KLeSGtOrPeWJ4rXG0z1869MLJ', 1),
(2, 'dapacc901@yahoo.com', '6d1e87d6737bdf25f1eda9e7e81e1177', 'KLeSGtOrPeWJ4rXG0z1869MLJ', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
