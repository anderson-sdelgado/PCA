package br.com.usinasantafe.pca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;
import br.com.usinasantafe.pca.model.pst.DatabaseHelper;
import br.com.usinasantafe.pca.util.ConnectNetwork;
import br.com.usinasantafe.pca.util.EnvioDadosServ;
import br.com.usinasantafe.pca.util.Tempo;
import br.com.usinasantafe.pca.view.ActivityGeneric;

public class NetworkChangeListerner extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (DatabaseHelper.getInstance() == null) {
			new DatabaseHelper(context);
		}

		Log.i("PCO", "DATA HORA = " + Tempo.getInstance().dthrAtualString());
		if(ConnectNetwork.isConnected(context)){
			ActivityGeneric.connectNetwork = true;
			EnvioDadosServ.getInstance().envioDados();
		} else {
			ActivityGeneric.connectNetwork = false;
		}

//		dados();

	}

	public void dados(){

		ViagemBean viagemBean = new ViagemBean();
		List<ViagemBean> viagemList = viagemBean.all();

		for(ViagemBean viagemBeanBD :viagemList){
			Log.i("PCA", "Viagem");
			Log.i("PCA", "idViagem = " + viagemBeanBD.getIdViagem());
			Log.i("PCA", "nroAparelhoViagem = " + viagemBeanBD.getNroAparelhoViagem());
			Log.i("PCA", "dthrSaidaViagem = " + viagemBeanBD.getDthrSaidaViagem());
			Log.i("PCA", "dthrRetornoViagem = " + viagemBeanBD.getDthrChegadaViagem());
			Log.i("PCA", "matricMotoristaViagem = " + viagemBeanBD.getMatricMotoristaViagem());
			Log.i("PCA", "matricPacienteViagem = " + viagemBeanBD.getMatricPacienteViagem());
			Log.i("PCA", "idEquipViagem = " + viagemBeanBD.getIdEquipViagem());
			Log.i("PCA", "kmSaidaViagem = " + viagemBeanBD.getKmSaidaViagem());
			Log.i("PCA", "kmRetornoViagem = " + viagemBeanBD.getKmChegadaViagem());
			Log.i("PCA", "idLocalSaidaViagem = " + viagemBeanBD.getIdLocalSaidaViagem());
			Log.i("PCA", "idLocalDestinoViagem = " + viagemBeanBD.getIdLocalDestinoViagem());
			Log.i("PCA", "idOcorAtendViagem = " + viagemBeanBD.getIdOcorAtendViagem());
			Log.i("PCA", "statusViagem = " + viagemBeanBD.getStatusViagem());
		}

	}

}