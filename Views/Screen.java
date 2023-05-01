package Views;

import Models.Menu.Menu;

public class Screen {
    protected View view;
    protected String data;
    protected Menu menu;
    protected String bar;

    public Screen(View view, String data, Menu menu) {
        this.view = view;
        this.data = data;
        this.menu = menu;
        this.bar = "";
    }

    public Screen(View view, String data) {
        this(view, data, new Menu());
    }

    public Screen(View view, Menu menu) {
        this(view, "", menu);
    }

    public Screen(View view) {
        this(view, "");
    }

    public String getScreen() {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[H\033[J");
        sb.append(data);
        if (! data.isEmpty()) sb.append("\n");
        sb.append(menu);
        if (! menu.toString().isEmpty()) sb.append("\n");
        sb.append(bar);
        return sb.toString();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        show();
    }

    public void setData(String data) {
        this.data = data;
        show();
    }

    public void setBar(String bar) {
        this.bar = bar;
        show();
    }

    public Menu getMenu() {
        return menu;
    }

    public void show() {
        view.print(getScreen());
    }

    public View getView() {
        return this.view;
    }
}
