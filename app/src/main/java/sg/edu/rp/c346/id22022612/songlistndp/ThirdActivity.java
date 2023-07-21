package sg.edu.rp.c346.id22022612.songlistndp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {

EditText etSongID, etTitle, etSinger,etYear;
RadioGroup rgStars;
Button btnUpdate, btnDelete, btnCancel;
RadioButton rbStars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etSongID = findViewById(R.id.etSongID);
        etTitle =  findViewById(R.id.etSong);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);


        Intent i = getIntent();
        Song obj = i.getParcelableExtra("song");
        etSongID.setText(String.valueOf(obj.get_id()));
        etTitle.setText(obj.getTitle());
        etSinger.setText(obj.getSingers());
        etYear.setText(String.valueOf(obj.getYear()));
        etSongID.setEnabled(false);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(obj.get_id());

                finish();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                obj.setTitle(etTitle.getText().toString());
                obj.setSingers(etSinger.getText().toString());
                obj.setYear(Integer.parseInt(etYear.getText().toString()));
                int selectedId = rgStars.getCheckedRadioButtonId();
                rbStars = findViewById(selectedId);
                obj.setStars(Integer.parseInt(rbStars.getText().toString()));
                dbh.updateNote(obj);
                dbh.close();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}