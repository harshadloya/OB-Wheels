package edu.binghamton.hloya1.obwheels;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hloya on 3/30/2017.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    EditText editedEditText;
    EditText toUpdateEditText;
    EditText egaAsOfEditText;
    TextView lmpegaTextView;

    public DateDialog()
    {
    }

    public DateDialog(View view)
    {
        editedEditText = (EditText) view;
    }

    public DateDialog(View editedView, View toUpdateView, View egaAsOfView, View lmpegaView) {
        editedEditText = (EditText) editedView;
        toUpdateEditText = (EditText) toUpdateView;
        egaAsOfEditText = (EditText) egaAsOfView;
        lmpegaTextView = (TextView) lmpegaView;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState)
    {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        if (editedEditText.getId() == R.id.editText1 || editedEditText.getId() == R.id.editText2)
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        try {
            String date = String.format("%02d", (month + 1)) + "/" + String.format("%02d", day) + "/" + year;
            editedEditText.setText(date);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Calendar calendar = Calendar.getInstance();

            Date inputDate = null;


            if (editedEditText.getId() == R.id.editText1) {
                inputDate = simpleDateFormat.parse(date);

                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, 280);
                toUpdateEditText.setText(simpleDateFormat.format(calendar.getTime()));
                updateWeekDayCount();
            } else if (editedEditText.getId() == R.id.editText5) {
                inputDate = simpleDateFormat.parse(date);

                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, -280);
                toUpdateEditText.setText(simpleDateFormat.format(calendar.getTime()));
                updateWeekDayCount();
            }


        } catch (ParseException e) {
            //e.printStackTrace();
        }
    }

    public void updateWeekDayCount() {
        try {
            CalculationsForCalendarScreen cs = new CalculationsForCalendarScreen();
            String temp1 = editedEditText.getText().toString();
            String temp2 = egaAsOfEditText.getText().toString();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date lmpDate = null;
            Date egaDate = null;

            if (!temp1.equals("")) {
                lmpDate = simpleDateFormat.parse(temp1);
            }

            if (!temp2.equals("")) {
                egaDate = simpleDateFormat.parse(temp2);
            }

            if (lmpDate != null && egaDate != null) {
                int dayCount = (int) cs.getDaysBetweenDates(lmpDate, egaDate);
                int weekCount = dayCount / 7;
                dayCount = dayCount % 7;

                lmpegaTextView.setText(weekCount + "Weeks, " + dayCount + "Days");
            } else {
                if (editedEditText.getId() == R.id.editText1) {
                    lmpegaTextView.setText(R.string.LMP_EGA);
                } else if (editedEditText.getId() == R.id.editText2) {
                    lmpegaTextView.setText(R.string.SONO_EGA);
                }
            }

        } catch (ParseException e) {
            //Error
        }

    }
}
