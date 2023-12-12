package br.com.usinasantafe.pca.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pca.control.ViagemCTR;
import br.com.usinasantafe.pca.model.dao.LogErroDAO;
import br.com.usinasantafe.pca.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pca.view.ActivityGeneric;

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


    public void envioDados() {
        status = 1;
        if (ActivityGeneric.connectNetwork) {
            status = 2;
            ViagemCTR viagemCTR = new ViagemCTR();
            if(viagemCTR.verViagemFechada()){
                envioViagem();
            }
            else {
                status = 3;
            }
        }
    }


    public void envioViagem(){

        ViagemCTR viagemCTR = new ViagemCTR();
        String dados = viagemCTR.dadosEnvio();

        Log.i("PMM", "Viagem = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInserirCirculacao()};
        Map<String, Object> parametrosPost = new HashMap<>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }


    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("SALVOU")) {
            ViagemCTR viagemCTR = new ViagemCTR();
            viagemCTR.updatePassageiro(result);
        } else {
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

}
