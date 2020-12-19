package dwiyanhartono.com.latihan12;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class AbsenActivity extends AppCompatActivity {

    TextView TV_Judul;
    String Nama_Mahasiswa = "";
    EditText E_Jam;
    EditText E_Tanggal;
    Button B_Submit;
    ImageView IV_Jam;
    ImageView IV_Tanggal;
    Calendar Kalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        Inisial();
        Set_Object();
        Listen_B_Submit();
        Listen_Jam();
        Listen_Tanggal();
    }

    private void Inisial() {
        TV_Judul = findViewById(R.id.textabjudul);
        E_Jam = findViewById(R.id.etabjam);
        E_Tanggal = findViewById(R.id.etabtgl);
        B_Submit = findViewById(R.id.btnsubmit);
        IV_Jam = findViewById(R.id.imgabjam);
        IV_Tanggal = findViewById(R.id.imgabcalender);
        Kalender = Calendar.getInstance();
    }

    private void Set_Object() {
        Intent intent = getIntent();
        Nama_Mahasiswa = intent.getStringExtra("nama");
        TV_Judul.setText("Nama: " + Nama_Mahasiswa);
    }

    private void Listen_B_Submit() {
        B_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Jam = E_Jam.getText().toString();
                String Tangal = E_Tanggal.getText().toString();
                if(Jam.equals("")) {
                    Pesan("Jam tidak boleh kosong.");
                    return;
                }
                if(Tangal.equals("")) {
                    Pesan("Tanggal tidak boleh kosong.");
                    return;
                }
                String Pesan = "Nama: " + Nama_Mahasiswa + "\n" +
                        "Telah melakukan absen pada tanggal: " + Tangal + " pukul: " + Jam;
                Toast.makeText(AbsenActivity.this, Pesan, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Listen_Jam(){
        IV_Jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(
                        AbsenActivity.this,
                        SetStart,
                        Kalender.get(Calendar.HOUR_OF_DAY),
                        Kalender.get(Calendar.MINUTE),
                        true
                ).show();
            }
        });
    }

    private void Listen_Tanggal(){
        IV_Tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        AbsenActivity.this,
                        SetTanggal,
                        Kalender.get(Calendar.YEAR),
                        Kalender.get(Calendar.MONTH),
                        Kalender.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener SetStart = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String Jam = Waktu(hourOfDay,minute);
            E_Jam.setText(Jam);
        }
    };

    DatePickerDialog.OnDateSetListener SetTanggal = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String Tanggal = year + "-" + Bulan(monthOfYear) + "-" + Tanggal(dayOfMonth);
            E_Tanggal.setText(Tanggal);
        }
    };

    String Waktu(int Jam, int Menit) {
        String Str_Jam = "";
        String Str_Menit = "";
        if (Jam < 10) { Str_Jam = "0" + Jam; } else { Str_Jam = Integer.toString(Jam); }
        if (Menit < 10) { Str_Menit = "0" + Menit; } else { Str_Menit = Integer.toString(Menit); }
        String Str_Waktu = Str_Jam + ":" + Str_Menit;
        return Str_Waktu;
    }

    String Bulan (int Bulan){
        String Str_Bulan = "";
        int Bln = Bulan + 1;
        if(Bln < 10) { Str_Bulan = "0" + Bln; } else { Str_Bulan = Integer.toString(Bln); }
        return Str_Bulan;
    }

    String Tanggal(int Tgl){
        String Str_Tanggal = "";
        if(Tgl < 10) { Str_Tanggal = "0" + Tgl; } else { Str_Tanggal = Integer.toString(Tgl); }
        return Str_Tanggal;
    }

    private void Pesan(String Pesan){
        AlertDialog.Builder A_Builder = new AlertDialog.Builder(AbsenActivity.this);
        A_Builder.setPositiveButton("OK",null);
        A_Builder.setTitle("Information");
        A_Builder.setMessage(Pesan);
        AlertDialog Alert = A_Builder.create();
        Alert.show();
    }

}
