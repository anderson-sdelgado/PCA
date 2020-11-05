package br.com.usinasantafe.pca.model.dao;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        List<ConfigBean> configList = configList();
        ConfigBean configBean = configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean getConfig(String senha){
        List<ConfigBean> configList = configList(senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    private List configList(){
        ConfigBean configBean = new ConfigBean();
        return configBean.all();
    }

    private List configList(String senha){
        ConfigBean configBean = new ConfigBean();
        return configBean.get("senhaConfig", senha);
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setSenhaConfig(senha);
        configBean.insert();
        configBean.commit();
    }

}
