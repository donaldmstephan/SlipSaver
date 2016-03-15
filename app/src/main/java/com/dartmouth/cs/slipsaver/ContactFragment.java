package com.dartmouth.cs.slipsaver;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Donald on 2/29/2016.
 */
public class ContactFragment extends Fragment {
    private View v;
    private ContactDBHelper db;
    private ArrayList<String> fromColumns;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<Contact> dbEntries;
    private ListView lv;

    public static final int RESULT_OK = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_contact, container, false);

        loadList();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loadList();
    }

    private void loadList() {
        db = new ContactDBHelper(getActivity());
        lv = (ListView)v.findViewById(R.id.List_View);

        final Loader loader = new Loader(this.getActivity());
        loader.loadInBackground();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if ( (position+1) > dbEntries.size() ) {
                    AddContactDialog acd = new AddContactDialog(getActivity());
                    acd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            loadList();

                            Log.d("Load", "Here");
                        }
                    });
                    acd.show();
                }

                else {

                    Contact contact = dbEntries.get(position);
                    if (contact.Selected == 0) {
                        contact.Selected = 1;
                        db.removeEntry(contact.id);
                        db.insertEntry(contact);

                        loadList();
                    }
                    else {
                        contact.Selected = 0;
                        db.removeEntry(contact.id);
                        db.insertEntry(contact);

                        loadList();
                    }
                }
            }
        });
    }


    public class Loader extends AsyncTaskLoader {

        public Loader(Context context) {
            super(context);
        }

        @Override
        public Object loadInBackground() {
            dbEntries = db.fetchEntries();
            fromColumns = new ArrayList<>();

            for ( Contact c : dbEntries) {
                if (c.Selected == 0)
                    fromColumns.add( "\nContact: " + c.FirstName.toString() + " " + c.LastName.toString() + " \n" + "Email: " + c.Email.toString() + "\n");
                else
                    fromColumns.add("\n***Contact: " + c.FirstName.toString() + " " + c.LastName.toString() + " \n" + "Email: " + c.Email.toString() + "\n");
            }

            fromColumns.add("\n +  Add Contact \n");

            mAdapter = new ArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1, fromColumns);
            lv.setAdapter(mAdapter);
            return mAdapter;
        }
    }
}