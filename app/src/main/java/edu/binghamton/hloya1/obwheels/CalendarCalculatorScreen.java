package edu.binghamton.hloya1.obwheels;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hloya on 3/29/2017.
 */

public class CalendarCalculatorScreen extends Fragment
{
    private EditText editText;
    private int year, month, day;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.calendarcalculator_screen, container, false);

        //Handling all the editText fields together
        editTextListRecurse((ViewGroup) view);



        return view;
    }

    private void editTextListRecurse(ViewGroup container)
    {
        int count = container.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = container.getChildAt(i);
            if (child instanceof EditText)
            {
                editText = (EditText) child;
                System.out.println(editText.getId());
                System.out.println(R.id.editText3);
                System.out.println(R.id.editText4);
                int currId = editText.getId();
                int editText3Id = R.id.editText3;
                int editText4Id = R.id.editText4;
                if((currId == editText3Id) || (currId == editText4Id))
                {
                    //Nothing to do - will use default keyboard
                }
                else
                {
                    editText.setOnFocusChangeListener(mOnFocusChangeListener);
                }
            }
            else if (child instanceof ViewGroup)
            {
                //recurse through children views
                editTextListRecurse((ViewGroup) child);
            }
        }
    }

    private View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if(hasFocus)
            {
                /*
                Calendar calendar = Calendar.getInstance();
                int xyear = calendar.get(Calendar.YEAR);
                int xmonth = calendar.get(Calendar.MONTH);
                int xday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), mOnDateSetListener, xyear, xmonth, xday);
                datePickerDialog.show();
                */
                DateDialog dialog = new DateDialog(v);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        }
    };


    /*
    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = (month+1) + "/" + dayOfMonth + "/" + year;
            editText1.setText(date);
        }
    };
    */
}
