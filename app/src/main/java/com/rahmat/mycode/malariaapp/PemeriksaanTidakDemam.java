package com.rahmat.mycode.malariaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rahmat.mycode.malariaapp.util.PatientData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PemeriksaanTidakDemam extends AppCompatActivity {

    LinearLayout linearLayout;
    RadioButton radiolekoya, radiolekotidak, radiotinjaya, radiotinjatidak, radiocacingya, radiocacingtidak,
            radiogizinormal, radiogizicukup, radiokelambunormal, radiokelambuckukup, radiopositif, radionegatif;
    EditText edkadarbasofil, edkadareonisofil, edkadarnetrofilbtg, edkadarnetrofilsgm, edkadarmonosit, edkadarlimfosit;
    RadioGroup radioGroup;
    String leko, tinja, cacing, gizi, kualitaskelambu, malaria;
    Double basofil, eosinofil, netrofilbtg, netrofilsgm, limfosit, monosit;
    Button btnSubmit;
    PatientData patientData = new PatientData();

    SimpleDateFormat sdf;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemeriksaan_tidak_demam);

        radiolekoya = (RadioButton) findViewById(R.id.radioyatidakdemam);
        radiolekotidak = (RadioButton) findViewById(R.id.radiotidaktidakdemam);
        radiotinjaya = (RadioButton) findViewById(R.id.radioyatinjatidakdemam);
        radiotinjatidak = (RadioButton) findViewById(R.id.radiotidaktinjatidakdemam);
        radiocacingya = (RadioButton) findViewById(R.id.radioyacacingtidakdemam);
        radiocacingtidak = (RadioButton) findViewById(R.id.radiotidakcacingtidakdemam);
        radiogizinormal = (RadioButton) findViewById(R.id.radionormalgizitidakdemam);
        radiogizicukup = (RadioButton) findViewById(R.id.radiocukupgizitidakdemam);
        radiokelambunormal = (RadioButton) findViewById(R.id.radionormalkelambutidakdemam);
        radiokelambuckukup = (RadioButton) findViewById(R.id.radiocukupkelambutidakdemam);
        radiopositif = (RadioButton) findViewById(R.id.radiopositifmalariatidakdemam);
        radionegatif = (RadioButton) findViewById(R.id.radionegatifmalariatidakdemam);

        edkadarbasofil = (EditText) findViewById(R.id.kadarbasofiltidakdemam);
        edkadareonisofil = (EditText) findViewById(R.id.kadareosinofiltidakdemam);
        edkadarnetrofilbtg = (EditText) findViewById(R.id.kadarnetrofilbtgtidakdemam);
        edkadarnetrofilsgm = (EditText) findViewById(R.id.kadarnetrofilsgmtidakdemam);
        edkadarlimfosit = (EditText) findViewById(R.id.kadarlimfosittidakdemam);
        edkadarmonosit = (EditText) findViewById(R.id.kadarmonosittidakdemam);

        //setting for date
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        c = Calendar.getInstance();
        c.setTime(new Date());

        btnSubmit = (Button) findViewById(R.id.btnsubmittidakdemam);

        radioGroup = (RadioGroup) findViewById(R.id.radiotindatidakdemamcacing);

        linearLayout = (LinearLayout) findViewById(R.id.editTextLayout);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radiolekoya.isChecked()) {
                    leko = "Y";
                    basofil = Double.parseDouble(edkadarbasofil.getText().toString());
                    eosinofil = Double.parseDouble(edkadareonisofil.getText().toString());
                    netrofilbtg = Double.parseDouble(edkadarnetrofilbtg.getText().toString());
                    netrofilsgm = Double.parseDouble(edkadarnetrofilsgm.getText().toString());
                    limfosit = Double.parseDouble(edkadarlimfosit.getText().toString());
                    monosit = Double.parseDouble(edkadarmonosit.getText().toString());
                } else {
                    leko = "T";
                }
                if (radiotinjaya.isChecked()) {
                    tinja = "Y";
                } else {
                    tinja = "T";
                }
                if (radiocacingya.isChecked()) {
                    cacing = "Y";
                } else {
                    cacing = "T";
                }
                if (radiokelambunormal.isChecked()) {
                    kualitaskelambu = "N";
                    Log.v("Testing", kualitaskelambu);
                } else {
                    kualitaskelambu = "C";
                    Log.v("Testing", kualitaskelambu);
                }
                if (radiopositif.isChecked()) {
                    malaria = "P";
                    Log.v("Testing", malaria);
                } else {
                    malaria = "N";
                    Log.v("Testing", malaria);
                }
                if (radiogizinormal.isChecked()) {
                    gizi = "N";
                } else {
                    gizi = "C";
                }


                int Gizi, Kelambu, age, umur, no_pemeriksaan, cacingan;
                String hasil = "";
                Intent intent = getIntent();
                Gizi = patientData.giziTranslator(gizi);
                Kelambu = patientData.kualitasKelambuTranslator(kualitaskelambu);
                no_pemeriksaan = Integer.valueOf(intent.getStringExtra("nomor_pemeriksaan"));
                age = Integer.valueOf(intent.getStringExtra("umur_pasien"));
                umur = patientData.ageTranslator(age);
                cacingan = patientData.kecacinganTranslator(cacing);
                Double modely;
                String tipe, result;

                if (leko.equals("Y") && tinja.equals("Y")) {
                    modely = 8.167 + (1.553 * Kelambu) - (0.739 * cacingan) + (0.612 * eosinofil) + (0.463 * netrofilbtg) -
                            (0.055 * netrofilsgm) - (0.341 * limfosit) + (0.187 * monosit);
                    if (modely <= 0.552) {
                        hasil = "negatif";
                    } else if (modely > 0.552) {
                        hasil = "positif";
                    }
                    tipe = "2A";
                } else if (leko.equals("Y") && tinja.equals("T")) {
                    //hitung nilai model y
                    modely = 7.186 + (1.608 * Kelambu) + (0.593 * eosinofil) + (0.468 * netrofilbtg) -
                            (0.053 * netrofilsgm) - (0.342 * limfosit) + (0.185 * monosit);
                    if (modely <= 0.447) {
                        hasil = "negatif";
                    } else if (modely > 0.447) {
                        hasil = "positif";
                    }
                    tipe = "2B";
                } else {
                    //hitung nilai model y
                    modely = -2.818 + (0.524 * umur) + (0.815 * Kelambu) + (0.427 * Gizi) - (0.552 * cacingan);
                    if (modely <= 0.162) {
                        hasil = "negatif";
                    } else if (modely > 0.162) {
                        hasil = "positif";
                    }
                    tipe = "2C";
                }
                if (malaria.equals("P")) {
                    result = "Pasien Positif Terjangkit Malaria";
                    Log.v("hasil", result);
                } else {
                    if (hasil.equals("positif")) {
                        if (no_pemeriksaan == 1) {
                            c.add(Calendar.DATE, 1); // Adding 1 days
                            String output = sdf.format(c.getTime());
                            result = "Model yang digunakan adalah " + tipe + "\n" +
                                    "Nilai model adalah = " + modely + "\n" +
                                    " Nilai tersebut lebih besar dari nilai batas model. Hal ini menunjukkan Pasien " +
                                    "masih berpeluang telah terjangkit Malaria.\n" +
                                    " Pasien diharapkan melakukan Pemeriksaan Serial Pertama besok," + output;
                            Log.v("hasil", result);
                        } else if (no_pemeriksaan == 2) {
                            c.add(Calendar.DATE, 8); // Adding 1 days
                            String output = sdf.format(c.getTime());
                            result = "Model yang digunakan adalah " + tipe + "\n" +
                                    "Nilai model adalah = " + modely + "\n" +
                                    " Nilai tersebut lebih besar dari nilai batas model. Hal ini menunjukkan Pasien " +
                                    "masih berpeluang telah terjangkit Malaria.\n" +
                                    " Pasien diharapkan melakukan Pemeriksaan Serial kedua pada hari ke 8," + output;
                            Log.v("hasil", result);
                        } else {
                            result = "Pasien tidak terjangkit malaria";
                            Log.v("hasil", result);
                        }
                    } else {
                        result = "Nilai tersebut lebih kecil dari nilai batas model. " + "Model yang digunakan adalah " + tipe + "\n" +
                                "Nilai model adalah = " + modely + "\n" +
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

    public void openEditText(View view) {
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void closeEditText(View view) {
        linearLayout.setVisibility(View.GONE);
        edkadarbasofil.setText("");
        edkadareonisofil.setText("");
        edkadarnetrofilsgm.setText("");
        edkadarnetrofilbtg.setText("");
        edkadarmonosit.setText("");
        edkadarlimfosit.setText("");
    }

    public void openRadioGroupTinja(View view) {
        radioGroup.setVisibility(View.VISIBLE);
    }

    public void closeRadioGroupTinja(View view) {
        radioGroup.setVisibility(View.GONE);
    }
}
