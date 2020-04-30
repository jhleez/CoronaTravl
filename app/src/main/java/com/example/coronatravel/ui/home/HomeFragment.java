package com.example.coronatravel.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

//우리가 봐야할 거는 initView 랑 getData Class. 이 두개만 수정하면 될거야 형

public class HomeFragment extends Fragment {

    TextView totalView, dischargedView, curingView, deathView, patientView;
    Context context;

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

        context = getActivity();

        // 네트워크 연결상태 체크
        if (NetworkConnection() == false) NotConnected_showAlert();
        checkPermissions();

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
        String path1 = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=";
        //execute하면 자동으로 doInBackground 랑 onPostExecute가 실행되는거같아 내 생각에는
        new getData1().execute(path2);
        new getData2().execute(path2);
        new getData3().execute(path2);
        new getData4().execute(path2);
        new getData5().execute(path2);
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
                compare = compare.substring(5,10);
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
}