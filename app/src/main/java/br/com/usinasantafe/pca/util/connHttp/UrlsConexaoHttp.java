package br.com.usinasantafe.pca.util.connHttp;

import br.com.usinasantafe.pca.PCAContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PCAContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/pcadev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcaqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcaprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pca.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp";

    public static String ColabBean = url + "colab.php";
    public static String EquipBean = url + "equip.php";
    public static String LocalBean = url + "local.php";
    public static String OcorAtendBean = url + "ocoratend.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirCirculacao() {
        return url + "inserirviagem.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = url + "equip.php";
        } else if (classe.equals("Atualiza")) {
            retorno = url + "atualaplic.php";
        } else if (classe.equals("Moto")) {
            retorno = url + "atualmoto.php";
        } else if (classe.equals("Colab")) {
            retorno = url + "atualcolab.php";
        } else if (classe.equals("Token")) {
            retorno = url + "aparelho.php";
        }
        return retorno;
    }

}
