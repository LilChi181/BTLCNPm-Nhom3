package protune.view.in.playbar.player.control.button;

import protune.Init;
import protune.view.in.playbar.player.control.ControlButton;

public class NextButton extends ControlButton {
    public NextButton(String iconPath) {
        super(iconPath);
        this.setOnMouseClicked(e -> Init.playBar.playNext());
    }
}
