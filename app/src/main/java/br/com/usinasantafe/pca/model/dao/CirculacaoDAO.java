package br.com.usinasantafe.pca.model.dao;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.util.Tempo;

public class CirculacaoDAO {

    private CirculacaoBean circulacaoBean;

    public CirculacaoDAO() {
    }

    public void criarCirculacao(Long matricUsuario, Long nroAparelho){
        circulacaoBean = new CirculacaoBean();
        circulacaoBean.setMatricMotoristaCirculacao(matricUsuario);
        circulacaoBean.setNroAparelhoCirculacao(nroAparelho);
        circulacaoBean.setStatusCirculacao(1L);
    }

    public void setMatricPacienteCirculacao(Long matricPaciente){
        circulacaoBean.setMatricPacienteCirculacao(matricPaciente);
    }

    public void setIdEquipCirculacao(Long idEquip){
        circulacaoBean.setIdEquipCirculacao(idEquip);
    }

    public void setIdLocalSaidaCirculacao(Long idLocalSaida){
        circulacaoBean.setIdLocalSaidaCirculacao(idLocalSaida);
    }

    public void setIdLocalDestinoCirculacao(Long idLocalDestino){
        circulacaoBean.setIdLocalDestinoCirculacao(idLocalDestino);
    }

    public void setIdOcorAtendCirculacao(Long idOcorAtend){
        circulacaoBean.setIdOcorAtendCirculacao(idOcorAtend);
    }

    public void setKmSaidaCirculacao(Double kmSaida){
        circulacaoBean.setKmSaidaCirculacao(kmSaida);
        circulacaoBean.setDthrSaidaCirculacao(Tempo.getInstance().dataComHora());
        circulacaoBean.insert();
    }

    public void setKmRetornoCirculacao(Double kmRetorno){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setKmRetornoCirculacao(kmRetorno);
        circulacaoBean.setDthrRetornoCirculacao(Tempo.getInstance().dataComHora());
        circulacaoBean.setStatusCirculacao(2L);
        circulacaoBean.update();
    }

    public CirculacaoBean getCirculacaoAberta(){
        List<CirculacaoBean> circulacaoList = circulacaoList();
        CirculacaoBean circulacaoBean = circulacaoList.get(0);
        circulacaoList.clear();
        return circulacaoBean;
    }

    public List<CirculacaoBean> circulacaoList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("statusCirculacao", 1L);
    }

    public void delCircAberto(){
        List<CirculacaoBean> circulacaoList = circulacaoList();
        for(CirculacaoBean circulacaoBean : circulacaoList){
            circulacaoBean.delete();
        }
        circulacaoList.clear();
    }

}
