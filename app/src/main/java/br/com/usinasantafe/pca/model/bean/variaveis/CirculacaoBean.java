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
    private Long nroAparelhoCirculacao;
    @DatabaseField
    private String dthrSaidaCirculacao;
    @DatabaseField
    private String dthrRetornoCirculacao;
    @DatabaseField
    private Long matricMotoristaCirculacao;
    @DatabaseField
    private Long matricPacienteCirculacao;
    @DatabaseField
    private Long idEquipCirculacao;
    @DatabaseField
    private Double kmSaidaCirculacao;
    @DatabaseField
    private Double kmRetornoCirculacao;
    @DatabaseField
    private Long idLocalSaidaCirculacao;
    @DatabaseField
    private Long idLocalDestinoCirculacao;
    @DatabaseField
    private Long idOcorAtendCirculacao;
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

    public Long getNroAparelhoCirculacao() {
        return nroAparelhoCirculacao;
    }

    public void setNroAparelhoCirculacao(Long nroAparelhoCirculacao) {
        this.nroAparelhoCirculacao = nroAparelhoCirculacao;
    }

    public String getDthrSaidaCirculacao() {
        return dthrSaidaCirculacao;
    }

    public void setDthrSaidaCirculacao(String dthrSaidaCirculacao) {
        this.dthrSaidaCirculacao = dthrSaidaCirculacao;
    }

    public String getDthrRetornoCirculacao() {
        return dthrRetornoCirculacao;
    }

    public void setDthrRetornoCirculacao(String dthrRetornoCirculacao) {
        this.dthrRetornoCirculacao = dthrRetornoCirculacao;
    }

    public Long getMatricMotoristaCirculacao() {
        return matricMotoristaCirculacao;
    }

    public void setMatricMotoristaCirculacao(Long matricMotoristaCirculacao) {
        this.matricMotoristaCirculacao = matricMotoristaCirculacao;
    }

    public Long getMatricPacienteCirculacao() {
        return matricPacienteCirculacao;
    }

    public void setMatricPacienteCirculacao(Long matricPacienteCirculacao) {
        this.matricPacienteCirculacao = matricPacienteCirculacao;
    }

    public Long getIdEquipCirculacao() {
        return idEquipCirculacao;
    }

    public void setIdEquipCirculacao(Long idEquipCirculacao) {
        this.idEquipCirculacao = idEquipCirculacao;
    }

    public Double getKmSaidaCirculacao() {
        return kmSaidaCirculacao;
    }

    public void setKmSaidaCirculacao(Double kmSaidaCirculacao) {
        this.kmSaidaCirculacao = kmSaidaCirculacao;
    }

    public Double getKmRetornoCirculacao() {
        return kmRetornoCirculacao;
    }

    public void setKmRetornoCirculacao(Double kmRetornoCirculacao) {
        this.kmRetornoCirculacao = kmRetornoCirculacao;
    }

    public Long getIdLocalSaidaCirculacao() {
        return idLocalSaidaCirculacao;
    }

    public void setIdLocalSaidaCirculacao(Long idLocalSaidaCirculacao) {
        this.idLocalSaidaCirculacao = idLocalSaidaCirculacao;
    }

    public Long getIdLocalDestinoCirculacao() {
        return idLocalDestinoCirculacao;
    }

    public void setIdLocalDestinoCirculacao(Long idLocalDestinoCirculacao) {
        this.idLocalDestinoCirculacao = idLocalDestinoCirculacao;
    }

    public Long getIdOcorAtendCirculacao() {
        return idOcorAtendCirculacao;
    }

    public void setIdOcorAtendCirculacao(Long idOcorAtendCirculacao) {
        this.idOcorAtendCirculacao = idOcorAtendCirculacao;
    }

    public Long getStatusCirculacao() {
        return statusCirculacao;
    }

    public void setStatusCirculacao(Long statusCirculacao) {
        this.statusCirculacao = statusCirculacao;
    }
}
