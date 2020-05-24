package com.example.coronatravel.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

//우리가 봐야할 거는 initView 랑 getData Class. 이 두개만 수정하면 될거야 형

public class HomeFragment extends Fragment {

    TextView addressTextview;
    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    TextView totalView, dischargedView, curingView, deathView, patientView, dailyCuredTextview, dailyDiagnosisTextview;
    Context context;

    TextView totalViewAddress, dischargedViewAddress, curingViewAddress, deathViewAddress;

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

        totalView = root.findViewById(R.id.totalView);
        dischargedView = root.findViewById(R.id.discharged);
        curingView = root.findViewById(R.id.curing);
        deathView = root.findViewById(R.id.death);
        patientView = root.findViewById(R.id.patientView);
        dailyDiagnosisTextview = root.findViewById(R.id.dailyDiagnosisTextview);
        dailyCuredTextview = root.findViewById(R.id.dailyCuredTextview);

        context = getActivity();

        addressTextview = root.findViewById(R.id.addressTextview);

        // 네트워크 연결상태 체크
        if (NetworkConnection() == false) NotConnected_showAlert();
        checkPermissions();

        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }else {
            checkRunTimePermission();
        }

        gpsTracker = new GpsTracker(getActivity());
         double latitude = gpsTracker.getLatitude();
         double longitude = gpsTracker.getLongitude();

         String address = getCurrentAddress(latitude, longitude);
        addressTextview.setText(address);

        return root;
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
        //연결 원하는 링크 문자열로 저장해주기
        String path2 = "http://ncov.mohw.go.kr/";
        String pathAddress = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
        //execute하면 자동으로 doInBackground 랑 onPostExecute가 실행되는거같아 내 생각에는
        new getData1().execute(path2);
        new getData2().execute(path2);
        new getData3().execute(path2);
        new getData4().execute(path2);
        new getData5().execute(path2);
        new getDailyDiagnosis().execute(path2);
        new getDailyCured().execute(path2);
    }

    private class getDailyDiagnosis extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("span.data1");
                String now = elements.get(0).text();
                return "일일 확진자\n" + now ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dailyDiagnosisTextview.setText(result);
        }
    }

    private class getDailyCured extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
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
            dailyCuredTextview.setText(result);
        }
    }

    private class getData1 extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
                try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                //자식으로 타고 간다고 생각하면 돼 <div class="liveNumOuter"> 에 있는 <ul class="liveNum"> 에 있는 <span class="num">을 elements에 저장
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                //elements에 있는 첫번째(0번 index) 값을 string 으로 받아오기
                String now = elements.get(0).text();
                //이거는 문자열 자르는거야
                now = now.substring(4,10);
                Elements elements1 = document.select("div.liveNumOuter").select("ul.liveNum").select("span.before");
                String compare = elements1.get(0).text();
                compare = compare.substring(5,11);
                return "확진환자\n\n" + now + "\n" + compare;
                //잘 이해가 안되면 저 링크 url 소스코드 열어보고 확인해보면 이해가 빠를거야
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
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(1).text();
                Elements elements1 = document.select("div.liveNumOuter").select("ul.liveNum").select("span.before");
                String compare = elements1.get(1).text();
                return "완치\n(격리해제)\n" + now + "\n" + compare;
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
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(2).text();
                Elements elements1 = document.select("div.liveNumOuter").select("ul.liveNum").select("span.before");
                String compare = elements1.get(2).text();
                return "치료 중\n(격리 중)\n" + now + "\n" + compare;
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
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("ul.liveNum").select("span.num");
                String now = elements.get(3).text();
                Elements elements1 = document.select("div.liveNumOuter").select("ul.liveNum").select("span.before");
                String compare = elements1.get(3).text();
                return "사망\n\n" + now + "\n" + compare;
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
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.liveNumOuter").select("span.livedate"); // 내용중에서 원하는 부분을 가져온다.
                String text = elements.get(0).text();
                return "환자현황" + text;
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
                        //application 프로세스를 강제 종료
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

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(getActivity(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(), "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(getActivity(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(getActivity(), "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(getActivity(), "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getActivity(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}