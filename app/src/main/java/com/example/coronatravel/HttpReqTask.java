package com.example.coronatravel;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpReqTask extends AsyncTask <String,String,String>{
    protected String doInBackground(String... arg) {
        String response = getHttpConnResult(arg[0]);
        return response;
    }

    protected void onPostExecute(String result) {

    }



    public String getHttpConnResult(String strUrl) {
        String line;
        String result = new String();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while((line = reader.readLine()) != null) {
                result += line + '\n';
                //if( result.length() > 20000) break;
            }
            reader.close();
            conn.disconnect();
        } catch(Exception e) {
            Log.d("tag", "HttpURLConnection error");
        }
        return result;
    }
}
