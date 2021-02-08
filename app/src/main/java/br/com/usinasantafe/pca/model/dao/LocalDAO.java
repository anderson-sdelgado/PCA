package br.com.usinasantafe.pca.model.dao;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.estaticas.LocalBean;

public class LocalDAO {

    public LocalDAO() {
    }

    public List<LocalBean> localSaidaList(){
        LocalBean localBean = new LocalBean();
        return localBean.getAndOrderBy("flagSaidaLocal", 1L, "descrLocal", true);
    }

    public List<LocalBean> localDestinoList(){
        LocalBean localBean = new LocalBean();
        return localBean.getAndOrderBy("flagDestinoLocal", 1L, "descrLocal", true);
    }

    public LocalBean getLocal(Long idLocal){
        LocalBean localBean = new LocalBean();
        List<LocalBean> localList = localBean.get("idLocal", idLocal);
        localBean = localList.get(0);
        localList.clear();
        return localBean;
    }

}
