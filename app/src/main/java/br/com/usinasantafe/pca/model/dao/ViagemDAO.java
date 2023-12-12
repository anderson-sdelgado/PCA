package br.com.usinasantafe.pca.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;
import br.com.usinasantafe.pca.util.EnvioDadosServ;
import br.com.usinasantafe.pca.util.Tempo;

public class ViagemDAO {

    public ViagemDAO() {
    }

    public boolean verViagemFechada(){
        List<ViagemBean> viagemList = viagemFechadaList();
        boolean ret = viagemList.size() > 0;
        viagemList.clear();
        return ret;
    }

    public void abrirViagem(Long idLocalDestino, Long idLocalSaida, Long matricMotorista, Long idEquip, Long nroAparelho) {
        Long dthrLong = Tempo.getInstance().dthrAtualLong();
        ViagemBean viagemBean = new ViagemBean();
        viagemBean.setIdLocalDestinoViagem(idLocalDestino);
        viagemBean.setIdLocalSaidaViagem(idLocalSaida);
        viagemBean.setMatricMotoristaViagem(matricMotorista);
        viagemBean.setIdEquipViagem(idEquip);
        viagemBean.setDthrLongViagem(dthrLong);
        viagemBean.setDthrViagem(Tempo.getInstance().dthrLongToString(dthrLong));
        viagemBean.setNroAparelhoViagem(nroAparelho);
        if(idLocalSaida > 0){
            viagemBean.setDthrSaidaLongViagem(dthrLong);
            viagemBean.setDthrSaidaViagem(Tempo.getInstance().dthrLongToString(dthrLong));
        }
        viagemBean.setStatusViagem(1L);
        viagemBean.insert();
    }

    public void fecharViagem(Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setStatusViagem(2L);
        viagemBean.update();
    }

    public void fecharViagem(){
        List<ViagemBean> viagemList = viagemAbertoList();
        for(ViagemBean viagemBean : viagemList){
            viagemBean.setStatusViagem(2L);
            viagemBean.update();
        }
        viagemList.clear();
    }

    public void excluirViagem(Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.delete();
    }

    public void enviadoViagem(Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setStatusViagem(3L);
        viagemBean.update();
    }

    public void setDthrSaida(String dthr, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setDthrSaidaViagem(dthr);
        viagemBean.setDthrSaidaLongViagem(Tempo.getInstance().dthrStringToLong(dthr));
        viagemBean.update();
    }

    public void setDthrRetorno(String dthr, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setDthrChegadaViagem(dthr);
        viagemBean.setDthrChegadaLongViagem(Tempo.getInstance().dthrStringToLong(dthr));
        viagemBean.update();
    }

    public void setMatricPacienteViagem(Long matric, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setMatricPacienteViagem(matric);
        viagemBean.update();
    }

    public void setIdLocalSaidaViagem(Long idLocalSaida, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setIdLocalSaidaViagem(idLocalSaida);
        viagemBean.update();
    }

    public void setIdLocalDestinoViagem(Long idLocalDestino, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setIdLocalDestinoViagem(idLocalDestino);
        viagemBean.update();
    }

    public void setKmSaidaViagem(Double kmSaida, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setKmSaidaViagem(kmSaida);
        viagemBean.update();
    }

    public void setKmChegadaViagem(Double kmRetorno, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setKmChegadaViagem(kmRetorno);
        viagemBean.update();
    }

    public void setIdOcorAtendViagem(Long idOcor, Long idViagem){
        ViagemBean viagemBean = getViagemId(idViagem);
        viagemBean.setIdOcorAtendViagem(idOcor);
        viagemBean.update();
    }

    public ViagemBean getViagemId(Long idViagem){
        List<ViagemBean> viagemList = viagemListId(idViagem);
        ViagemBean viagemBean = viagemList.get(0);
        viagemList.clear();
        return viagemBean;
    }

    public List<ViagemBean> viagemListId(Long idViagem){
        ViagemBean viagemBean = new ViagemBean();
        return viagemBean.get("idViagem", idViagem);
    }


    public List<ViagemBean> viagemAbertoList(){
        ViagemBean viagemBean = new ViagemBean();
        return viagemBean.get("statusViagem", 1L);
    }

    public List<ViagemBean> viagemFechadaList(){
        ViagemBean viagemBean = new ViagemBean();
        return viagemBean.get("statusViagem", 2L);
    }

    public List<ViagemBean> viagemEnviadoList(){
        ViagemBean viagemBean = new ViagemBean();
        return viagemBean.get("statusViagem", 3L);
    }

    public String dadosEnvio(){

        List<ViagemBean> viagemList = viagemFechadaList();

        JsonArray jsonArrayViagem = new JsonArray();

        for (ViagemBean viagemBean : viagemList) {
            Gson gsonViagem = new Gson();
            jsonArrayViagem.add(gsonViagem.toJsonTree(viagemBean, viagemBean.getClass()));
        }

        viagemList.clear();

        JsonObject jsonViagem = new JsonObject();
        jsonViagem.add("viagem", jsonArrayViagem);

        return jsonViagem.toString();

    }

    public void updateViagem(String retorno) {

        try {

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjViagem = new JSONObject(objPrinc);
            JSONArray jsonArrayViagem = jObjViagem.getJSONArray("viagem");

            if (jsonArrayViagem.length() > 0) {

                for (int i = 0; i < jsonArrayViagem.length(); i++) {

                    JSONObject objViagem = jsonArrayViagem.getJSONObject(i);
                    Gson gsonViagem = new Gson();
                    ViagemBean viagemBean = gsonViagem.fromJson(objViagem.toString(), ViagemBean.class);

                    enviadoViagem(viagemBean.getIdViagem());

                }

            }

        } catch(Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
        }
    }

    public void excluirViagemEnviada() {
        for (ViagemBean viagemBean : viagemEnviadoList()) {
            if (viagemBean.getDthrLongViagem() < Tempo.getInstance().dthrLongDiaMenos(15)) {
                viagemBean.delete();
            }
        }
    }

}
