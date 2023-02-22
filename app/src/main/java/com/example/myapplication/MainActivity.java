package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button b6;
    private Button b20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b6 = (Button) findViewById(R.id.button6);
        b20 =(Button) findViewById(R.id.button20);
        Intent intent=new Intent(this,DiceActivity.class);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("roll_number",6);
                startActivity(intent);
            }
        });
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("roll_number",20);
                startActivity(intent);
            }
        });
    }
}