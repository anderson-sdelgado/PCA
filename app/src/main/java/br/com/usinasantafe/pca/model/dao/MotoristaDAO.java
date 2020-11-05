package br.com.usinasantafe.pca.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.util.VerifDadosServ;

public class MotoristaDAO {

    public MotoristaDAO() {
    }

    public boolean hasElements(){
        MotoristaBean motoristaBean = new MotoristaBean();
        return motoristaBean.hasElements();
    }

    public boolean verMotorista(Long matricMoto){
        List<MotoristaBean> motoristaList = motoristaList(matricMoto);
        boolean ret = motoristaList.size() > 0;
        motoristaList.clear();
        return ret;
    }

    public MotoristaBean getMotorista(Long matricMoto){
        List<MotoristaBean> motoristaList = motoristaList(matricMoto);
        MotoristaBean motoristaBean = motoristaList.get(0);
        motoristaList.clear();
        return motoristaBean;
    }

    private List motoristaList(Long matricMoto){
        MotoristaBean motoristaBean = new MotoristaBean();
        return motoristaBean.get("matricMoto", matricMoto);
    }

    public void verMotorista(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Moto", telaAtual, telaProx, progressDialog);
    }

    public void recDadosMotorista(String result){

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                MotoristaBean motoristaBean = gson.fromJson(objeto.toString(), MotoristaBean.class);
                motoristaBean.insert();

                ConfigCTR configCTR = new ConfigCTR();
                configCTR.setMotoConfig(motoristaBean);

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("MOTORISTA INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE MOTORISTA! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

}
