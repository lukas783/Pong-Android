/**
 * I would just like to note, the majority of this code and the color picker layout comes from
 * skydoves and his repository at https://github.com/skydoves/ColorPickerPreference
 *
 */


package edu.sdsmt.carpenter.pong;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.FlagView;

public class CustomFlag extends FlagView {
    private TextView textView;
    private View view;

    public CustomFlag(Context context, int layout) {
        super(context, layout);
        textView = findViewById(R.id.flag_color_code);
        view = findViewById(R.id.flag_color_layout);
    }

    @Override
    public void onRefresh(ColorEnvelope color) {
        textView.setText("#"+String.format("%06X", (0xFFFFFF & color.getColor())));
        view.setBackgroundColor(color.getColor());
    }
}
