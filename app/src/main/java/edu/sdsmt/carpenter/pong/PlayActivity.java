package edu.sdsmt.carpenter.pong;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import edu.sdsmt.carpenter.pong.Game.PongView;

public class PlayActivity extends Activity {

    PongView pongView;

    @Override
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pongView = new PongView(this);
        setContentView(pongView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pongView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pongView.pause();
    }


}
