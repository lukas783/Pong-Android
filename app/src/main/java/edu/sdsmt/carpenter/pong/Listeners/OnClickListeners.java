package edu.sdsmt.carpenter.pong.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.sdsmt.carpenter.pong.InstructionActivity;
import edu.sdsmt.carpenter.pong.OptionsActivity;
import edu.sdsmt.carpenter.pong.PlayActivity;

public class OnClickListeners {
    public class PlayOnClickListener implements View.OnClickListener {

        Context context;
        public PlayOnClickListener(Context c) { context = c; }

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(context, PlayActivity.class);
            context.startActivity(intent);
        }
    }

    public class InstrOnClickListener implements View.OnClickListener {

        Context context;
        public InstrOnClickListener(Context c) { context = c; }

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(context, InstructionActivity.class);
            context.startActivity(intent);
        }
    }

    public class OptionsOnClickListener implements View.OnClickListener {

        Context context;
        public OptionsOnClickListener(Context c) { context = c; }

        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(context, OptionsActivity.class);
            context.startActivity(intent);
        }
    }

    public class QuitOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View arg0) {
            System.exit(0);
        }
    }
}
