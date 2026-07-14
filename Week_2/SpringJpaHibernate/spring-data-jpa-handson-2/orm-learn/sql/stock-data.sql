-- ============================================================
-- stock-data.sql
-- Hands-on 2 (2. spring-data-jpa-handson)
-- Run this in MySQL before starting the application:
--   mysql> source <path>\sql\stock-data.sql
-- ============================================================
-- NOTE: This contains the exact rows from the expected query results
-- plus some extra rows for context. If you have the full stock-data.csv
-- from Cognizant's spring-data-jpa-files folder, you can replace this
-- file with the full dataset generated from that CSV.
-- ============================================================

USE ormlearn;

DROP TABLE IF EXISTS stock;

CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
    `st_id`     INT          NOT NULL AUTO_INCREMENT,
    `st_code`   VARCHAR(10),
    `st_date`   DATE,
    `st_open`   NUMERIC(10, 2),
    `st_close`  NUMERIC(10, 2),
    `st_volume` NUMERIC,
    PRIMARY KEY (`st_id`)
);

-- ---- Facebook (FB) data ----
-- September 2019 - used for date range query
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-03', 184.00, 182.39, 9779400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-04', 184.65, 187.14, 11308000);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-05', 188.53, 190.90, 13876700);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-06', 190.21, 187.49, 15226800);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-09', 187.73, 188.76, 14722400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-10', 187.44, 186.17, 15455900);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-11', 186.46, 188.49, 11761700);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-12', 189.86, 187.47, 11419800);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-13', 187.33, 187.19, 11441100);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-16', 186.93, 186.22, 8444800);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-17', 186.66, 188.08, 9671100);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-18', 188.09, 188.14, 9681900);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-19', 188.66, 190.14, 10392700);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-20', 190.66, 189.93, 19934200);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-23', 189.34, 186.82, 13327600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-24', 187.98, 181.28, 18546600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-25', 181.45, 182.80, 18068300);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-26', 181.33, 180.11, 16083300);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-27', 180.49, 177.10, 14656200);

-- FB rows needed for top 3 highest volume query
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-01-31', 165.60, 166.69, 77233600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2018-10-31', 155.00, 151.79, 60101300);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2018-12-19', 141.21, 133.24, 57404900);

-- some extra FB rows for context
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-08-01', 198.05, 193.65, 12445200);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-08-02', 194.00, 175.46, 42955700);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-10-01', 176.68, 179.38, 12381900);

-- ---- Google (GOOGL) data ----
-- rows where close > 1250 - used for greater than query
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-22', 1236.67, 1253.76, 954200);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-23', 1256.64, 1270.59, 1593400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-24', 1270.59, 1260.05, 1169800);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-25', 1270.30, 1267.34, 1567200);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-26', 1273.38, 1277.42, 1361400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-29', 1280.51, 1296.20, 3618400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-10-17', 1251.40, 1252.80, 1047900);

-- some extra GOOGL rows below 1250 close (these should NOT appear in the query result)
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-01-02', 1016.00, 1065.00, 1279600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-02-01', 1110.46, 1119.92, 1450700);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-03-01', 1154.93, 1140.22, 1246800);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-01', 1184.10, 1203.00, 1021300);

-- ---- Netflix (NFLX) data ----
-- rows needed for 3 lowest close prices query
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2018-12-24', 242.00, 233.88, 9547600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2018-12-21', 263.83, 246.39, 21397600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2018-12-26', 233.92, 253.67, 14402700);

-- extra NFLX rows with higher close prices (should not appear in lowest 3)
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2019-01-02', 259.28, 267.66, 9695400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2019-01-03', 269.37, 271.20, 7356800);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2019-06-01', 352.10, 358.73, 4232100);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2019-07-01', 365.40, 380.15, 5312400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2019-09-16', 279.33, 288.70, 5889200);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('NFLX', '2018-12-28', 255.32, 267.49, 8891200);
