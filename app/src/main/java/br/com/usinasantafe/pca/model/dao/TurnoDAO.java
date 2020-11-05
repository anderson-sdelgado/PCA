package br.com.usinasantafe.pca.model.dao;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.estaticas.TurnoBean;

public class TurnoDAO {

    public TurnoDAO() {
    }

    public List getTurnoList(Long codTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("codTurno", codTurno);
    }

    public TurnoBean getTurno(Long idTurno){
        List turnoList = turnoList(idTurno);
        TurnoBean turnoBean = (TurnoBean) turnoList.get(0);
        turnoList.clear();
        return turnoBean;
    }

    private List turnoList(Long idTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("idTurno", idTurno);
    }

}
