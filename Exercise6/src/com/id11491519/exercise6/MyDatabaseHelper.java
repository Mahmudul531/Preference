package com.id11491519.exercise6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper {

	/* Database table key or column name */
	private String Famousperson = "famous";
	private String KEY_ID = "id";
	private String KEY_FIRST_NAME = "f_name";
	private String KEY_LAST_NAME = "l_name";
	private String KEY_GENDER = "gender";
	private String KEY_BIRTHDAY = "bday";
	private String KEY_HOBBY = "hobby";

	private String DatabaseName = "TF.db";
	private int DatabaseVersion = 1;

	private DBHelper ourhelper;
	private final Context ourcontext;
	private SQLiteDatabase ourdatabase;

	/* Create database sql */
	private String CREATE_TABLE_FAMOUS = "CREATE TABLE " + Famousperson + " ("
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_FIRST_NAME
			+ " TEXT  NOT NULL, " + KEY_LAST_NAME + " TEXT NOT NULL, "
			+ KEY_GENDER + " INTEGER NOT NULL," + KEY_BIRTHDAY
			+ " INTEGER NOT NULL," + KEY_HOBBY + " TEXT NOT NULL );";

	class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DatabaseName, null, DatabaseVersion);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE_FAMOUS);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FAMOUS);
			onCreate(db);
		}

	}

	public MyDatabaseHelper(Context c) {
		ourcontext = c;
	}

	public MyDatabaseHelper open() {
		ourhelper = new DBHelper(ourcontext);
		ourdatabase = ourhelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourhelper.close();
	}

	/* Database value and set it from another class using this method */
	public long CreatePersonalInfo(String firstname, String lastname,
			String gernder, String birthday, String Hobbi) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_FIRST_NAME, firstname);
		cv.put(KEY_LAST_NAME, lastname);
		cv.put(KEY_GENDER, gernder);
		cv.put(KEY_BIRTHDAY, birthday);
		cv.put(KEY_HOBBY, Hobbi);
		return ourdatabase.insert(Famousperson, null, cv);

	}

	/* Getting all value from database for list */
	public Cursor getAllData() {
		String sql = "select * from " + Famousperson;
		return ourdatabase.rawQuery(sql, null);
	}

}
