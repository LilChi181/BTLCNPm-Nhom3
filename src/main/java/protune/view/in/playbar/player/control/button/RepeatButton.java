package protune.view.in.playbar.player.control.button;

import protune.Init;
import protune.model.Constant;
import protune.view.in.playbar.player.control.ControlButton;

public class RepeatButton extends ControlButton {
    int repeat = 0;
    public RepeatButton(String iconPath) {
        super(iconPath);
        this.setOnMouseClicked(e -> {
            if(repeat == 0){
                repeat = 1;
                this.setIcon(Constant.repeatOnceIconPath);
            }
            else if(repeat == 1){
                repeat = Constant.INF;
                this.setIcon(Constant.repeatOnIconPath);
            }
            else{
                repeat = 0;
                this.setIcon(Constant.repeatOffIconPath);
            }
            Init.playBar.setRepeat(repeat);
        });
    }
}
