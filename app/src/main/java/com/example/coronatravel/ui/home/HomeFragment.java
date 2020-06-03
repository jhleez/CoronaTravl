package com.example.coronatravel.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.coronatravel.Festival;
import com.example.coronatravel.HttpReqTask;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.content.Context.LOCATION_SERVICE;

//우리가 봐야할 거는 initView 랑 getData Class. 이 두개만 수정하면 될거야 형

public class HomeFragment extends Fragment {

    TextView totalView, dischargedView, curingView, deathView, patientView, dailyCuredTextview, dailyDiagnosisTextview;
    Context context;

    private ImageView spotImage;
    private TextView spotName, spotEventPeriod;

    private String startdate = "", enddate = "";
    private String totalcount;
    ImageView card1;
    private HomeViewModel homeViewModel;

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            initView();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getActivity(), "권한 허용을 하지 않으면 서비스를 이용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        totalView = root.findViewById(R.id.totalView);
        dischargedView = root.findViewById(R.id.discharged);
        curingView = root.findViewById(R.id.curing);
        deathView = root.findViewById(R.id.death);
        patientView = root.findViewById(R.id.patientView);
        dailyDiagnosisTextview = root.findViewById(R.id.dailyDiagnosisTextview);

        spotImage = root.findViewById(R.id.spotImage);
        spotName = root.findViewById(R.id.spotName);
        spotEventPeriod = root.findViewById(R.id.spotEventPeriod);

        context = getActivity();

        recommendation();

        // 네트워크 연결상태 체크
        if (NetworkConnection() == false) NotConnected_showAlert();
        checkPermissions();

        spotImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSpotDetailView();
            }
        });



        return root;


    }

    public void moveToSpotDetailView() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.fragment_home_resetButton :
                recommendation();
                initView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void recommendation() {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        startdate = df.format(cal.getTime());
        cal.add(Calendar.DATE, 14);
        enddate = df.format(cal.getTime());


        String ServiceKey = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
        String RandomFestivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
                "ServiceKey=" + ServiceKey +
                "&eventStartDate=" + startdate +
                "&eventEndDate=" + enddate +
                "&areaCode=&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y" +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&arrange=O&numOfRows=1&pageNo=1&_type=json";
        Log.d("RND",RandomFestivalUrl);
        String JSONFromRandomFestivalUrl = "";
        try {
            JSONFromRandomFestivalUrl = new HttpReqTask().execute(RandomFestivalUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        totalcount = ShortJSONParsing(JSONFromRandomFestivalUrl);
        Random rnd = new Random();
        String randomnum = String.valueOf(rnd.nextInt((Integer.parseInt(totalcount) / 10) * 8));
        int randomarrange =rnd.nextInt(4);
        String arrange[] = {"O","P","Q","R"};
        RandomFestivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
                "ServiceKey=" + ServiceKey +
                "&eventStartDate=" + startdate +
                "&eventEndDate=" + enddate +
                "&areaCode=&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y" +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&arrange=" + arrange[randomarrange] +
                "&numOfRows=1&pageNo=" + randomnum + "&_type=json";


        try {
            JSONFromRandomFestivalUrl = new HttpReqTask().execute(RandomFestivalUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }


        Festival festival = JSONParsing(JSONFromRandomFestivalUrl);

        final String startMonth = festival.getEventstartdate().substring(4,6);
        final String startDate = festival.getEventstartdate().substring(6);
        final String endMonth = festival.getEventenddate().substring(4,6);
        final String endDate = festival.getEventenddate().substring(6);

        spotName.setText(" " + festival.getTitle());
        spotEventPeriod.setText("  (" + startMonth + "월 " + startDate + "일 ~ " + endMonth + "월 "+ endDate + "일)");

        String URI = festival.getFirstimage();
        if (URI == "") URI = "http://";
        Picasso.get().load(URI).placeholder(R.drawable.ic_launcher_background).into(spotImage);

    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) { // 마시멜로(안드로이드 6.0) 이상 권한 체크
            TedPermission.with(context)
                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage("앱을 이용하기 위해서는 접근 권한이 필요합니다")
                    .setDeniedMessage("앱에서 요구하는 권한설정이 필요합니다...\n [설정] > [권한] 에서 사용으로 활성화해주세요.")
                    .setPermissions(new String[]{
                            android.Manifest.permission.WRITE_CONTACTS, // 주소록 액세스 권한
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE // 기기, 사진, 미디어, 파일 엑세스 권한
                    })
                    .check();

        } else {
            initView();
        }
    }

    private void initView() {
        String path2 = "http://ncov.mohw.go.kr/";
        new getDailyDiagnosis().execute(path2);
        new getData1().execute(path2);
        new getData2().execute(path2);
        new getData3().execute(path2);
        new getData4().execute(path2);
        new getData5().execute(path2);
    }

    private class getDailyDiagnosis extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get();
                Elements elements = document.select("div.liveNumOuter").select("span.data1");
                String now = elements.get(0).text();
                return now ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dailyDiagnosisTextview.setText("+" + result);
        }
    }

    private class getDailyCured extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get();
                Elements elements = document.select("div.liveNumOuter").select("span.data2");
                String now = elements.get(0).text();
                return "일일 완치자\n" + now ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //dailyCuredTextview.setText(result);
        }
    }

    private class getData1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
                try {
                Document document = Jsoup.connect(params[0].toString()).get();
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(0).text();
                now = now.substring(4,10);
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            totalView.setText(result);
        }
    }

    private class getData2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get();
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(1).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dischargedView.setText(result);
        }
    }

    private class getData3 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get();
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(2).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            curingView.setText(result);
        }
    }

    private class getData4 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(3).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            deathView.setText(result);
        }
    }

    private class getData5 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("span.livedate");
                String text = elements.get(0).text();
                return "  " + text;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            patientView.setText(result);
        }
    }

    private void NotConnected_showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("네트워크 연결 오류");
        builder.setMessage("사용 가능한 무선네트워크가 없습니다.\n" + "먼저 무선네트워크 연결상태를 확인해 주세요.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private boolean NetworkConnection() {
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.getType() == networkType) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static String ShortJSONParsing(String JSONFromLocationBasedListaddr) {
        String totalcount = "";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalcount = jsonObject_body.getString("totalCount");
        } catch (Exception e) {

        }
        return totalcount;
    }


    public static Festival JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        String addr1="";
        String areacode="";
        String eventenddate="";
        String eventstartdate="";
        String firstimage="";
        String title="";
        String contentid="";
        String contenttypeid="";
        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONObject jsonArray_item = new JSONObject(item);

            try {
                eventenddate = jsonArray_item.getString("eventenddate");
            } catch (JSONException e) {
                eventenddate = "";
            }
            try {
                eventstartdate = jsonArray_item.getString("eventstartdate");
            } catch (JSONException e) {
                eventstartdate = "";
            }
            try {
                firstimage = jsonArray_item.getString("firstimage");
            } catch (JSONException e) {
                firstimage = "";
            }
            try {
                title = jsonArray_item.getString("title");
            } catch (JSONException e) {
                title = "";
            }

            contentid = jsonArray_item.getString("contentid");
            contenttypeid = jsonArray_item.getString("contenttypeid");
            Festival festival = new Festival(addr1, areacode, eventenddate, eventstartdate, firstimage, title, contentid, contenttypeid, "");
            return festival;
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        Festival festival = new Festival(addr1, areacode, eventenddate, eventstartdate, firstimage, title, contentid, contenttypeid, "");
        return festival;
    }

}