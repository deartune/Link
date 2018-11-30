package com.womenproiot.www.link;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AttendeesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private DbHelper dbHelper;
    private SQLiteDatabase mdb;
    ImageButton buttonNew,buttonCenter;
    String seletedMeetUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees);

        dbHelper = new DbHelper(this);
        mdb = dbHelper.getWritableDatabase();

        ((ImageButton) findViewById(R.id.buttonNew)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.buttonCenter)).setOnClickListener(this);



        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<String> listAge = new ArrayList<String>();
        listAge.add("모임1");
        listAge.add("모임2");

        ArrayAdapter<String> dataAdapter1 =
                new ArrayAdapter<String>(this, R.layout.spinner_item, listAge);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter1);
        spinner.setOnItemSelectedListener(this);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, createItemList(),
                R.layout.row_recyclerview);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> createItemList() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add("아이템 " + i);
        }
        return items;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (position >= 1) {
            seletedMeetUp = ((TextView) view).getText().toString();


        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onClick(View v) {
        String query ="INSERT INTO attendee (seq,name,latitude,longitude,reg_date,modi_date,fr_code,address) values ('m0172','광화문역 5호선', 37.5712497, 126.9773945, '2018/11/29 06:20:09',null,'QuQTW2cBOAUY6uUl2HEK','서울특별시 종로구 세종대로 172' )";
        //"INSERT INTO MEETUP VALUES ('" + str + "','" + title + "','" + seletedAge + "','" + seletedGender + "','" + formatDate + "','" + null + "')";
        mdb.execSQL(query);
       // Toast.makeText(getApplicationContext(), title + "모임등록 완료", LENGTH_SHORT).seshow();
    }
}
