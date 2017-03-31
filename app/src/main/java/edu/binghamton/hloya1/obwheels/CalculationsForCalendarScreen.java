package edu.binghamton.hloya1.obwheels;

import android.nfc.Tag;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hloya on 3/30/2017.
 */

public class CalculationsForCalendarScreen
{
    private EditText editedEditText;
    private EditText toUpdateEditText;

    public CalculationsForCalendarScreen()
    {

    }

    public CalculationsForCalendarScreen(View editedView, View toBeUpdatedView)
    {
        editedEditText = (EditText) editedView;
        if(editedEditText.getId() == R.id.editText1 || editedEditText.getId() == R.id.editText2)
            editedEditText.addTextChangedListener(EDDCalcFromLMPorSono);
        else if(editedEditText.getId() == R.id.editText5 || editedEditText.getId() == R.id.editText6)
            editedEditText.addTextChangedListener(LMPorSonoCalcFromEDD);
        toUpdateEditText = (EditText) toBeUpdatedView;
    }

    private TextWatcher EDDCalcFromLMPorSono = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                String date = s.toString();
                //String date = editedEditText.getText();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar calendar = Calendar.getInstance();

                Date inputDate = simpleDateFormat.parse(date);
                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, 280);
                toUpdateEditText.setText(simpleDateFormat.format(calendar.getTime()));
            } catch (ParseException e) {
                //Error
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
    };

    private TextWatcher LMPorSonoCalcFromEDD = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                String date = s.toString();
                //String date = editedEditText.getText();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar calendar = Calendar.getInstance();

                Date inputDate = simpleDateFormat.parse(date);
                calendar.setTime(inputDate);
                calendar.add(Calendar.DATE, -280);
                toUpdateEditText.setText(simpleDateFormat.format(calendar.getTime()));
            } catch (ParseException e) {
                //Error
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
    };
}
