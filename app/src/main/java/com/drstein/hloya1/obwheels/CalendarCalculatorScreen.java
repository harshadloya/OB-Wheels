package com.drstein.hloya1.obwheels;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by hloya on 3/29/2017.
 */

public class CalendarCalculatorScreen extends Fragment {
    CalculationsForCalendarScreen cs1;
    CalculationsForCalendarScreen cs2;
    private Button textButton;
    private Button editText1;
    private Button editText2;
    private EditText editText3;
    private EditText editText4;
    private Button editText5;
    private Button editText6;
    private Button editText7;
    private Button editText8;
    private TextView lmpega;
    private TextView sonoega;
    public View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            DateDialog dialog;
            if (v.getId() == R.id.editText1) {
                dialog = new DateDialog(v, editText5, editText7, lmpega);
            } else if (v.getId() == R.id.editText2) {
                dialog = new DateDialog(v, editText3, editText4, editText6, editText8, sonoega);
            } else if (v.getId() == R.id.editText5) {
                dialog = new DateDialog(v, editText1, editText7, lmpega);
            } else if (v.getId() == R.id.editText7) {
                dialog = new DateDialog(v, editText5, editText1, lmpega);
            } else if (v.getId() == R.id.editText8) {
                dialog = new DateDialog(v, editText3, editText4, editText6, editText2, sonoega);
            } else {
                dialog = new DateDialog(v);
            }
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.show(ft, "DatePicker");
        }
    };
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view;

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        if (width >= 720) {
            view = inflater.inflate(R.layout.calendarcalculator_screen, container, false);
        } else {
            view = inflater.inflate(R.layout.calendarcalculator_screen_smalldevices, container, false);
        }

        //Handling all the editText fields together
        editTextListRecurse((ViewGroup) view);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EDDSettingDetailsScreen eddSettingDetailsScreen = new EDDSettingDetailsScreen();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, eddSettingDetailsScreen);
                fragmentTransaction.addToBackStack("replaceWithEDDSettingDetailsScreen");
                fragmentTransaction.commit();
            }
        });


        editText1 = (Button) view.findViewById(R.id.editText1);

        editText2 = (Button) view.findViewById(R.id.editText2);

        editText3 = (EditText) view.findViewById(R.id.editText3);
        editText3.setFilters(new InputFilter[]{new InputFilterMinMax("0", "40")});

        editText4 = (EditText) view.findViewById(R.id.editText4);
        editText4.setFilters(new InputFilter[]{new InputFilterMinMax("0", "6")});

        editText5 = (Button) view.findViewById(R.id.editText5);

        editText6 = (Button) view.findViewById(R.id.editText6);

        editText7 = (Button) view.findViewById(R.id.editText7);

        editText8 = (Button) view.findViewById(R.id.editText8);

        lmpega = (TextView) view.findViewById(R.id.lmpega);
        sonoega = (TextView) view.findViewById(R.id.sonoega);

        //Default Initializing EGA as of to current date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        editText7.setText(new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime()));
        editText8.setText(new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime()));

        //Update EDD if Report value entered (if Sono value not given default current system date will be taken)
        cs1 = new CalculationsForCalendarScreen(editText3, editText4, editText6, editText8, sonoega, editText2);
        cs2 = new CalculationsForCalendarScreen(editText4, editText3, editText6, editText8, sonoega, editText2);

        return view;
    }

    private void editTextListRecurse(ViewGroup container) {
        int count = container.getChildCount();

        for (int i = 0; i < count; i++) {
            View child = container.getChildAt(i);
            if (child instanceof Button) {
                textButton = (Button) child;

                int currId = textButton.getId();
                if ((currId == R.id.editText3) || (currId == R.id.editText4) || (currId == R.id.editText6)) {
                    //Do nothing - Use default system keypad if editable
                } else {
                    //editText.setOnFocusChangeListener(mOnFocusChangeListener);
                    textButton.setOnClickListener(mOnClickListener);
                }
            } else if (child instanceof ViewGroup) {
                //recurse through children views
                editTextListRecurse((ViewGroup) child);
            }
        }
    }
}
