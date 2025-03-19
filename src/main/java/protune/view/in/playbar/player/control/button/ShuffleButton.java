package protune.view.in.playbar.player.control.button;

import protune.Init;
import protune.model.Constant;
import protune.view.in.playbar.player.control.ControlButton;

public class ShuffleButton extends ControlButton {
    boolean active = false;
    public ShuffleButton(String iconPath) {
        super(iconPath);
        this.setOnMouseClicked(e -> {
            if (active) {
                this.setIcon(Constant.shuffleOffIconPath);
            }
            else{
                this.setIcon(Constant.shuffleOnIconPath);
            }
            active ^= true;
            Init.playBar.setShuffle(active);
        });
    }
}
