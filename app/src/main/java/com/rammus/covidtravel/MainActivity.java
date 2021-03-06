package com.rammus.covidtravel;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;

import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<LocationBasedList_Class> LocationBasedList_ArrayList = new ArrayList<>();
    public static ArrayList<Mask> MASK_AraayList = new ArrayList<>();
    public static ArrayList<ShortWeather> ShortWeather_ArrayList = new ArrayList<>();
    public static ShortWeather sub_shortweather = new ShortWeather();
    private AppBarConfiguration mAppBarConfiguration;
    String id = "LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D";
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
        SharedPreferences preferences;
        preferences = getSharedPreferences("Prefers", MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
        if(isFirstRun){
            Intent intent = new Intent(this,FirstPopup.class);
            startActivity(intent);
            preferences.edit().putBoolean("isFirstRun", false).apply();
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

            LocationBasedList_Class subclass = new LocationBasedList_Class(addr1,contentid,contenttypeid,firstimage,title,"");
            LocationBasedList_ArrayList.add(subclass);
        }
    }

}