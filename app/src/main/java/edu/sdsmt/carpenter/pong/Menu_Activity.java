package edu.sdsmt.carpenter.pong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.sdsmt.carpenter.pong.Listeners.OnClickListeners;

public class Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        findViewById(R.id.play_button).setOnClickListener(new OnClickListeners().new PlayOnClickListener(Menu_Activity.this));
        findViewById(R.id.inst_button).setOnClickListener(new OnClickListeners().new InstrOnClickListener(Menu_Activity.this));
        findViewById(R.id.opti_button).setOnClickListener(new OnClickListeners().new OptionsOnClickListener(Menu_Activity.this));
        findViewById(R.id.quit_button).setOnClickListener(new OnClickListeners().new QuitOnClickListener());

    }

}
