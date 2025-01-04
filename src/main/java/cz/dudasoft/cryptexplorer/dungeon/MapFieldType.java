package cz.dudasoft.cryptexplorer.dungeon;

public enum MapFieldType {
    PLAYER('@', "Your character"),
    EMPTY('.', "An empty room."),
    TREASURE('T', "You see a pile of glittering treasure."),
    MONSTER('M', "A fierce monster blocks your path!"),
    EXIT('E', "You see the dungeon's exit."),
    START('S', "You stand at the dungeon entrance."),
    WALL('#', "You bounce into a wall");

    private final char symbol;
    private final String description;

    MapFieldType(char symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public static MapFieldType fromSymbol(char symbol) {
        for (MapFieldType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }
        return EMPTY; // Default to EMPTY if symbol is unrecognized
    }
}
