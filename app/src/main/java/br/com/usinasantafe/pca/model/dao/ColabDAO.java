package br.com.usinasantafe.pca.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pca.control.CirculacaoCTR;
import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.util.VerifDadosServ;

public class ColabDAO {

    public ColabDAO() {
    }

    public boolean hasElements(){
        ColabBean colabBean = new ColabBean();
        return colabBean.hasElements();
    }

    public boolean verColab(Long matricColab){
        List colabList = colabList(matricColab);
        boolean ret = colabList.size() > 0;
        colabList.clear();
        return ret;
    }

    public ColabBean getColab(Long matricColab){
        List colabList = colabList(matricColab);
        ColabBean colabBean = (ColabBean) colabList.get(0);
        colabList.clear();
        return colabBean;
    }

    private List colabList(Long matricColab){
        ColabBean colabBean = new ColabBean();
        return colabBean.get("matricColab", matricColab);
    }

    public void verColab(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Colab", telaAtual, telaProx, progressDialog);
    }

    public void recDadosColab(String result){

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                ColabBean colabBean = gson.fromJson(objeto.toString(), ColabBean.class);
                colabBean.insert();

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("COLABORADOR INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE COLABORADOR! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

}
