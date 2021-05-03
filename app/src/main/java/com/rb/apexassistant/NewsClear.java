package com.rb.apexassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import com.rb.apexassistant.data.DataContract;
import com.rb.apexassistant.data.DataDbHelper;

public class NewsClear extends AsyncTask {

    private Context context;

    public NewsClear(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        clearNews();

        return null;
    }

    public void clearNews() {
        DataDbHelper dbHelper = new DataDbHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        String deleteQuery = "DELETE" +
                " FROM " + DataContract.NewsEntry.TABLE_NAME +
                " WHERE " + DataContract.NewsEntry._ID +
                " NOT IN (SELECT " + DataContract.NewsEntry._ID +
                " FROM " + DataContract.NewsEntry.TABLE_NAME +
                " ORDER BY " + DataContract.NewsEntry._ID +
                " DESC " +
                " LIMIT 20)";

        sqLiteDatabase.execSQL(deleteQuery);
    }
}