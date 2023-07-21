package sg.edu.rp.c346.id22022612.songlistndp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song>arrayListSong;
    ToggleButton star5;
    CustomAdapter adapter;
    ArrayList<Song> songList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = findViewById(R.id.lv);
        star5 = findViewById(R.id.star5);


        DBHelper dbh = new DBHelper(SecondActivity.this);
        songList = new ArrayList<>();
        adapter = new CustomAdapter(this, R.layout.row, songList);
        for (int i =0; i < songList.size(); i++ ){
            songList.get(i).toString();
        }
        lv.setAdapter(adapter);
        songList.addAll(dbh.getSong());
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song clickedSong = songList.get(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("song", clickedSong);
                startActivity(intent);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songList.clear();
                if (star5.isChecked()) {
                    songList.addAll(dbh.getSong());
                } else {
                    songList.addAll(dbh.getSongsByStars(5));
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}