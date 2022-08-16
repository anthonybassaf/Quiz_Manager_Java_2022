package datamodel;

public class Answer {
    private int id;
    private String text;
    private Quiz quiz;
    private Question question;
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer \t[text=" + text + ", \n quiz=" + quiz + ", \n question=" + question + "\n]";
    }
}
