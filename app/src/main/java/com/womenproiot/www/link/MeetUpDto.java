package com.womenproiot.www.link;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

class  MeetUpDto{
        public String seq;
        public String title;
        public String age;
        public String gender;
        public String reg_date;
        public String modi_date;




        public MeetUpDto(String seq, String title, String age, String gender, String reg_date, String modi_date) {
            this.seq = seq;
            this.title = title;
            this.age = age;
            this.gender = gender;
            this.reg_date = reg_date;
            this.modi_date = modi_date;
        }

        public MeetUpDto(String seq, String title) {
            this.seq = seq;
            this.title = title;
        }


}
