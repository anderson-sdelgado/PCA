package br.com.usinasantafe.pca.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pca.model.pst.Entidade;

@DatabaseTable(tableName="tbocoratendest")
public class OcorAtendBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idOcorAtend;
    @DatabaseField
    private String descrOcorAtend;

    public OcorAtendBean() {
    }

    public Long getIdOcorAtend() {
        return idOcorAtend;
    }

    public void setIdOcorAtend(Long idOcorAtend) {
        this.idOcorAtend = idOcorAtend;
    }

    public String getDescrOcorAtend() {
        return descrOcorAtend;
    }

    public void setDescrOcorAtend(String descrOcorAtend) {
        this.descrOcorAtend = descrOcorAtend;
    }
}
