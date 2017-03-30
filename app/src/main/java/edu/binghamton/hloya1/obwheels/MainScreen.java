package edu.binghamton.hloya1.obwheels;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    HomeScreen homeScreen = new HomeScreen();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, homeScreen).commit();
                    navigation.setBackgroundColor(getResources().getColor(R.color.mainScreenTabColor));
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
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Following line can be uncommented if the home button needs to be seen as selected when the alert is displayed as well
        //navigation.setBackgroundColor(getResources().getColor(R.color.mainScreenTabColor));

        AlertDialog alertDialog = new AlertDialog.Builder(MainScreen.this).create();
        alertDialog.setTitle(R.string.alertTitle);
        alertDialog.setMessage(getString(R.string.alertMessage));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.alertButtonName), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                HomeScreen homeScreen = new HomeScreen();
                getSupportFragmentManager().beginTransaction().replace(R.id.content, homeScreen).commit();
                navigation.setBackgroundColor(getResources().getColor(R.color.mainScreenTabColor));
            }
        });
        alertDialog.show();
    }



}
