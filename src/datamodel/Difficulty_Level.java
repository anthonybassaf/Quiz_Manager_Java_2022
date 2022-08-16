package datamodel;

public class Difficulty_Level {
    private int id;
    private String difficulty_level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(String difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public Difficulty_Level(int id, String difficulty_level) {
        this.id = id;
        this.difficulty_level = difficulty_level;
    }

    @Override
    public String toString() {
        return "DifficultyLevel [id=" + id + ", difficulty_level=" + difficulty_level + "]";
    }
}
