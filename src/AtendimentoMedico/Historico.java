package AtendimentoMedico;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    private List<Atendimento> pacientesAtendidos = new ArrayList<>();
    private FilaAtendimento filaAtual;

    public Historico(List<Atendimento> pacientesAtendidos, FilaAtendimento filaAtual) {
        setAtendimentos(pacientesAtendidos);
        setFilaAtual(filaAtual);
    }

    public void setAtendimentos(List<Atendimento> pacientesAtendidos) {
        this.pacientesAtendidos = pacientesAtendidos;
    }

    public FilaAtendimento getFilaAtual() {
        return filaAtual;
    }

    public void registrarAtendimento(Atendimento atendimento) {
        this.pacientesAtendidos.add(atendimento);
    }

    public void setFilaAtual(FilaAtendimento filaAtual) {
        this.filaAtual = filaAtual;
    }

    public List<Atendimento> getPacientesAtendidos() {
        return pacientesAtendidos;
    }

    public String MostrarHistorico() {
        StringBuilder sb = new StringBuilder();
        if (pacientesAtendidos.isEmpty()) {
            sb.append("Nenhum atendimento encontrado.\n");
            return sb.toString();
        }
        sb.append("Historico de atendimentos:\n");
        for (Atendimento atendimento : pacientesAtendidos) {
            sb.append(atendimento.toString() + "\n");
        }
        return sb.toString();
    }
}
