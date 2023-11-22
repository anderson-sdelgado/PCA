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
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

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
        status = 1;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            status = 2;
            if (EnvioDadosServ.getInstance().verifDadosEnvio()) {
                envioDadosPrinc();
            } else {
                status = 3;
            }
        }
    }

    public void envioDadosPrinc() {
        CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
        if(circulacaoCTR.verCirculacaoNEnviado()){
            envioCirculacao();
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

    public boolean verifDadosEnvio() {
        CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
        if ((!circulacaoCTR.verCirculacaoNEnviado())){
            return false;
        } else {
            return true;
        }
    }

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("SALVOU")) {
            CirculacaoCTR circulacaoCTR = new CirculacaoCTR();
            circulacaoCTR.updatePassageiro(result);
        } else {
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

}
