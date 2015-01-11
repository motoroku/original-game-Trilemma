package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
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
	private Context mContext;
	private static final String SQL_PATH = "";

	private MyOpenHelper(Context context, String name, CursorFactory factory) {
		super(context, DB_NAME, factory);

		DAO_MASTER = new DaoMaster(this.getWritableDatabase());
		DAO_SESSION = DAO_MASTER.newSession();
		mContext = context;
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
		// try {
		// // execSql(db, "sql/");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
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

	/**
	 * 引数に指定したassetsフォルダ内のsqlを実行します。
	 * @param db データベース
	 * @param assetsDir assetsフォルダ内のフォルダのパス
	 * @throws IOException
	 */
	private void execSql(SQLiteDatabase db, String assetsDir) throws IOException {
		AssetManager as = mContext.getResources().getAssets();
		try {
			String files[] = as.list(assetsDir);
			for (int i = 0; i < files.length; i++) {
				String str = readFile(as.open(assetsDir + "/" + files[i]));
				for (String sql : str.split(";")) {
					db.execSQL(sql);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ファイルから文字列を読み込みます。
	 * @param is
	 * @return ファイルの文字列
	 * @throws IOException
	 */
	private String readFile(InputStream is) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is, "SJIS"));

			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			return sb.toString();
		} finally {
			if (br != null)
				br.close();
		}
	}
}
