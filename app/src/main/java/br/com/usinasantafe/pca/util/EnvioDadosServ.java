package br.com.usinasantafe.pca.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pca.control.CirculacaoCTR;
import br.com.usinasantafe.pca.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;

    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private int posEnvio;
    private Context context;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    public void enviarDados(Context context) {

//        this.context = context;
//
//        ConexaoWeb conexaoWeb = new ConexaoWeb();
//        if (conexaoWeb.verificaConexao(this.context)) {
//
//            CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
//            String dados = circulacaoCTR.dadosEnvio();
//
//            Log.i("PMM", "PASSAGEIRO = " + dados);
//
//            UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//            String[] url = {urlsConexaoHttp.getsInserirPassageiro()};
//            Map<String, Object> parametrosPost = new HashMap<String, Object>();
//            parametrosPost.put("dado", dados);
//
//            PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
//            conHttpPostGenerico.setParametrosPost(parametrosPost);
//            conHttpPostGenerico.execute(url);
//
//        }
//        else{
//            statusEnvio = 2;
//        }

    }

//    public boolean verifDadosEnvio() {
//        CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
//        return circulacaoCTR.verPassageiroNEnviado();
//    }

    public int getStatusEnvio() {
        return statusEnvio;
    }

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("SALVOU")) {
//            CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
//            circulacaoCTR.updatePassageiro(result);
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
}
