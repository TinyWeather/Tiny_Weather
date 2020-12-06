package org.techtown.tiny_weather;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    private TextView id_textview;
    private TextView pw_textview;
    private Button joinBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_login);

        id_textview = (TextView) findViewById(R.id.email);
        pw_textview =(TextView) findViewById(R.id.password);
        joinBtn = (Button) findViewById(R.id.join_btn);

        Intent intent = new Intent(this, LodingActivity.class);
        startActivity(intent);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
