package services.data;

import datamodel.MCQChoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MCQChoiceDBDAO {
    private static String SEARCH_QUERY = "select id, title, question_id from MCQChoice where id=? or title is like ?";
    private static String INSERT_QUERY = "INSERT into MCQChoice (title, question_id) VALUES (?, ?)";
    private static String UPDATE_QUERY = "UPDATE MCQChoice SET title=?, question_id=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from MCQChoice where id=?";
    private static String SEARCH_BY_TITLE = "SELECT id from MCQChoice where title=?";

    public static void create(MCQChoice mcqChoice) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setString(1, mcqChoice.getStudent_answer());
            preparedStatement.setString(2, String.valueOf(mcqChoice.getChoice()));
            preparedStatement.setBoolean(3, mcqChoice.Is_valid());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    List<MCQChoice> MCQChoiceList = new ArrayList<MCQChoice>();

    public List<MCQChoice> search(MCQChoice mcqChoice) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setString(1, mcqChoice.getStudent_answer());
            preparedStatement.setString(2, String.valueOf(mcqChoice.getChoice()));
            preparedStatement.setBoolean(3, mcqChoice.Is_valid());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                MCQChoice newMcqChoice = new MCQChoice();
                newMcqChoice.setStudent_answer(searchResult.getString("Student Answer"));
                newMcqChoice.setChoice(searchResult.getString("MCQ Question"));
                newMcqChoice.setIs_valid(searchResult.getBoolean("Valid Answer"));
                MCQChoiceList.add(newMcqChoice);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MCQChoiceList;
    }

    public int SearchByTitle(MCQChoice mcqChoice) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_TITLE);

            preparedStatement.setString(1, mcqChoice.getStudent_answer());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                mcqChoice.setStudent_answer(searchResult.getString("Student Answer"));

            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int update(MCQChoice mcqChoice) {
        int mcqChoiceUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1, mcqChoice.getStudent_answer());
            preparedStatement.setString(2, String.valueOf(mcqChoice.getChoice()));
            preparedStatement.setBoolean(3, mcqChoice.Is_valid());
            mcqChoiceUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mcqChoiceUpdate;
    }

    public static int delete(MCQChoice mcqChoice){
        int mcqChoiceDelete = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setString(1, mcqChoice.getStudent_answer());
            mcqChoiceDelete = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return mcqChoiceDelete;
    }


}
