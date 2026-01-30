use travel;

drop table if exists ticket_routes;
drop table if exists routes;
drop table if exists tickets;
drop table if exists transport_type;
drop table if exists locations;
drop table if exists users;

CREATE TABLE users (
  id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  uname varchar(100) UNIQUE,
  pwd varchar(255) not NULL,
  email varchar(255) not null
) ENGINE=InnoDB;

CREATE TABLE transport_type (
  id int unsigned NOT NULL PRIMARY KEY,
  type varchar(20) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE locations (
  id int unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE routes (
  id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  from_id int unsigned NOT NULL,
  to_id int unsigned NOT NULL,
  transport_type_id int unsigned NOT NULL,
  distance int unsigned not null,
  price double check(price>=0) not null,
  time int DEFAULT NULL,
  FOREIGN KEY (from_id) REFERENCES locations(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (to_id) REFERENCES locations(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (transport_type_id) REFERENCES transport_type(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE tickets (
  id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id int unsigned NOT NULL,
  from_place varchar(100) NOT NULL,
  to_place varchar(100) NOT NULL,
  transport_type int unsigned NOT NULL,
  total_price double NOT NULL,
  travel_time DATETIME,
  status varchar(20) NOT NULL,
  booked_at timestamp DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (transport_type) REFERENCES transport_type(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE ticket_routes (
  id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ticket_id int unsigned NOT NULL,
  from_place varchar(100) NOT NULL,
  to_place varchar(100) NOT NULL,
  transport_type int unsigned NOT NULL DEFAULT 1,
  cost double NOT NULL,
  FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (transport_type) REFERENCES transport_type(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO users(uname,pwd,email) VALUES ('admin6628','$argon2id$v=19$m=16384,t=10,p=1$fpBZlBMBGC7pIoQBtZJ9SA$M8S6vRGXSn1nRLyaE7w4vPsPEAn2FKKlZ3agq2iKenc','hello@world.com');


INSERT INTO transport_type (id, type) VALUES 
(3, 'FLIGHT'), (1, 'ROAD'), (2, 'TRAIN');

INSERT INTO locations (name) VALUES 
('Madurai'), ('Chennai'), ('Trichy'), ('Coimbatore'), 
('Salem'), ('Erode'), ('Tirunelveli'), ('Thoothukudi');

INSERT INTO routes (id, from_id, to_id, transport_type_id, distance, price, time) VALUES 
(1, 1, 3, 1, 135, 180, 150), (2, 3, 1, 1, 135, 180, 150),
(3, 3, 5, 1, 140, 200, 160), (4, 5, 3, 1, 140, 200, 160),
(5, 5, 2, 1, 340, 450, 360), (6, 2, 5, 1, 340, 450, 360),
(7, 1, 4, 1, 215, 280, 240), (8, 4, 1, 1, 215, 280, 240),
(9, 4, 6, 1, 100, 150, 120), (10, 6, 4, 1, 100, 150, 120),
(11, 6, 5, 1, 65, 110, 90), (12, 5, 6, 1, 65, 110, 90),
(13, 1, 7, 1, 160, 220, 170), (14, 7, 1, 1, 160, 220, 170),
(15, 7, 8, 1, 50, 90, 60), (16, 8, 7, 1, 50, 90, 60),
(17, 1, 2, 2, 495, 320, 480), (18, 2, 1, 2, 495, 320, 480),
(19, 1, 3, 2, 135, 150, 180), (20, 3, 1, 2, 135, 150, 180),
(21, 3, 5, 2, 140, 160, 200), (22, 5, 3, 2, 140, 160, 200),
(23, 1, 2, 3, 495, 3200, 70), (24, 2, 1, 3, 495, 3200, 70),
(25, 4, 2, 3, 510, 3500, 80), (26, 2, 4, 3, 510, 3500, 80);

