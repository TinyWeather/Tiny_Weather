package org.techtown.tiny_weather;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    private TextView id_textview;
    private TextView pw_textview;
    private Button joinBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        id_textview = (TextView) findViewById(R.id.email);
        pw_textview =(TextView) findViewById(R.id.password);
    }
}
