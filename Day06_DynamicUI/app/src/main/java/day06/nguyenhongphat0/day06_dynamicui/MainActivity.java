package day06.nguyenhongphat0.day06_dynamicui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToCreate(View view) {
        Intent intent = new Intent(this, UIDynamicActivity.class);
        startActivity(intent);
    }
}
