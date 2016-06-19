package com.tiderdev.dilo.forms;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.tiderdev.dilo.forms.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {

    TextInputLayout TILFirstName, TILLastName, TILEmail, TILCity, TILAddress, TILPostalCode;
    Button BtnSave;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private static final String EMPTY_STRING = "";

    private Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
    private Pattern patternEmpty = Pattern.compile(EMPTY_STRING);
    private Matcher matcher;

    ArrayList<TextInputLayout> textInputLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        TILFirstName = (TextInputLayout) findViewById(R.id.til_first_name);
        TILLastName = (TextInputLayout) findViewById(R.id.til_last_name);
        TILEmail = (TextInputLayout) findViewById(R.id.til_email);
        TILCity = (TextInputLayout) findViewById(R.id.til_city);
        TILAddress = (TextInputLayout) findViewById(R.id.til_address);
        TILPostalCode = (TextInputLayout) findViewById(R.id.til_postal_code);

        BtnSave = (Button) findViewById(R.id.btn_save);


        textInputLayouts.add(TILFirstName);
        textInputLayouts.add(TILLastName);
        textInputLayouts.add(TILEmail);
        textInputLayouts.add(TILCity);
        textInputLayouts.add(TILAddress);
        textInputLayouts.add(TILPostalCode);


        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard();

                if (validateEmpty(textInputLayouts)) {

                    if (validateEmail(TILEmail.getEditText().getText().toString())) {
                        TILEmail.setErrorEnabled(false);

                        Person person = getFormToPerson(textInputLayouts);

                        Intent intentDetailActivity = new Intent(FormActivity.this, DetailActivity.class);
                        intentDetailActivity.putExtra("firstName", person.getFirstName());
                        intentDetailActivity.putExtra("lastName", person.getLastName());
                        intentDetailActivity.putExtra("email", person.getEmail());
                        intentDetailActivity.putExtra("address", person.getAddress());
                        intentDetailActivity.putExtra("city", person.getCity());
                        intentDetailActivity.putExtra("postalCode", person.getPostalCode());

                        startActivity(intentDetailActivity);

                    } else {
                        TILEmail.setErrorEnabled(true);
                        TILEmail.setError(getResources().getString(R.string.must_email));
                    }
                }

            }
        });
    }

    //hide virtual keyboard
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    //get data from textinputlayouts and put it to person object
    private Person getFormToPerson(ArrayList<TextInputLayout> textInputLayouts) {

        HashMap<String, String> data = new HashMap<>();

        data.put("firstName", textInputLayouts.get(0).getEditText().getText().toString());
        data.put("lastName", textInputLayouts.get(1).getEditText().getText().toString());
        data.put("email", textInputLayouts.get(2).getEditText().getText().toString());
        data.put("city", textInputLayouts.get(3).getEditText().getText().toString());
        data.put("address", textInputLayouts.get(4).getEditText().getText().toString());
        data.put("postalCode", textInputLayouts.get(5).getEditText().getText().toString());

        Person mPerson = new Person(data);

        return mPerson;
    }

    //validate email address
    public boolean validateEmail(String email) {
        matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    //validate empty input
    //if return true it's mean string is empty then we prevent user to go forward so we make inverse return value
    public boolean validateEmpty(ArrayList<TextInputLayout> textInputLayouts) {

        int lengthOfTIL = textInputLayouts.size();
        boolean notEmpty = true;

        for (int i = 0; i < lengthOfTIL; i++) {

            TextInputLayout textInputLayout = textInputLayouts.get(i);

            matcher = patternEmpty.matcher(textInputLayout.getEditText().getText().toString());

            if (matcher.matches()) {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(getResources().getString(R.string.required));

                notEmpty = false;

            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }

        return notEmpty;
    }
}
