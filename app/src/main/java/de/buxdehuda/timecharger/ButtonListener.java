package de.buxdehuda.timecharger;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ButtonListener implements OnTouchListener {

    private ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
    private long savedTime;
    private final Activity activity;

    public ButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {

        final TextView time = (TextView) activity.findViewById(R.id.remainingTimeTextView);

        //button press
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            savedTime = System.currentTimeMillis();
            activity.getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            time.setText(R.string.chargingText);
            return true;
        }

        //button release
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            long chargedTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - savedTime + 500); // round to full seconds
            activity.getWindow().getDecorView().setBackgroundColor(Color.GREEN);

            if (((CheckBox) activity.findViewById(R.id.doubleBox)).isChecked()) {
                chargedTime *= 2;
            }

            new CountDownTimer(TimeUnit.SECONDS.toMillis(chargedTime + 1), TimeUnit.SECONDS.toMillis(1)) {

                @Override
                public void onTick(long millisUntilFinished) {
                    time.setText(activity.getString(R.string.remainingTimeText, TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
                }

                @Override
                public void onFinish() {
                    activity.getWindow().getDecorView().setBackgroundColor(Color.RED);
                    time.setText(activity.getString(R.string.remainingTimeText, 0));

                    if (((CheckBox) activity.findViewById(R.id.soundBox)).isChecked()) {
                        tg.startTone(ToneGenerator.TONE_PROP_ACK);
                    }
                }

            }.start();
            v.performClick();
            return true;

        }
        return false;
    }

}
