package com.vinnycoastli.coastli;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    ImageView imgView1 = (ImageView)findViewById(R.id.QRcodeView);
                    imgView1.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    ImageView imgView = (ImageView)findViewById(R.id.QRcodeView);
                    imgView.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    ImageView imgView3 = (ImageView)findViewById(R.id.QRcodeView);
                    imgView3.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        //layout file
        setContentView(R.layout.activity_main);

        //get value from bundle
        Bundle extras = getIntent().getExtras();
        String account;
        if (extras != null) {
            account = extras.getString("account");
        }
        else{
            account="Example";
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //QRcodeView
        ImageView imageView = (ImageView) findViewById(R.id.QRcodeView);

        Glide.with(this).load("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+account).into(imageView);
    }

}
