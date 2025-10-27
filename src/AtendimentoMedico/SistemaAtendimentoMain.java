package AtendimentoMedico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SistemaAtendimentoMain {

    public static SistemaAtendimento sa = new SistemaAtendimento();

    public static void main(String[] args) {

        sa.carregarDadosIniciais();
        exibirMenu();
    }

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== SISTEMA DE ATENDIMENTO MÉDICO =====");
            System.out.println("1. Inserir Novo Paciente");
            System.out.println("2. Inserir Novo Médico");
            System.out.println("3. Mostrar Fila de Atendimento");
            System.out.println("4. Simular Atendimento (Chamar Próximo Paciente)");
            System.out.println("5. Mostrar Histórico de Atendimentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            Scanner sc = new Scanner(System.in);

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print(InserirNovoPaciente());
                        break;
                    case 2:
                        System.out.print(InserirNovoMedico());
                        break;
                    case 3:
                        System.out.print(sa.getFila().mostrarFila());
                        break;
                    case 4:
                        System.out.print(SimularAtendimento());
                        break;
                    case 5:
                        System.out.print(MostrarHistorico());
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema. Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    public static String InserirNovoPaciente(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- INSERÇÃO DE NOVO PACIENTE ---");
        String nome;
        do {
            System.out.print("Nome: ");
            nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome não pode ficar vazio!");
            }
        } while (nome.isEmpty());

        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = sc.nextLine().trim();
            if (cpf.isEmpty()) {
                System.out.println("O CPF não pode ficar vazio!");
            }
        } while (cpf.isEmpty());

        LocalDate dataNascimento = null;
        boolean dataValida = false;

        do {
            System.out.print("Digite sua data de nascimento (yyyy-MM-dd): ");
            String entrada = sc.nextLine().trim();

            try {
                dataNascimento = LocalDate.parse(entrada);
                dataValida = true;
            } catch (Exception e) {
                System.out.println("Data inválida! Use o formato correto: yyyy-MM-dd (ex: 1985-10-27).");
            }

        } while (!dataValida);

        ClassificacaoRisco risco = RealizarTriagem();

        Paciente paciente = new Paciente(nome, cpf, dataNascimento, LocalDateTime.now(), risco);

        return sa.inserirNovoPaciente(paciente);
    }

    public static String InserirNovoMedico() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- INSERÇÃO DE NOVO MÉDICO ---");
        String nome;
        do {
            System.out.print("Nome: ");
            nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome não pode ficar vazio!");
            }
        } while (nome.isEmpty());

        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = sc.nextLine().trim();
            if (cpf.isEmpty()) {
                System.out.println("O CPF não pode ficar vazio!");
            }
        } while (cpf.isEmpty());

        LocalDate dataNascimento = null;
        boolean dataValida = false;

        do {
            System.out.print("Digite sua data de nascimento (yyyy-MM-dd): ");
            String entrada = sc.nextLine().trim();

            try {
                dataNascimento = LocalDate.parse(entrada);
                dataValida = true;
            } catch (Exception e) {
                System.out.println("Data inválida! Use o formato correto: yyyy-MM-dd (ex: 1985-10-27).");
            }

        } while (!dataValida);

        String especialidade;
        do {
            System.out.print("Especialidade: ");
            especialidade = sc.nextLine().trim();
            if (especialidade.isEmpty()) {
                System.out.println("A especialidade não pode ficar vazia!");
            }
        } while (especialidade.isEmpty());

        Medico medico = new Medico(nome, cpf, dataNascimento, especialidade);
        return sa.inserirNovoMedico(medico);
    }

    public static ClassificacaoRisco RealizarTriagem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- TRIAGEM (Classificação de Risco) ---");
        int pontuacao = 0;

        System.out.print("1. Você apresenta dificuldade respiratória grave? (S/N): ");
        if (sc.nextLine().equalsIgnoreCase("S")) pontuacao += 4;

        System.out.print("2. Você apresenta dor torácica aguda (forte)? (S/N): ");
        if (sc.nextLine().equalsIgnoreCase("S")) pontuacao += 3;

        System.out.print("3. Sua dor geral é severa ou incapacitante? (S/N): ");
        if (sc.nextLine().equalsIgnoreCase("S")) pontuacao += 2;

        System.out.print("4. Você possui febre alta ou vômitos persistentes? (S/N): ");
        if (sc.nextLine().equalsIgnoreCase("S")) pontuacao += 1;

        return sa.realizarTriagem(pontuacao);
    }

    public static String SimularAtendimento(){
        return sa.simularAtendimento();
    }

    public static String MostrarHistorico(){
        return sa.getHistorico().MostrarHistorico();
    }
}