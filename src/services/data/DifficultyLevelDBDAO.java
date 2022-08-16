package services.data;

import datamodel.Difficulty_Level;
import datamodel.MCQAnswer;

import java.security.DigestInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DifficultyLevelDBDAO {
    private static String SEARCH_QUERY = "select id, title from DIFFICULTY where id=? or difficulty_level is like ?";
    private static String INSERT_QUERY = "INSERT into DIFFICULTY (difficulty_level) VALUES (?)";
    private static String UPDATE_QUERY = "UPDATE DIFFICULTY SET difficulty_level=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from DIFFICULTY where id=?";

    public static void create(Difficulty_Level difficultyLevel) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setString(1, difficultyLevel.getDifficulty_level());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    List<Difficulty_Level> DifficultyLevelList = new ArrayList<Difficulty_Level>();

    public List<Difficulty_Level> search(Difficulty_Level difficultyLevel) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setInt(1, difficultyLevel.getId());
            preparedStatement.setString(2, difficultyLevel.getDifficulty_level());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                Difficulty_Level newDifficultyLevel = new Difficulty_Level(05, "Difficulty");
                newDifficultyLevel.setId(searchResult.getInt("id"));
                newDifficultyLevel.setDifficulty_level(searchResult.getString("difficulty_level"));
                DifficultyLevelList.add(newDifficultyLevel);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DifficultyLevelList;
    }

    public static int update(Difficulty_Level difficultyLevel) {
        int difficultyLevelUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setInt(1, difficultyLevel.getId());
            preparedStatement.setString(2, difficultyLevel.getDifficulty_level());
            difficultyLevelUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return difficultyLevelUpdate;
    }

    public static int delete(Difficulty_Level difficultyLevel){
        int difficultyLevelDelete = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1, difficultyLevel.getId());
            difficultyLevelDelete = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return difficultyLevelDelete;
    }


}
