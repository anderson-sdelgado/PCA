package br.com.usinasantafe.pca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pca.model.pst.DatabaseHelper;
import br.com.usinasantafe.pca.util.EnvioDadosServ;
import br.com.usinasantafe.pca.util.Tempo;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (DatabaseHelper.getInstance() == null) {
			new DatabaseHelper(context);
		}

		Log.i("PCO", "DATA HORA = " + Tempo.getInstance().dataComHora());

//		if (EnvioDadosServ.getInstance().verifDadosEnvio()) {
//			EnvioDadosServ.getInstance().setStatusEnvio(1);
//			EnvioDadosServ.getInstance().enviarDados(context);
//		}
//		else{
//			EnvioDadosServ.getInstance().setStatusEnvio(3);
//		}

	}

}