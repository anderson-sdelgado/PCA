package br.com.usinasantafe.pca.model.bean;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualAplicBean {

    private Long nroEquipAtual;
    private String versaoAtual;
    private String versaoNova;

    public AtualAplicBean() {
    }

    public Long getNroEquipAtual() {
        return nroEquipAtual;
    }

    public void setNroEquipAtual(Long nroEquipAtual) {
        this.nroEquipAtual = nroEquipAtual;
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

    public String getVersaoNova() {
        return versaoNova;
    }

    public void setVersaoNova(String versaoNova) {
        this.versaoNova = versaoNova;
    }
}
