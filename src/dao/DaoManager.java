package dao;

import android.content.Context;
import Trilemma.DaoSession;

public class DaoManager {

	private MyOpenHelper dbHelper;

	public DaoSession session;

	public DaoManager(Context context) {
		dbHelper = MyOpenHelper.getInstance(context);
		session = dbHelper.session();
	}
}
