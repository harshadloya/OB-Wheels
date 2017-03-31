package edu.binghamton.hloya1.obwheels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by hloya on 3/29/2017.
 */

public class CalendarCalculatorScreen extends Fragment
{
    private EditText editText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;

    private Button button;
    private int year, month, day;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.calendarcalculator_screen, container, false);

        //Handling all the editText fields together
        editTextListRecurse((ViewGroup) view);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EDDSettingDetailsScreen eddSettingDetailsScreen = new EDDSettingDetailsScreen();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, eddSettingDetailsScreen);
                fragmentTransaction.addToBackStack("replaceWithEDDSettingDetailsScreen");
                fragmentTransaction.commit();
            }
        });


        editText1 = (EditText) view.findViewById(R.id.editText1);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        editText3 = (EditText) view.findViewById(R.id.editText3);
        editText4 = (EditText) view.findViewById(R.id.editText4);
        editText5 = (EditText) view.findViewById(R.id.editText5);
        editText6 = (EditText) view.findViewById(R.id.editText6);
        editText7 = (EditText) view.findViewById(R.id.editText7);
        editText8 = (EditText) view.findViewById(R.id.editText8);

        //Update EDD if LMP value entered
        CalculationsForCalendarScreen cs = new CalculationsForCalendarScreen(editText1, editText5);
        //Update EDD if Sono value entered
        CalculationsForCalendarScreen cs2 = new CalculationsForCalendarScreen(editText2, editText6);

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

                int currId = editText.getId();
                if((currId == R.id.editText3) || (currId == R.id.editText4))
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

    /*
    private TextWatcher watcher = new TextWatcher()
    {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            String date = s.toString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Calendar calendar = Calendar.getInstance();

            try
            {
                Date inputDate = simpleDateFormat.parse(date);
                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, 280);
                editText5.setText(simpleDateFormat.format(calendar.getTime()));

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    */



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

                //Update LMP if EDD value entered
                //CalculationsForCalendarScreen cs3 = new CalculationsForCalendarScreen(editText5, editText1);
                //Update Sono if EDD value entered
                //CalculationsForCalendarScreen cs4 = new CalculationsForCalendarScreen(editText6, editText2);
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
