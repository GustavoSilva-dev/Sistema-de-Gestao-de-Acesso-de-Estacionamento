/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author alunos
 */
@Entity
@Table(name = "visitante", catalog = "ESTACIONAMENTO", schema = "")
@NamedQueries({
    @NamedQuery(name = "Visitante.findAll", query = "SELECT v FROM Visitante v"),
    @NamedQuery(name = "Visitante.findByCliNome", query = "SELECT v FROM Visitante v WHERE v.cliNome = :cliNome"),
    @NamedQuery(name = "Visitante.findByCliCpf", query = "SELECT v FROM Visitante v WHERE v.cliCpf = :cliCpf"),
    @NamedQuery(name = "Visitante.findByCliEmpresa", query = "SELECT v FROM Visitante v WHERE v.cliEmpresa = :cliEmpresa"),
    @NamedQuery(name = "Visitante.findByCliMotivo", query = "SELECT v FROM Visitante v WHERE v.cliMotivo = :cliMotivo"),
    @NamedQuery(name = "Visitante.findByCliColaborador", query = "SELECT v FROM Visitante v WHERE v.cliColaborador = :cliColaborador"),
    @NamedQuery(name = "Visitante.findByCliFoto", query = "SELECT v FROM Visitante v WHERE v.cliFoto = :cliFoto"),
    @NamedQuery(name = "Visitante.findByCliPlaca", query = "SELECT v FROM Visitante v WHERE v.cliPlaca = :cliPlaca")})
public class Visitante implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "cli_nome")
    private String cliNome;
    @Id
    @Basic(optional = false)
    @Column(name = "cli_cpf")
    private Long cliCpf;
    @Basic(optional = false)
    @Column(name = "cli_empresa")
    private String cliEmpresa;
    @Basic(optional = false)
    @Column(name = "cli_motivo")
    private String cliMotivo;
    @Basic(optional = false)
    @Column(name = "cli_colaborador")
    private String cliColaborador;
    @Basic(optional = false)
    @Column(name = "cli_foto")
    private String cliFoto;
    @Column(name = "cli_placa")
    private String cliPlaca;

    public Visitante() {
    }

    public Visitante(Long cliCpf) {
        this.cliCpf = cliCpf;
    }

    public Visitante(Long cliCpf, String cliNome, String cliEmpresa, String cliMotivo, String cliColaborador, String cliFoto) {
        this.cliCpf = cliCpf;
        this.cliNome = cliNome;
        this.cliEmpresa = cliEmpresa;
        this.cliMotivo = cliMotivo;
        this.cliColaborador = cliColaborador;
        this.cliFoto = cliFoto;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        String oldCliNome = this.cliNome;
        this.cliNome = cliNome;
        changeSupport.firePropertyChange("cliNome", oldCliNome, cliNome);
    }

    public Long getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(Long cliCpf) {
        Long oldCliCpf = this.cliCpf;
        this.cliCpf = cliCpf;
        changeSupport.firePropertyChange("cliCpf", oldCliCpf, cliCpf);
    }

    public String getCliEmpresa() {
        return cliEmpresa;
    }

    public void setCliEmpresa(String cliEmpresa) {
        String oldCliEmpresa = this.cliEmpresa;
        this.cliEmpresa = cliEmpresa;
        changeSupport.firePropertyChange("cliEmpresa", oldCliEmpresa, cliEmpresa);
    }

    public String getCliMotivo() {
        return cliMotivo;
    }

    public void setCliMotivo(String cliMotivo) {
        String oldCliMotivo = this.cliMotivo;
        this.cliMotivo = cliMotivo;
        changeSupport.firePropertyChange("cliMotivo", oldCliMotivo, cliMotivo);
    }

    public String getCliColaborador() {
        return cliColaborador;
    }

    public void setCliColaborador(String cliColaborador) {
        String oldCliColaborador = this.cliColaborador;
        this.cliColaborador = cliColaborador;
        changeSupport.firePropertyChange("cliColaborador", oldCliColaborador, cliColaborador);
    }

    public String getCliFoto() {
        return cliFoto;
    }

    public void setCliFoto(String cliFoto) {
        String oldCliFoto = this.cliFoto;
        this.cliFoto = cliFoto;
        changeSupport.firePropertyChange("cliFoto", oldCliFoto, cliFoto);
    }

    public String getCliPlaca() {
        return cliPlaca;
    }

    public void setCliPlaca(String cliPlaca) {
        String oldCliPlaca = this.cliPlaca;
        this.cliPlaca = cliPlaca;
        changeSupport.firePropertyChange("cliPlaca", oldCliPlaca, cliPlaca);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliCpf != null ? cliCpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitante)) {
            return false;
        }
        Visitante other = (Visitante) object;
        if ((this.cliCpf == null && other.cliCpf != null) || (this.cliCpf != null && !this.cliCpf.equals(other.cliCpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "View.Visitante[ cliCpf=" + cliCpf + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
