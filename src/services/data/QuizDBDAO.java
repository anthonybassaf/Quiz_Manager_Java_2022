package services.data;

import datamodel.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDBDAO {
    private static String SEARCH_QUERY = "select id, title from QUIZ where id=? or title is like ?";
    private static String INSERT_QUERY = "INSERT into QUIZ (title) VALUES (?)";
    private static String UPDATE_QUERY = "UPDATE QUIZ SET title=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from QUIZ where id=?";
    private static String QUIZ_BUILDER = "SELECT q.question_type, q.title, q.topic_id, q.difficulty_level, m.*"
            + "FROM QUESTIONS as q" + "JOIN MCQ_QUESTIONS as m ON q.id = m.question_id WHERE topic is like ?";

    public static void create(Quiz quiz) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1, quiz.getId());
            preparedStatement.setString(2, quiz.getTitle());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    List<Quiz> QuizList = new ArrayList<Quiz>();

    public List<Quiz> search(Quiz quiz) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setInt(1, quiz.getId());
            preparedStatement.setString(2, quiz.getTitle());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                Quiz newQuiz = new Quiz("Quiz Title");
                newQuiz.setId(searchResult.getInt("id"));
                newQuiz.setTitle(searchResult.getString("Title"));
                QuizList.add(newQuiz);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return QuizList;
    }

    public static int update(Quiz quiz) {
        int quizUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setInt(1, quiz.getId());
            preparedStatement.setString(2, quiz.getTitle());
            quizUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizUpdate;
    }

    public static int delete(Quiz quiz){
        int quizDelete = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1, quiz.getId());
            quizDelete = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return quizDelete;
    }

    public static int QuizBuilder(Quiz quiz) {
        int quizBuild = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(QUIZ_BUILDER);

            preparedStatement.setInt(1, quiz.getId());
            quizBuild = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return quizBuild;
    }

}
