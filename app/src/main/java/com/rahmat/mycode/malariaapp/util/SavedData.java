package com.rahmat.mycode.malariaapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by rahmat on 3/10/2017.
 */

public class SavedData {
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "PatientData";

    public SavedData(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
     }
    public void setAge(int age) {
        editor.putInt("age", age);
        // commit changes
        editor.commit();
    }
}
