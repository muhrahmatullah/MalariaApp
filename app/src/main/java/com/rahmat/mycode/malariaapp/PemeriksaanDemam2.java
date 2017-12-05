package com.rahmat.mycode.malariaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rahmat.mycode.malariaapp.util.Calculation;
import com.rahmat.mycode.malariaapp.util.PatientData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PemeriksaanDemam2 extends AppCompatActivity {

    private RadioGroup radioGroupKelambu2;
    private RadioButton pasienNormal, pasienCukup, kelambuNormal, kelambuCukup, positif, negatif,
            kelambuya, kelambutidak, tinggalNormal, tinggalCukup;
    private Button btnSubmit;
    String gizi, kelambu, malaria, result, kualitaskelambu, tinggal;
    private Calculation calculation = new Calculation();
    private PatientData patientData = new PatientData();

    SimpleDateFormat sdf;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemeriksaan_demam2);

        pasienNormal = (RadioButton) findViewById(R.id.radionormalgizidemam2);
        pasienCukup = (RadioButton) findViewById(R.id.radiocukupgizidemam2);
        kelambuNormal = (RadioButton) findViewById(R.id.radionormalkelambudemam2);
        kelambuCukup = (RadioButton) findViewById(R.id.radiocukupkelambudemam2);
        positif = (RadioButton) findViewById(R.id.radiopositifmalariademam2);
        negatif = (RadioButton) findViewById(R.id.radionegatifmalariademam2);
        kelambuya = (RadioButton) findViewById(R.id.radioyakelambudemam2);
        kelambutidak = (RadioButton) findViewById(R.id.radiotidakkelambudemam2);
        tinggalNormal = (RadioButton) findViewById(R.id.radionormaltinggaldemam2);
        tinggalCukup = (RadioButton) findViewById(R.id.radiocukuptinggaldemam2);

        kualitaskelambu="";

        btnSubmit = (Button) findViewById(R.id.btnsubmitdemam2);

        radioGroupKelambu2 = (RadioGroup) findViewById(R.id.radiokualitaskelambudemam2);

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pasienNormal.isChecked()){
                    gizi = "N";
                    Log.v("Testing", gizi);
                }else{
                    gizi = "C";
                    Log.v("Testing", gizi);
                }
                if(kelambuya.isChecked()){
                    kelambu = "Y";
                    Log.v("Testing", kelambu);
                }else{
                    kelambu = "T";
                    Log.v("Testing", kelambu);
                }
                if(kelambuNormal.isChecked()){
                    kualitaskelambu = "N";
                    Log.v("Testing", kualitaskelambu);
                }else{
                    kualitaskelambu = "C";
                    Log.v("Testing", kualitaskelambu);
                }
                if(tinggalNormal.isChecked()){
                    tinggal = "N";
                }else{
                    tinggal = "C";
                }
                if(positif.isChecked()){
                    malaria = "P";
                    Log.v("Testing", malaria);
                }else{
                    malaria = "N";
                    Log.v("Testing", malaria);
                }

                int Gizi, Kelambu, age, umur, no_pemeriksaan, adaKelambu, ttinggal;
                Intent intent = getIntent();
                Gizi = patientData.giziTranslator(gizi);
                Kelambu = patientData.kualitasKelambuTranslator(kualitaskelambu);
                adaKelambu = patientData.kelambuTranslator(kelambu);
                ttinggal = patientData.kualitasTinggalTranslator(tinggal);
                no_pemeriksaan = Integer.valueOf(intent.getStringExtra("nomor_pemeriksaan"));
                age = Integer.valueOf(intent.getStringExtra("umur_pasien"));
                umur = patientData.ageTranslator(age);


                double modelY = calculation.hitungNilaiModelYDemam2(umur, adaKelambu,Kelambu, ttinggal, Gizi);
                String hasil = calculation.hitungHasilDemam2(modelY);
                Log.v("hasil modely", ""+String.valueOf(modelY));
                Log.v("hasil kualitas kelambu", ""+String.valueOf(Kelambu));
                Log.v("hasil umur", ""+String.valueOf(umur));
                Log.v("hasil gizi", ""+String.valueOf(Gizi));
                Log.v("hasil ada kelambu", ""+String.valueOf(adaKelambu));
                Log.v("hasil ttingal", ""+String.valueOf(ttinggal));
                String tipe = "1H";

                if(malaria.equals("P")){
                    result = "Pasien Positif Terjangkir Malaria";
                    Log.v("hasil", result);
                }else{
                    if(hasil.equals("positif")){
                        if(no_pemeriksaan == 1){
                            c.add(Calendar.DATE, 1); // Adding 1 days
                            String output = sdf.format(c.getTime());
                            result = "Model yang digunakan adalah "+tipe+"\n" +
                                    "Nilai model adalah = "+modelY+"\n" +
                                    " Nilai tersebut lebih besar dari nilai batas model. Hal ini menunjukkan Pasien " +
                                    "masih berpeluang telah terjangkit Malaria.\n" +
                                    " Pasien diharapkan melakukan Pemeriksaan Serial Pertama besok,"+output;
                            Log.v("hasil", result);
                        }
                        else if(no_pemeriksaan == 2){
                            c.add(Calendar.DATE, 8); // Adding 1 days
                            String output = sdf.format(c.getTime());
                            result = "Model yang digunakan adalah "+tipe+"\n" +
                                    "Nilai model adalah = "+modelY+"\n" +
                                    " Nilai tersebut lebih besar dari nilai batas model. Hal ini menunjukkan Pasien " +
                                    "masih berpeluang telah terjangkit Malaria.\n" +
                                    " Pasien diharapkan melakukan Pemeriksaan Serial kedua pada hari ke 8,"+output;
                            Log.v("hasil", result);
                        }
                        else{
                            result = "Pasien tidak terjangkit malaria";
                            Log.v("hasil", result);
                        }
                    }else{
                        result = "Nilai tersebut lebih kecil dari nilai batas model. " +"Model yang digunakan adalah "+tipe+"\n" +
                                "Nilai model adalah = "+modelY+"\n" +
                                "Hal ini menunjukkan Pasien tidak berpeluang terjangkit Malaria.";
                        Log.v("hasil", result);
                    }
                }
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                resultIntent.putExtra("result", result);
                startActivity(resultIntent);
            }
        });

    }

    public void openRadioGroup(View view) {
        radioGroupKelambu2.setVisibility(View.VISIBLE);
    }

    public void closeRadioGroup(View view) {
        radioGroupKelambu2.setVisibility(View.INVISIBLE);
    }
}
