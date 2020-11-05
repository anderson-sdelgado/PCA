package br.com.usinasantafe.pca.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.pca.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pca.model.dao.ConfigDAO;
import br.com.usinasantafe.pca.util.AtualDadosServ;

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

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho, senha);
    }

}
