package com.rahmat.mycode.malariaapp.util;

/**
 * Created by rahmat on 3/9/2017.
 */

public class Calculation {


    //hitung nilai model y tidak demam
    public double hitungNilaiModelYTidakDemam(int num_usia, int num_kualitaskelambu, int num_kecacingan, int num_gizi) {
        return  -2.818 + (0.524 * num_usia) + (0.815 * num_kualitaskelambu) + (0.427 * num_gizi) - (0.552 * num_kecacingan);
    }
    //hitung nilai model y tidak demam 2
    public double hitungNilaiModelYTidakDemam2(int num_usia, int num_kelambu, int num_gizi) {
        return  -3.472 + (0.495 * num_usia) + (0.835 * num_kelambu) + (0.412 * num_gizi);
    }
    //hitung nilai model y demam
    public double hitungNilaiModelYDemam(int num_kualitaskelambu, int hb, int num_gizi, int num_kecacingan,
                                         int netrofilsegmen, int eosinofil, int netrofilbatang, int limfosit, int monosit) {
        return  19.867 + (1.066 * num_kualitaskelambu) + (0.967 * num_gizi) - (0.497 * num_kecacingan) - (1.069 * hb) +
                (0.530 * eosinofil) + (0.438 * netrofilbatang) - (0.049 * netrofilsegmen) - (0.302 * limfosit) - (0.121 * monosit);
    }
    public double hitungNilaiModelYDemam2(int num_usia, int num_kelambu, int num_kualitaskelambu, int num_tempattinggal, int num_gizi) {
        return -1.915 + (0.089 * num_usia) - (0.421 * num_kelambu) + (0.552 * num_kualitaskelambu) - (0.395 * num_tempattinggal) + (0.975 * num_gizi);
    }

    public String hitungHasilDemam(double modely){
        if(modely <= 0.502){
            return "negatif";
        }else
            return "positif";
    }
    public String hitungHasilDemam2(double modely){
        if(modely <= 0.501){
            return "negatif";
        }else
            return "positif";
    }
    public String hitungHasilTidakDemam(double modely){
        if(modely <= 0.162){
            return "negatif";
        }else
            return "positif";
    }
    public String hitungHasilTidakDemam2(double modely){
        if(modely <= 0.102){
            return "negatif";
        }else
            return "positif";
    }

}
