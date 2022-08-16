package datamodel;

import java.util.ArrayList;
import java.util.List;

public class TopicList {

    private List<Topic> topicsList = null;

    public void setTopicsList(List<Topic> topicsList) {
        this.topicsList = topicsList;
    }

    public void setTopicsList(String list) {
        this.topicsList = new ArrayList<Topic>();
        String[] parts = list.split(",");
        for (String string : parts) {
            topicsList.add(new Topic(string));
        }
    }

    public List<Topic> getTopicsList() {
        return topicsList;
    }


}
