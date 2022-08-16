package datamodel;

public class MCQChoice {

    private String[] choice;
    private String student_answer;
    private boolean is_valid;
    private MCQQuestion mcq_question;

    public MCQQuestion getMcq_question() {
        return mcq_question;
    }

    public void setMcq_question(MCQQuestion mcq_question) {
        this.mcq_question = mcq_question;
    }

    public MCQChoice() {
        this.setChoice(choice);
    }

    public String[] getChoice() {
        return choice;
    }
    private void setChoice(String[] choice) {
        this.choice = choice;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public boolean Is_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public void setChoice(String mcq_question) {
    }
}