package Models.Toys;

public class Toy {
    
    private Integer id;
    private String name;
    private Integer count;
    private Byte frequency;

    public Toy(Integer id, String name, Integer count, Byte frequency) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.frequency = frequency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getFrequency() {
        return frequency;
    }

    public void setFrequency(Byte frequency) {
        this.frequency = frequency;
    }


    @Override
    public String toString() {
        return
                "id: " + String.format("%-5d", id) + 
                " Наименование: " + String.format("%-20s",name) + 
                " Количество: " + String.format("%-5d",count) +
                " Вероятность выпадения в розыгрыше: " + frequency + " к 100";
    }
}

