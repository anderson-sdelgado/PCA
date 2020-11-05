package br.com.usinasantafe.pca.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pca.model.pst.Entidade;

@DatabaseTable(tableName="tbcirculacaovar")
public class CirculacaoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCirculacao;
    @DatabaseField
    private String dthrIdaCirculacao;
    @DatabaseField
    private String dthrRetornoCirculacao;
    @DatabaseField
    private Long matricUsuCirculacao;
    @DatabaseField
    private Long matricColabCirculacao;
    @DatabaseField
    private Double kmIdaCirculacao;
    @DatabaseField
    private Double kmRetornoCirculacao;
    @DatabaseField
    private Long localSaidaCirculacao;
    @DatabaseField
    private Long destinoCirculacao;
    @DatabaseField
    private Long ocorAtendCirculacao;
    @DatabaseField
    private Long statusCirculacao; // 1 - Sem Envio; 2 - Enviado

    public CirculacaoBean() {
    }

    public Long getIdCirculacao() {
        return idCirculacao;
    }

    public void setIdCirculacao(Long idCirculacao) {
        this.idCirculacao = idCirculacao;
    }

    public String getDthrIdaCirculacao() {
        return dthrIdaCirculacao;
    }

    public void setDthrIdaCirculacao(String dthrIdaCirculacao) {
        this.dthrIdaCirculacao = dthrIdaCirculacao;
    }

    public String getDthrRetornoCirculacao() {
        return dthrRetornoCirculacao;
    }

    public void setDthrRetornoCirculacao(String dthrRetornoCirculacao) {
        this.dthrRetornoCirculacao = dthrRetornoCirculacao;
    }

    public Long getMatricUsuCirculacao() {
        return matricUsuCirculacao;
    }

    public void setMatricUsuCirculacao(Long matricUsuCirculacao) {
        this.matricUsuCirculacao = matricUsuCirculacao;
    }

    public Long getMatricColabCirculacao() {
        return matricColabCirculacao;
    }

    public void setMatricColabCirculacao(Long matricColabCirculacao) {
        this.matricColabCirculacao = matricColabCirculacao;
    }

    public Double getKmIdaCirculacao() {
        return kmIdaCirculacao;
    }

    public void setKmIdaCirculacao(Double kmIdaCirculacao) {
        this.kmIdaCirculacao = kmIdaCirculacao;
    }

    public Double getKmRetornoCirculacao() {
        return kmRetornoCirculacao;
    }

    public void setKmRetornoCirculacao(Double kmRetornoCirculacao) {
        this.kmRetornoCirculacao = kmRetornoCirculacao;
    }

    public Long getLocalSaidaCirculacao() {
        return localSaidaCirculacao;
    }

    public void setLocalSaidaCirculacao(Long localSaidaCirculacao) {
        this.localSaidaCirculacao = localSaidaCirculacao;
    }

    public Long getDestinoCirculacao() {
        return destinoCirculacao;
    }

    public void setDestinoCirculacao(Long destinoCirculacao) {
        this.destinoCirculacao = destinoCirculacao;
    }

    public Long getOcorAtendCirculacao() {
        return ocorAtendCirculacao;
    }

    public void setOcorAtendCirculacao(Long ocorAtendCirculacao) {
        this.ocorAtendCirculacao = ocorAtendCirculacao;
    }

    public Long getStatusCirculacao() {
        return statusCirculacao;
    }

    public void setStatusCirculacao(Long statusCirculacao) {
        this.statusCirculacao = statusCirculacao;
    }
}
