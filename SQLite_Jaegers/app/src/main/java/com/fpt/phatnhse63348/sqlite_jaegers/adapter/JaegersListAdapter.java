package com.fpt.phatnhse63348.sqlite_jaegers.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fpt.phatnhse63348.sqlite_jaegers.AddJeagerActivity;
import com.fpt.phatnhse63348.sqlite_jaegers.MainActivity;
import com.fpt.phatnhse63348.sqlite_jaegers.R;
import com.fpt.phatnhse63348.sqlite_jaegers.model.Jaeger;
import com.fpt.phatnhse63348.sqlite_jaegers.util.SQLiteHelper;

public class JaegersListAdapter extends BaseAdapter {
    private MainActivity context;

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    private Cursor cursor;

    public JaegersListAdapter(MainActivity context, Cursor cursor) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.jaeger_list_item, parent, false);
        }
        if (cursor.moveToPosition(position)) {
            final long _ID = cursor.getLong(cursor.getColumnIndex(Jaeger._ID));
            Button model = convertView.findViewById(R.id.model);
            TextView description = convertView.findViewById(R.id.description);
            model.setText(cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_MODEL)));
            model.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddJeagerActivity.class);
                    intent.putExtra(AddJeagerActivity.EXTRA_ID, _ID);
                    context.startActivityForResult(intent, MainActivity.REQUEST_ADD_JAEGER);
                }
            });
            model.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Jaeger.delete(new SQLiteHelper(context), _ID);
                    context.renderJaegers();
                    return false;
                }
            });
            description.setText(context.getString(R.string.jaeger_description,
                    cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_CREATOR)),
                    cursor.getDouble(cursor.getColumnIndex(Jaeger.COLUMN_HEIGHT)),
                    cursor.getDouble(cursor.getColumnIndex(Jaeger.COLUMN_WEIGHT))
            ));
        }
        return convertView;
    }
}
