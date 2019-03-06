package com.example.happyrojgar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class Underdog {
    private String TAG = "Underdog";
    private Context context;

    public Underdog() {
        //Default constructor
    }

    public Underdog(Context context) {
        this.context = context;
    }

    protected JSONArray HttpCaller(JSONObject parent) {
        Log.w(TAG, "In Underdog HttpCaller : " + parent);
        String result = "";
        HTTPRequestHandler getRequest = new HTTPRequestHandler();
        try {
            result = getRequest.execute(parent).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        //Log.w(TAG, "HttpCaller: "+convertToJsonArray(result));
        //return convertToHashMap(result);
        if (result != null) {
            return convertToJsonArray(result);
        } else {
            return null;
        }

    }

    protected JSONArray HttpCaller(JSONObject parent, ProgressBar progressBar) {
        Log.w(TAG, "In Underdog HttpCaller with progressBar : " + parent);
        String result = "";
        HTTPRequestHandler getRequest = new HTTPRequestHandler(progressBar);
        try {
            result = getRequest.execute(parent).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        //Log.w(TAG, "HttpCaller: "+convertToJsonArray(result));
        //return convertToHashMap(result);
        if (result != null) {
            return convertToJsonArray(result);
        } else {
            return null;
        }

    }


    private HashMap<String, Object> convertToHashMap(String jsonString) {
        HashMap<String, Object> myHashMap = new HashMap<String, Object>();
        try {
            JSONArray jArray = new JSONArray(jsonString);
            JSONObject jObject = null;
            String keyString = null;
            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);
                keyString = (String) jObject.names().get(i);
                myHashMap.put(keyString, jObject.get(keyString));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myHashMap;
    }

    private JSONArray convertToJsonArray(String jsonString) {
        JSONArray jArray = null;
        JSONObject jObject = null;
        try {
            jArray = new JSONArray(jsonString);

            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jArray;
    }

    protected long TimeStringToMillis(String timeString) {
        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        //SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        try {
            Date mDate = sdf.parse(timeString);
            //System.out.println("Date in milli :: " + timeInMilliseconds);
            return mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected String MillisToDateFormat(String dateInMilliStrings, String dateFormat) {
        if (!dateInMilliStrings.equals("")) {
            //long milliSeconds = DateFormat.format(dateFormat, Long.parseLong(dateInMillistrings)).toString();
            long milliSeconds = Long.parseLong(dateInMilliStrings);
            // Create a DateFormatter object for displaying date in specified format.
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Log.w(TAG, "" + milliSeconds);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return formatter.format(calendar.getTime());
        }else
            return "";
    }

    protected String TimeStringToDataFormat(String timeString) {
        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        //SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm", Locale.getDefault());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm", Locale.getDefault());
        try {
            Date mDate = sdf.parse(timeString);
            long timeInMilliseconds = mDate.getTime();
            //System.out.println("Date in milli :: " + timeInMilliseconds);
            //return timeInMilliseconds;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeInMilliseconds);
            return formatter.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String ConvertDateOne(String dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }

    protected String GetImageByte(ImageView imgView) {
        Bitmap bitmap = ((BitmapDrawable) imgView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] imageInByte = baos.toByteArray();
        String imageString = Base64.encodeToString(imageInByte, Base64.DEFAULT);
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    protected Bitmap ImageByteToImage(String imageString, int width, int height) {
        //https://www.thecrazyprogrammer.com/2016/10/android-convert-image-base64-string-base64-string-image.html
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        //image.setImageBitmap(decodedImage);
        decodedImage.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        /*Bitmap resized = */
        return Bitmap.createScaledBitmap(decodedImage, width, height, true);
        //return resized;


        /*byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        */
    }

    protected void DisplaySoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    protected void HideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

    }

    protected boolean IsSoftKeyBoardOnDisplay(){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        Log.w(TAG,"IsSoftKeyBoardOnDisplay : "+imm.isAcceptingText());
        return imm.isAcceptingText();
    }
}
