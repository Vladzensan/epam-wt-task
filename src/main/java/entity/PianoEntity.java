package entity;

import java.util.List;

public class PianoEntity extends Entity{
    private String name;
    private int price;
    private int pianoId;

    public PianoEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPianoId() {
        return pianoId;
    }

    public void setPianoId(int pianoId) {
        this.pianoId = pianoId;
    }
}
