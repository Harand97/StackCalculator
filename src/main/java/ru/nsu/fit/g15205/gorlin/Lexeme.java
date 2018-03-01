package ru.nsu.fit.g15205.gorlin;

public class Lexeme
{
    private LexemeType type;
    private String text;

    public Lexeme(LexemeType type, String text) {
        this.text = text;
        this.type = type;
    }

    public LexemeType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
