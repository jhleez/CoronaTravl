package com.example.coronatravel;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.example.coronatravel.detail.Detail_view;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<LocationBasedList_Class> LocationBasedList_ArrayList = new ArrayList<>();
    public static ArrayList<Mask> MASK_AraayList = new ArrayList<>();
    public static ArrayList<ShortWeather> ShortWeather_ArrayList = new ArrayList<>();
    public static ShortWeather sub_shortweather = new ShortWeather();
    private AppBarConfiguration mAppBarConfiguration;
    String id = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
    DbOpenHelper mDbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDbOpenHelper = new DbOpenHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.actionbar));
        setSupportActionBar(toolbar);
        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.MainActivity_app_bar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(appBarLayout, "elevation", 0.1f));
            appBarLayout.setStateListAnimator(stateListAnimator);
        }

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



        // sqlite example data set

        mDbOpenHelper.open();
        mDbOpenHelper.create();

        mDbOpenHelper.deleteAllColumns();
        mDbOpenHelper.insertColumn("서울특별시 종로구 북촌로 52", "130446","14", "http://tong.visitkorea.or.kr/cms/resource/75/2550575_image2_1.jpg", "가회민화박물관");
        mDbOpenHelper.insertColumn("서울특별시 종로구 창경궁로 88", "132183","38", "http://tong.visitkorea.or.kr/cms/resource/11/710311_image2_1.jpg", "광장시장");


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
                "&numOfRows=10" +
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
        if(sigunguCode.equals("0")){
            sigunguCode = "";
        }
        if(areaCode.equals("0")){
            areaCode="";
        }
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
                "&numOfRows=10" +
                "&pageNo=" +pageNo+
                "&_type=json";
        Log.d("URL",LocalSearchAddr);
        String JSONFromLocalSearch = "";
        try {
            JSONFromLocalSearch = new HttpReqTask().execute(LocalSearchAddr).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        LocationBasedList_Class.JSONParsing(JSONFromLocalSearch);
    }

    public void localSearch2(String contentTypeId,String areaCode, String sigunguCode, String cat1, String cat2, String cat3,String arrage, String pageNo){
        LocationBasedList_ArrayList.clear();
        if(sigunguCode.equals("0")){
            sigunguCode = "";
        }
        if(areaCode.equals("0")){
            areaCode="";
        }
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
        Log.d("URL",LocalSearchAddr);
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
        if(sigunguCode.equals("0")){
            sigunguCode = "";
        }
        if(areaCode.equals("0")){
            areaCode="";
        }
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
                "&numOfRows=10&" +
                "pageNo="+pageNo+
                "&_type=json";
        Log.d("TAG",TotalSearchAddr);
        String JSONFromTotalSearch = "";
        try {
            JSONFromTotalSearch = new HttpReqTask().execute(TotalSearchAddr).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        LocationBasedList_Class.JSONParsing(JSONFromTotalSearch);
    }

    public void bookMarkList(){
        LocationBasedList_ArrayList.clear();
        String addr1="", contentid="", contenttypeid="", firstimage="",title="";
        Cursor iCursor = mDbOpenHelper.sortColumn("_id");
        while (iCursor.moveToNext()) {
            addr1 = iCursor.getString(iCursor.getColumnIndex("addr1"));
            contentid = iCursor.getString(iCursor.getColumnIndex("contentid"));
            contenttypeid = iCursor.getString(iCursor.getColumnIndex("contenttypeid"));
            firstimage = iCursor.getString(iCursor.getColumnIndex("firstimage"));
            title = iCursor.getString(iCursor.getColumnIndex("title"));

            LocationBasedList_Class subclass = new LocationBasedList_Class(addr1,contentid,contenttypeid,firstimage,title);
            LocationBasedList_ArrayList.add(subclass);
        }
    }
    private long keyPressTime = 0;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > keyPressTime + 2000) {
            keyPressTime = System.currentTimeMillis();
            Toast.makeText(this, "버튼을 한번 더 누르시면 종료 됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= keyPressTime + 2000) {
            finish();
        }
    }
}