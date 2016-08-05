package com.sjimenez.contacts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sjimenez.contacts.Object.Contacts;
import com.sjimenez.contacts.R;

import java.util.ArrayList;


public class ContactsAdapter extends ArrayAdapter<Contacts> {

    private Context mContext;
    private int         mLayoutId;
    private ArrayList<Contacts> mContacts;

    public ContactsAdapter(Context context, int resource, ArrayList<Contacts> con) {
        super(context, resource, con);

        mContext = context;
        mLayoutId = resource;
        mContacts = con;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // Inflate the layout
            convertView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);

            // create the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvContactNo = (TextView) convertView.findViewById(R.id.tvContactNo);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set the movie data
        Contacts cont = mContacts.get(position);

        if (cont != null) {
            if (viewHolder.tvName != null) {
                viewHolder.tvName.setText(cont.getLastName() +", " +cont.getFirstName());
            }
            if (viewHolder.tvContactNo != null) {
                viewHolder.tvContactNo.setText(cont.getContactNo());
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView tvName;
        public TextView tvContactNo;
    }
}
