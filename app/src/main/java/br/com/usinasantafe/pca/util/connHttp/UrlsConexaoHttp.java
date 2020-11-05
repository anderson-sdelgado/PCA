package br.com.usinasantafe.pca.util.connHttp;

import br.com.usinasantafe.pca.PCAContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pco/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pco/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pca.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pca.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PCAContext.versaoAplic.replace(".", "_");

    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String MotoristaBean = urlPrincipal + "moto.php" + put;
    public static String TurnoBean = urlPrincipal + "turno.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirPassageiro() {
        return urlPrincEnvio + "inserirpassageiro.php" + put;
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
