/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author alunos
 */
@Entity
@Table(name = "veiculo", catalog = "ESTACIONAMENTO", schema = "")
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByVeiPlaca", query = "SELECT v FROM Veiculo v WHERE v.veiPlaca = :veiPlaca"),
    @NamedQuery(name = "Veiculo.findByVeiModelo", query = "SELECT v FROM Veiculo v WHERE v.veiModelo = :veiModelo"),
    @NamedQuery(name = "Veiculo.findByVeiCpf", query = "SELECT v FROM Veiculo v WHERE v.veiCpf = :veiCpf"),
    @NamedQuery(name = "Veiculo.findByVeiCor", query = "SELECT v FROM Veiculo v WHERE v.veiCor = :veiCor"),
    @NamedQuery(name = "Veiculo.findByVeiVaga", query = "SELECT v FROM Veiculo v WHERE v.veiVaga = :veiVaga"),
    @NamedQuery(name = "Veiculo.findByVeiTipo", query = "SELECT v FROM Veiculo v WHERE v.veiTipo = :veiTipo"),
    @NamedQuery(name = "Veiculo.findByVeiEntrada", query = "SELECT v FROM Veiculo v WHERE v.veiEntrada = :veiEntrada"),
    @NamedQuery(name = "Veiculo.findByVeiSaida", query = "SELECT v FROM Veiculo v WHERE v.veiSaida = :veiSaida"),
    @NamedQuery(name = "Veiculo.findByVeiStatus", query = "SELECT v FROM Veiculo v WHERE v.veiStatus = :veiStatus")})
public class Veiculo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vei_placa")
    private String veiPlaca;
    @Column(name = "vei_modelo")
    private String veiModelo;
    @Basic(optional = false)
    @Column(name = "vei_cpf")
    private long veiCpf;
    @Basic(optional = false)
    @Column(name = "vei_cor")
    private String veiCor;
    @Basic(optional = false)
    @Column(name = "vei_vaga")
    private String veiVaga;
    @Basic(optional = false)
    @Column(name = "vei_tipo")
    private String veiTipo;
    @Basic(optional = false)
    @Column(name = "vei_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date veiEntrada;
    @Column(name = "vei_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date veiSaida;
    @Basic(optional = false)
    @Column(name = "vei_status")
    private String veiStatus;

    public Veiculo() {
    }

    public Veiculo(String veiPlaca) {
        this.veiPlaca = veiPlaca;
    }

    public Veiculo(String veiPlaca, long veiCpf, String veiCor, String veiVaga, String veiTipo, Date veiEntrada, String veiStatus) {
        this.veiPlaca = veiPlaca;
        this.veiCpf = veiCpf;
        this.veiCor = veiCor;
        this.veiVaga = veiVaga;
        this.veiTipo = veiTipo;
        this.veiEntrada = veiEntrada;
        this.veiStatus = veiStatus;
    }

    public String getVeiPlaca() {
        return veiPlaca;
    }

    public void setVeiPlaca(String veiPlaca) {
        String oldVeiPlaca = this.veiPlaca;
        this.veiPlaca = veiPlaca;
        changeSupport.firePropertyChange("veiPlaca", oldVeiPlaca, veiPlaca);
    }

    public String getVeiModelo() {
        return veiModelo;
    }

    public void setVeiModelo(String veiModelo) {
        String oldVeiModelo = this.veiModelo;
        this.veiModelo = veiModelo;
        changeSupport.firePropertyChange("veiModelo", oldVeiModelo, veiModelo);
    }

    public long getVeiCpf() {
        return veiCpf;
    }

    public void setVeiCpf(long veiCpf) {
        long oldVeiCpf = this.veiCpf;
        this.veiCpf = veiCpf;
        changeSupport.firePropertyChange("veiCpf", oldVeiCpf, veiCpf);
    }

    public String getVeiCor() {
        return veiCor;
    }

    public void setVeiCor(String veiCor) {
        String oldVeiCor = this.veiCor;
        this.veiCor = veiCor;
        changeSupport.firePropertyChange("veiCor", oldVeiCor, veiCor);
    }

    public String getVeiVaga() {
        return veiVaga;
    }

    public void setVeiVaga(String veiVaga) {
        String oldVeiVaga = this.veiVaga;
        this.veiVaga = veiVaga;
        changeSupport.firePropertyChange("veiVaga", oldVeiVaga, veiVaga);
    }

    public String getVeiTipo() {
        return veiTipo;
    }

    public void setVeiTipo(String veiTipo) {
        String oldVeiTipo = this.veiTipo;
        this.veiTipo = veiTipo;
        changeSupport.firePropertyChange("veiTipo", oldVeiTipo, veiTipo);
    }

    public Date getVeiEntrada() {
        return veiEntrada;
    }

    public void setVeiEntrada(Date veiEntrada) {
        Date oldVeiEntrada = this.veiEntrada;
        this.veiEntrada = veiEntrada;
        changeSupport.firePropertyChange("veiEntrada", oldVeiEntrada, veiEntrada);
    }

    public Date getVeiSaida() {
        return veiSaida;
    }

    public void setVeiSaida(Date veiSaida) {
        Date oldVeiSaida = this.veiSaida;
        this.veiSaida = veiSaida;
        changeSupport.firePropertyChange("veiSaida", oldVeiSaida, veiSaida);
    }

    public String getVeiStatus() {
        return veiStatus;
    }

    public void setVeiStatus(String veiStatus) {
        String oldVeiStatus = this.veiStatus;
        this.veiStatus = veiStatus;
        changeSupport.firePropertyChange("veiStatus", oldVeiStatus, veiStatus);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veiPlaca != null ? veiPlaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.veiPlaca == null && other.veiPlaca != null) || (this.veiPlaca != null && !this.veiPlaca.equals(other.veiPlaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "View.Veiculo[ veiPlaca=" + veiPlaca + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
