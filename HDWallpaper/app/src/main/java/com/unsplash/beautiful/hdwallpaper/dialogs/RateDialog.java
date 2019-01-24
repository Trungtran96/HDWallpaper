package com.unsplash.beautiful.hdwallpaper.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.unsplash.beautiful.hdwallpaper.R;
import com.unsplash.beautiful.hdwallpaper.listeners.RateListener;
import com.unsplash.beautiful.hdwallpaper.ultils.SharedPreferencesManager;

public class RateDialog extends AlertDialog {
    private RateListener listener;

    private CheckBox cbDoNotShowAgain;
    private LinearLayout layoutDoNotShowAgain;

    public RateDialog(Context context, RateListener mListener) {
        super(context);
        this.listener = mListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rate);

        cbDoNotShowAgain = findViewById(R.id.cb_do_not_show_again);
        layoutDoNotShowAgain = findViewById(R.id.layout_do_not_show_again);

        layoutDoNotShowAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDoNotShowAgain.isChecked()) {
                    cbDoNotShowAgain.setChecked(false);
                } else {
                    cbDoNotShowAgain.setChecked(true);
                }
            }
        });
        findViewById(R.id.tv_rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDoNotShowAgain.isChecked()) {
                    SharedPreferencesManager.setAgain(getContext(), true);
                } else {
                    SharedPreferencesManager.setAgain(getContext(), false);
                }
                dismiss();
                SharedPreferencesManager.setRate(getContext(), true);
                listener.onRateClicked();
            }
        });
        findViewById(R.id.tv_later).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDoNotShowAgain.isChecked()) {
                    SharedPreferencesManager.setAgain(getContext(), true);
                } else {
                    SharedPreferencesManager.setAgain(getContext(), false);
                }
                dismiss();
                listener.onLaterClicked();
            }
        });
    }
}
