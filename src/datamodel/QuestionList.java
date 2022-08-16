package datamodel;

import java.util.List;

public class QuestionList {
    private List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public QuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
