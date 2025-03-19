package protune.view.in.navbar.item;

import protune.Init;

import java.io.FileNotFoundException;

public class MyLocalItem extends NavItem{
    public MyLocalItem(String iconPath, String content) throws FileNotFoundException {
        super(iconPath, content);
        this.setOnMouseClicked(e -> Init.localPane.toFront());
    }
}
