package db.Models;

import java.sql.Timestamp;
import java.util.List;

public class Atendimento 
{
    private Timestamp horario;
    private int codigo;
    private String observacoes;
    private PacienteTratamento pt;
    private Funcionario dentista;
    
    private List <Material> itens;

    public Atendimento() {
    }
    
    public Atendimento(Timestamp horario, int codigo, PacienteTratamento pt) {
        this.horario = horario;
        this.codigo = codigo;
        this.pt = pt;
    }

    public Atendimento(Timestamp horario) {
        this.horario = horario;
    }

    public Atendimento(Timestamp horario, PacienteTratamento pt) {
        this.horario = horario;
        this.pt = pt;
    }

    public Atendimento(Timestamp horario, int codigo, String observacoes, PacienteTratamento pt, Funcionario dentista, List<Material> itens) {
        this.horario = horario;
        this.codigo = codigo;
        this.observacoes = observacoes;
        this.pt = pt;
        this.dentista = dentista;
        this.itens = itens;
    }

    public Atendimento(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Funcionario getDentista() {
        return dentista;
    }

    public void setDentista(Funcionario dentista) {
        this.dentista = dentista;
    }

    public List<Material> getItens() {
        return itens;
    }

    public void setItens(List<Material> itens) {
        this.itens = itens;
    }

    public PacienteTratamento getPt() {
        return pt;
    }

    public void setPt(PacienteTratamento pt) {
        this.pt = pt;
    }
    
}
