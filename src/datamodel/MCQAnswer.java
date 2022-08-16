package datamodel;

public class MCQAnswer {
    private MCQChoice mcq_choice;
    private Quiz quiz;
    private Student student;

    public MCQChoice getMcq_choice() {
        return mcq_choice;
    }

    public void setMcq_choice(MCQChoice mcq_choice) {
        this.mcq_choice = mcq_choice;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
