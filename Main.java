import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>();
        int opt = -1;
        do {
            System.out.println("==== Cadastro De Robo ====");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar Robo");
            System.out.println("2. Buscar Robo por código");
            System.out.println("3. Listar Robos");
            System.out.println("Opção: ");
            opt = buscarOperacao(s);
            switch (opt) {
                case 0:
                    System.out.println("Adeus!");
                    break;
                case 1:
                    int codigo;
                    boolean codigoValido;
                    do {
                        System.out.println("Digite o código do Robo: ");
                        codigo = buscarOperacao(s);
                        codigoValido = true;
                        if (codigo == 0) {
                            System.out.println("O código deve ser positivo.");
                            codigoValido = false;
                        } else if (buscarRobo(robos, codigo) != null) {
                            System.out.println("Já existe um robô com esse código.");
                            codigoValido = false;
                        }
                    } while (!codigoValido);

                    System.out.println("Digite o nome do Robo");
                    String nome = s.next();

                    int ataque;
                    do {
                        System.out.println("Digite o ataque do Robo (10 a 30): ");
                        ataque = buscarOperacao(s);
                        if (ataque < 10 || ataque > 30) {
                            System.out.println("O ataque deve estar entre 10 e 30.");
                        }
                    } while (ataque < 10 || ataque > 30);

                    int defesa;
                    do {
                        System.out.println("Digite a defesa do Robo (0 a 20): ");
                        defesa = buscarOperacao(s);
                        if (defesa > 20) {
                            System.out.println("A defesa deve estar entre 0 e 20.");
                        }
                    } while (defesa > 20);

                    Robo robo = new Robo(codigo, nome, ataque, defesa);
                    robos.add(robo);
                    System.out.println("Robô cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o código do Robo: ");
                    int codigoBusca = buscarOperacao(s);
                    Robo encontrado = buscarRobo(robos, codigoBusca);
                    if (encontrado == null) {
                        System.out.println("Não existe robô com o código " + codigoBusca + ".");
                    } else {
                        encontrado.exibirDados();
                    }
                    break;
                case 3:
                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robô cadastrado ainda.");
                    } else {
                        for (Robo r : robos) {
                            r.exibirDados();
                        }
                    }
                    break;
                default:
                    System.out.println("Operação inválida.");
                    break;
            }
        } while (opt != 0);
        s.close();
    }

    public static int buscarOperacao(Scanner s) {
        int opt = -1;
        do {
            try {
                opt = s.nextInt();
            } catch (InputMismatchException e) {
                s.next();
                System.out.println("Operação inválida");
                System.out.println("Digite novamente a informação!");
                opt = -1;
            }
        } while (opt < 0);

        return opt;
    }

    public static Robo buscarRobo(ArrayList<Robo> robos, int codigo) {
        for (Robo r : robos) {
            if (r.codigo == codigo) {
                return r;
            }
        }
        return null;
    }
}