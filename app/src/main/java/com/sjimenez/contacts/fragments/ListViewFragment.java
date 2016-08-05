package com.sjimenez.contacts.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.sjimenez.contacts.Adapters.ContactsAdapter;
import com.sjimenez.contacts.Object.Contacts;
import com.sjimenez.contacts.R;
import com.sjimenez.contacts.ViewContacts;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a {@link android.widget.ListView}.
 */
public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private TextView mTvEmpty;

    private ArrayList<Contacts> mController= new ArrayList<>();


    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        Firebase ref = new Firebase("https://contacts-53a65.firebaseio.com/");


        ref.addValueEventListener(new com.firebase.client.ValueEventListener(){
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                String firstName,lastName,contactNo;

                for (com.firebase.client.DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                    firstName = String.valueOf(messageSnapshot.child("firstName").getValue());
                    lastName = String.valueOf(messageSnapshot.child("lastName").getValue());
                    contactNo = String.valueOf(messageSnapshot.child("contactNo").getValue());

                    Contacts cont = new Contacts(firstName, lastName, contactNo);
                    mController.add(cont);
                    ((BaseAdapter) mListView.getAdapter()).notifyDataSetChanged();
                }

                ContactsAdapter adapter = new ContactsAdapter(getActivity(),
                        R.layout.list_item, mController);

                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }




        });

        // find all the views
        mListView = (ListView) view.findViewById(R.id.listView);
        mTvEmpty = (TextView) view.findViewById(android.R.id.empty);

        // create a new instance of adapter
        ContactsAdapter adapter = new ContactsAdapter(getActivity(),
                R.layout.list_item, mController);

        // set the adapter
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // set item click listener
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ViewContacts.class);

        intent.putExtra("FirstName",mController.get(position).getFirstName());
        intent.putExtra("LastName",mController.get(position).getLastName());
        intent.putExtra("ContactNo",mController.get(position).getContactNo());

        startActivity(intent);
    }











}
