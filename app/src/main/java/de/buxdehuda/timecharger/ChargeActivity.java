package de.buxdehuda.timecharger;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ChargeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_charge);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button button = (Button) this.findViewById(R.id.chargeButton);
        button.setOnTouchListener(new ButtonListener(this));

        ((Button) this.findViewById(R.id.chargeButton)).setText(R.string.chargeText);
        ((TextView) this.findViewById(R.id.remainingTimeTextView)).setText(this.getString(R.string.remainingTimeText, 0));
        ((CheckBox) this.findViewById(R.id.soundBox)).setText(R.string.soundText);
        ((CheckBox) this.findViewById(R.id.doubleBox)).setText(R.string.doubleText);
    }
}
