package com.example.coronatravel;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FirstPopup extends Activity {
    TextView contents;
    Button closebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first_popup);

        contents = (TextView)findViewById(R.id.contents);
        closebtn = (Button)findViewById(R.id.closebtn);
        String notice1 = "* 공공데이터 서버가 리뉴얼 작업으로 불안정한 관계로 날씨 및 공적마스크 정보 조회시 오류가 발생할 수 있습니다 \n\n";
        String notice3 = "* 인터넷 연결상태가 고르지 않을 경우, 어플 작동에 오류가 발생 할 수 있습니다\n\n";
        String notice4 = "* 이 안내 메시지는 앱 설치후 최초 1회 만 자동으로 실행됩니다";

        contents.setText(notice1+notice2+notice3+notice4);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
