package services.data;

import datamodel.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDBDAO {
    private static String SEARCH_QUERY = "select id, text from ANSWER where id=? or text is like ?";
    private static String INSERT_QUERY = "INSERT into ANSWER (text) VALUES (?)";
    private static String UPDATE_QUERY = "UPDATE ANSWER SET text=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from ANSWER where id=?";

    public static void create(Answer answer) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1, answer.getId());
            preparedStatement.setString(2, answer.getText());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static List<Answer> AnswerList = new ArrayList<Answer>();

    public static List<Answer> search(Answer answer) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setInt(1, answer.getId());
            preparedStatement.setString(2, answer.getText());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                Answer newAnswer = new Answer("Text Answer");
                newAnswer.setId(searchResult.getInt("id"));
                newAnswer.setText(searchResult.getString("text"));
                AnswerList.add(newAnswer);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AnswerList;
    }

    public static int update(Answer answer) {
        int textAnswerUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setInt(1, answer.getId());
            preparedStatement.setString(2, answer.getText());
            textAnswerUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return textAnswerUpdate;
    }

    public static int delete(Answer answer){
        int textAnswerDelete = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1, answer.getId());
            textAnswerDelete = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return textAnswerDelete;
    }
}
