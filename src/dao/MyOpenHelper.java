package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import Trilemma.DaoMaster;
import Trilemma.DaoMaster.OpenHelper;
import Trilemma.DaoSession;

public class MyOpenHelper extends OpenHelper {
	private static final String DB_NAME = "Trilemma";
	private static MyOpenHelper INSTANCE;
	private SQLiteDatabase db;
	private static DaoMaster DAO_MASTER;
	private static DaoSession DAO_SESSION;

	private MyOpenHelper(Context context, String name, CursorFactory factory) {
		super(context, DB_NAME, factory);

		DAO_MASTER = new DaoMaster(this.getWritableDatabase());
		DAO_SESSION = DAO_MASTER.newSession();
	}

	public static MyOpenHelper getInstance(Context context) {
		if (INSTANCE == null) {
			INSTANCE = new MyOpenHelper(context, null, null);
		}
		return INSTANCE;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		super.onCreate(db);
	};

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public DaoMaster master() {
		return DAO_MASTER;
	}

	public DaoSession session() {
		return DAO_SESSION;
	}
}

