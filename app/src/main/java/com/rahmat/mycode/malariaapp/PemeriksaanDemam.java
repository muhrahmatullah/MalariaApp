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

import static com.rahmat.mycode.malariaapp.R.id.radioya;

public class PemeriksaanDemam extends AppCompatActivity {

    LinearLayout linearLayout, mLinearLayout;
    RadioButton radiolekoya, radiolekotidak, radiotinjaya, radiotinjatidak, radiocacingya, radiocacingtidak,
            radiogizinormal, radiogizicukup, radiokelambunormal, radiokelambuckukup, radiopositif, radionegatif,
            radiotinggalya, radiotinggaltidak, radiokelambuya, radiokelambutidak, radioyahb, radiotidakhb;
    EditText edkadarbasofil, edkadareonisofil, edkadarnetrofilbtg, edkadarnetrofilsgm, edkadarmonosit, edkadarlimfosit, edkadarhb;
    RadioGroup radioGroupTinja, radioGroupKelambu;
    String leko, tinja, cacing, gizi, kualitaskelambu, malaria, kelambu, ttinggal, hb;
    Double basofil, eosinofil, netrofilbtg, netrofilsgm, limfosit, monosit, kadarhb;
    Button btnSubmit;
    PatientData patientData = new PatientData();

    SimpleDateFormat sdf;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemeriksaan_demam);

        radiolekoya = (RadioButton) findViewById(R.id.radioyalekodemam);
        radiolekotidak = (RadioButton) findViewById(R.id.radiotidaklekodemam);
        radiotinjaya = (RadioButton) findViewById(R.id.radioyatinjademam);
        radiotinjatidak = (RadioButton) findViewById(R.id.radiotidaktinjademam);
        radiocacingya = (RadioButton) findViewById(R.id.radioyacacingdemam);
        radiocacingtidak = (RadioButton) findViewById(R.id.radiotidakcacingdemam);
        radiogizinormal = (RadioButton) findViewById(R.id.radionormalgizidemam);
        radiogizicukup = (RadioButton) findViewById(R.id.radiocukupgizidemam);
        radiokelambunormal = (RadioButton) findViewById(R.id.radionormalkelambudemam);
        radiokelambuckukup = (RadioButton) findViewById(R.id.radiocukupkelambudemam);
        radiopositif = (RadioButton) findViewById(R.id.radiopositifmalariademam);
        radionegatif = (RadioButton) findViewById(R.id.radionegatifmalariademam);
        radiotinggalya = (RadioButton) findViewById(R.id.radionormaltinggaldemam);
        radiotinggaltidak = (RadioButton) findViewById(R.id.radiocukuptinggaldemam);
        radiokelambuya = (RadioButton) findViewById(R.id.radioyakelambudemam);
        radiokelambutidak = (RadioButton) findViewById(R.id.radiotidakkelambudemam);
        radioyahb = (RadioButton) findViewById(R.id.radioyademam);
        radiotidakhb = (RadioButton) findViewById(R.id.radiotidakdemam);

        edkadarhb = (EditText) findViewById(R.id.kadarhb);

        edkadarbasofil = (EditText) findViewById(R.id.kadarbasofildemam);
        edkadareonisofil = (EditText) findViewById(R.id.kadareosinofildemam);
        edkadarnetrofilbtg = (EditText) findViewById(R.id.kadarnetrofilbtgdemam);
        edkadarnetrofilsgm = (EditText) findViewById(R.id.kadarnetrofilsgmdemam);
        edkadarlimfosit = (EditText) findViewById(R.id.kadarlimfositdemam);
        edkadarmonosit = (EditText) findViewById(R.id.kadarmonositdemam);

        linearLayout = (LinearLayout) findViewById(R.id.editTextLayoutdemam);
        mLinearLayout = (LinearLayout) findViewById(R.id.editTextLayoutdemam1);

        radioGroupTinja = (RadioGroup) findViewById(R.id.radiotinjademamcacing);
        radioGroupKelambu = (RadioGroup) findViewById(R.id.radioadakelambudemam);

        btnSubmit = (Button) findViewById(R.id.btnsubmitdemam);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioyahb.isChecked()) {
                    hb = "Y";
                    kadarhb = Double.parseDouble(edkadarhb.getText().toString());
                } else {
                    hb = "T";
                }
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
                if (radiokelambuya.isChecked()) {
                    kelambu = "Y";
                } else {
                    kelambu = "T";
                }
                if (radiotinggalya.isChecked()) {
                    ttinggal = "N";
                } else {
                    ttinggal = "C";
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

                int Gizi, Kelambu, adakelambu, age, umur, no_pemeriksaan, cacingan, tinggal;
                String hasil = "";
                Intent intent = getIntent();
                Gizi = patientData.giziTranslator(gizi);
                adakelambu = patientData.kelambuTranslator(kelambu);
                Kelambu = patientData.kualitasKelambuTranslator(kualitaskelambu);
                tinggal = patientData.kualitasTinggalTranslator(ttinggal);
                no_pemeriksaan = Integer.valueOf(intent.getStringExtra("nomor_pemeriksaan"));
                age = Integer.valueOf(intent.getStringExtra("umur_pasien"));
                umur = patientData.ageTranslator(age);
                cacingan = patientData.kecacinganTranslator(cacing);
                Double modely;
                String tipe, result;

                if (leko.equals("Y") && tinja.equals("Y") && hb.equals("Y")) {
                    //hitung nilai model y
                    modely = 19.867 + (1.066 * Kelambu) + (0.967 * Gizi) - (0.497 * cacingan) - (1.069 * kadarhb) + (0.530 * eosinofil)
                            + (0.438 * netrofilbtg) - (0.049 * netrofilsgm) - (0.302 * limfosit) - (0.121 * monosit);
                    if (modely <= 0.502) {
                        hasil = "negatif";
                    } else if (modely > 0.502) {
                        hasil = "positif";
                    }
                    tipe = "1A";
                } else if (leko.equals("Y") && tinja.equals("T") && hb.equals("T")) {
                    //hitung nilai model y
                    modely = 19.253 + (1.072 * Kelambu) + (0.927 * Gizi) - (1.066 * kadarhb) + (0.524 * eosinofil)
                            + (0.443 * netrofilbtg) - (0.048 * netrofilsgm) - (0.302 * limfosit) + (0.124 * monosit);
                    if (modely <= 0.450) {
                        hasil = "negatif";
                    } else if (modely > 0.450) {
                        hasil = "positif";
                    }
                    tipe = "1B";
                } else if (leko.equals("Y") && tinja.equals("Y") && hb.equals("T")) {
                    //hitung nilai model y
                    modely = 5.541 + (1.161 * Kelambu) + (0.963 * Gizi) - (0.500 * cacingan) + (0.554 * eosinofil)
                            + (0.453 * netrofilbtg) - (0.049 * netrofilsgm) - (0.300 * limfosit) + (0.129 * monosit);
                    if (modely <= 0.461) {
                        hasil = "negatif";
                    } else if (modely > 0.461) {
                        hasil = "positif";
                    }
                    tipe = "1C";
                } else if (leko.equals("T") && tinja.equals("Y") && hb.equals("Y")) {
                    modely = -3.377 + (0.108 * umur) + (0.803 * Kelambu) + (1.038 * Gizi) - (0.388 * cacingan);
                    if (modely <= 0.133) {
                        hasil = "negatif";
                    } else if (modely > 0.133) {
                        hasil = "positif";
                    }
                    tipe = "1D";
                } else if (leko.equals("Y") && tinja.equals("T") && hb.equals("T")) {
                    //hitung nilai model y
                    modely = 4.959 + (1.161 * Kelambu) + (0.928 * Gizi) + (0.547 * eosinofil) + (0.458 * netrofilbtg)
                            - (0.049 * netrofilsgm) - (0.301 * limfosit) + (0.132 * monosit);
                    if (modely <= 0.499) {
                        hasil = "negatif";
                    } else if (modely > 0.499) {
                        hasil = "positif";
                    }
                    tipe = "1E";
                } else if (leko.equals("T") && tinja.equals("T") && hb.equals("Y")) {
                    //hitung nilai model y
                    modely = -2.460 - (0.660 * adakelambu) + (0.875 * Kelambu) + (1.004 * Gizi);
                    if (modely <= 0.638) {
                        hasil = "negatif";
                    } else if (modely > 0.638) {
                        hasil = "positif";
                    }
                    tipe = "1F";
                } else {
                    //hitung nilai model y
                    modely = -1.312 - (0.459 * adakelambu) + (0.568 * Kelambu) - (0.383 * tinggal) + (1.015 * Gizi)
                            - (0.393 * cacingan);
                    if (modely <= 0.619) {
                        hasil = "negatif";
                    } else if (modely > 0.619) {
                        hasil = "positif";
                    }
                    tipe = "1G";
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
        edkadarhb.setText("");
    }
    public void closeEditTextLeko(View view) {
        mLinearLayout.setVisibility(View.GONE);
        edkadarbasofil.setText("");
        edkadareonisofil.setText("");
        edkadarnetrofilsgm.setText("");
        edkadarnetrofilbtg.setText("");
        edkadarmonosit.setText("");
        edkadarlimfosit.setText("");
    }

    public void openEditTextLeko(View view) {
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    public void openRadioGroupTinja(View view) {
        radioGroupTinja.setVisibility(View.VISIBLE);
    }

    public void closeRadioGroupTinja(View view) {
        radioGroupTinja.setVisibility(View.GONE);
    }

    public void openRadioKelambu(View view) {
        radioGroupKelambu.setVisibility(View.VISIBLE);
    }

    public void closeRadioKelambu(View view) {
        radioGroupKelambu.setVisibility(View.GONE);
    }
}
