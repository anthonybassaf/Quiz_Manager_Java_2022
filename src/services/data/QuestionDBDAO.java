package services.data;

import datamodel.Question;
import datamodel.QuestionType;
import datamodel.Topic;
import datamodel.TopicList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDBDAO {
    private static String SEARCH_QUERY = "select id, title, question_type_id, topic_id from QUESTION where id=? " +
            "or title like ? or question_type_id=? or topic_id=?";
    private static String SEARCH_BY_TOPIC_ID = "select id, title, question_id, topic_id from QUESTION where topic_id=?";
    private static String INSERT_QUERY = "INSERT into QUESTION (title, question_type_id, topic_id, difficulty_level)" +
            "VALUES (?, ?, ?, ?)";
    private static String UPDATE_QUERY = "UPDATE QUESTION SET title=?, question_type_id=?, topic_id=?, " +
            "difficulty_level=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from QUESTION where id=?";
    private static String SEARCH_BY_TITLE = "SELECT id from QUESTION where title=?";

    public static void create(Question question) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setArray(2, (Array) question.getTopics());
            preparedStatement.setInt(3,question.getDifficulty());
            preparedStatement.setString(4, QuestionType.values().toString());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static List<Question> Questions = new ArrayList<>();

    public List<Question> search(Question question) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setArray(2, (Array) question.getTopics());
            preparedStatement.setInt(3,question.getDifficulty());
            preparedStatement.setString(4, QuestionType.values().toString());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                Question question1 = new Question();
                question1.setQuestion(searchResult.getString("Question"));
                question1.setTopics((ArrayList<TopicList>) searchResult.getArray("Topics"));
                question1.setDifficulty(searchResult.getInt("Difficulty"));
                question1.setType(QuestionType.valueOf(searchResult.getString("Type")));
                Questions.add(question1);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Questions;
    }

    public static List<Question> SearchByTopicId(Question question) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_TOPIC_ID);

            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setArray(2, (Array) question.getTopics());
            preparedStatement.setInt(3,question.getDifficulty());
            preparedStatement.setString(4, QuestionType.values().toString());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Question question_by_topic = new Question();
                question.setQuestion(String.valueOf(result.getString("Question")));
                question.setTopics((ArrayList<TopicList>) result.getArray("Topics"));
                question.setDifficulty(result.getInt("Difficulty"));
                question.setType(QuestionType.valueOf(result.getString("Type")));
                Questions.add(question);

            }
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Questions;
    }

    public static int SearchByTitle(Question question) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_TITLE);

            preparedStatement.setString(1,question.getQuestion());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                question.setQuestion(searchResult.getString("Question"));

            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int update(Question question) {
        int questionUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setArray(2, (Array) question.getTopics());
            preparedStatement.setInt(3,question.getDifficulty());
            preparedStatement.setString(4, QuestionType.values().toString());
            questionUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionUpdate;
    }

    public static int delete(Question question){
        int questionDelete = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setString(1, question.getQuestion());
            questionDelete = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return questionDelete;
    }


}
