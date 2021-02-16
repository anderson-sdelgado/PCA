package br.com.usinasantafe.pca.util.connHttp;

import br.com.usinasantafe.pca.PCAContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pcaqa/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pcaqa/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pca.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PCAContext.versaoAplic.replace(".", "_");

    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String EquipBean = urlPrincipal + "equip.php" + put;
    public static String LocalBean = urlPrincipal + "local.php" + put;
    public static String OcorAtendBean = urlPrincipal + "ocoratend.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirCirculacao() {
        return urlPrincEnvio + "inserircirculacao.php" + put;
    }

    public String getsInsertLogErro() {
        return urlPrincEnvio + "inserirlogerro.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincipal + "equip.php" + put;
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplic.php" + put;
        } else if (classe.equals("Moto")) {
            retorno = urlPrincEnvio + "atualmoto.php" + put;
        } else if (classe.equals("Colab")) {
            retorno = urlPrincEnvio + "atualcolab.php" + put;
        }
        return retorno;
    }

}
