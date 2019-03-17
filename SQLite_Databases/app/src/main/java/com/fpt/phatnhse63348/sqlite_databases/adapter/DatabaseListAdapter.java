package com.fpt.phatnhse63348.sqlite_databases.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fpt.phatnhse63348.sqlite_databases.R;
import com.fpt.phatnhse63348.sqlite_databases.activity.AddDatabaseActivity;
import com.fpt.phatnhse63348.sqlite_databases.activity.MainActivity;
import com.fpt.phatnhse63348.sqlite_databases.model.Database;

public class DatabaseListAdapter extends BaseAdapter {
    private Activity context;
    private Cursor cursor;

    public DatabaseListAdapter(Activity context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

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
            convertView = context.getLayoutInflater().inflate(R.layout.database_list_item, parent, false);
        }
        final long id = cursor.getLong(cursor.getColumnIndex(Database._ID));
        Button name = convertView.findViewById(R.id.name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddDatabaseActivity.class);
                intent.putExtra(AddDatabaseActivity.EXTRA_ID, id);
                context.startActivityForResult(intent, MainActivity.REQUEST_ADD_DATABASE);
            }
        });
        TextView description = convertView.findViewById(R.id.description);
        name.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME)));
        description.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_DESCRIPTION)));
        return convertView;
    }
}
