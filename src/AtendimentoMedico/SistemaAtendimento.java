package AtendimentoMedico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaAtendimento {
    private FilaAtendimento fila;
    private Historico historico;
    private List<Medico> MedicosDisponiveis = new ArrayList<>();

    public SistemaAtendimento(){
        setFila(new FilaAtendimento(new ArrayList<>()));
        setHistorico(new Historico(new ArrayList<>(), new FilaAtendimento(new ArrayList<>())));
        setMedicosDisponiveis(new ArrayList<>());
    }

    public SistemaAtendimento(FilaAtendimento fila, Historico historico, List<Medico> medicosDisponiveis) {
        setFila(fila);
        setHistorico(historico);
        setMedicosDisponiveis(medicosDisponiveis);
    }

    public FilaAtendimento getFila() {
        return fila;
    }

    public void setFila(FilaAtendimento fila) {
        this.fila = fila;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public List<Medico> getMedicosDisponiveis() {
        return MedicosDisponiveis;
    }

    public void setMedicosDisponiveis(List<Medico> medicosDisponiveis) {
        MedicosDisponiveis = medicosDisponiveis;
    }

    public ClassificacaoRisco realizarTriagem(int pontuacao) {
        if (pontuacao >= 6)
            return new ClassificacaoRisco(CorTriagem.VERMELHO, "ATENDIMENTO IMEDIATO", 4);
        if (pontuacao >= 4)
            return new ClassificacaoRisco(CorTriagem.AMARELO, "ATENDIMENTO MUITO URGENTE", 3);
        if (pontuacao >= 2)
            return new ClassificacaoRisco(CorTriagem.VERDE, "POUCO URGENTE", 2);
        return new ClassificacaoRisco(CorTriagem.AZUL, "NÃO URGENTE", 1);
    }

    public void carregarDadosIniciais() {
        List<Medico> ListaMedicosDisponiveis = new ArrayList<>();
        ListaMedicosDisponiveis.add(new Medico("Dr. Carlos Silva", "12345678901", LocalDate.of(1980, 5, 10),  "Clínica Geral"));
        ListaMedicosDisponiveis.add(new Medico("Dra. Ana Costa", "98765432109", LocalDate.of(1975, 11, 25),  "Pediatria"));
        ListaMedicosDisponiveis.add(new Medico("Dr. Renato Souza", "11223344556", LocalDate.of(1990, 8, 1),  "Cardiologia"));

        setMedicosDisponiveis(ListaMedicosDisponiveis);

        String[] nomes = {"João", "Maria", "Pedro", "Sofia", "Lucas", "Laura", "Miguel", "Isabela", "Davi", "Manuela", "Anderson", "Gabriel", "Camile", "Mateus", "Ana", "Renato", "Júlio", "Julia", "Cássio", "Rafael"};
        ClassificacaoRisco[] prioridades = new ClassificacaoRisco[]{};
        Random random = new  Random();
        for (int i = 0; i < 20; i++) {

            String nome = nomes[random.nextInt(nomes.length)];
            String cpf = String.format("%011d", i + 100);
            LocalDate dataNascimento = LocalDate.now().minusYears(random.nextInt(70) + 5);

            LocalDateTime dataHoraChegada = LocalDateTime.now().minusMinutes(random.nextInt(180)).minusSeconds(random.nextInt(60));

            ClassificacaoRisco risco = realizarTriagem(random.nextInt(8));

            List<String> respostas = Arrays.asList("Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4");

            Paciente p = new Paciente(nome, cpf, dataNascimento, dataHoraChegada, risco);

            p.setClassificacao(risco);
            p.setPerguntas(respostas);
            fila.inserirPaciente(p);
        }
    }

    public String inserirNovoPaciente(Paciente paciente) {

        StringBuilder sb = new StringBuilder();

        fila.inserirPaciente(paciente);

        sb.append(String.format("\nPaciente %s inserido na fila com prioridade: %s\n", paciente.getNome(), paciente.getClassificacao().getDescricao()));

        return sb.toString();
    }

    public String inserirNovoMedico(Medico medico) {
        List<Medico> medicosCadastrados = getMedicosDisponiveis();

        medicosCadastrados.add(medico);

        setMedicosDisponiveis(medicosCadastrados);

        StringBuilder sb = new StringBuilder();

        sb.append("O Médico: ").append(medico.getNome()).append("\n");

        sb.append("Foi inserido com sucesso");

        return sb.toString();
    }

    public String simularAtendimento() {
        StringBuilder sb = new StringBuilder();

        if (fila.estaVazia()) {
            sb.append("Não há pacientes na fila para atendimento.");
            return sb.toString();
        }

        Paciente pacienteAtendido = fila.removerPrimeiroPaciente();

        Random random = new  Random();
        Medico medicoSelecionado = MedicosDisponiveis.get(random.nextInt(MedicosDisponiveis.size()));

        LocalDateTime dataHoraAtendimento = LocalDateTime.now();

        Atendimento registro = new Atendimento(dataHoraAtendimento, medicoSelecionado, pacienteAtendido);

        historico.registrarAtendimento(registro);

        sb.append("\n--- ATENDIMENTO REALIZADO ---\n");

        sb.append(String.format("Paciente: %s | Prioridade: %s | Atendido por: %s\n",
                pacienteAtendido.getNome(),
                pacienteAtendido.getClassificacao().getDescricao(),
                medicoSelecionado.getNome()));
        return sb.toString();
    }


}
