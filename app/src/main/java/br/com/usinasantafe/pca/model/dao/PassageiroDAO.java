package br.com.usinasantafe.pca.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pca.util.EnvioDadosServ;
import br.com.usinasantafe.pca.util.Tempo;

public class PassageiroDAO {

    public PassageiroDAO() {
    }

    public boolean verPassageiroNEnviado(){
        List passageiroList = passageiroNEnviadoList();
        boolean ret = passageiroList.size() > 0;
        passageiroList.clear();
        return ret;
    }

    public boolean verMatricColabViagem(Long matricColab, String dthr){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDthrViagem(dthr));
        pesqArrayList.add(getPesqMatricColab(matricColab));

        CirculacaoBean circulacaoBean = new CirculacaoBean();
        List<CirculacaoBean> passageiroList = circulacaoBean.get(pesqArrayList);
        boolean ret = (passageiroList.size() == 0);
        passageiroList.clear();
        return ret;
    }

    public List<CirculacaoBean> passageiroEnviadoList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("statusPassageiro", 2L);
    }

    public List<CirculacaoBean> passageiroNEnviadoList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("statusPassageiro", 1L);
    }

    public List<CirculacaoBean> passageiroViagemList(String dthr, Long matricMoto, Long idturno){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDthrViagem(dthr));
        pesqArrayList.add(getPesqMatricMoto(matricMoto));
        pesqArrayList.add(getPesqTurno(idturno));

        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.getAndOrderBy(pesqArrayList, "idPassageiro", false);
    }

    public List<CirculacaoBean> passageiroList(){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.orderBy("idPassageiro", false);
    }

    public List<CirculacaoBean> passageiroViagemList(String dthr){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        return circulacaoBean.get("dthrViagemPassageiro", dthr);
    }

    public void salvarPassageiro(ConfigBean configBean, Long matricColab){
        CirculacaoBean circulacaoBean = new CirculacaoBean();
        circulacaoBean.setDthrPassageiro(Tempo.getInstance().dataComHora());
        circulacaoBean.setDthrViagemPassageiro(configBean.getDtrhViagemConfig());
        circulacaoBean.setIdEquipPassageiro(configBean.getIdEquipConfig());
        circulacaoBean.setMatricMotoPassageiro(configBean.getMatricMotoConfig());
        circulacaoBean.setIdTurnoPassageiro(configBean.getIdTurnoConfig());
        circulacaoBean.setMatricColabPassageiro(matricColab);
        circulacaoBean.setStatusPassageiro(1L);
        circulacaoBean.insert();

        EnvioDadosServ.getInstance().setStatusEnvio(2);

    }

    public String dadosEnvio(){

        List passageiroList = passageiroNEnviadoList();

        JsonArray jsonArrayPassageiro = new JsonArray();

        for (int i = 0; i < passageiroList.size(); i++) {

            CirculacaoBean circulacaoBean = (CirculacaoBean) passageiroList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayPassageiro.add(gsonCabec.toJsonTree(circulacaoBean, circulacaoBean.getClass()));

        }

        passageiroList.clear();

        JsonObject jsonPassageiro = new JsonObject();
        jsonPassageiro.add("passageiro", jsonArrayPassageiro);

        return jsonPassageiro.toString();

    }

    public void updatePassageiro(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjPassageiro = new JSONObject(objPrinc);
            JSONArray jsonArrayPassageiro = jObjPassageiro.getJSONArray("passageiro");

            if (jsonArrayPassageiro.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                CirculacaoBean circulacaoBean = new CirculacaoBean();

                for (int i = 0; i < jsonArrayPassageiro.length(); i++) {

                    JSONObject objPassageiro = jsonArrayPassageiro.getJSONObject(i);
                    Gson gsonPassageiro = new Gson();
                    circulacaoBean = gsonPassageiro.fromJson(objPassageiro.toString(), CirculacaoBean.class);

                    rList.add(circulacaoBean.getIdPassageiro());

                }

                List passageiroList = circulacaoBean.in("idPassageiro", rList);

                for (int i = 0; i < passageiroList.size(); i++) {

                    circulacaoBean = (CirculacaoBean) passageiroList.get(i);
                    circulacaoBean.setStatusPassageiro(2L);
                    circulacaoBean.update();

                }

                rList.clear();

            }

            EnvioDadosServ.getInstance().setStatusEnvio(3);

        }
        catch(Exception e){
        }

    }

    public void delPassageiro(){

        for(CirculacaoBean circulacaoBean : passageiroEnviadoList()){

            if(Tempo.getInstance().timeDataHora(circulacaoBean.getDthrViagemPassageiro()) <= Tempo.getInstance().timeMenos1Mes()){
                circulacaoBean.delete();
            }

        }

    }

    private EspecificaPesquisa getPesqDthrViagem(String dthrViagemPassageiro){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrViagemPassageiro");
        pesquisa.setValor(dthrViagemPassageiro);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMatricMoto(Long matricMoto){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("matricMotoPassageiro");
        pesquisa.setValor(matricMoto);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqTurno(Long idTurno){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idTurnoPassageiro");
        pesquisa.setValor(idTurno);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMatricColab(Long matricColab){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("matricColabPassageiro");
        pesquisa.setValor(matricColab);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
