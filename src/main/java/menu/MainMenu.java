package menu;

import entity.User;

public class MainMenu {
    private User token;
    private final LoginMenu loginMenu;
    private final ItemsMenu itemsMenu;

    public MainMenu(LoginMenu loginMenu, ItemsMenu itemsMenu) {
        this.loginMenu = loginMenu;
        this.itemsMenu = itemsMenu;
    }

    public void showMainMenu() {
        while (true) {
            loginMenu.showLoginMenu();
            token = loginMenu.getToken();
            if (token != null) {
                itemsMenu.showItemMenu(token);
                token = null;
            } else {
                break;
            }
        }
        System.out.println("Exiting the application...");
    }
}
