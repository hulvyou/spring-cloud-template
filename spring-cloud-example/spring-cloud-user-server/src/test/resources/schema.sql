CREATE TABLE IF NOT EXISTS t_user(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100),
  secret VARCHAR(100),
  is_delete TINYINT,
  create_time DATETIME,
  update_time DATETIME
);