package br.com.usinasantafe.pca.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pca.model.bean.estaticas.OcorAtendBean;
import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;
import br.com.usinasantafe.pca.model.dao.ConfigDAO;
import br.com.usinasantafe.pca.model.dao.ViagemDAO;
import br.com.usinasantafe.pca.model.dao.ColabDAO;
import br.com.usinasantafe.pca.model.dao.EquipDAO;
import br.com.usinasantafe.pca.model.dao.LocalDAO;
import br.com.usinasantafe.pca.model.dao.OcorAtendDAO;
import br.com.usinasantafe.pca.util.AtualDadosServ;
import br.com.usinasantafe.pca.util.EnvioDadosServ;

public class ViagemCTR {

    private Long idLocalDestino;
    private Long idViagemSelecinado;
    private int tipoSelecionado; // 1 - Saída; 2 - Retorno;

    public ViagemCTR() {
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

    public boolean verViagemFechada(){
        ViagemDAO viagemDAO = new ViagemDAO();
        return viagemDAO.verViagemFechada();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void fecharViagem(){
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.fecharViagem(idViagemSelecinado);

        EnvioDadosServ.getInstance().envioDados();
    }

    public void fecharViagemAberta(){
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.fecharViagem();

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setStatusAplicFechado();

        EnvioDadosServ.getInstance().envioDados();
    }

    public void excluirViagem(){
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.excluirViagem(idViagemSelecinado);
    }

    public void updatePassageiro(String retorno) {
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.updateViagem(retorno);
    }

    public void excluirViagemEnviada(){
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.excluirViagemEnviada();
    }

    public void setIdViagemSelecionado(Long idCircSelect) {
        this.idViagemSelecinado = idCircSelect;
    }

    public void setIdLocalDestino(Long idLocalDestino, int tipo) {
        if(tipo == 1){
            this.idLocalDestino = idLocalDestino;
        } else {
            ViagemDAO viagemDAO = new ViagemDAO();
            viagemDAO.setIdLocalDestinoViagem(idLocalDestino, idViagemSelecinado);
        }
    }

    public void setIdLocalSaida(Long idLocalSaida, int tipo) {
        ViagemDAO viagemDAO = new ViagemDAO();
        if(tipo == 1){
            ConfigCTR configCTR = new ConfigCTR();
            viagemDAO.abrirViagem(this.idLocalDestino, idLocalSaida, configCTR.getConfig().getMatricMotorista(), configCTR.getConfig().getIdEquip(), configCTR.getConfig().getNroAparelhoConfig());
        } else {
            viagemDAO.setIdLocalSaidaViagem(idLocalDestino, idViagemSelecinado);
        }
    }

    public void setTipoSelecionado(int tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }

    public void setDthrViagem(String dthr){
        ViagemDAO viagemDAO = new ViagemDAO();
        if(this.tipoSelecionado == 1){
            viagemDAO.setDthrSaida(dthr, idViagemSelecinado);
        } else {
            viagemDAO.setDthrRetorno(dthr, idViagemSelecinado);
        }
    }

    public void setMatricPacienteViagem(Long matricPaciente){
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.setMatricPacienteViagem(matricPaciente, idViagemSelecinado);
    }

    public void setKilometragemViagem(Double kilometragem){
        ViagemDAO viagemDAO = new ViagemDAO();
        if(this.tipoSelecionado == 1){
            viagemDAO.setKmSaidaViagem(kilometragem, idViagemSelecinado);
        } else {
            viagemDAO.setKmChegadaViagem(kilometragem, idViagemSelecinado);
        }
    }

    public void setIdOcorAtendViagem(Long idOcor){
        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.setIdOcorAtendViagem(idOcor, idViagemSelecinado);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// GET DADOS ////////////////////////////////////////////

    public ViagemBean getViagem(){
        ViagemDAO viagemDAO = new ViagemDAO();
        return viagemDAO.getViagemId(idViagemSelecinado);
    }

    public List<ViagemBean> viagemAbertaList(){
        ViagemDAO viagemDAO = new ViagemDAO();
        return viagemDAO.viagemAbertoList();
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

    public int getTipoSelecionado() {
        return tipoSelecionado;
    }

    public List<EquipBean> equipList(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.equipList();
    }

    public List<LocalBean> localList(){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.localList();
    }

    public List<OcorAtendBean> ocorAtendList(){
        OcorAtendDAO ocorAtendDAO = new OcorAtendDAO();
        return ocorAtendDAO.ocorAtendList();
    }

    public String dadosEnvio(){
        ViagemDAO viagemDAO = new ViagemDAO();
        return viagemDAO.dadosEnvio();
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
