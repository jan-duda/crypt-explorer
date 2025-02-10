package cz.dudasoft.cryptexplorer.item;

public abstract class Item {
    private final String name;   // Povinný název předmětu
    private final String description;   // Volitelný popis předmětu

    // Konstruktor pro inicializaci názvu a popisu
    public Item(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
        this.description = description != null ? description : "";  // Pokud není popis, nastaví prázdný řetězec
    }

    // Getter pro název předmětu
    public String getName() {
        return name;
    }

    // Getter pro popis předmětu
    public String getDescription() {
        return description;
    }

    // Abstraktní metoda pro použití předmětu (bude implementována v konkrétních třídách)
    public abstract void use();
}
