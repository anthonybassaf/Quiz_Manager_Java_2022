package datamodel;

public enum QuestionType {
   MCQ("mcq") , OPEN("open");
  private String name;
  private QuestionType(String name) {
      this.name = name;
  }

    @Override
    public String toString() {
        return name;
    }
}
