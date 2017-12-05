package com.rahmat.mycode.malariaapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.rahmat.mycode.malariaapp.model.PatientModel;
import com.rahmat.mycode.malariaapp.util.DateUtill;
import com.rahmat.mycode.malariaapp.util.SavedData;

import java.util.Calendar;

/**
 * Created by rahmat on 3/5/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

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
        populateSetDate(yy, mm + 1, dd);
        int umur = dateUtill.getAge(yy, mm, dd);
        this.mListener.onFinishEditDialog(umur);

    }

    public void populateSetDate(int year, int month, int day) {
        TextView t1 = (TextView) getActivity().findViewById(R.id.tvpatientage);
        t1.setText(month + "/" + day + "/" + year);
    }

    public interface OnCompleteListener {
        void onFinishEditDialog(int inputText);
    }

    private OnCompleteListener mListener;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }

    }
}
