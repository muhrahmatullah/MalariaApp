package com.rahmat.mycode.malariaapp.util;

import java.util.Calendar;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.media.CamcorderProfile.get;

/**
 * Created by rahmat on 3/8/2017.
 */

public class DateUtill {

    Calendar dob = Calendar.getInstance();
    Calendar today = Calendar.getInstance();

    public int getAge(int year, int month, int day){

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
    public String getDateNow(){
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int date = today.get(Calendar.DATE);

        return ""+month+"/"+date+"/"+year;
    }
    public int ageTranslator(int age){
        if(age < 6){
            return 1;
        }else if(age >= 6 || age <65){
            return 2;
        }else
            return 3;
    }



}
