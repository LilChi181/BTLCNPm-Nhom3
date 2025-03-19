package protune;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import protune.controller.auth.UserManager;
import protune.controller.inapp.SongListManager;
import protune.view.out.WaitStage;

import java.io.IOException;

public class App extends Application {

    WaitStage waitStage = new WaitStage();

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Init.moveCredential();

        UserManager.importList();

        Task<Void> panelTask = new Task<>() {
            @Override
            protected Void call(){
                Platform.runLater(() -> waitStage.show());
                SongListManager.getOnlineAudioList();
                return null;
            }

            @Override
            protected void succeeded() {
                SongListManager.addOnlineAudio();
                waitStage.close();
                Init.appStage.show();
            }
        };

        var t = new Thread(panelTask);
        t.start();
    }

    public static void main(String[] args) {
        launch();
    }
}