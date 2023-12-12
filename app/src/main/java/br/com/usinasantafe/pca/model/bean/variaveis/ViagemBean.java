package br.com.usinasantafe.pca.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pca.model.pst.Entidade;

@DatabaseTable(tableName="tbviagemvar")
public class ViagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idViagem;
    @DatabaseField
    private Long nroAparelhoViagem;
    @DatabaseField
    private String dthrViagem;
    @DatabaseField
    private Long dthrLongViagem;
    @DatabaseField
    private String dthrSaidaViagem;
    @DatabaseField
    private Long dthrSaidaLongViagem;
    @DatabaseField
    private String dthrChegadaViagem;
    @DatabaseField
    private Long dthrChegadaLongViagem;
    @DatabaseField
    private Long matricMotoristaViagem;
    @DatabaseField
    private Long matricPacienteViagem;
    @DatabaseField
    private Long idEquipViagem;
    @DatabaseField
    private Double kmSaidaViagem;
    @DatabaseField
    private Double kmChegadaViagem;
    @DatabaseField
    private Long idLocalSaidaViagem;
    @DatabaseField
    private Long idLocalDestinoViagem;
    @DatabaseField
    private Long idOcorAtendViagem;
    @DatabaseField
    private Long statusViagem; // 1 - Aberto; 2 - Fechado; 3 - Enviado

    public ViagemBean() {
    }

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public Long getNroAparelhoViagem() {
        return nroAparelhoViagem;
    }

    public void setNroAparelhoViagem(Long nroAparelhoViagem) {
        this.nroAparelhoViagem = nroAparelhoViagem;
    }

    public String getDthrSaidaViagem() {
        return dthrSaidaViagem;
    }

    public void setDthrSaidaViagem(String dthrSaidaViagem) {
        this.dthrSaidaViagem = dthrSaidaViagem;
    }

    public String getDthrChegadaViagem() {
        return dthrChegadaViagem;
    }

    public void setDthrChegadaViagem(String dthrChegadaViagem) {
        this.dthrChegadaViagem = dthrChegadaViagem;
    }

    public Long getMatricMotoristaViagem() {
        return matricMotoristaViagem;
    }

    public void setMatricMotoristaViagem(Long matricMotoristaViagem) {
        this.matricMotoristaViagem = matricMotoristaViagem;
    }

    public Long getMatricPacienteViagem() {
        return matricPacienteViagem;
    }

    public void setMatricPacienteViagem(Long matricPacienteViagem) {
        this.matricPacienteViagem = matricPacienteViagem;
    }

    public Long getIdEquipViagem() {
        return idEquipViagem;
    }

    public void setIdEquipViagem(Long idEquipViagem) {
        this.idEquipViagem = idEquipViagem;
    }

    public Double getKmSaidaViagem() {
        return kmSaidaViagem;
    }

    public void setKmSaidaViagem(Double kmSaidaViagem) {
        this.kmSaidaViagem = kmSaidaViagem;
    }

    public Double getKmChegadaViagem() {
        return kmChegadaViagem;
    }

    public void setKmChegadaViagem(Double kmChegadaViagem) {
        this.kmChegadaViagem = kmChegadaViagem;
    }

    public Long getIdLocalSaidaViagem() {
        return idLocalSaidaViagem;
    }

    public void setIdLocalSaidaViagem(Long idLocalSaidaViagem) {
        this.idLocalSaidaViagem = idLocalSaidaViagem;
    }

    public Long getIdLocalDestinoViagem() {
        return idLocalDestinoViagem;
    }

    public void setIdLocalDestinoViagem(Long idLocalDestinoViagem) {
        this.idLocalDestinoViagem = idLocalDestinoViagem;
    }

    public Long getIdOcorAtendViagem() {
        return idOcorAtendViagem;
    }

    public void setIdOcorAtendViagem(Long idOcorAtendViagem) {
        this.idOcorAtendViagem = idOcorAtendViagem;
    }

    public Long getStatusViagem() {
        return statusViagem;
    }

    public void setStatusViagem(Long statusViagem) {
        this.statusViagem = statusViagem;
    }

    public Long getDthrSaidaLongViagem() {
        return dthrSaidaLongViagem;
    }

    public void setDthrSaidaLongViagem(Long dthrSaidaLongViagem) {
        this.dthrSaidaLongViagem = dthrSaidaLongViagem;
    }

    public Long getDthrChegadaLongViagem() {
        return dthrChegadaLongViagem;
    }

    public void setDthrChegadaLongViagem(Long dthrChegadaLongViagem) {
        this.dthrChegadaLongViagem = dthrChegadaLongViagem;
    }

    public String getDthrViagem() {
        return dthrViagem;
    }

    public void setDthrViagem(String dthrViagem) {
        this.dthrViagem = dthrViagem;
    }

    public Long getDthrLongViagem() {
        return dthrLongViagem;
    }

    public void setDthrLongViagem(Long dthrLongViagem) {
        this.dthrLongViagem = dthrLongViagem;
    }
}
