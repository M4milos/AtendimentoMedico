package AtendimentoMedico;

import java.time.LocalDateTime;

public class Atendimento {
    private LocalDateTime dataHoraAtendimento;
    private Paciente paciente;
    private Medico medico;

    public Atendimento(LocalDateTime dataHoraAtendimento, Medico medico, Paciente paciente) {
        setDataHoraAtendimento(dataHoraAtendimento);
        setMedico(medico);
        setPaciente(paciente);
    }

    public LocalDateTime getDataHoraAtendimento() {
        return dataHoraAtendimento;
    }

    public void setDataHoraAtendimento(LocalDateTime dataHoraAtendimento) {
        this.dataHoraAtendimento = dataHoraAtendimento;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
