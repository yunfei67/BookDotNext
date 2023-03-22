CREATE SCHEMA IF NOT EXISTS BookDotNext;
USE BookDotNext;

 

DROP TABLE IF EXISTS UniversalRecommendation;
DROP TABLE IF EXISTS Votes;
DROP TABLE IF EXISTS BookReview;
DROP TABLE IF EXISTS TopTenLists;
DROP TABLE IF EXISTS PersonalRecommend;
DROP TABLE IF EXISTS SearchHistory;
DROP TABLE IF EXISTS BookInfo;
DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Publishers;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS Persons;



CREATE TABLE Persons (
    UserId INT AUTO_INCREMENT,
    UserName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    PassWord VARCHAR(255) NOT NULL,
    Permission BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk_Persons_UserId PRIMARY KEY (UserId)
);



CREATE TABLE Administrators (
    UserId INT,
    LastLogin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_Administrators_UserId PRIMARY KEY(UserId),
    CONSTRAINT fk_Administrators_UserId FOREIGN KEY(UserId)
        REFERENCES Persons(UserId)
        ON UPDATE CASCADE ON DELETE CASCADE
); 

CREATE TABLE Users (
    UserId INT,
    DOB DATE NOT NULL,
    CONSTRAINT Pk_Users_UserId PRIMARY KEY (UserId),
    CONSTRAINT Fk_Users_UserId FOREIGN KEY (UserId)
        REFERENCES Persons(UserId)
        ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE SearchHistory (
    SearchId INT AUTO_INCREMENT,
    UserId INT,
    VisitedBooks INT NOT NULL,
    SearchTime TIMESTAMP, #DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT Pk_SearchHistory_SearchId PRIMARY KEY (SearchId),
    CONSTRAINT Fk_SearchHistory_UserId FOREIGN KEY (UserId)
        REFERENCES Users(UserId)
        ON UPDATE CASCADE ON DELETE CASCADE
);


 

CREATE TABLE Publishers(
    PublisherName VARCHAR(255) NOT NULL,
    CountryCode VARCHAR(255),
    CONSTRAINT pk_Publishers_PublisherName PRIMARY KEY(PublisherName)    
);


 

CREATE TABLE Author (
    
    AuthorName VARCHAR(255) NOT NULL,
    CountryCode VARCHAR(255),
    CONSTRAINT pk_Authors_AuthorName PRIMARY KEY (AuthorName)
); 



CREATE TABLE BookInfo(
    BookId INT AUTO_INCREMENT,
    BookTitle VARCHAR(255) NOT NULL ,
    -- PublishedDate YEAR NOT NULL,
    PublishedDate INT NOT NULL,
    Description LONGTEXT,
    InfoLink LONGTEXT,
    -- Categories ENUM('Fiction', 'Comics & Graphic Novels', 'African Americans', 'American literature', 'Architecture',
--                     'Biography & Autobiography', 'Religion', 'Aeronautics', 'American fiction', 'Art',
--                     'Social Science', 'Juvenile Nonfiction', 'Actors', 'Antiques & Collectibles',
--                     'Technology & Engineering', 'Adventure stories', 'Business & Economics'),
    Categories VARCHAR(255),           
    PublisherName VARCHAR(255),
    AuthorName VARCHAR(255),
    ImageLink VARCHAR(255),
    CONSTRAINT pk_BookInfo_BookId PRIMARY KEY(BookId),
	CONSTRAINT fk_BookInfo_AuthorName FOREIGN KEY(AuthorName)
      REFERENCES Author(AuthorName)
      ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_BookInfo_PublisherName FOREIGN KEY(PublisherName)
      REFERENCES Publishers(PublisherName)
      ON UPDATE CASCADE ON DELETE CASCADE
);

 


CREATE TABLE PersonalRecommend (
RecomID INT AUTO_INCREMENT,
UserId INT,
BookId INT,  
Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_PersonalRecommend_RecomID
  PRIMARY KEY (RecomID),
CONSTRAINT fk_PersonalRecommend_UserId
  FOREIGN KEY (UserId)
  REFERENCES Users(UserId)
  ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_PersonalRecommend_BookId
  FOREIGN KEY (BookId)
  REFERENCES BookInfo(BookId)
  ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE TopTenLists (
TopTenListId INT AUTO_INCREMENT,
UserId INT,
BookId INT,
Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_TopTenLists_TopTenListId
  PRIMARY KEY (TopTenListId),
CONSTRAINT fk_TopTenLists_UserId
  FOREIGN KEY (UserId)
  REFERENCES Users(UserId)
  ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_TopTenListsw_BookId
  FOREIGN KEY (BookId)
  REFERENCES BookInfo(BookId)
  ON UPDATE CASCADE ON DELETE CASCADE
);

 

CREATE TABLE BookReview (
ReviewId INT AUTO_INCREMENT,
ReviewScore DECIMAL(2,1),
Content LONGTEXT,
Created Date,
UserId INT,
BookId INT,
CONSTRAINT pk_BookReview_ReviewId
  PRIMARY KEY (ReviewId),
CONSTRAINT fk_BookReview_UserId
  FOREIGN KEY (UserId)
  REFERENCES Users(UserId)
  ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_BookReview_BookId
  FOREIGN KEY (BookId)
  REFERENCES BookInfo(BookId)
  ON UPDATE CASCADE ON DELETE CASCADE
);

 

CREATE TABLE Votes(
  VoteId INT AUTO_INCREMENT NOT NULL,
  UserId INT, 
  BookId INT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  
  CONSTRAINT pk_Votes_VoteId PRIMARY KEY(VoteId),
  
  CONSTRAINT fk_Votes_UserId FOREIGN KEY(UserId)
  REFERENCES Users(UserId) ON UPDATE CASCADE ON DELETE SET NULL,
  
  CONSTRAINT fk_Votes_BookId FOREIGN KEY(BookId)
  REFERENCES BookInfo(BookId) ON UPDATE CASCADE ON DELETE CASCADE
  );


CREATE TABLE UniversalRecommendation(
   UniversalRecomId INT AUTO_INCREMENT NOT NULL,
   Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   VoteId INT,
   BookId INT,
   #Primary Key
   CONSTRAINT pk_UniversalRecommendation_UniversalRecomId PRIMARY KEY(UniversalRecomId),
   #Foreign Key
   CONSTRAINT fk_UniversalRecommendation_VoteId FOREIGN KEY(VoteId)
   REFERENCES Votes(VoteId) ON UPDATE CASCADE ON DELETE SET NULL,
   
   CONSTRAINT fk_UniversalRecommendation_BookId FOREIGN KEY(BookId)
   REFERENCES BookInfo(BookId) ON UPDATE CASCADE ON DELETE CASCADE
   );
   
-- USE BookDotNext;
-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/Persons.csv' INTO TABLE Persons
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   

-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/Users.csv' INTO TABLE Users
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES
--   (Userid, @var1) 
--   SET DOB = STR_TO_DATE(@var1, '%m/%d/%Y');
--   

-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/Administrators.csv' INTO TABLE Administrators
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   

-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/SearchHistory.csv' INTO TABLE SearchHistory
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES
--   (SearchId,UserId,VisitedBooks,@var_col1)
-- SET SearchTime = STR_TO_DATE(@var_col1,'%Y-%m-%d %H:%i:%s');


-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS 5200/Project/MockData 032023/Author.csv' INTO TABLE Author
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   

-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/Publishers.csv' INTO TABLE Publishers
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--    



-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/BookInfo.csv' INTO TABLE BookInfo
--   CHARACTER SET latin1
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;



-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/BookReview.csv' INTO TABLE BookReview
--   CHARACTER SET latin1
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES
--   (ReviewId,ReviewScore,Content,@var_col1,UserId,BookId)
--   SET Created = STR_TO_DATE(@var_col1,'%Y-%m-%d');
--   
--  


-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/Votes.csv' INTO TABLE Votes
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES
--   (VoteId,UserId,BookId,@var_col1)
--   SET Created = STR_TO_DATE(@var_col1,'%Y-%m-%d %H:%i:%s');



-- -- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/CSV/final/UniversalRecommendation.csv' INTO TABLE UniversalRecommendation
-- --   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
-- --   LINES TERMINATED BY '\n'
-- --   IGNORE 1 LINES
-- --   (UniversalRecomId,@var_col1,VoteId, BookId)
-- --   SET Created = STR_TO_DATE(@var_col1,'%Y/%m/%d %H:%i:%s');



-- LOAD DATA LOCAL INFILE '/Users/huizou/Desktop/Northeastern/CS\ 5200/Project/MockData\ 032023/TopTenLists.csv' INTO TABLE TopTenLists
--   FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   
--   -- Assign SearchHistory.VisitedBooks in [0, 50]
-- -- UPDATE SearchHistory
-- -- SET VisitedBooks = FLOOR(0 + RAND() * 50)
-- -- WHERE SearchId < 300;
  
  