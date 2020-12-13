package org.techtown.tiny_weather;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Guide extends AppCompatActivity {

    Button btn1,btn2,btn3;
    TextView text1,text2,text3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.VISIBLE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text2.setVisibility(View.VISIBLE);
                text1.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text3.setVisibility(View.VISIBLE);
                text2.setVisibility(View.GONE);
                text1.setVisibility(View.GONE);
            }
        });

    }
}
