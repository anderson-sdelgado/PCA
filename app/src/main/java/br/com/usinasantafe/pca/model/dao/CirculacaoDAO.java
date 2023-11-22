package br.com.usinasantafe.pca.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.util.EnvioDadosServ;
import br.com.usinasantafe.pca.util.Tempo;

public class CirculacaoDAO {

    private CirculacaoBean circulacaoBean;

    public CirculacaoDAO() {
    }

    public boolean verCirculacaoAberta(){
        List<CirculacaoBean> circulacaoList = circulacaoAbertoList();
        boolean ret = circulacaoList.size() > 0;
        circulacaoList.clear();
        return ret;
    }

    public boolean verCirculacaoNEnviado(){
        List<CirculacaoBean> circulacaoList = circulacaoNEnviadoList();
        boolean ret = circulacaoList.size() > 0;
        circulacaoList.clear();
        return ret;
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
        circulacaoBean.setDthrSaidaCirculacao(Tempo.getInstance().dthrAtualString());
        circulacaoBean.setDthrLongSaidaCirculacao(Tempo.getInstance().dthrAtualLong());
        circulacaoBean.insert();
    }

    public void setKmRetornoCirculacao(Double kmRetorno){
        CirculacaoBean circulacaoBean = getCirculacaoAberta();
        circulacaoBean.setKmRetornoCirculacao(kmRetorno);
        circulacaoBean.setDthrRetornoCirculacao(Tempo.getInstance().dthrAtualString());
        circulacaoBean.setStatusCirculacao(2L);
        circulacaoBean.update();
    }

    public CirculacaoBean getCirculacaoAberta(){
        List<CirculacaoBean> circulacaoList = circulacaoAbertoList();
        CirculacaoBean circulacaoBean = circulacaoList.get(0);
        circulacaoList.clear();
        return circulacaoBean;
    }

    public List<CirculacaoBean> circulacaoAbertoList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("statusCirculacao", 1L);
    }

    public List<CirculacaoBean> circulacaoNEnviadoList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("statusCirculacao", 2L);
    }

    public List<CirculacaoBean> circulacaoEnviadoList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("statusCirculacao", 3L);
    }

    public void delCircAberto(){
        List<CirculacaoBean> circulacaoList = circulacaoAbertoList();
        for(CirculacaoBean circulacaoBean : circulacaoList){
            circulacaoBean.delete();
        }
        circulacaoList.clear();
    }


    public String dadosEnvio(){

        List<CirculacaoBean> circulacaoList = circulacaoNEnviadoList();

        JsonArray jsonArrayCirculacao = new JsonArray();

        for (CirculacaoBean circulacaoBean : circulacaoList) {
            Gson gsonCirculacao = new Gson();
            jsonArrayCirculacao.add(gsonCirculacao.toJsonTree(circulacaoBean, circulacaoBean.getClass()));
        }

        circulacaoList.clear();

        JsonObject jsonCirculacao = new JsonObject();
        jsonCirculacao.add("circulacao", jsonArrayCirculacao);

        return jsonCirculacao.toString();

    }

    public void updateCirculacao(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjCirculacao = new JSONObject(objPrinc);
            JSONArray jsonArrayCirculacao = jObjCirculacao.getJSONArray("passageiro");

            if (jsonArrayCirculacao.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                CirculacaoBean circulacaoBean = new CirculacaoBean();

                for (int i = 0; i < jsonArrayCirculacao.length(); i++) {

                    JSONObject objCirculacao = jsonArrayCirculacao.getJSONObject(i);
                    Gson gsonCirculacao = new Gson();
                    circulacaoBean = gsonCirculacao.fromJson(objCirculacao.toString(), CirculacaoBean.class);

                    rList.add(circulacaoBean.getIdCirculacao());

                }

                List<CirculacaoBean> circulacaoList = circulacaoBean.in("idCirculacao", rList);

                for (CirculacaoBean circulacaoBeanBD : circulacaoList) {
                    circulacaoBeanBD.setStatusCirculacao(3L);
                    circulacaoBeanBD.update();
                }

                rList.clear();

            }

        }
        catch(Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void delCircEnviado(){

        for(CirculacaoBean circulacaoBean : circulacaoEnviadoList()){
            if(circulacaoBean.getDthrLongSaidaCirculacao() < Tempo.getInstance().dthrLongDiaMenos(15)){
                circulacaoBean.delete();
            }
        }

    }

}
