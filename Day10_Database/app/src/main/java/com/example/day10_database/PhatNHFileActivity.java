package com.example.day10_database;

import android.icu.util.Output;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PhatNHFileActivity extends AppCompatActivity {
    private EditText edtName;
    private TextView txtResult;
    private static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phat_nhfile);
        edtName = findViewById(R.id.edtName);
        txtResult = findViewById(R.id.txtResult);
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String str = null;
        String result = "";
        try {
            is = this.getResources().openRawResource(R.raw.data);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                result += str;
            }
            edtName.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToSaveInternal(View view) {
        String str = edtName.getText().toString();
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            osw.write(str);
            osw.flush();
            txtResult.setText("Save file success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToLoadInternal(View view) {
        FileInputStream fis = null;
        InputStreamReader isr  = null;
        try {
            fis = openFileInput("myfile.txt");
            isr = new InputStreamReader(fis);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String s = "Value: ";
            int charRead;
            while ((charRead = isr.read(buffer)) > 0) {
                String reading = String.copyValueOf(buffer, 0, charRead);
                s += reading;
                buffer = new char[READ_BLOCK_SIZE];
            }
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                s += line;
//            }
            edtName.setText(s);
            txtResult.setText("Load Internal Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToSaveExternal(View view) {
        String str = edtName.getText().toString();
        File sdCard = Environment.getExternalStorageDirectory();
//        String realPath = sdCard.getAbsolutePath();
        String realPath = "/sdcard";
        File directory = new File(realPath, "/Test");
        directory.mkdir();
        File file = new File(directory, "myfile.txt");
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            osw.write(str);
            osw.flush();
            txtResult.setText("Save file to sdCard success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToLoadExternal(View view) {
    }
}
