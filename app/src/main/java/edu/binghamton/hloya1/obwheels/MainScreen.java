package edu.binghamton.hloya1.obwheels;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_disclaimer:
                    mTextMessage.setText(R.string.disclaimer_text);
                    return true;
                case R.id.navigation_aboutApp:
                    mTextMessage.setText(R.string.aboutapp_text);
                    return true;
                case R.id.navigation_references:
                    mTextMessage.setText(R.string.ref_text);
                    return true;
                case R.id.navigation_moreApps:
                    mTextMessage.setText(R.string.title_moreApps);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mTextMessage = (TextView) findViewById(R.id.message);
        //mTextMessage.setMovementMethod(new ScrollingMovementMethod());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
