package com.example.android.expenselist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 2018-03-12.
 */

public class ExpenseDatabase {

    private static List<Expense> expenses = new ArrayList<Expense>();

    public static List<Expense> getExpenses() {
        return expenses;
    }

    static  {
        expenses.add(new Expense("Jajka" , 4.2, Expense.ExpenseCategory.FOOD));
        expenses.add(new Expense("Kino" , 32, Expense.ExpenseCategory.ENTERTAINMENT));
        expenses.add(new Expense("Szampon" , 9.99, Expense.ExpenseCategory.HYGIENE));
    }

    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }
}

