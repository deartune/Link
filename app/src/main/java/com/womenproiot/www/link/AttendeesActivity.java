package com.womenproiot.www.link;
import android.database.Cursor;
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

import static android.widget.Toast.LENGTH_SHORT;

public class AttendeesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private DbHelper dbHelper;
    private SQLiteDatabase mdb;
    ImageButton buttonNew,buttonCenter;
    String getSeq,Items;
    Spinner spinner;
    ArrayAdapter dataAdapter1;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees);

        dbHelper = new DbHelper(this);
        mdb = dbHelper.getWritableDatabase();

        ((ImageButton) findViewById(R.id.buttonNew)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.buttonCenter)).setOnClickListener(this);



        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        String query = "SELECT seq,title FROM meetup order by reg_date asc";
        c = mdb.rawQuery(query,null);

        ArrayList<String> list=new ArrayList<String>();
        while(c.moveToNext()){
            String getTitle=c.getString(1);
            getSeq=c.getString(0);
            list.add(getTitle);

        }

        dataAdapter1 =
                new ArrayAdapter<String>(this, R.layout.spinner_item, list);
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



        String query1 = "SELECT address FROM attendee where fr_seq='"+getSeq+"'";
        c = mdb.rawQuery(query1,null);

        ArrayList<String> items=new ArrayList<String>();
        while(c.moveToNext()){
            String getAddress=c.getString(0);
            items.add(getAddress);
        }

        return items;


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        String query1 = "SELECT address FROM attendee where fr_seq='"+getSeq+"'";
        c = mdb.rawQuery(query1,null);

        ArrayList<String> items=new ArrayList<String>();
        while(c.moveToNext()){
            String getAddress=c.getString(0);
            items.add(getAddress);
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onClick(View v) {
     //  String query ="INSERT INTO attendee (fr_seq,name,latitude,longitude,reg_date,modi_date,fr_code,address) values ('m0179','ㅎㅎㅎ광화문역 5호선', 37.5712497, 126.9773945, '2018/11/29 06:20:09',null,'QuQTW2cBOAUY6uUl2HEK','과천시 종로구 세종대로 172' )";
        //"INSERT INTO MEETUP VALUES ('" + str + "','" + title + "','" + seletedAge + "','" + seletedGender + "','" + formatDate + "','" + null + "')";
      // String query="INSERT INTO MEETUP VALUES ('m0179','수요일엔독서모임', '40대', '여성','2018/11/29 06:20:09',null);";
      //   mdb.execSQL(query);
    //   Toast.makeText(getApplicationContext(),"완료", LENGTH_SHORT).show();
    }
}
