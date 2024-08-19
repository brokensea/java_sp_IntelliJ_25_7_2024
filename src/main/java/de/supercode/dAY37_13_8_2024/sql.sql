-- 创建 Directors 表
CREATE TABLE IF NOT EXISTS Directors (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Vorname VARCHAR(255),
    Nachname VARCHAR(255)
);

-- 创建 Movies 表
CREATE TABLE IF NOT EXISTS Movies (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Titel VARCHAR(255),
    Genre VARCHAR(255),
    ReleaseYear INT,
    DirectorID INT,
    FOREIGN KEY (DirectorID) REFERENCES Directors(ID)
);

-- 插入导演数据
INSERT INTO Directors (Vorname, Nachname)
VALUES
    ('Steven', 'Spielberg'),
    ('Christopher', 'Nolan');

    -- 插入电影数据
INSERT INTO Movies (Titel, Genre, ReleaseYear, DirectorID)
VALUES
    ('Jurassic Park', 'Adventure', 1993, 1),
    ('Inception', 'Sci-Fi', 2010, 2);

    -- 查询所有电影
SELECT * FROM Movies;

-- 查询特定类型的电影
SELECT * FROM Movies
WHERE Genre = 'Adventure';

-- 查询1995年之后上映的电影
SELECT * FROM Movies
WHERE ReleaseYear > 1995;

-- 查询所有导演
SELECT * FROM Directors;


