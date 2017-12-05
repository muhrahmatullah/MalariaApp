package com.rahmat.mycode.malariaapp;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rahmat.mycode.malariaapp.util.DateUtill;




public class MainActivity extends AppCompatActivity implements DatePickerFragment.OnCompleteListener{

    private LinearLayout linearLayoutTanggalPemeriksaan;
    private Button buttonSubmit;
    private TextView tvTglLahir, tvTanggalPemeriksaan;
    private RadioButton radio1, radio2, radio3, radioya, radiotidak;
    private DateUtill dateUtill = new DateUtill();
    int age, no_pemeriksaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutTanggalPemeriksaan = (LinearLayout) findViewById(R.id.tanggalsebelum);
        buttonSubmit = (Button) findViewById(R.id.btnSubmit);
        tvTglLahir = (TextView) findViewById(R.id.tvpatientage);
        tvTanggalPemeriksaan = (TextView) findViewById(R.id.tvdate);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radioya = (RadioButton) findViewById(R.id.radioya);
        radiotidak = (RadioButton) findViewById(R.id.radiotidak);


        Log.v("testing ", dateUtill.getDateNow());

        tvTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });
        tvTanggalPemeriksaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });


    }

    public void setButtonSubmitClicked(View view){
        if(radio1.isChecked()){
            no_pemeriksaan = 1;
        }else if(radio2.isChecked()){
            no_pemeriksaan = 2;
        }else{
            no_pemeriksaan = 3;
        }
        if(radioya.isChecked()){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            Intent intent = new Intent(MainActivity.this, PemeriksaanDemam.class);
                            String umur = String.valueOf(age);
                            intent.putExtra("umur_pasien", umur);
                            String nomor = String.valueOf(no_pemeriksaan);
                            Log.v("umur_pasien", umur);
                            Log.v("no_pemeriksaan", nomor);
                            intent.putExtra("nomor_pemeriksaan", nomor);
                            startActivity(intent);
                            Log.v("testing", "yahud tidak");
                            Log.v("testing", "yahud");
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            intent = new Intent(MainActivity.this, PemeriksaanDemam2.class);
                            umur = String.valueOf(age);
                            intent.putExtra("umur_pasien", umur);
                            nomor = String.valueOf(no_pemeriksaan);
                            Log.v("umur_pasien", umur);
                            Log.v("no_pemeriksaan", nomor);
                            intent.putExtra("nomor_pemeriksaan", nomor);
                            startActivity(intent);
                            Log.v("testing", "yahud tidak");
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Apakah ada pemeriksaan lab?").setPositiveButton("ada", dialogClickListener)
                    .setNegativeButton("tidak", dialogClickListener).show();
        }else{
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            Intent intent = new Intent(MainActivity.this, PemeriksaanTidakDemam.class);
                            String umur = String.valueOf(age);
                            intent.putExtra("umur_pasien", umur);
                            String nomor = String.valueOf(no_pemeriksaan);
                            Log.v("umur_pasien", umur);
                            Log.v("no_pemeriksaan", nomor);
                            intent.putExtra("nomor_pemeriksaan", nomor);
                            startActivity(intent);
                            Log.v("testing", "yahud tidak");
                            Log.v("testing", "yahud 1");
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            intent = new Intent(MainActivity.this, PemeriksaanTidakDemam2.class);
                            umur = String.valueOf(age);
                            intent.putExtra("umur_pasien", umur);
                            nomor = String.valueOf(no_pemeriksaan);
                            Log.v("umur_pasien", umur);
                            Log.v("no_pemeriksaan", nomor);
                            intent.putExtra("nomor_pemeriksaan", nomor);
                            startActivity(intent);
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Apakah ada pemeriksaan lab?").setPositiveButton("ada", dialogClickListener)
                    .setNegativeButton("tidak", dialogClickListener).show();
        }
    }

    public void openLinear(View view) {
        Log.v("test", "testing jie");
        linearLayoutTanggalPemeriksaan.setVisibility(View.VISIBLE);
    }

    public void closeLinear(View view) {
        linearLayoutTanggalPemeriksaan.setVisibility(View.INVISIBLE);
    }

    public void onFinishEditDialog(int age) {
        SharedPreferences pref = getSharedPreferences("UMUR", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("age", age);
        editor.commit();
        this.age = age;
    }
}
