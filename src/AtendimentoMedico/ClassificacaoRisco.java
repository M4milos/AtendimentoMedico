package AtendimentoMedico;

public class ClassificacaoRisco {

    private CorTriagem cor;
    private int prioridade;
    private String descricao;


    public ClassificacaoRisco() {
    }

    public ClassificacaoRisco(CorTriagem cor, String descricao, int prioridade) {
        this.cor = cor;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public CorTriagem getCor() {
        return cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("cor: ").append(cor);
        sb.append(", prioridade: ").append(prioridade);
        sb.append(", descricao: '").append(descricao).append('\'');
        return sb.toString();
    }
}
enum CorTriagem {
    VERMELHO, AMARELO, VERDE, AZUL
}
