package edu.binghamton.hloya1.obwheels;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
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
    Button editedEditText;
    Button toUpdateEditText;
    EditText considerEditText;
    EditText considerAlsoEditText;
    Button egaAsOfEditText;
    TextView lmpOrSonoEgaTextView;

    public DateDialog()
    {
    }

    public DateDialog(View view)
    {
        editedEditText = (Button) view;
    }

    public DateDialog(View editedView, View toUpdateView, View egaAsOfView, View lmpegaView) {
        editedEditText = (Button) editedView;
        toUpdateEditText = (Button) toUpdateView;
        egaAsOfEditText = (Button) egaAsOfView;
        lmpOrSonoEgaTextView = (TextView) lmpegaView;
    }

    public DateDialog(View editedView, View considerView, View considerAlsoView, View toUpdateView, View egaAsOfView, View sonoEgaView) {
        editedEditText = (Button) editedView;
        considerEditText = (EditText) considerView;
        considerAlsoEditText = (EditText) considerAlsoView;
        toUpdateEditText = (Button) toUpdateView;
        egaAsOfEditText = (Button) egaAsOfView;
        lmpOrSonoEgaTextView = (TextView) sonoEgaView;
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

                Button tempButton = toUpdateEditText;
                toUpdateEditText = editedEditText;
                editedEditText = tempButton;

                updateWeekDayCount();
            } else if (editedEditText.getId() == R.id.editText2) {
                updateSonoFields(calendar);
            } else if (editedEditText.getId() == R.id.editText7) {
                Button tempButton = editedEditText;
                editedEditText = egaAsOfEditText;
                egaAsOfEditText = tempButton;

                updateWeekDayCount();
            } else if (editedEditText.getId() == R.id.editText8) {
                Button tempButton = editedEditText;
                editedEditText = egaAsOfEditText;
                egaAsOfEditText = tempButton;

                updateSonoFields(calendar);
            }

        } catch (ParseException e) {
            //e.printStackTrace();
        }
    }

    public void updateSonoFields(Calendar calendar) {
        try {
            String editText3Data = considerEditText.getText().toString();
            String editText4Data = considerAlsoEditText.getText().toString();
            int weekOrDayCount;
            int dayOrWeekCount;
            if (editText3Data.compareTo("") != 0) {
                weekOrDayCount = Integer.parseInt(editText3Data);
            } else
                weekOrDayCount = 0;

            if (editText4Data.compareTo("") != 0) {
                dayOrWeekCount = Integer.parseInt(editText4Data);
            } else
                dayOrWeekCount = 0;

            CalculationsForCalendarScreen cs = new CalculationsForCalendarScreen();

            String temp1 = editedEditText.getText().toString();
            String temp2 = egaAsOfEditText.getText().toString();

            Date sonoDate = null;
            Date egaDate = null;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

            if (!temp1.equals("")) {
                sonoDate = simpleDateFormat.parse(temp1);
                calendar.setTime(sonoDate);
            } else {
                sonoDate = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
                calendar.setTime(sonoDate);
            }

            calendar.add(Calendar.WEEK_OF_YEAR, -weekOrDayCount);
            calendar.add(Calendar.DAY_OF_YEAR, -dayOrWeekCount);

            sonoDate = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));

            if (!temp2.equals("")) {
                egaDate = simpleDateFormat.parse(temp2);
            }

            if (sonoDate != null && egaDate != null) {
                int dayCount = (int) cs.getDaysBetweenDates(sonoDate, egaDate);
                int weekCount = dayCount / 7;
                dayCount = dayCount % 7;

                lmpOrSonoEgaTextView.setText(weekCount + " Weeks, " + dayCount + " Days");

                calendar.add(Calendar.DATE, 280);
                toUpdateEditText.setText(simpleDateFormat.format(calendar.getTime()));
            } else {
                lmpOrSonoEgaTextView.setText(R.string.SONO_EGA);
                toUpdateEditText.setText("");
            }
        } catch (ParseException e) {
            //Error
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

                lmpOrSonoEgaTextView.setText(weekCount + " Weeks, " + dayCount + " Days");
            } else {
                if (editedEditText.getId() == R.id.editText1) {
                    lmpOrSonoEgaTextView.setText(R.string.LMP_EGA);
                } else if (editedEditText.getId() == R.id.editText2) {
                    lmpOrSonoEgaTextView.setText(R.string.SONO_EGA);
                }
            }

        } catch (ParseException e) {
            //Error
        }

    }
}
