package br.com.usinasantafe.pca.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pca.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pca.model.bean.estaticas.OcorAtendBean;
import br.com.usinasantafe.pca.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;
import br.com.usinasantafe.pca.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pca.model.dao.LogErroDAO;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pca_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);;

		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			TableUtils.createTable(cs, ColabBean.class);
			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, LocalBean.class);
			TableUtils.createTable(cs, OcorAtendBean.class);

			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, ViagemBean.class);
			TableUtils.createTable(cs, LogErroBean.class);
			TableUtils.createTable(cs, LogProcessoBean.class);

		}
		catch(Exception e){
			LogErroDAO.getInstance().insertLogErro(e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
                          ConnectionSource cs,
                          int oldVersion,
                          int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				//TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}
		
	}

}
