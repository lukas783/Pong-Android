package edu.sdsmt.carpenter.pong;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

import edu.sdsmt.carpenter.pong.Game.GameLogic;

public class OptionsActivity extends Activity {

    @Override
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_options);
        findViewById(R.id.ball_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(OptionsActivity.this);
                builder.setTitle("Ball Color Selector");
                builder.setFlagView(new CustomFlag(OptionsActivity.this, R.layout.flag_layout));
                builder.setPositiveButton(getString(R.string.confirm), new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {
                        TextView ballText = findViewById(R.id.ball_color_text);
                        ballText.setText("#"+colorEnvelope.getColorHtml());
                        TextView ballColor = findViewById(R.id.ball_color);
                        ballColor.setBackgroundColor(colorEnvelope.getColor());
                        GameLogic.ballColor = colorEnvelope.getColor();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        findViewById(R.id.paddle_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(OptionsActivity.this);
                builder.setTitle("Paddle Color Selector");
                builder.setFlagView(new CustomFlag(OptionsActivity.this, R.layout.flag_layout));
                builder.setPositiveButton(getString(R.string.confirm), new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {
                        TextView ballText = findViewById(R.id.paddle_color_text);
                        ballText.setText("#"+colorEnvelope.getColorHtml());
                        TextView ballColor = findViewById(R.id.paddle_color);
                        ballColor.setBackgroundColor(colorEnvelope.getColor());
                        GameLogic.paddleColor = colorEnvelope.getColor();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });


    }

}
