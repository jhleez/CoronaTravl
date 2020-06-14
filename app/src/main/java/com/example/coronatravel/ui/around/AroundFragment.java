package com.example.coronatravel.ui.around;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.coronatravel.Adapter.ItemAdapter;
import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;
import com.example.coronatravel.Adapter.SwipeAdapter;
import com.example.coronatravel.SpinnerAdapter;
import com.example.coronatravel.TypeId;
import com.example.coronatravel.ui.ListViewFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class AroundFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private AroundViewModel aroundViewModel;
    String contentTypeId;
    double latitude, longitude;
    String address;
    ImageButton next,pre;


    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private GpsTracker gpsTracker;

    private SwipeAdapter swipeAdapter;
    private View root;
    private ViewPager viewPager;
    private Spinner spinner;
    private EditText editText;
    private Spinner spinner_searchtype;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aroundViewModel =
                ViewModelProviders.of(this).get(AroundViewModel.class);
        root = inflater.inflate(R.layout.fragment_around, container, false);
        viewPager = root.findViewById(R.id.viewpager_around_page);
        viewPager.addOnPageChangeListener(this);

        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        } else {
            checkRunTimePermission();
        }

        SpinnerAdapter adapter = new SpinnerAdapter(getContext());
        spinner = root.findViewById(R.id.spinner_around_traveltype);
        adapter.addItem("전체");
        adapter.addItem("관광지");
        adapter.addItem("문화 시설");
        adapter.addItem("축제 공연 행사");
        adapter.addItem("여행 코스");
        adapter.addItem("레포츠");
        adapter.addItem("숙박");
        adapter.addItem("쇼핑");
        adapter.addItem("음식점");
        spinner.setAdapter(adapter);

        ImageButton button = root.findViewById(R.id.button_around_search);
        button.setOnClickListener(this);
        next=root.findViewById(R.id.around_floatbt_next);
        pre=root.findViewById(R.id.around_floatbt_previous);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((MainActivity) getActivity()).aroundSearch(contentTypeId, "10000", "E", String.valueOf(longitude), String.valueOf(latitude), String.valueOf(viewPager.getCurrentItem()));
                    //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨
                    ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                    ListViewFragment.listView.setAdapter(itemAdapter);
                }catch(Exception e){
                    e.printStackTrace();
                }
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                ListViewFragment.listView.setAdapter(itemAdapter);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((MainActivity) getActivity()).aroundSearch(contentTypeId, "10000", "E", String.valueOf(longitude), String.valueOf(latitude), String.valueOf(viewPager.getCurrentItem()+1));
                    //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨
                    ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                    ListViewFragment.listView.setAdapter(itemAdapter);
                }catch(Exception e){
                    e.printStackTrace();
                }
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
                ListViewFragment.listView.setAdapter(itemAdapter);
            }
        });
        gpsTracker = new GpsTracker(getActivity());
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        address = getCurrentAddress(latitude, longitude);
        return root;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("ITPANGPANG", "onPageScrolled : " + position);
    }

    @Override
    public void onPageSelected(int position) {
        int pagecount= (Integer.parseInt(LocationBasedList_Class.totalcount)%10==0)?
                (Integer.parseInt(LocationBasedList_Class.totalcount)/10):(Integer.parseInt(LocationBasedList_Class.totalcount)/10)+1;
        if(position==0){
            pre.setVisibility(View.GONE);
        }else if(position+1==pagecount){
            next.setVisibility(View.GONE);
        }else{
            pre.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }

        Log.d("ITPANGPANG", "onPageSelected : " + position);
        ((MainActivity) getActivity()).aroundSearch(contentTypeId, "10000", "E", String.valueOf(longitude), String.valueOf(latitude), String.valueOf(position + 1));
        //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);
        ListViewFragment.listView.setAdapter(itemAdapter);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("ITPANGPANG", "onPageScrollStateChanged : " + state);

    }

    @Override
    public void onClick(View view) {

        contentTypeId = TypeId.ContentTypeId(spinner.getSelectedItemPosition());

        ((MainActivity) getActivity()).aroundSearch(contentTypeId, "10000", "E",  String.valueOf(longitude),String.valueOf(latitude), "1");
        Log.d("거리","거리" + MainActivity.LocationBasedList_ArrayList.get(1).getDist());
        if (Integer.parseInt(LocationBasedList_Class.totalcount) != 0) {

            int pagecount= (Integer.parseInt(LocationBasedList_Class.totalcount)%10==0)?
                    (Integer.parseInt(LocationBasedList_Class.totalcount)/10):(Integer.parseInt(LocationBasedList_Class.totalcount)/10)+1;
            pre.setVisibility(View.GONE);
            if(1!=pagecount){
                next.setVisibility(View.VISIBLE);
            }

            swipeAdapter = new SwipeAdapter(getChildFragmentManager(), (Integer.parseInt(LocationBasedList_Class.totalcount) / 10) + 1);

            viewPager.setOffscreenPageLimit(1);
            viewPager.setAdapter(swipeAdapter);
            viewPager.setCurrentItem(0);

            //변수에 우리가 선택한 스피너, 위도경도, 정렬이 드가면 됨

            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.LocationBasedList_ArrayList);

            ListViewFragment.listView.setAdapter(itemAdapter);
            ListViewFragment.linearLayout.setVisibility(View.GONE);
           // Toast.makeText(getActivity(), Integer.parseInt(LocationBasedList_Class.totalcount) + "\n" + "현재위치 \n위도 " + latitude + "\n경도 " + longitude + "\n" + address, Toast.LENGTH_LONG).show();
        }else{
            ListViewFragment.linearLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {
            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            boolean check_result = true;
            // 모든 퍼미션을 허용했는지 체크합니다.
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if (check_result) {
                //위치 값을 가져올 수 있음
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(getActivity(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    void checkRunTimePermission() {
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

    public String getCurrentAddress(double latitude, double longitude) {

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
        return address.getAddressLine(0).toString() + "\n";

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