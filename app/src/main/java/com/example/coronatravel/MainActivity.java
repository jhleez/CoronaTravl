package com.example.coronatravel;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<LocationBasedList_Class> LocationBasedList_ArrayList = new ArrayList<>();
    private AppBarConfiguration mAppBarConfiguration;
    String id = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_around, R.id.nav_festival,
                 R.id.nav_share, R.id.nav_send, R.id.nav_bookmark,R.id.nav_totalsearch)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void aroundSearch(String contentTypeId, String radius, String arrange, String mapX, String mapY, String pageNo) {
        LocationBasedList_ArrayList.clear();
        String AroundSearchAddr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?" +
                "ServiceKey=" + id +
                "&contentTypeId=" + contentTypeId +
                "&mapX=" + mapX +
                "&mapY=" + mapY +
                "&radius=" + radius +
                "&listYN=Y" +
                "&arrange=" + arrange +
                "&numOfRows=5" +
                "&pageNo=" + pageNo + //한 페이지 몇개씩 가져올껀지, 뒤에는 페이지 수
                "&MobileOS=AND&MobileApp=CoronaTravel&_type=json";

        String JSONFromAroundSearch = "";
        try {
            JSONFromAroundSearch = new HttpReqTask().execute(AroundSearchAddr).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        LocationBasedList_Class.JSONParsing(JSONFromAroundSearch);
    }

    public void localSearch(String contentTypeId,String areaCode, String sigunguCode, String cat1, String cat2, String cat3,String arrage, String pageNo){
        LocationBasedList_ArrayList.clear();
        String LocalSearchAddr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?" +
                "ServiceKey="+id+
                "&contentTypeId="+contentTypeId+
                "&areaCode="+areaCode+
                "&sigunguCode="+sigunguCode+
                "&cat1="+cat1+
                "&cat2="+cat2+
                "&cat3="+cat3+
                "&listYN=Y&MobileOS=AND&MobileApp=CoronaTravel" +
                "&arrange=" +arrage+
                "&numOfRows=5" +
                "&pageNo=" +pageNo+
                "&_type=json";

        String JSONFromLocalSearch = "";
        try {
            JSONFromLocalSearch = new HttpReqTask().execute(LocalSearchAddr).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        LocationBasedList_Class.JSONParsing(JSONFromLocalSearch);
    }

    public void totalSearch(String keyword,String areaCode, String sigunguCode, String cat1, String cat2, String cat3,String arrage, String pageNo){
        LocationBasedList_ArrayList.clear();
        String encodeStr="";
        try{
            encodeStr = URLEncoder.encode(keyword, "UTF-8");
        }catch (Exception e){
            Log.d("TAG","Encoding error");
        }
        String TotalSearchAddr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?" +
                "ServiceKey="+id+
                "&keyword="+encodeStr+
                "&areaCode=" + areaCode+
                "&sigunguCode="+sigunguCode+
                "&cat1="+cat1+
                "&cat2="+cat2+
                "&cat3="+cat3+
                "&listYN=Y&MobileOS=AND&MobileApp=CoronaTravel" +
                "&arrange="+arrage+
                "&numOfRows=5&" +
                "pageNo="+pageNo+
                "&_type=json";

        String JSONFromTotalSearch = "";
        try {
            JSONFromTotalSearch = new HttpReqTask().execute(TotalSearchAddr).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        LocationBasedList_Class.JSONParsing(JSONFromTotalSearch);
    }
}