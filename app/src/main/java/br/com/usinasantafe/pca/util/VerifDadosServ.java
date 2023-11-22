package br.com.usinasantafe.pca.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.model.bean.AtualAplicBean;
import br.com.usinasantafe.pca.model.dao.ConfigDAO;
import br.com.usinasantafe.pca.model.pst.GenericRecordable;
import br.com.usinasantafe.pca.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pca.view.MenuInicialActivity;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dados;
    private String classe;
    private String senha;
    public static int status;
    private PostVerGenerico postVerGenerico;

    public VerifDadosServ() {
        genericRecordable = new GenericRecordable();
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.recToken(result.trim(), this.senha, this.telaAtual, this.telaProx, this.progressDialog);
    }

    public void salvarToken(String senha, String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dados = dados;
        this.senha = senha;
        this.classe = "Token";

        envioVerif();

    }

    public void envioVerif() {

        String[] url = {urlsConexaoHttp.urlVerifica(classe)};
        Map<String, Object> parametrosPost = new HashMap<>();
        parametrosPost.put("dado", String.valueOf(dados));

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }
}
