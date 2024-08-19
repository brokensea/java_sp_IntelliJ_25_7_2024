package de.supercode.dAY37_13_8_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLMain {
    public static void main(String[] args) {
        String url = "jdbc:h2:/home/lalagum/h2datenBank/13_8_2024/supcodeUebungDB";
        String user = "sa";
        String password = "Cgte1234@"; // 替换为你的实际密码

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // 1. 创建表 Directors 和 Movies
            String createDirectorsTable = "CREATE TABLE IF NOT EXISTS Directors (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "Vorname VARCHAR(255), " +
                    "Nachname VARCHAR(255));";
            stmt.execute(createDirectorsTable);

            String createMoviesTable = "CREATE TABLE IF NOT EXISTS Movies (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "Titel VARCHAR(255), " +
                    "Genre VARCHAR(255), " +
                    "ReleaseYear INT, " +
                    "DirectorID INT, " +
                    "FOREIGN KEY (DirectorID) REFERENCES Directors(ID));";
            stmt.execute(createMoviesTable);

            // 2. 插入数据到 Directors 和 Movies
            String insertDirectors = "INSERT INTO Directors (Vorname, Nachname) VALUES ('Steven', 'Spielberg'), ('Christopher', 'Nolan');";
            stmt.execute(insertDirectors);

            String insertMovies = "INSERT INTO Movies (Titel, Genre, ReleaseYear, DirectorID) VALUES " +
                    "('Jurassic Park', 'Adventure', 1993, 1), " +
                    "('Inception', 'Sci-Fi', 2010, 2);";
            stmt.execute(insertMovies);

            // 3. 查询所有电影
            String selectAllMovies = "SELECT * FROM Movies;";
            ResultSet rsAllMovies = stmt.executeQuery(selectAllMovies);
            System.out.println("Alle Filme:");
            while (rsAllMovies.next()) {
                System.out.println(rsAllMovies.getString("Titel") +
                        " (" + rsAllMovies.getInt("ReleaseYear") + ")");
            }

            // 4. 查询特定类型的电影
            String genre = "Adventure";
            String selectByGenre = "SELECT * FROM Movies WHERE Genre = '" + genre + "';";
            ResultSet rsByGenre = stmt.executeQuery(selectByGenre);
            System.out.println("\nFilme des Genres " + genre + ":");
            while (rsByGenre.next()) {
                System.out.println(rsByGenre.getString("Titel"));
            }

            // 5. 查询1995年之后上映的电影
            String selectAfter1995 = "SELECT * FROM Movies WHERE ReleaseYear > 1995;";
            ResultSet rsAfter1995 = stmt.executeQuery(selectAfter1995);
            System.out.println("\nFilme nach 1995:");
            while (rsAfter1995.next()) {
                System.out.println(rsAfter1995.getString("Titel"));
            }

            // 6. 查询所有导演
            String selectAllDirectors = "SELECT * FROM Directors;";
            ResultSet rsAllDirectors = stmt.executeQuery(selectAllDirectors);
            System.out.println("\nAlle Regisseure:");
            while (rsAllDirectors.next()) {
                System.out.println(rsAllDirectors.getString("Vorname") + " " + rsAllDirectors.getString("Nachname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
