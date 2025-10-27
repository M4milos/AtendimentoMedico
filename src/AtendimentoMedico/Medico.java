package AtendimentoMedico;

import java.time.LocalDate;

public class Medico extends Pessoa {
    private String especialidade;

    public Medico(String nome, String cpf, LocalDate dataNascimento, String especialidade) {
        super(nome, cpf,  dataNascimento);
        setEspecialidade(especialidade);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
