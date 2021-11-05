package db.Models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Atendimento 
{
    private Timestamp horario;
    private int codigo;
    private String observacoes, dentes;
    private PacienteTratamento pt;
    private Funcionario dentista;
    private Paciente paciente;
    
    private List <Material> itens;

    public Atendimento() {
        itens = new ArrayList<>();
    }

    public Atendimento(Timestamp horario, int codigo, String observacoes, Funcionario dentista, List<Material> itens, String dentes, Paciente paciente, PacienteTratamento pt) {
        this.horario = horario;
        this.codigo = codigo;
        this.observacoes = observacoes;
        this.dentes = dentes;
        this.dentista = dentista;
        this.paciente = paciente;
        this.itens = itens;
        this.pt = pt;
    }

    public Atendimento(Timestamp horario, int codigo, Paciente paciente) {
        this.horario = horario;
        this.codigo = codigo;
        this.paciente = paciente;
    }

    public Atendimento(Timestamp horario, Paciente paciente) {
        this.horario = horario;
        this.paciente = paciente;
    }
    
    public Atendimento(Timestamp horario, int codigo, PacienteTratamento pt) {
        this.horario = horario;
        this.codigo = codigo;
        this.pt = pt;
        itens = new ArrayList<>();
    }

    public Atendimento(Timestamp horario, PacienteTratamento pt) {
        this.horario = horario;
        this.pt = pt;
        itens = new ArrayList<>();
    }

    public Atendimento(Timestamp horario, int codigo, String observacoes, PacienteTratamento pt, Funcionario dentista, List<Material> itens, String dentes) {
        this.horario = horario;
        this.codigo = codigo;
        this.observacoes = observacoes;
        this.pt = pt;
        this.dentista = dentista;
        this.itens = itens;
        this.dentes = dentes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Atendimento(int codigo) {
        this.codigo = codigo;
        itens = new ArrayList<>();
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
    
    public String getDentes() {
        return dentes;
    }

    public void setDentes(String dentes) {
        this.dentes = dentes;
    }
    
}
