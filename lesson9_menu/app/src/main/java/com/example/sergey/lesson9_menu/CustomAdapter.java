package com.example.sergey.lesson9_menu;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Text> {
    private Holder holder = new Holder();
    private Context context;
    private int resource;
    private List<Text> list;
    private LayoutInflater inflater;
    private ActionMode actionMode;
    private String TAG = "tag";
    private PopupMenu popupMenu;


    public CustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Text o = list.get(position);

        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            holder.text1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.text2 = (TextView) convertView.findViewById(R.id.textView2);
        }

        holder.text1.setText(o.getText2());
        holder.text2.setText(o.getText2());

        holder.text2.setOnClickListener(onClickListenerCallback);

        final View popupButton = holder.text1;
        holder.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(context, popupButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
                popupMenu.show();
            }
        });

        return convertView;
    }

    private PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.item1:
                    Toast.makeText(context, "popupmenu item1", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.item2:
                    Toast.makeText(context, "popupmenu item2", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.item3:
                    Toast.makeText(context, "popupmenu item3", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }
    };


    private View.OnClickListener onClickListenerCallback = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (actionMode == null) {
                actionMode = view.startActionMode(callback);
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

class Holder {

    TextView text1, text2;
}