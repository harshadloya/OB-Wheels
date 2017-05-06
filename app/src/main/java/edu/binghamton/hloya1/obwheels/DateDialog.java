package edu.binghamton.hloya1.obwheels;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hloya on 3/30/2017.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    EditText editText;
    EditText editText2;
    public DateDialog()
    {

    }

    public DateDialog(View view)
    {
        editText = (EditText) view;
    }

    public DateDialog(View view1, View view2) {
        editText = (EditText) view1;
        editText2 = (EditText) view2;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState)
    {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        if(editText.getId() == R.id.editText1 || editText.getId() == R.id.editText2)
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        try {
            String date = String.format("%02d", (month + 1)) + "/" + String.format("%02d", day) + "/" + year;
            editText.setText(date);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Calendar calendar = Calendar.getInstance();

            Date inputDate = null;


            if (editText.getId() == R.id.editText1) {
                inputDate = simpleDateFormat.parse(date);

                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, 280);
                editText2.setText(simpleDateFormat.format(calendar.getTime()));
            } else if (editText.getId() == R.id.editText5) {
                inputDate = simpleDateFormat.parse(date);

                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, -280);
                editText2.setText(simpleDateFormat.format(calendar.getTime()));
            }

        } catch (ParseException e) {
            //e.printStackTrace();
        }
    }
}
