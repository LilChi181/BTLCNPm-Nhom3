package protune.view.in.playbar.player.control;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import protune.model.Constant;
import protune.view.in.playbar.player.control.button.*;

public class ControlZone extends FlowPane {
    PlayButton playButton;
    NextButton nextButton;
    PreviousButton previousButton;
    RepeatButton repeatButton;
    ShuffleButton shuffleButton;
    public ControlZone(){
        this.setPrefSize(480, 50);
        this.setAlignment(Pos.CENTER);
        this.setHgap(20);
        playButton = new PlayButton(Constant.playIconPath);
        nextButton = new NextButton(Constant.nextIconPath);
        previousButton = new PreviousButton(Constant.previousIconPath);
        repeatButton = new RepeatButton(Constant.repeatOffIconPath);
        shuffleButton = new ShuffleButton(Constant.shuffleOffIconPath);

        this.getChildren().addAll( shuffleButton, previousButton, playButton, nextButton, repeatButton);
    }

    public void init(){
        playButton.init();
    }
}
