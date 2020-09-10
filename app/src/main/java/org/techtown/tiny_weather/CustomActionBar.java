package org.techtown.tiny_weather;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class CustomActionBar extends AppCompatActivity {
    private Activity activity;
    private ActionBar actionBar;

    ImageButton imageButton;
    private DrawerLayout drawerLayout;

    public CustomActionBar(Activity activity, ActionBar actionBar, DrawerLayout drawerLayout) {
        this.activity = activity;
        this.actionBar = actionBar;
        this.drawerLayout = drawerLayout;
    }

    public void setCustomActionbar() {
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        ColorDrawable newColor = new ColorDrawable(0x00000000);//your color from res
        actionBar.setBackgroundDrawable(newColor);

        View mCustomView = LayoutInflater.from(activity).inflate(R.layout.activity_actionbar, null);

        actionBar.setCustomView(mCustomView);

        imageButton = findViewById(R.id.action_bar);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
