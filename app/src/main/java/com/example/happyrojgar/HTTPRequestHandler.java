package com.example.happyrojgar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HTTPRequestHandler extends AsyncTask<JSONObject, Void, String> {

    private String TAG = "HttpGetRequest";
    private static final String REQUEST_METHOD = "POST";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final String Base_url = "http://192.168.0.22:7825/bin/interceptor";
    private String boundary;
    private static final String LINE_FEED = "\r\n";
    private String charset;
    private OutputStream outputStream;
    private BufferedWriter writer;
    private Handler mHandler;

    @SuppressLint("StaticFieldLeak")
    private ProgressBar mProgressBar;

    public HTTPRequestHandler() {
        //Default constructor
    }

    public HTTPRequestHandler(ProgressBar mProgressDialog) {
        this.mProgressBar = mProgressDialog;
    }

    @Override
    protected String doInBackground(JSONObject... params) {
        Log.w(TAG, Arrays.toString(params));
        String result = null;
        String inputLine;
        boundary = "===" + System.currentTimeMillis() + "===";
        //HttpURLConnection connection = null;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(Base_url);
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            //connection.setReadTimeout(READ_TIMEOUT);
            //connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestProperty("Origin", Base_url);
            connection.setAllowUserInteraction(false);
            connection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("function", String.valueOf(params[0].get("function")))
                    .appendQueryParameter("token", String.valueOf(params[0].get("token")))
                    .appendQueryParameter("args", String.valueOf(params[0].get("args")));
            String query = builder.build().getEncodedQuery();

            outputStream = connection.getOutputStream();
            writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            assert query != null;
            writer.write(query);
            writer.flush();
            writer.close();
            outputStream.close();

            //Connect to our url
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // OK

                // otherwise, if any other status code is returned, or no status
                // code is returned, do stuff in the else block
            } else {
                // Server returned HTTP error code.
                Log.e(TAG, "Sorry Not connected to server, try again !!!");
            }
            //Create a new InputStreamReader
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        } catch (IOException e) {
            Log.e(TAG, "Sorry Not connected to server, try again !!!(IOException)");
            e.printStackTrace();
            result = null;
        } catch (JSONException e) {
            e.printStackTrace();
        } /*finally {
            if (connection != null) {
                connection.disconnect();
            }
        }*/

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       // Log.w(TAG, "onPostExecute");
        //mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (mProgressBar != null) {
                    //Log.w(TAG, "onPreExecute progress bar not empty");
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        },1000);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Log.w(TAG, "onPreExecute");
        mHandler = new Handler();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar != null) {
                    //Log.w(TAG, "onPreExecute progress bar not empty");
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
