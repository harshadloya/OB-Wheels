package edu.binghamton.hloya1.obwheels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
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

public class CalendarCalculatorScreen extends Fragment
{
    CalculationsForCalendarScreen cs1;
    CalculationsForCalendarScreen cs2;
    CalculationsForCalendarScreen cs3;
    CalculationsForCalendarScreen cs4;
    CalculationsForCalendarScreen cs7;
    CalculationsForCalendarScreen cs8;
    private EditText editText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;
    private TextView lmpega;
    private TextView sonoega;
    private Button button;
    //private int year, month, day;
    private View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (hasFocus) {
                /*
                Calendar calendar = Calendar.getInstance();
                int xyear = calendar.get(Calendar.YEAR);
                int xmonth = calendar.get(Calendar.MONTH);
                int xday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), mOnDateSetListener, xyear, xmonth, xday);
                datePickerDialog.show();
                */
                DateDialog dialog;
                if (v.getId() == R.id.editText1) {
                    dialog = new DateDialog(v, editText5);
                } else if (v.getId() == R.id.editText5) {
                    dialog = new DateDialog(v, editText1);
                } else {
                    dialog = new DateDialog(v);
                }
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");

                //Update EDD if LMP value entered
                if (v.getId() == editText1.getId()) {
                    cs1 = new CalculationsForCalendarScreen(editText1, editText5, editText7, lmpega);
                }

                //Update EDD if Sono Date entered (Report values default initialized to 0w0d)
                else if (v.getId() == editText2.getId()) {
                    cs2 = new CalculationsForCalendarScreen(editText2, editText4, editText6, editText8, sonoega, editText3);
                }

                //Update LMP if EDD value entered
                //else if(v.getId() == editText5.getId()) {
                //  cs5 = new CalculationsForCalendarScreen(editText5, editText1);
                //}

                //Update LMP EGA if EGA as of value entered
                else if (v.getId() == editText7.getId()) {
                    cs7 = new CalculationsForCalendarScreen(editText1, editText7, lmpega);
                }

                //Update Sono EGA if EGA as of value entered
                else if (v.getId() == editText8.getId()) {
                    cs8 = new CalculationsForCalendarScreen(editText2, editText8, sonoega);
                }
            }
        }
    };

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
        editText3.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "40")});

        editText4 = (EditText) view.findViewById(R.id.editText4);
        editText4.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "6")});

        editText5 = (EditText) view.findViewById(R.id.editText5);

        editText6 = (EditText) view.findViewById(R.id.editText6);

        editText7 = (EditText) view.findViewById(R.id.editText7);

        editText8 = (EditText) view.findViewById(R.id.editText8);

        lmpega = (TextView) view.findViewById(R.id.lmpega);
        sonoega = (TextView) view.findViewById(R.id.sonoega);

        //Default Initializing EGA as of to current date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        editText7.setText(new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime()));
        editText8.setText(new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime()));

        //Update EDD if Report value entered (if Sono value not given default current system date will be taken)
        cs3 = new CalculationsForCalendarScreen(editText3, editText4, editText6, editText8, sonoega, editText2);
        cs4 = new CalculationsForCalendarScreen(editText4, editText3, editText6, editText8, sonoega, editText2);

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
                if((currId == R.id.editText3) || (currId == R.id.editText4) || (currId == R.id.editText6))
                {
                    //Do nothing - Use default system keypad if editable
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
    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = (month+1) + "/" + dayOfMonth + "/" + year;
            editText1.setText(date);
        }
    };
    */
}
