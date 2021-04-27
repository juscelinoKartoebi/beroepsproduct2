package sr.unasat.beroepsproduct2.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTableLockedException;

import sr.unasat.beroepsproduct2.Adapters.GebruikerModel;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public GebruikerModel vindGebruiker (String username){
        GebruikerModel gebruikerModel = new GebruikerModel();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= null;
        String whereClause = String.format("%s = ?", COL_2);
        String[] whereArgs = {username};
        cursor = db.query(TABLE_NAME, null, whereClause, whereArgs ,null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
                gebruikerModel.setUsername(cursor.getString(cursor.getColumnIndex(COL_2)));
                gebruikerModel.setPassword(cursor.getString(cursor.getColumnIndex(COL_3)));
                gebruikerModel.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
        }

        return gebruikerModel;
    }

    public void updateUser (String username, String password, int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET " + COL_2 + " = ?," + COL_3 + " = ? " + " WHERE " + COL_1 + " = ? ";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.bindString(1, username);
        sqLiteStatement.bindString(2, password);
        sqLiteStatement.bindLong(3, id);
        sqLiteStatement.execute();
        db.close();


    }
}

