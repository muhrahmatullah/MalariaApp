package com.rahmat.mycode.malariaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.rahmat.mycode.malariaapp.util.Calculation;
import com.rahmat.mycode.malariaapp.util.PatientData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PemeriksaanTidakDemam2 extends AppCompatActivity {

    RadioButton pasienNormal, pasienCukup, kelambuNormal, kelambuCukup, positif, negatif;
    Button btnSubmit;
    String gizi, kelambu, malaria, result;
    private Calculation calculation = new Calculation();
    private PatientData patientData = new PatientData();

    SimpleDateFormat sdf;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemeriksaan_tidak_demam2);

        pasienNormal = (RadioButton) findViewById(R.id.radionormalgizi2);
        pasienCukup = (RadioButton) findViewById(R.id.radiocukupgizi2);
        kelambuNormal = (RadioButton) findViewById(R.id.radionormalkelambu2);
        kelambuCukup = (RadioButton) findViewById(R.id.radiocukupkelambu2);
        positif = (RadioButton) findViewById(R.id.radiopositifmalaria2);
        negatif = (RadioButton) findViewById(R.id.radionegatifmalaria2);

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.

        btnSubmit = (Button) findViewById(R.id.btnsubmit);

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
                if(kelambuNormal.isChecked()){
                    kelambu = "N";
                    Log.v("Testing", kelambu);
                }else{
                    kelambu = "C";
                    Log.v("Testing", kelambu);
                }
                if(positif.isChecked()){
                    malaria = "P";
                    Log.v("Testing", malaria);
                }else{
                    malaria = "N";
                    Log.v("Testing", malaria);
                }
                int Gizi, Kelambu, age, umur, no_pemeriksaan;
                Intent intent = getIntent();
                Gizi = patientData.giziTranslator(gizi);
                Kelambu = patientData.kualitasKelambuTranslator(kelambu);
                no_pemeriksaan = Integer.valueOf(intent.getStringExtra("nomor_pemeriksaan"));
                age = Integer.valueOf(intent.getStringExtra("umur_pasien"));
                umur = patientData.ageTranslator(age);


                double modelY = calculation.hitungNilaiModelYTidakDemam2(umur, Kelambu, Gizi);
                String hasil = calculation.hitungHasilTidakDemam2(modelY);
                Log.v("hasil", ""+String.valueOf(modelY));
                Log.v("hasil", ""+String.valueOf(Kelambu));
                Log.v("hasil", ""+String.valueOf(umur));
                Log.v("hasil", ""+String.valueOf(Gizi));
                String tipe = "2D";

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

}

