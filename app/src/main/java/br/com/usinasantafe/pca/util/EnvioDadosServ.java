package br.com.usinasantafe.pca.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pca.control.CirculacaoCTR;
import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.model.dao.LogErroDAO;
import br.com.usinasantafe.pca.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private int posEnvio;
    private boolean enviando = false;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }


    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {
        CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
        ConfigCTR configCTR = new ConfigCTR();
        if (configCTR.verEnvioLogErro()) {
            envioLogErro();
        }
        else {
            if(circulacaoCTR.verCirculacaoNEnviado()){
                envioCirculacao();
            }
        }
    }

    public void envioCirculacao(){

        CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
        String dados = circulacaoCTR.dadosEnvio();

        Log.i("PMM", "CIRCULACAO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInserirCirculacao()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioLogErro() {

        ConfigCTR configCTR = new ConfigCTR();
        String dados = configCTR.dadosEnvioLogErro();

        Log.i("PMM", "LOG ERRO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertLogErro()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public boolean verifDadosEnvio() {
        CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
        ConfigCTR configCTR = new ConfigCTR();
        if ((!circulacaoCTR.verCirculacaoNEnviado())
                && (!configCTR.verEnvioLogErro())){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("SALVOU")) {
            CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
            circulacaoCTR.updatePassageiro(result);
        } else if (result.trim().startsWith("LOGERRO")) {
            ConfigCTR configCTR = new ConfigCTR();
            configCTR.updLogErro(result);
        }
        else{
            LogErroDAO.getInstance().insert(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

    public int getPosEnvio() {
        return posEnvio;
    }

    public void setPosEnvio(int posEnvio) {
        this.posEnvio = posEnvio;
    }

    public boolean isEnviando() {
        return enviando;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }
}
