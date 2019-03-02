package com.fpt.phatnhse63348.sqlite;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.phatnhse63348.sqlite.model.Message;
import com.fpt.phatnhse63348.sqlite.model.User;
import com.fpt.phatnhse63348.sqlite.utils.CursorIterator;
import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadMessageActivity extends AppCompatActivity {
    public static final String PARAM_USER_ID = "FROM_USER_ID";

    MessengerDbHelper dbHelper;
    ListView messagesList;
    List<Map<String, String>> messages = new ArrayList();
    long fromUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);
        fromUserID = getIntent().getLongExtra(PARAM_USER_ID, 0);

        messagesList = findViewById(R.id.messagesList);
        dbHelper = new MessengerDbHelper(this);
        attachEvent();
        fetchMessage();
    }

    private void fetchMessage() {
        messages.clear();
        Message.getAllMessagesFrom(dbHelper, fromUserID).finish(new CursorIterator.Iterator() {
            @Override
            public void each(Cursor cursor) {
                Map<String, String> message = new HashMap<>();
                message.put(Message.SENDER, cursor.getString(cursor.getColumnIndex(Message.SENDER)));
                message.put(Message.CONTENT, cursor.getString(cursor.getColumnIndex(Message.CONTENT)));
                message.put(Message.DATE_SENT, cursor.getString(cursor.getColumnIndex(Message.DATE_SENT)));
                message.put(Message.SEEN, cursor.getString(cursor.getColumnIndex(Message.SEEN)));
                messages.add(message);
            }
        });
        messagesList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return messages.size();
            }

            @Override
            public Object getItem(int position) {
                return messages.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.layout_message_item, parent, false);
                }
                TextView content = convertView.findViewById(R.id.content);
                TextView sentAt = convertView.findViewById(R.id.sent_at);
                TextView seen = convertView.findViewById(R.id.seen);
                if (messages.get(position).get(Message.SENDER).equals(User.LOGGED_USER_ID + "")) {
                    convertView.setBackgroundResource(R.color.colorPrimary);
                    content.setGravity(Gravity.RIGHT);
                    content.setTextColor(Color.WHITE);
                }
                content.setText(messages.get(position).get(Message.CONTENT));
                sentAt.setText(messages.get(position).get(Message.DATE_SENT));
                seen.setText(messages.get(position).get(Message.SEEN));
                return convertView;
            }
        });
    }

    private void attachEvent() {
        final EditText newMessage = findViewById(R.id.newMessage);
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = newMessage.getText().toString();
                if (Message.sendMessage(dbHelper, content, fromUserID) > 0) {
                    newMessage.setText("");
                    Toast.makeText(ReadMessageActivity.this, "SENDED SUCCESSFULLY", Toast.LENGTH_SHORT);
                    fetchMessage();
                } else {
                    Toast.makeText(ReadMessageActivity.this, "ERROR SENDING MESSAGE", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
