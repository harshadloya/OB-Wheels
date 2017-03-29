package edu.binghamton.hloya1.obwheels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hloya on 3/29/2017.
 */

public class HomeScreen extends Fragment
{
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_screen, container, false);

        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                CalendarCalculatorScreen calendarCalculatorScreen = new CalendarCalculatorScreen();
                getFragmentManager().beginTransaction().replace(R.id.content, calendarCalculatorScreen).commit();
            }
        });

        button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TraditionalObWheelScreen traditionalObWheelScreen = new TraditionalObWheelScreen();
                getFragmentManager().beginTransaction().replace(R.id.content, traditionalObWheelScreen).commit();
            }
        });

        button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EDDSettingDetailsScreen eddSettingDetailsScreen = new EDDSettingDetailsScreen();
                getFragmentManager().beginTransaction().replace(R.id.content, eddSettingDetailsScreen).commit();
            }
        });

        return view;
    }
/*
Not Working Yet
    public void openNextScreen(View view)
    {
        button = (Button) view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EDDSettingDetailsScreen eddSettingDetailsScreen = new EDDSettingDetailsScreen();
                getFragmentManager().beginTransaction().replace(R.id.content, eddSettingDetailsScreen).commit();
            }
        });

    }

*/
}
