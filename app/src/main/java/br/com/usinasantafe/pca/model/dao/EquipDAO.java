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

    public EquipDAO() {
    }

    public List<EquipBean> equipList(){
        EquipBean equipBean = new EquipBean();
        return  equipBean.orderBy("nroEquip", true);
    }

    public EquipBean getEquip(Long idEquip){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get("idEquip", idEquip);
        equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }
}
