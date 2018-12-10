package com.womenproiot.www.link;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AttendeesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ImageButton buttonNew, buttonCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees);

        ((ImageButton) findViewById(R.id.buttonNew)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.buttonCenter)).setOnClickListener(this);


        //  String query = "SELECT seq,title FROM meetup order by reg_date asc";
        ArrayList<MeetUpDto> spinnerList = LinkDAO.getInstance(this).selectMeetupList();
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);


        MySpinner adapter =
                new MySpinner(this, R.layout.spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        //스피너에서 아이템중 하나를 선택하면 호출된다
        //view는 item_spinner의 가장 바깥 View
        ConstraintLayout layout = (ConstraintLayout) view;
        //layout에서 gone으로 설정된 seq를 불러온다.
        TextView seq = (TextView) layout.getViewById(R.id.textViewSeq);
        //seq(모임 테이블의 pk)를 들고 db에서 참석자들의 위치를 가져온다.
        ArrayList<AttendeeDto> list = LinkDAO.getInstance(this).selectAttendee(seq.getText().toString());

        //db에서 가져온 참석자 리스트를 리사이클러뷰에 뿌려준다.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //리스트 형식으로 보여줄꺼니까 LinearLayoutManager를 선택한다.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);

       // Toast.makeText(this, seq.getText().toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View v) {

    }
}
