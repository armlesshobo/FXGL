/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package sandbox;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.gameplay.ScoreData;
import com.almasb.fxgl.scene.ProgressDialog;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.app.DSLKt.onKeyDown;

/**
 *
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class RESTClientSample extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("RESTClientSample");
        settings.setVersion("0.1");
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setCloseConfirmation(false);
    }

    @Override
    protected void initInput() {
        // TODO: we shouldn't allow closing game during progress dialog operations?

        onKeyDown(KeyCode.Q, "Get Top", () -> {
            getGameplay().getLeaderboard()
                    .loadTopTask(5)
                    .onSuccess(scores -> scores.forEach(System.out::println))
                    .executeAsyncWithDialogFX(new ProgressDialog("Connecting to FXGL server"));
        });

        onKeyDown(KeyCode.E, "Put New Score", () -> {
            getGameplay().getLeaderboard()
                    .postNewScoreTask(new ScoreData("AlmasB", 1500))
                    .onSuccess(n -> System.out.println("Success put"))
                    .executeAsyncWithDialogFX(new ProgressDialog("Uploading to FXGL server"));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}