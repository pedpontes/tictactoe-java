package tictactoe;

public class Player {
    private String name;
    private char tag;

    String getName() {
        return this.name;
    }

    char getTag() {
        return this.tag;
    }

    void setName(String name) {
        this.name = name;
    }

    void setTag(char tag) {
        this.tag = tag;
    }

}
