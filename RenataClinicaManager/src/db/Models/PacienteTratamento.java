package db.Models;

/**
 *
 * @author vicga
 */
public class PacienteTratamento {
    private Paciente paciente;
    private Tratamento tratamento;
    private char ativo;
    private int codigo;

    public PacienteTratamento() {
    }

    public PacienteTratamento(Paciente paciente, Tratamento tratamento, char ativo, int codigo) {
        this.paciente = paciente;
        this.tratamento = tratamento;
        this.ativo = ativo;
        this.codigo = codigo;
    }

    public PacienteTratamento(Paciente paciente, Tratamento tratamento, char ativo) {
        this.paciente = paciente;
        this.tratamento = tratamento;
        this.ativo = ativo;
    }

    public PacienteTratamento(Paciente paciente, Tratamento tratamento) {
        this.paciente = paciente;
        this.tratamento = tratamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
