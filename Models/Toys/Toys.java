package Models.Toys;

import java.util.ArrayList;

public class Toys {
    private ArrayList<Toy> toys;
    private int count;

    public Toys() {
        count = 0;
        this.toys = new ArrayList<>();
    }

    public ArrayList<Toy> getToysToArrayList() {
        return toys;
    }

    public boolean addToy(Toy toy) {
        return toys.add(toy);
    }
    
    public boolean removeToy(Toy toy) {
        return toys.remove(toy);
    }
    public boolean removeToy(Integer id) {
        boolean result = false;
        for (Toy toy : toys) {
            if (toy.getId() == id) result = toys.remove(toy);
        }
        return result;
    }
    
    public void removeAllToy() {
        count = 0;
        this.toys = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (! toys.isEmpty())
            str.append("ВИТРИНА ИГРУШЕК:\n");
        for (Toy toy : toys) {
            str.append(toy + "\n");
        }
        return str.toString();
    }

    public ArrayList<Integer> getIdsToArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Toy toy : toys) {
            arrayList.add(toy.getId());
        }
        return arrayList;
    }
}

