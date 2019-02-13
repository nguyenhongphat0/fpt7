package com.example.day09_picturesandmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnContext = findViewById(R.id.btnContext);
        btnContext.setOnCreateContextMenuListener(this);
    }

    public void clickToGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    private boolean createMenu(Menu menu) {
        menu.add(0, 0, 0, "Item 1");
        menu.add(0, 1, 1, "Item 2");
        menu.add(0, 2, 2, "Item 3");
        return true;
    }

    private boolean menuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_LONG).show();
                return true;
            case 1:
                Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_LONG).show();
                return true;
            case 2:
                Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return createMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuChoice(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        createMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return menuChoice(item);
    }
}
