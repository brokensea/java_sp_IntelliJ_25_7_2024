SELECT * FROM DIRECTORS;
SELECT * FROM MOVIES;

-- Arbeite an deiner **Film-Datenbank mit Regisseur-Tabelle** von gestern weiter:

-- - **Filme gruppieren nach Genre:**
--     - Zeige die Anzahl der Filme pro Genre an
-- add one more Film to Directors 1, add two Films to Directors 2
INSERT INTO MOVIES (TITEL, GENRE, RELEASEYEAR, DIRECTORID)
VALUES ('E.T.', 'Adventure', 1982, 1);

INSERT INTO MOVIES (TITEL, GENRE, RELEASEYEAR, DIRECTORID)
VALUES ('Interstellar', 'Sci-Fi', 2014, 2),
       ('The Dark Knight', 'Action', 2008, 2);

SELECT Genre,
COUNT(ID) AS MovieCount
FROM MOVIES
GROUP BY Genre;


-- - **Durchschnittliches Erscheinungsjahr pro Genre:**
--     - Berechne das durchschnittliche Erscheinungsjahr für Filme in jedem Genre
SELECT Genre, AVG(RELEASEYEAR) AS `Durchschnittliches Erscheinungsjahr`
FROM MOVIES
GROUP BY Genre;



-- - **Top 5 Genres nach Anzahl der Filme:**
--     - Zeige die Top 5 Genres mit den meisten Filmen an
SELECT Genre, COUNT(ID) AS MovieCount
FROM MOVIES
GROUP BY Genre
ORDER BY MovieCount DESC
LIMIT 5;

-- - **Jahr mit den meisten Filmen:**
--     - Finde das Erscheinungsjahr mit den meisten veröffentlichten Filmen
-- add one film in 2008: New Movie
INSERT INTO MOVIES (TITEL, GENRE, RELEASEYEAR, DIRECTORID)
VALUES ('New Movie', 'Action', 2008, 1);

SELECT RELEASEYEAR, COUNT(ID) AS MovieCount
FROM MOVIES
GROUP BY RELEASEYEAR
ORDER BY MovieCount DESC
LIMIT 1;

-- andere Möglichkeit
WITH MovieCounts AS (
    SELECT RELEASEYEAR, COUNT(ID) AS MovieCount
    FROM MOVIES
    GROUP BY RELEASEYEAR
),
MaxCount AS (
    SELECT MAX(MovieCount) AS MaxMovieCount
    FROM MovieCounts
)
SELECT RELEASEYEAR, MovieCount
FROM MovieCounts
WHERE MovieCount = (SELECT MaxMovieCount FROM MaxCount);


-- - **Genres mit den meisten Filmen nach 2000:**
--     - Zeige die Genres mit den meisten Filmen, die nach dem Jahr 2000 veröffentlicht wurden
SELECT GENRE, COUNT(ID) AS MovieCount
FROM MOVIES
WHERE RELEASEYEAR > 2000
GROUP BY GENRE
ORDER BY MovieCount DESC
LIMIT 1;


-- - **Genres nach der Anzahl der Filme sortiert:**
--     - Sortiere die Genres nach der Anzahl der darin enthaltenen Filme, absteigend
SELECT GENRE, COUNT(ID) AS MovieCount
FROM MOVIES
GROUP BY GENRE
ORDER BY MovieCount DESC; --Descending


-- - **Genre mit den ältesten durchschnittlichen Erscheinungsjahren:**
--     - Finde das Genre, dessen Filme im Durchschnitt am ältesten sind
SELECT GENRE, AVG(RELEASEYEAR) AS DurchschnittJahren
FROM MOVIES
GROUP BY GENRE
ORDER BY DurchschnittJahren ASC --Ascending
LIMIT 1;