package br.com.usinasantafe.pca.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pca.model.bean.estaticas.OcorAtendBean;
import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.model.dao.CirculacaoDAO;
import br.com.usinasantafe.pca.model.dao.ColabDAO;
import br.com.usinasantafe.pca.model.dao.EquipDAO;
import br.com.usinasantafe.pca.model.dao.LocalDAO;
import br.com.usinasantafe.pca.model.dao.OcorAtendDAO;
import br.com.usinasantafe.pca.util.AtualDadosServ;

public class CirculacaoCTR {

    private CirculacaoDAO circulacaoDAO;

    public CirculacaoCTR() {
    }

    ///////////////////////////////// VERIFICAR DADOS ////////////////////////////////////////////

    public boolean hasElementsColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.hasElements();
    }

    public boolean verColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.verColab(matricColab);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void criarCirculacao(Long matricUsuario){
        circulacaoDAO = new CirculacaoDAO();
        ConfigCTR configCTR = new ConfigCTR();
        circulacaoDAO.criarCirculacao(matricUsuario, configCTR.getConfig().getNroAparelhoConfig());
    }

    public void delCircAberto(){
        CirculacaoDAO circulacaoDAO = new CirculacaoDAO();
        circulacaoDAO.delCircAberto();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// GET DADOS ////////////////////////////////////////////

    public CirculacaoBean getCirculacaoAberta(){
        CirculacaoDAO circulacaoDAO = new CirculacaoDAO();
        return circulacaoDAO.getCirculacaoAberta();
    }

    public ColabBean getColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getColab(matricColab);
    }

    public EquipBean getEquip(Long idEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(idEquip);
    }

    public LocalBean getLocal(Long idLocal){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.getLocal(idLocal);
    }

    public OcorAtendBean getOcorAtend(Long idOcorAtend){
        OcorAtendDAO ocorAtendDAO = new OcorAtendDAO();
        return ocorAtendDAO.getOcorAtend(idOcorAtend);
    }

    public List<EquipBean> equipList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.equipList();
    }

    public List<LocalBean> localSaidaList(){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.localSaidaList();
    }

    public List<LocalBean> localDestinoList(){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.localDestinoList();
    }

    public List<OcorAtendBean> ocorAtendList(){
        OcorAtendDAO ocorAtendDAO = new OcorAtendDAO();
        return ocorAtendDAO.ocorAtendList();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// SET DADOS ////////////////////////////////////////////

    public void setMatricPacienteCirculacao(Long matricPaciente){
        circulacaoDAO.setMatricPacienteCirculacao(matricPaciente);
    }

    public void setIdEquipCirculacao(Long idEquip){
        circulacaoDAO.setIdEquipCirculacao(idEquip);
    }

    public void setIdLocalSaidaCirculacao(Long idLocalSaida){
        circulacaoDAO.setIdLocalSaidaCirculacao(idLocalSaida);
    }

    public void setIdLocalDestinoCirculacao(Long idLocalDestino){
        circulacaoDAO.setIdLocalDestinoCirculacao(idLocalDestino);
    }

    public void setIdOcorAtendCirculacao(Long idOcorAtend){
        circulacaoDAO.setIdOcorAtendCirculacao(idOcorAtend);
    }

    public void setKmSaidaCirculacao(Double kmSaida){
        circulacaoDAO.setKmSaidaCirculacao(kmSaida);
    }

    public void setKmRetornoCirculacao(Double kmRetorno){
        CirculacaoDAO circulacaoDAO = new CirculacaoDAO();
        circulacaoDAO.setKmRetornoCirculacao(kmRetorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS POR SERVIDOR /////////////////////

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosEquip(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList equipArrayList = new ArrayList();
        equipArrayList.add("EquipBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, equipArrayList);
    }

    public void atualDadosLocal(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList localArrayList = new ArrayList();
        localArrayList.add("LocalBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, localArrayList);
    }

    public void atualDadosOcorAtend(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList ocorAtendArrayList = new ArrayList();
        ocorAtendArrayList.add("OcorAtendBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, ocorAtendArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
