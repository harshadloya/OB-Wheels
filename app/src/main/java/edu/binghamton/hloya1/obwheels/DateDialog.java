package edu.binghamton.hloya1.obwheels;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hloya on 3/30/2017.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    EditText editText;
    public DateDialog()
    {

    }

    public DateDialog(View view)
    {
        editText = (EditText) view;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState)
    {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        if(editText.getId() == R.id.editText1 || editText.getId() == R.id.editText2)
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {

        String date = String.format("%02d", (month+1)) + "/" + String.format("%02d", day) + "/" + year;
        editText.setText(date);
    }
}
