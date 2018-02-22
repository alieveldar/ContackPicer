package com.example.modus.contackpicer;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContackPicker extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null,null);
        String[] from = new String[] {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        int[] to = new int[] {R.id.itemTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitemlayout, c, from, to);
        ListView lv = (ListView)findViewById(R.id.contactListView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                c.moveToPosition(pos);
                int rowId =c.getInt(c.getColumnIndexOrThrow("_id"));
                Uri outURI= ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);

            }
        });
    }

}
