package AtendimentoMedico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa{
    private LocalDateTime dataHoraChegada;
    private List<String> perguntas = new ArrayList<>();
    private ClassificacaoRisco classificacao;

    public Paciente(String nome, String cpf, LocalDate dataNascimento, LocalDateTime dataHoraChegada, ClassificacaoRisco classificacao) {
        super(nome, cpf, dataNascimento);
        setDataHoraChegada(dataHoraChegada);
        setClassificacao(classificacao);
        setPerguntas(perguntas);
    }

    public ClassificacaoRisco getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoRisco classificacao) {
        this.classificacao = classificacao;
    }

    public LocalDateTime getDataHoraChegada() {
        return dataHoraChegada;
    }

    public void setDataHoraChegada(LocalDateTime dataHoraChegada) {
        this.dataHoraChegada = dataHoraChegada;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<String> perguntas) {
        this.perguntas = perguntas;
    }


}
