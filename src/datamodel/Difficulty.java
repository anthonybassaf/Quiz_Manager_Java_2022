package datamodel;

public enum Difficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3),
    EXPERT(4);

    private Integer difficulty;

    private Difficulty(Integer difficulty){
        this.difficulty = difficulty;
    }

    public Integer getDifficulty() {
        return this.difficulty;
    }
}

