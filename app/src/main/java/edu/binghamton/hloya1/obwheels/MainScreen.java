package edu.binghamton.hloya1.obwheels;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeScreen homeScreen = new HomeScreen();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, homeScreen).commit();
                    navigation.setBackgroundColor(getResources().getColor(R.color.homeTabColor));
                    return true;

                case R.id.navigation_disclaimer:
                    DisclaimerScreen disclaimerScreen = new DisclaimerScreen();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, disclaimerScreen).commit();
                    navigation.setBackgroundColor(getResources().getColor(R.color.disclaimerTabColor));
                    return true;

                case R.id.navigation_aboutApp:
                    AboutAppScreen aboutAppScreen = new AboutAppScreen();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, aboutAppScreen).commit();
                    navigation.setBackgroundColor(getResources().getColor(R.color.aboutAppTabColor));
                    return true;

                case R.id.navigation_references:
                    ReferencesScreen referencesScreen = new ReferencesScreen();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, referencesScreen).commit();
                    navigation.setBackgroundColor(getResources().getColor(R.color.referencesTabColor));
                    return true;

                case R.id.navigation_moreApps:
                    MoreAppsScreen moreAppsScreen = new MoreAppsScreen();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, moreAppsScreen).commit();
                    navigation.setBackgroundColor(getResources().getColor(R.color.moreAppsTabColor));
                    navigation.setItemTextColor(getResources().getColorStateList(R.color.disclaimerTabColor));
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
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setBackgroundColor(getResources().getColor(R.color.mainScreenTabColor));
        navigation.setItemTextColor(getResources().getColorStateList(R.color.tabTextColor));
    }

}
