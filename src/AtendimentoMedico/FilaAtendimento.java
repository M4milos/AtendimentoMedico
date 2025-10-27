package AtendimentoMedico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilaAtendimento {
    private List<Paciente> pacientesAguardando = new ArrayList<>();

    public FilaAtendimento(List<Paciente> pacientesAguardando) {
        setPacientesAguardando(pacientesAguardando);
    }
    //Compara a classificação de 0 a 4 (Prioridade definida na classificação, para desempatar caso sejam iguais, irá comparar a hora de chegada do paciente)
    private static final Comparator<Paciente> COMPARADOR_PRIORIDADE = Comparator.comparing((Paciente p) -> p.getClassificacao().getPrioridade()).reversed().thenComparing(Paciente::getDataHoraChegada);

    public void setPacientesAguardando(List<Paciente> pacientesAguardando) {
        this.pacientesAguardando = pacientesAguardando;
    }

    public void inserirPaciente(Paciente paciente) {
        this.pacientesAguardando.add(paciente);
        ordenarFila();
    }

    public Paciente removerPrimeiroPaciente() {
        if (pacientesAguardando.isEmpty()) {
            return null;
        }
        return pacientesAguardando.remove(0);
    }

    private void ordenarFila() {
        this.pacientesAguardando.sort(COMPARADOR_PRIORIDADE);
    }

    public List<Paciente> getPacientesAguardando() {
        return pacientesAguardando;
    }

    public boolean estaVazia() {
        return pacientesAguardando.isEmpty();
    }

    public String mostrarFila() {
        StringBuilder sb = new StringBuilder();

        if (pacientesAguardando.isEmpty()){
            sb.append("Nenhum Paciente Aguardando\n");
            return sb.toString();
        }

        sb.append("\n--- FILA DE ATENDIMENTO (Prioridade/Chegada) ---\n");

        for (int i = 0; i < pacientesAguardando.size(); i++) {
            Paciente p = pacientesAguardando.get(i);

            sb.append(String.format("%dº | %s | Prioridade: %s | Chegada: %s\n",
                    (i + 1),
                    p.getNome(),
                    p.getClassificacao().toString(),
                    p.getDataHoraChegada().toLocalTime().toString()));
        }

        sb.append("---------------------------------------------------\n");

        return sb.toString();
    }
}
