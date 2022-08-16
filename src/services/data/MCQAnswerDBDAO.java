package services.data;

import datamodel.MCQAnswer;
import datamodel.MCQChoice;
import datamodel.Quiz;
import datamodel.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MCQAnswerDBDAO {
    private static String SEARCH_QUERY = "select id, title from MCQAnswer where id=? or title is like ?";
    private static String INSERT_QUERY = "INSERT into MCQAnswer (title, mcq_choice_id) VALUES (?, ?)";
    private static String UPDATE_QUERY = "UPDATE MCQAnswer SET title=?, mcq_choice_id=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from MCQAnswer where id=?";

    public static void create(MCQAnswer mcqAnswer) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setArray(1, (Array) mcqAnswer.getMcq_choice());
            preparedStatement.setArray(2, (Array) mcqAnswer.getStudent());
            preparedStatement.setArray(3, (Array) mcqAnswer.getQuiz());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static List<MCQAnswer> MCQAnswerList = new ArrayList<MCQAnswer>();

    public static List<MCQAnswer> search(MCQAnswer mcqAnswer) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setArray(1, (Array) mcqAnswer.getMcq_choice());
            preparedStatement.setArray(2, (Array) mcqAnswer.getStudent());
            preparedStatement.setArray(3, (Array) mcqAnswer.getQuiz());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                MCQAnswer newMcqAnswer = new MCQAnswer();
                newMcqAnswer.setMcq_choice((MCQChoice) searchResult.getArray("mcq_answer_id"));
                newMcqAnswer.setStudent((Student) searchResult.getArray("Student Name"));
                newMcqAnswer.setQuiz((Quiz) searchResult.getArray("Quiz"));
                MCQAnswerList.add(newMcqAnswer);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MCQAnswerList;
    }

    public static int update(MCQAnswer mcqAnswer) {
        int mcqAnswerUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setArray(1, (Array) mcqAnswer.getMcq_choice());
            preparedStatement.setArray(2, (Array) mcqAnswer.getStudent());
            preparedStatement.setArray(3, (Array) mcqAnswer.getQuiz());
            mcqAnswerUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mcqAnswerUpdate;
    }

    public static int delete(MCQAnswer mcqAnswer){
        int mcqAnswerDelete = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setArray(1, (Array) mcqAnswer.getMcq_choice());
            mcqAnswerDelete = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return mcqAnswerDelete;
    }


}
