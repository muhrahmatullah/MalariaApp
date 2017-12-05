package com.rahmat.mycode.malariaapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.rahmat.mycode.malariaapp.util.DateUtill;

/**
 * Created by rahmat on 3/13/2017.
 */

public class DatePickerFragment1 extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    DateUtill dateUtill = new DateUtill();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        TextView t1 = (TextView) getActivity().findViewById(R.id.tvdate);
        t1.setText(month+"/"+day+"/"+year);
    }
}
