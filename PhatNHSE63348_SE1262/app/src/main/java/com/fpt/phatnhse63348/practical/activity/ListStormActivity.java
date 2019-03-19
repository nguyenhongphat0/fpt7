package com.fpt.phatnhse63348.practical.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fpt.phatnhse63348.practical.R;
import com.fpt.phatnhse63348.practical.Storm;
import com.fpt.phatnhse63348.practical.util.StormDBHelper;

public class ListStormActivity extends AppCompatActivity {
    private StormDBHelper helper;
    private ListView stormsList;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_storm);
        helper = new StormDBHelper(this);

        renderStorms();
    }

    private void renderStorms() {
        stormsList = findViewById(R.id.stormsList);
        cursor = Storm.all(helper);
        stormsList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return cursor.getCount();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                cursor.moveToPosition(position);
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.storm_list_item, parent, false);
                }
                Button stormName = convertView.findViewById(R.id.stormName);
                TextView stormID = convertView.findViewById(R.id.stormID);
                TextView description = convertView.findViewById(R.id.description);
                TextView windSpeed = convertView.findViewById(R.id.windSpeed);
                stormName.setText(cursor.getString(cursor.getColumnIndex(Storm.COLUMN_NAME)));
                stormID.setText("ID: " + cursor.getString(cursor.getColumnIndex(Storm.COLUMN_ID)));
                description.setText(cursor.getString(cursor.getColumnIndex(Storm.COLUMN_DESCRIPTION)));
                windSpeed.setText("Windspeed: " + cursor.getFloat(cursor.getColumnIndex(Storm.COLUMN_WINDSPEED)));
                return convertView;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        helper.close();
    }
}
