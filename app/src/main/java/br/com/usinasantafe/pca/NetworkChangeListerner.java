package br.com.usinasantafe.pca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.model.pst.DatabaseHelper;
import br.com.usinasantafe.pca.util.EnvioDadosServ;
import br.com.usinasantafe.pca.util.Tempo;

public class NetworkChangeListerner extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (DatabaseHelper.getInstance() == null) {
			new DatabaseHelper(context);
		}

		Log.i("PCO", "DATA HORA = " + Tempo.getInstance().dthrAtualString());
		EnvioDadosServ.getInstance().envioDados(context);
		dados();

	}

	public void dados(){

		CirculacaoBean circulacaoBean = new CirculacaoBean();
		List<CirculacaoBean> circulacaoList = circulacaoBean.all();

		for(CirculacaoBean circulacaoBeanBD : circulacaoList){
			Log.i("PCA", "CIRCULACAO");
			Log.i("PCA", "idCirculacao = " + circulacaoBeanBD.getIdCirculacao());
			Log.i("PCA", "nroAparelhoCirculacao = " + circulacaoBeanBD.getNroAparelhoCirculacao());
			Log.i("PCA", "dthrSaidaCirculacao = " + circulacaoBeanBD.getDthrSaidaCirculacao());
			Log.i("PCA", "dthrRetornoCirculacao = " + circulacaoBeanBD.getDthrRetornoCirculacao());
			Log.i("PCA", "matricMotoristaCirculacao = " + circulacaoBeanBD.getMatricMotoristaCirculacao());
			Log.i("PCA", "matricPacienteCirculacao = " + circulacaoBeanBD.getMatricPacienteCirculacao());
			Log.i("PCA", "idEquipCirculacao = " + circulacaoBeanBD.getIdEquipCirculacao());
			Log.i("PCA", "kmSaidaCirculacao = " + circulacaoBeanBD.getKmSaidaCirculacao());
			Log.i("PCA", "kmRetornoCirculacao = " + circulacaoBeanBD.getKmRetornoCirculacao());
			Log.i("PCA", "idLocalSaidaCirculacao = " + circulacaoBeanBD.getIdLocalSaidaCirculacao());
			Log.i("PCA", "idLocalDestinoCirculacao = " + circulacaoBeanBD.getIdLocalDestinoCirculacao());
			Log.i("PCA", "idOcorAtendCirculacao = " + circulacaoBeanBD.getIdOcorAtendCirculacao());
			Log.i("PCA", "statusCirculacao = " + circulacaoBeanBD.getStatusCirculacao());
		}

	}

}