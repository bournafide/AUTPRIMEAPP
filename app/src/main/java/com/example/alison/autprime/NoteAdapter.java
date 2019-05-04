package com.example.alison.autprime;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<theNote>{


    public NoteAdapter(Context context, int resource, ArrayList<theNote> noteItems) {
        super(context, resource, noteItems);
    }

    @Override
     public View getView(int position, View convertView, ViewGroup parent) {
        theNote n = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_list, null);
        }

        if(n != null)
        {
            TextView title = (TextView) convertView.findViewById(R.id.list_nTitle);
            TextView content = (TextView) convertView.findViewById(R.id.list_nContent);
            TextView date_time = (TextView) convertView.findViewById(R.id.list_nDateTime);

            title.setText(n.getNTitle());
            date_time.setText(n.formatNDateTime(getContext()));

            if(n.getNContent().length() > 50)
            {
                content.setText(n.getNContent().substring(0,50));
            }
            else
            {
                content.setText(n.getNContent());
            }
        }

        return convertView;
    }
}
