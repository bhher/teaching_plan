package a0326.coffee1;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Coffee> menuMap;
    
    public Menu() {
        menuMap = new HashMap<>();
        initializeMenu();
    }
    
    private void initializeMenu() {
        menuMap.put("Americano", new Coffee("Americano", 3000));
        menuMap.put("Latte", new Coffee("Latte", 4000));
        menuMap.put("Mocha", new Coffee("Mocha", 4500));
        menuMap.put("Espresso", new Coffee("Espresso", 2500));
    }
    
    public void displayMenu() {
        System.out.println("\n메뉴");
        for(Coffee coffee : menuMap.values()) {
            System.out.println(coffee);
        }
    }
    
    public boolean containsCoffee(String name) {
        return menuMap.containsKey(name);
    }
    
    public Coffee getCoffee(String name) {
        return menuMap.get(name);
    }
    
    public int getPrice(String name) {
        Coffee coffee = menuMap.get(name);
        return coffee != null ? coffee.getPrice() : 0;
    }
}
