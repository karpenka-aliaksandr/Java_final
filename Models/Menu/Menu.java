package Models.Menu;
 
import java.util.LinkedHashMap;

public class Menu {
    private LinkedHashMap<Integer,PunktMenu> puncts;

    public Menu() {
        this.puncts = new LinkedHashMap<>();
    }

    public void addPunkt(Integer number, PunktMenu punktMenu) {
        puncts.put(number, punktMenu);
    }

    public void clear() {
        this.puncts = new LinkedHashMap<>();
        
    }

    public PunktMenu getPunkt(Integer number){
        if (puncts.containsKey(number)){
           return this.puncts.get(number);
        } else {
            return null;
        }
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!puncts.isEmpty())
        sb.append("Меню:\n");
        for (Integer key : puncts.keySet()) {
            sb.append(String.format("%d. %s\n", key, puncts.get(key)));
        }
        return sb.toString();
    }
}
