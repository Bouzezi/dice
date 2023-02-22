package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.security.SecureRandom;

public class DiceActivity extends AppCompatActivity {
    private ImageView dice;
    private TextView affiche;
    private Button roll;
    private TextView money;
    private TextView count;
    private Animation anim,anim1;
    int n=0;
    int value=0;
    int j=0;
    int imgArr[] = new int[]{
            R.mipmap.dice_1,
            R.mipmap.dice_2,
            R.mipmap.dice_3,
            R.mipmap.dice_4,
            R.mipmap.dice_5,
            R.mipmap.dice_6
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        dice= (ImageView) findViewById(R.id.dice);
        roll =(Button) findViewById(R.id.roll);
        money=(TextView) findViewById(R.id.money);
        affiche=(TextView) findViewById(R.id.affiche);
        count=(TextView) findViewById(R.id.count);
        anim = AnimationUtils.loadAnimation(this,R.anim.set);
        anim1 = AnimationUtils.loadAnimation(this,R.anim.rotate);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.dicerolling);
        count.setText("");
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                int nb=intent.getIntExtra("roll_number",6);

                value=Integer.parseInt((String) money.getText());
                mp.start();
                 Handler handler = new Handler();
                SecureRandom random =new SecureRandom();
                handler.postDelayed(new Runnable(){
                    public void run() {

                        //int i=random.nextInt(n);
                        dice.setImageResource(imgArr[n]);

                        dice.startAnimation(anim1);
                        handler.postDelayed(this, 500);
                        n++;
                        if(n>5){
                            handler.removeCallbacks(this);
                            n=0;
                        }
                    }

                }, 500);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SecureRandom random =new SecureRandom();
                        int i=random.nextInt(nb)+1;
                        switch(i) {
                            case 1:
                                dice.setImageResource(R.mipmap.dice_1);
                                value+=100;
                                count.setText("+100");
                                break;
                            case 2:
                                dice.setImageResource(R.mipmap.dice_2);
                                value+=200;
                                count.setText("+200");
                                break;
                            case 3:
                                dice.setImageResource(R.mipmap.dice_3);
                                value+=300;
                                count.setText("+300");
                                break;
                            case 4:
                                dice.setImageResource(R.mipmap.dice_4);
                                value+=400;
                                count.setText("+400");
                                break;
                            case 5:
                                dice.setImageResource(R.mipmap.dice_5);
                                value+=500;
                                count.setText("+500");
                                break;
                            case 6:
                                dice.setImageResource(R.mipmap.dice_6);
                                value+=600;
                                count.setText("+600");
                                break;
                        }
                        count.startAnimation(anim);
                        money.setText(""+value);
                    }
                },1000);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.my_switch_item);
        item.setActionView(R.layout.switch_item);

        Switch mySwitch = item.getActionView().findViewById(R.id.id_switch);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                MenuItem next= menu.findItem(R.id.next);

                MenuItem prev= menu.findItem(R.id.previous);
                 if (isChecked){
                     next.setEnabled(false);
                     prev.setEnabled(false);
                 }else {
                     next.setEnabled(true);
                     prev.setEnabled(true);
                 }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.next:j++;affiche.setText(" "+j);break;
            case R.id.previous:j--;affiche.setText(" "+j);break;

        }
        return super.onOptionsItemSelected(item);
    }
}