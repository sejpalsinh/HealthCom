package com.example.healthcom;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    String json_url;
    String JSON_STRING;
    public  static  String doc;
    public  static  String fac;
    public  static  String hos;
    public  static  Boolean pro_flag;


    public AsyncResponse delegate = null;

    public BackgroundTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }


    @Override
    protected String doInBackground(String... strings) {

        json_url = "http://jasminjasani.com/sub_domain/rkuapps/datagov/appservice/";
        String havetoDo;
        String registration = json_url + "registration.php";
        String login = json_url + "chkLogin.php";
        String getAllFacility = json_url + "getFacilites.php";
        String getAllDoc = json_url + "getDoctors.php";
        String getAllHos =  json_url + "getHospital.php";
        String getSelectedHospita = json_url + "getSelectedHospital.php";
        havetoDo = strings[0];
        switch (havetoDo) {
            case "regi": {
                try {
                    URL url = new URL(registration);
                    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz"+strings[1]);
                    strings[1] = "'"+strings[1]+"'";
                    strings[2] = "'"+strings[2]+"'";
                    strings[3] = "'"+strings[3]+"'";
                    strings[4] = "'"+strings[4]+"'";
                    strings[5] = "'"+strings[5]+"'";
                    strings[6] = "'"+strings[6]+"'";
                    strings[7] = "'"+strings[7]+"'";
                    strings[8] = "'"+strings[8]+"'";
                    strings[9] = "'"+strings[9]+"'";
                    strings[10] = "'"+strings[10]+"'";
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data =
                            URLEncoder.encode("h_name","UTF-8")+"="+
                                    URLEncoder.encode(strings[1],"UTF-8")+"&"+
                                    URLEncoder.encode("h_address","UTF-8")+"="+
                                    URLEncoder.encode(strings[2],"UTF-8")+"&"+
                                    URLEncoder.encode("h_city","UTF-8")+"="+
                                    URLEncoder.encode(strings[3],"UTF-8")+"&"+
                                    URLEncoder.encode("h_state","UTF-8")+"="+
                                    URLEncoder.encode(strings[4],"UTF-8")+"&"+
                                    URLEncoder.encode("h_number","UTF-8")+"="+
                                    URLEncoder.encode(strings[5],"UTF-8")+"&"+
                                    URLEncoder.encode("h_time","UTF-8")+"="+
                                    URLEncoder.encode(strings[6],"UTF-8")+"&"+
                                    URLEncoder.encode("h_type","UTF-8")+"="+
                                    URLEncoder.encode(strings[7],"UTF-8")+"&"+
                                    URLEncoder.encode("uname","UTF-8")+"="+
                                    URLEncoder.encode(strings[8],"UTF-8")+"&"+
                                    URLEncoder.encode("password","UTF-8")+"="+
                                    URLEncoder.encode(strings[9],"UTF-8")+"&"+
                                    URLEncoder.encode("flag","UTF-8")+"="+
                                    URLEncoder.encode(strings[10],"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    JSON_STRING = sb.toString().trim();
                    //  System.out.println("wwwwwwwwww"+JSON_STRING );
                    return JSON_STRING;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "login": {
                try {
                    URL url = new URL(login);
                    System.out.println("urlurkurl :"+url);
                    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz"+strings[1]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data =
                            URLEncoder.encode("uname","UTF-8")+"="+
                                    URLEncoder.encode(strings[1],"UTF-8")+"&"+
                                    URLEncoder.encode("password","UTF-8")+"="+
                                    URLEncoder.encode(strings[2],"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    JSON_STRING = sb.toString().trim();
                    System.out.println("wwwwwwwwww"+JSON_STRING );
                    return JSON_STRING;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "regi2": {
                try {
                    URL url = new URL(registration);
                    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz"+strings[1]);
                    strings[1] = "'"+strings[1]+"'";
                    strings[2] = "'"+strings[2]+"'";
                    strings[3] = "'"+strings[3]+"'";
                    strings[4] = "'"+strings[4]+"'";
                    strings[5] = "'"+strings[5]+"'";
                    strings[6] = "'"+strings[6]+"'";
                    strings[7] = "'"+strings[7]+"'";
                    strings[8] = "'"+strings[8]+"'";
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data =
                            URLEncoder.encode("p_name","UTF-8")+"="+
                                    URLEncoder.encode(strings[1],"UTF-8")+"&"+
                                    URLEncoder.encode("p_address","UTF-8")+"="+
                                    URLEncoder.encode(strings[2],"UTF-8")+"&"+
                                    URLEncoder.encode("p_bdate","UTF-8")+"="+
                                    URLEncoder.encode(strings[3],"UTF-8")+"&"+
                                    URLEncoder.encode("p_number","UTF-8")+"="+
                                    URLEncoder.encode(strings[4],"UTF-8")+"&"+
                                    URLEncoder.encode("p_gen","UTF-8")+"="+
                                    URLEncoder.encode(strings[5],"UTF-8")+"&"+
                                    URLEncoder.encode("uname","UTF-8")+"="+
                                    URLEncoder.encode(strings[6],"UTF-8")+"&"+
                                    URLEncoder.encode("password","UTF-8")+"="+
                                    URLEncoder.encode(strings[7],"UTF-8")+"&"+
                                    URLEncoder.encode("flag","UTF-8")+"="+
                                    URLEncoder.encode(strings[8],"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    JSON_STRING = sb.toString().trim();
                    //   System.out.println("wwwwwwwwww"+JSON_STRING );
                    return JSON_STRING;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "getfacility": {
                try {
                    URL url = new URL(getAllFacility);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    String message = sb.toString().trim();
                    System.out.println("ffffffffffffffffffffffffff :"+message);
                    fac = message;
                    fac = "{"+"\"facilities\":"+fac+"}";
                    System.out.println("jjjjjjjjjjjjjjj :"+fac);
                    return fac;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("in facility eror :"+e);
                }
                break;
            }
            case "gethospital": {
                try {
                    URL url = new URL(getAllHos);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    String message = sb.toString().trim();
                    System.out.println("ffffffffffffffffffffffffff :"+message);
                    hos = message;
                    hos = "{"+"\"hospital\":"+hos+"}";
                    return hos;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "getdoctors": {
                try {
                    URL url = new URL(getAllDoc);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    String message = sb.toString().trim();
                    System.out.println("ffffffffffffffffffffffffff :"+message);
                    doc = message;
                    doc = "{"+"\"doctors\":"+doc+"}";
                    return doc;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "getSelectedHospita": {
                try {
                    URL url = new URL(getSelectedHospita);
                    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz"+strings[1]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data =
                                    URLEncoder.encode("sqlstr","UTF-8")+"="+
                                    URLEncoder.encode(strings[1],"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    is.close();
                    httpURLConnection.disconnect();
                    JSON_STRING = sb.toString().trim();
                    JSON_STRING = "{"+"\"selected_hospital\":"+JSON_STRING+"}";
                    System.out.println("wwwwwwwwww"+JSON_STRING );
                    return JSON_STRING;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("erroerro + "+e);
                }
                break;
            }

        }
        System.out.println("nonononononononnononononononononono");
        return "sejpal";
    }


    @Override
    protected void onPreExecute() {
        json_url = "https://datadotgov.000webhostapp.com/";
        pro_flag = false;

    }


    @Override
    protected void onPostExecute(String result) {
        pro_flag = true;
        try {
            delegate.processFinish(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface AsyncResponse {
        void processFinish(String output) throws JSONException;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
