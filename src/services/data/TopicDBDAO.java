package services.data;

import datamodel.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDBDAO {
    private static String SEARCH_QUERY = "select id, name from TOPIC where id=? or name is like ?";
    private static String INSERT_QUERY = "INSERT into TOPIC (name) VALUES (?)";
    private static String UPDATE_QUERY = "UPDATE TOPIC SET name=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE from TOPIC where id=?";

    public static void create(Topic topic) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1, topic.getId());
            preparedStatement.setString(2, topic.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    List<Topic> TopicList = new ArrayList<Topic>();

    public List<Topic> search(Topic topic) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY);

            preparedStatement.setInt(1, topic.getId());
            preparedStatement.setString(2, topic.getName());
            ResultSet searchResult = preparedStatement.executeQuery();

            while (searchResult.next()) {
                Topic newTopic = new Topic("Topic");
                newTopic.setId(searchResult.getInt("id"));
                newTopic.setName(searchResult.getString("name"));
                TopicList.add(newTopic);


            }
            searchResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TopicList;
    }

    public static int update(Topic topic) {
        int topicUpdate = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setInt(1, topic.getId());
            preparedStatement.setString(2, topic.getName());
            topicUpdate = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicUpdate;
    }

    public static int delete(Topic topic){
        int deleted = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "131755");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1, topic.getId());
            deleted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error! " + e.getMessage());
        }
        return deleted;
    }

}
