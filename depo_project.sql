-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 04 Eyl 2021, 22:02:08
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `depo_project`
--

DELIMITER $$
--
-- Yordamlar
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkOutBoxActionSearch` (IN `custid` INT, IN `sdate` DATE, IN `fdate` DATE)  BEGIN
SELECT cashin.cu_id as cashincustomer_id, customer.cu_id,customer.cu_name,customer.cu_surname,cu_company_title,customer.cu_code,customer.cu_mobile,
customer.cu_email
FROM cashin INNER JOIN or_order  on cashin.or_id =or_order.or_id
INNER JOIN customer on customer.cu_id=or_order.customer_cu_id 
INNER JOIN product on product.pid=or_order.product_pid
WHERE cashin.pdate BETWEEN fdate and sdate  and customer.cu_id =custid;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `admin`
--

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `admin`
--

INSERT INTO `admin` (`aid`, `email`, `name`, `password`) VALUES
(1, 'alper@mail.com', 'Alper Yılmaz', '827ccb0eea8a706c4c34a16891f84e7b');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cashin`
--

CREATE TABLE `cashin` (
  `cash_id` int(11) NOT NULL,
  `cash_status` int(11) NOT NULL,
  `cu_id` int(11) NOT NULL,
  `or_id` int(11) NOT NULL,
  `payInDetail` varchar(255) DEFAULT NULL,
  `payInTotal` int(11) NOT NULL,
  `pdate` date DEFAULT NULL,
  `order_or_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `cashin`
--

INSERT INTO `cashin` (`cash_id`, `cash_status`, `cu_id`, `or_id`, `payInDetail`, `payInTotal`, `pdate`, `order_or_id`) VALUES
(5, 1, 3, 37, 'dEtay', 23432, '2021-09-01', 37),
(7, 1, 3, 30, 'Detay Detay', 435453, '2021-09-03', 30),
(9, 1, 5, 54, 'detay', 450000, '2021-09-03', 54),
(10, 1, 3, 24, '', 333345, '2021-09-03', 24),
(17, 1, 3, 54, 'Detay', 2234, '2021-09-03', 54),
(21, 1, 1, 57, 'Detay', 12123, '2021-09-04', 57),
(23, 1, 1, 57, 'detay', 2332, '2021-09-04', 57);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cashout`
--

CREATE TABLE `cashout` (
  `cout_id` int(11) NOT NULL,
  `payOutDetail` varchar(255) DEFAULT NULL,
  `payOutTitle` varchar(255) DEFAULT NULL,
  `payOutTotal` int(11) NOT NULL,
  `payOutType` int(11) NOT NULL,
  `odate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `cashout`
--

INSERT INTO `cashout` (`cout_id`, `payOutDetail`, `payOutTitle`, `payOutTotal`, `payOutType`, `odate`) VALUES
(5, 'detay', 'Ödeme', 323, 1, '2021-09-03'),
(6, 'Detay', 'Ödeme', 333, 1, '2021-09-03'),
(7, 'Detay', 'Ödeme', 333, 4, '2021-09-03'),
(9, '43', 'DEtay', 234324, 1, '2021-09-04'),
(10, '43', 'DEtay', 234324, 1, '2021-09-04');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `checkoutboxactionsearch`
--

CREATE TABLE `checkoutboxactionsearch` (
  `cashincustomer_id` int(11) NOT NULL,
  `cu_code` int(11) NOT NULL,
  `cu_company_title` varchar(255) DEFAULT NULL,
  `cu_email` varchar(255) DEFAULT NULL,
  `cu_id` int(11) NOT NULL,
  `cu_mobile` int(11) NOT NULL,
  `cu_name` varchar(255) DEFAULT NULL,
  `cu_surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `checkoutsrchobj`
--

CREATE TABLE `checkoutsrchobj` (
  `cname` int(11) NOT NULL,
  `ctype` int(11) NOT NULL,
  `endDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `cu_id` int(11) NOT NULL,
  `cu_address` varchar(500) DEFAULT NULL,
  `cu_code` bigint(20) NOT NULL,
  `cu_company_title` varchar(255) DEFAULT NULL,
  `cu_email` varchar(500) DEFAULT NULL,
  `cu_mobile` varchar(255) DEFAULT NULL,
  `cu_name` varchar(255) DEFAULT NULL,
  `cu_password` varchar(32) DEFAULT NULL,
  `cu_phone` varchar(255) DEFAULT NULL,
  `cu_status` int(11) NOT NULL,
  `cu_surname` varchar(255) DEFAULT NULL,
  `cu_tax_administration` varchar(255) DEFAULT NULL,
  `cu_tax_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`cu_id`, `cu_address`, `cu_code`, `cu_company_title`, `cu_email`, `cu_mobile`, `cu_name`, `cu_password`, `cu_phone`, `cu_status`, `cu_surname`, `cu_tax_administration`, `cu_tax_number`) VALUES
(1, '423 Bursa ', 748095562, 'Şirket', 'alper@mail.com', '32423456', 'Alper', '234234', '123454745', 1, 'Yılmaz', 'Bursa', 432423),
(3, 'Bursa', 748130237, 'Ünvan', 'alper@mail.com', '324324', 'Alperen', '2342', '43435', 1, 'Bora', '46545', 234234),
(4, 'Bursa', 748130237, 'Ünvan', 'alper@mail.com', '324324', 'Ferhat', '2342', '43435', 1, 'Yıldırım', '46545', 234234),
(5, 'Bursa', 748130237, 'Ünvan', 'alper@mail.com', '324324', 'İbrahim', '2342', '43435', 1, 'Şimşek', '46545', 234234);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `custproord`
--

CREATE TABLE `custproord` (
  `cu_id` int(11) NOT NULL,
  `cu_name` varchar(255) DEFAULT NULL,
  `cu_surname` varchar(255) DEFAULT NULL,
  `fis_no` int(11) NOT NULL,
  `or_id` int(11) NOT NULL,
  `or_size` int(11) NOT NULL,
  `ptitle` varchar(255) DEFAULT NULL,
  `selprice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `or_order`
--

CREATE TABLE `or_order` (
  `or_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `fis_no` int(11) NOT NULL,
  `or_size` int(11) NOT NULL,
  `customer_cu_id` int(11) DEFAULT NULL,
  `product_pid` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `or_order`
--

INSERT INTO `or_order` (`or_id`, `date`, `fis_no`, `or_size`, `customer_cu_id`, `product_pid`, `status`, `total`) VALUES
(17, '2021-08-29', 55, 66, 5, 4, 1, 992127816),
(24, '2021-08-30', 335485129, 35, 5, 3, 1, 992127816),
(30, '2021-08-30', 340692796, 234, 1, 5, 1, 119144025),
(32, '2021-08-30', 341417385, 21, 4, 3, 1, 992127816),
(33, '2021-08-30', 341417385, 12, 4, 4, 1, 7718470),
(36, '2021-08-31', 364971765, 45, 3, 4, 1, 7718470),
(37, '2021-08-31', 364971765, 45, 3, 2, 1, 992127816),
(52, '2021-08-31', 430609394, 12, 4, 5, 1, 119144025),
(54, '2021-09-03', 636003884, 43, 3, 4, 1, 7718470),
(55, '2021-09-03', 666155791, 15, 1, 5, 1, 119133675),
(56, '2021-09-04', 666155791, 334, 1, 3, 1, 992000796),
(57, '2021-09-04', 772323305, 122, 1, 3, 1, 376826),
(58, '2021-09-04', 772323305, 33, 1, 3, 1, 2345),
(59, '2021-09-04', 774044919, 33, 1, 2, 1, 2323),
(60, '2021-09-04', 774044919, 32, 1, 2, 1, 13223),
(61, '2021-09-04', 774044919, 333, 1, 4, 1, 5532);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `product`
--

CREATE TABLE `product` (
  `pid` int(11) NOT NULL,
  `buyprice` int(11) NOT NULL,
  `pcode` int(11) NOT NULL,
  `pdetail` varchar(255) DEFAULT NULL,
  `psection` int(11) NOT NULL,
  `ptax` int(11) NOT NULL,
  `ptitle` varchar(255) DEFAULT NULL,
  `selprice` int(11) NOT NULL,
  `size` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `product`
--

INSERT INTO `product` (`pid`, `buyprice`, `pcode`, `pdetail`, `psection`, `ptax`, `ptitle`, `selprice`, `size`) VALUES
(2, 123, 5423, 'demi3', 3, 2, 'Demir3', 4234, 234324),
(3, 123, 54233, 'Bakır', 3, 3, 'Bakır', 4234, 234324),
(4, 342, 753838733, 'bakır2', 1, 2, 'bakır2', 3455, 2234),
(5, 123, 755743590, '75675Tungsten', 3, 2, 'Tungsten', 345, 345345),
(7, 342, 753838733, 'bakır2', 1, 3, 'bakır2', 3455, 2234);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`aid`);

--
-- Tablo için indeksler `cashin`
--
ALTER TABLE `cashin`
  ADD PRIMARY KEY (`cash_id`),
  ADD KEY `FK4qvj9rvhlfh7w4towg35x8vsf` (`order_or_id`);

--
-- Tablo için indeksler `cashout`
--
ALTER TABLE `cashout`
  ADD PRIMARY KEY (`cout_id`);

--
-- Tablo için indeksler `checkoutboxactionsearch`
--
ALTER TABLE `checkoutboxactionsearch`
  ADD PRIMARY KEY (`cashincustomer_id`);

--
-- Tablo için indeksler `checkoutsrchobj`
--
ALTER TABLE `checkoutsrchobj`
  ADD PRIMARY KEY (`cname`);

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cu_id`);

--
-- Tablo için indeksler `custproord`
--
ALTER TABLE `custproord`
  ADD PRIMARY KEY (`cu_id`);

--
-- Tablo için indeksler `or_order`
--
ALTER TABLE `or_order`
  ADD PRIMARY KEY (`or_id`),
  ADD KEY `FK98novephb7mw09qh2molh4lnj` (`customer_cu_id`),
  ADD KEY `FK4xyb7suuurfsijvscflo9vxt4` (`product_pid`);

--
-- Tablo için indeksler `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pid`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `admin`
--
ALTER TABLE `admin`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `cashin`
--
ALTER TABLE `cashin`
  MODIFY `cash_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `cashout`
--
ALTER TABLE `cashout`
  MODIFY `cout_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `cu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `custproord`
--
ALTER TABLE `custproord`
  MODIFY `cu_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `or_order`
--
ALTER TABLE `or_order`
  MODIFY `or_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Tablo için AUTO_INCREMENT değeri `product`
--
ALTER TABLE `product`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `cashin`
--
ALTER TABLE `cashin`
  ADD CONSTRAINT `FK4qvj9rvhlfh7w4towg35x8vsf` FOREIGN KEY (`order_or_id`) REFERENCES `or_order` (`or_id`);

--
-- Tablo kısıtlamaları `or_order`
--
ALTER TABLE `or_order`
  ADD CONSTRAINT `FK4xyb7suuurfsijvscflo9vxt4` FOREIGN KEY (`product_pid`) REFERENCES `product` (`pid`),
  ADD CONSTRAINT `FK98novephb7mw09qh2molh4lnj` FOREIGN KEY (`customer_cu_id`) REFERENCES `customer` (`cu_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
