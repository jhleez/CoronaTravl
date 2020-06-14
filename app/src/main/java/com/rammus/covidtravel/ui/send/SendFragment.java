package com.rammus.covidtravel.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rammus.covidtravel.R;

public class SendFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_send, container, false);

        WebView mywebview = root.findViewById(R.id.testwebview);
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);

            }
        });
        WebSettings mywebsettings = mywebview.getSettings();
        mywebsettings.setJavaScriptEnabled(true);


        mywebview.loadUrl("https://mohw.go.kr/react/ncov_map_page.jsp");
        return root;
    }
}