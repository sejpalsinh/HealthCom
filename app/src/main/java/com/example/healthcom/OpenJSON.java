package com.example.healthcom;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class OpenJSON {
    public static final String readJSONFromAsset(Context context, String jsonFile) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
