package br.com.usinasantafe.pca.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.util.VerifDadosServ;

public class EquipDAO {

    public EquipBean getEquip(Long idEquip){
        List equipList = equipList(idEquip);
        EquipBean equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    private List equipList(Long idEquip){
        EquipBean equipBean = new EquipBean();
        return  equipBean.get("idEquip", idEquip);
    }

    public void verEquip(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Equip", telaAtual, telaProx, progressDialog);
    }

    public void recDadosEquip(String result){
        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                EquipBean equipBean = new EquipBean();
                equipBean.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    equipBean = gson.fromJson(objeto.toString(), EquipBean.class);
                    equipBean.insert();

                }

                ConfigCTR configCTR = new ConfigCTR();
                configCTR.setEquipConfig(equipBean);

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("EQUIPAMENTO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE EQUIPAMENTO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

}
