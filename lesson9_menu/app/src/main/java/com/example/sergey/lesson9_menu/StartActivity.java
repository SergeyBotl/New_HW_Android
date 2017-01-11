package com.example.sergey.lesson9_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private String TAG = "TAG";
    private List<Text> list = new ArrayList<>();
    private CustomAdapter customAdapter;
    private ListView listView;
    private Button button;
    private String str[] = {"item1", "item2", "item3", "item4"};
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getSupportActionBar().setTitle("Task menu");
        list.add(new Text("test3", "test3"));
        list.add(new Text("test2", "test2"));
        list.add(new Text("test1", "test1"));

        listView = (ListView) findViewById(R.id.listView1);
        customAdapter = new CustomAdapter(this, R.layout.custom_adapter, list);
        listView.setAdapter(customAdapter);

        button = (Button) findViewById(R.id.buttonTstPopupMenu);
        button.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (actionMode == null) {
                actionMode = startActionMode(callback);
            } else {
                actionMode.finish();
            }
        }
    };

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.callback, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            Log.d(TAG, "onPrepareActionMode  ");
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Log.d(TAG, "onActionItemClicked  " + menuItem.getTitle());
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode a) {
            Log.d(TAG, "destroy  ");
            actionMode = null;
        }
    };
}
