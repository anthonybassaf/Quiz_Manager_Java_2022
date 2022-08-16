package datamodel;

import java.util.ArrayList;

public class Question {

   private String question;
   private ArrayList<TopicList> topics;
   private Integer difficulty;
   private QuestionType type;

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public ArrayList<TopicList> getTopics() {
      return topics;
   }

   public void setTopics(ArrayList<TopicList> topics) {this.topics = topics; }

   public Integer getDifficulty() {
      return difficulty;
   }

   public void setDifficulty(Integer difficulty) {
      this.difficulty = difficulty;
   }

   public QuestionType getType() {
      return type;
   }

   public void setType(QuestionType type) {
      this.type = type;
   }
}
