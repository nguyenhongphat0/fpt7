package com.example.day10_database;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToPreference(View view) {
        Intent intent = new Intent(this, MyPreferenceActivity.class);
        startActivity(intent);
    }

    public void clickToFile(View view) {
        Intent intent = new Intent(this, PhatNHFileActivity.class);
        startActivity(intent);
    }

    public void clickToBackup(View view) {
        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = sdCard.getAbsolutePath();
        System.out.println(realPath);
        File data = Environment.getDataDirectory();
        String desDir = realPath + "/MyFile";
        File directory = new File(desDir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String dataPath = "/data/" + this.getPackageName() + "/files";
//        String dataPath = "/data/" + this.getPackageName() + "/shared_prefs";
//        String dataPath = "/data/" + this.getPackageName() + "/databases";
        File dataDir = new File(data, dataPath);
        File[] fileList = dataDir.listFiles();
        String result = "Copies file: \n";
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                File f = fileList[i];
                result += f.getName() + "\n";
                copyFile(f.getAbsolutePath(), desDir + "/" + f.getName());
            }
        } else {
            result += "No Fle!";
        }
        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(result);
    }

    public void clickToRestore(View view) {
        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = sdCard.getAbsolutePath() + "/MyFile";
        String dataPath = "/data/data/" + this.getPackageName() + "/files";
        File dataDir = new File(realPath);
        File[] fileList = dataDir.listFiles();
        String result = "Restore file: \n";
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                File f = fileList[i];
                result += f.getName() + "\n";
                copyFile(f.getAbsolutePath(), dataPath + "/" + f.getName());
            }
        } else {
            result += "No file";
        }
        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(result);
    }

    private void copyFile(String src, String des) {
        File srcF = new File(src);
        File desF = new File(des);
        try {
            FileChannel srcChannel = new FileInputStream(srcF).getChannel();
            FileChannel desChannel = new FileInputStream(desF).getChannel();
            desChannel.transferFrom(srcChannel, 0, srcChannel.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
