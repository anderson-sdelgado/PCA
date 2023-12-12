package br.com.usinasantafe.pca.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pca.model.bean.AtualAplicBean;
import br.com.usinasantafe.pca.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pca.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pca.model.dao.ConfigDAO;
import br.com.usinasantafe.pca.model.dao.LogErroDAO;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.AtualDadosServ;
import br.com.usinasantafe.pca.util.VerifDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean getConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig(senha);
    }

    public Long getStatusConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getStatusConfig();
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho, senha);
    }

    public void setMatricUsuario(Long matricUsuario) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setMatricUsuario(matricUsuario);
    }

    public void setIdEquip(Long idEquip){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdEquip(idEquip);
    }

    public void setStatusAplicFechado(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusAplicFechado();
    }

    public void salvarToken(String senha, String versao, Long nroAparelho, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        VerifDadosServ.getInstance().salvarToken(senha, atualAplicDAO.dadosAplic(nroAparelho, versao), telaAtual, telaProx, progressDialog);
    }

    public void recToken(String result, String senha, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            progressDialog.dismiss();

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
                atualAplicBean = atualAplicDAO.recAparelho(jsonArray);
            }

            salvarConfig(atualAplicBean.getNroAparelho(), senha);

            progressDialog = new ProgressDialog(telaAtual);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("ATUALIZANDO ...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();

            AtualDadosServ.getInstance().atualTodasTabBD(telaAtual, telaProx, progressDialog);

        } catch (Exception e) {
            VerifDadosServ.status = 1;
        }
    }

    public void deleteLogErro(){
        LogErroDAO logErroDAO = new LogErroDAO();
        logErroDAO.deleteLogErro();
    }

    public void deleteLogProcesso(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        logProcessoDAO.deleteLogProcesso();
    }

}
