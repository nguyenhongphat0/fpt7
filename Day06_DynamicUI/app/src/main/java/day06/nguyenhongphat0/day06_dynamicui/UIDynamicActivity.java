package day06.nguyenhongphat0.day06_dynamicui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UIDynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView txtTitle = new TextView(this);
        txtTitle.setText("Create new Account");
        txtTitle.setLayoutParams(params);

        TextView txtTitleUsername = new TextView(this);
        txtTitleUsername.setText("Username:");
        txtTitleUsername.setLayoutParams(params);

        EditText edtUsername = new EditText(this);
        edtUsername.setId(R.id.txtUsername);
        edtUsername.setLayoutParams(params);

        TextView txtTitleFullname = new TextView(this);
        txtTitleFullname.setText("Fullname:");
        txtTitleFullname.setLayoutParams(params);

        final EditText edtFullname = new EditText(this);
        edtFullname.setId(R.id.txtFullname);
        edtFullname.setLayoutParams(params);

        Button btnInput = new Button(this);
        btnInput.setText("Input");
        btnInput.setLayoutParams(params);

        linearLayout.addView(txtTitle);
        linearLayout.addView(txtTitleUsername);
        linearLayout.addView(edtUsername);
        linearLayout.addView(txtTitleFullname);
        linearLayout.addView(edtFullname);
        linearLayout.addView(btnInput);

        this.addContentView(linearLayout, params);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtUsername = findViewById(R.id.txtUsername);
                Intent intent = new Intent(UIDynamicActivity.this, ResultActivity.class);
                intent.putExtra("username", txtUsername.getText().toString());
                intent.putExtra("fullname", edtFullname.getText().toString());
                startActivity(intent);
            }
        });
    }
}
