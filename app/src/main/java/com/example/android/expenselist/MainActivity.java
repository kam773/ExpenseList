package com.example.android.expenselist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView expenseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        expenseListView = (ListView) findViewById(R.id.listView);
        expenseListView.setAdapter(new ExpenseAdapter());

    }

    @Override
    protected void onStart() {
        super.onStart();
        expenseListView.invalidateViews();
    }

    private class ExpenseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ExpenseDatabase.getExpenses().size();
        }

        @Override
        public Expense getItem(int position) {
            return ExpenseDatabase.getExpenses().get(position);
        }

        @Override
        public long getItemId(int position) {
            return  position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_expense, null);
            }

            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView category = (TextView) convertView.findViewById(R.id.category);
            Expense item = getItem(position);
            title.setText(item.getName());

            price.setText(String.format("%.2f", item.getPrice()) + "zł");
            category.setText(item.getCategory().getName());

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_expense:
                Intent intent = new Intent(getApplicationContext(), ExpenseActivity.class);
                startActivity(intent);
                return true;
            case R.id.preferences:
                intent = new Intent(getApplicationContext(), PreferencesActivity.class);
                startActivity(intent);
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
}
