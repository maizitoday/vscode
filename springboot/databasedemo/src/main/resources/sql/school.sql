
DROP TABLE IF EXISTS  work.PDMAN_DB_VERSION;
DROP TABLE IF EXISTS  work.school;
-- Create the table in the specified schema
CREATE TABLE work.school
(
  id INT NOT NULL PRIMARY KEY,
  -- primary key column
  name NVARCHAR(50) NOT NULL,
  address NVARCHAR(50) NOT NULL
  -- specify more columns here
);


INSERT INTO work.school
  ( -- columns to insert data into
  id, name, address
  )
VALUES
  (
    1, "中文1号", "湖南"
),
  (  
    2, "中文2号", "深圳"
);

 
 
 
