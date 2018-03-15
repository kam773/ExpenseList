package com.example.android.expenselist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import db.ExpenseDbHelper;

/**
 * Created by Kamil on 2018-03-12.
 */

public class ExpenseActivity extends AppCompatActivity {

    private static final String PREF_LAST_CATEGORY = "pref.last.category";

    SharedPreferences sharedPreferences;

    private EditText titleEditText;
    private EditText priceEditText;
    private Spinner categorySpinner;

    private ExpenseDbHelper mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        titleEditText = (EditText) findViewById(R.id.expense_name);
        priceEditText = (EditText) findViewById(R.id.expensePrice);
        categorySpinner = (Spinner) findViewById(R.id.expense_category);

        categorySpinner.setAdapter(new CategoryAdapter());
        loadLastCategory(categorySpinner);

        Button expenseButton = (Button) findViewById(R.id.add_expense);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewExpense();
            }
        });

        mDbHelper = new ExpenseDbHelper(this);

    }
    private void addNewExpense() {
        String title = titleEditText.getText().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        Expense.ExpenseCategory category = (Expense.ExpenseCategory) categorySpinner.getSelectedItem();
        Expense expense = new Expense(title, price, category);
        ExpenseDatabase.addExpense(expense);

        saveLastCategory(category);
        finish();
    }

    private void saveLastCategory(Expense.ExpenseCategory expenseCategory) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_LAST_CATEGORY, expenseCategory.name());
        editor.apply();
    }

    private void loadLastCategory(Spinner categorySpinner) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String lastCategoryName = prefs.getString(PREF_LAST_CATEGORY, "");
        if(!lastCategoryName.isEmpty()) {
            int id = Expense.ExpenseCategory.getId(lastCategoryName);
            categorySpinner.setSelection(id);
        }
    }

    private class CategoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Expense.ExpenseCategory.values().length;
        }

        @Override
        public Expense.ExpenseCategory getItem(int position) {
            return Expense.ExpenseCategory.values()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_spinner_item, null);
            }

            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(getItem(position).getName());

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.expense_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                addNewExpense();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
