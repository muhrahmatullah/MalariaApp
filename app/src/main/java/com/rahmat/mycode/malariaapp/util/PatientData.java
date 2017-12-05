package com.rahmat.mycode.malariaapp.util;

/**
 * Created by rahmat on 3/8/2017.
 */

public class PatientData {


    public int giziTranslator(String gizi){
        if(gizi.equals("N")){
            return 1;
        }else{
            return 2;
        }
    }

    public int ageTranslator(int age){
        if(age < 6){
            return 1;
        }else if(age >= 6 || age <65){
            return 2;
        }else
            return 3;
    }

    public int kelambuTranslator(String kelambu){
        if(kelambu.equals("Y")){
            return 1;
        }else{
            return 2;
        }
    }

    public int kualitasKelambuTranslator(String kualitaskelambu){
        if(kualitaskelambu.equals("N")){
            return 1;
        }else if(kualitaskelambu.equals("C")){
            return 2;
        }else{
            return 0;
        }
    }
    public int kualitasTinggalTranslator(String kualitastinggal){
        if(kualitastinggal.equals("N")){
            return 1;
        }else{
            return 2;
        }
    }
    public int kecacinganTranslator(String cacing){
        if(cacing.equals("Y")){
            return 1;
        }else{ return 2;}
    }
}
