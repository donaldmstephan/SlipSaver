package com.dartmouth.cs.slipsaver;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Donald on 3/1/2016.
 */
public class AddContactDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button add, cancel;

    public AddContactDialog(Activity a) {
        super(a);
        c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_dialog);
        add = (Button) findViewById(R.id.btn_add);
        cancel = (Button) findViewById(R.id.btn_cancel);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                ContactDBHelper db = new ContactDBHelper(getContext());
                Contact c = new Contact(((EditText) findViewById(R.id.add_first_name)).getText().toString(),((EditText) findViewById(R.id.add_last_name)).getText().toString(),
                        ((EditText) findViewById(R.id.add_email)).getText().toString(), 0, 0);
                db.insertEntry(c);
                Toast.makeText(getContext(), "Contact Added",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}

