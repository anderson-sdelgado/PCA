package br.com.usinasantafe.pca.model.dao;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.estaticas.OcorAtendBean;

public class OcorAtendDAO {

    public OcorAtendDAO() {
    }

    public List<OcorAtendBean> ocorAtendList(){
        OcorAtendBean ocorAtendBean = new OcorAtendBean();
        return ocorAtendBean.orderBy("descrOcorAtend", true);
    }

    public OcorAtendBean getOcorAtend(Long idOcorAtend){
        OcorAtendBean ocorAtendBean = new OcorAtendBean();
        List<OcorAtendBean> ocorAtendList = ocorAtendBean.get("idOcorAtend", idOcorAtend);
        ocorAtendBean = ocorAtendList.get(0);
        ocorAtendList.clear();
        return ocorAtendBean;
    }

}
