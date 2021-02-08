package br.com.usinasantafe.pca.model.dao;

import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.util.Tempo;

public class CirculacaoDAO {

    public CirculacaoDAO() {
    }

    public void criarCirculacao(Long matricUsuario){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        circulacaoBean.setMatricMotoristaCirculacao(matricUsuario);
        circulacaoBean.setStatusCirculacao(1L);
        circulacaoBean.insert();
    }

    public void setMatricPacienteCirculacao(Long matricPaciente){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setMatricPacienteCirculacao(matricPaciente);
        circulacaoBean.update();
    }

    public void setIdEquipCirculacao(Long idEquip){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setIdEquipCirculacao(idEquip);
        circulacaoBean.update();
    }

    public void setIdLocalSaidaCirculacao(Long idLocalSaida){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setIdLocalSaidaCirculacao(idLocalSaida);
        circulacaoBean.update();
    }

    public void setIdLocalDestinoCirculacao(Long idLocalDestino){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setIdLocalDestinoCirculacao(idLocalDestino);
        circulacaoBean.update();
    }

    public void setIdOcorAtendCirculacao(Long idOcorAtend){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setIdLocalDestinoCirculacao(idOcorAtend);
        circulacaoBean.update();
    }

    public void setKmSaidaCirculacao(Double kmSaida){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setKmSaidaCirculacao(kmSaida);
        circulacaoBean.setDthrSaidaCirculacao(Tempo.getInstance().dataComHora());
        circulacaoBean.update();
    }

    public void setKmRetornoCirculacao(Double kmRetorno){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setKmRetornoCirculacao(kmRetorno);
        circulacaoBean.setDthrRetornoCirculacao(Tempo.getInstance().dataComHora());
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
