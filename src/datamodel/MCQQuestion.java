package datamodel;

public class MCQQuestion extends Question {
    private String choice1;
    private String choice2;
    private String choice3;
    private String Answer;
    private MCQChoice mcq_choice;

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public MCQChoice getMcq_choice() {
        return mcq_choice;
    }

    public void setMcq_choice(MCQChoice mcq_choice) {
        this.mcq_choice = mcq_choice;
    }
}
