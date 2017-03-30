package edu.binghamton.hloya1.obwheels;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hloya on 3/29/2017.
 */

public class CalendarCalculatorScreen extends Fragment
{
    private EditText editText1;
    private int year, month, day;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.calendarcalculator_screen, container, false);
        editText1 = (EditText) view.findViewById(R.id.editText1);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    //Calendar calendar = Calendar.getInstance();
                    //year = calendar.get(Calendar.YEAR);
                    //month = calendar.get(Calendar.MONTH);
                    //day = calendar.get(Calendar.DAY_OF_MONTH);

                    //DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), mOnDateSetListener, year, month, day);
                    //InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(viewContainer.getWindowToken(), 0);
                    //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    //datePickerDialog.show();

                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();

                    //InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromInputMethod(container.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

                    dialog.show(ft, "DatePicker");



                }
            }
        });

        return view;
    }

    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = (month+1) + "/" + day + "/" + year;
            editText1.setText(date);
        }

    };
}
