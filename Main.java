 // Herança: RoboOfensivo, RoboDefensivo e RoboEquilibrado estendem Robo e usam super() para reaproveitar os atributos e métodos comuns
 // Polimorfismo: getAtaqueEfetivo(), ajustarDanoRecebido() e getTipo() são sobrescritos nas subclasses. calcularDano() e exibirDados() funcionam com qualquer tipo
 // Encapsulamento: atributos private com getters e sem setters. Energia, pontos e resultados só mudam por métodos do Robo que mantêm as regras

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>();
        int opt = -1;

        do {
            System.out.println("--------------------------");
            System.out.println("==== Cadastro De Robo ====");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar Robo");
            System.out.println("2. Buscar Robo por código");
            System.out.println("3. Listar Robos");
            System.out.println("4. Realizar Combate");
            System.out.println("5. Recuperar Energia");
            System.out.println("Digite a operação: ");
            opt = buscarOperacao(s);
            System.out.println("--------------------------");
            switch (opt) {
                case 0:
                    System.out.println("--------------------------");
                    System.out.println("Adeus, arrogantchi!");
                    break;

                case 1:
                    int codigo;
                    boolean codigoValido;

                    do {
                        System.out.println("--------------------------");
                        System.out.println("Digite o código do Robo: ");
                        codigo = buscarOperacao(s);
                        codigoValido = true;

                        if (codigo == 0) {
                            System.out.println("--------------------------");
                            System.out.println("O código deve ser positivo.");
                            codigoValido = false;
                        } else if (buscarRobo(robos, codigo) != null) {
                            System.out.println("--------------------------");
                            System.out.println("Já existe um robô com esse código.");
                            codigoValido = false;
                        }
                    } while (!codigoValido);

                    System.out.println("--------------------------");
                    System.out.println("Digite o nome do Robo");
                    String nome = s.next();

                    int ataque;
                    do {
                        System.out.println("--------------------------");
                        System.out.println("Digite o ataque do Robo (10 a 30): ");
                        ataque = buscarOperacao(s);

                        if (ataque < 10 || ataque > 30) {
                            System.out.println("--------------------------");
                            System.out.println("O ataque deve estar entre 10 e 30.");
                        }
                    } while (ataque < 10 || ataque > 30);

                    int defesa;
                    do {
                        System.out.println("--------------------------");
                        System.out.println("Digite a defesa do Robo (0 a 20): ");
                        defesa = buscarOperacao(s);

                        if (defesa > 20) {
                            System.out.println("--------------------------");
                            System.out.println("A defesa deve estar entre 0 e 20.");
                        }
                    } while (defesa > 20);

                    int tipo;
                    do {
                        System.out.println("--------------------------");
                        System.out.println("Escolha o tipo do Robo:");
                        System.out.println("1. Ofensivo");
                        System.out.println("2. Defensivo");
                        System.out.println("3. Equilibrado");
                        tipo = buscarOperacao(s);

                        if (tipo < 1 || tipo > 3) {
                            System.out.println("--------------------------");
                            System.out.println("Tipo inválido. Escolha 1, 2 ou 3.");
                        }
                    } while (tipo < 1 || tipo > 3);

                    Robo robo;
                    if (tipo == 1) {
                        robo = new RoboOfensivo(codigo, nome, ataque, defesa);
                    } else if (tipo == 2) {
                        robo = new RoboDefensivo(codigo, nome, ataque, defesa);
                    } else {
                        robo = new RoboEquilibrado(codigo, nome, ataque, defesa);
                    }
                    robos.add(robo);

                    System.out.println("--------------------------");
                    System.out.println("Robô cadastrado");
                    break;

                case 2:
                    System.out.println("--------------------------");
                    System.out.println("Digite o código do Robo: ");
                    int codigoBusca = buscarOperacao(s);
                    Robo encontrado = buscarRobo(robos, codigoBusca);

                    if (encontrado == null) {
                        System.out.println("--------------------------");
                        System.out.println("Não existe robô com o código " + codigoBusca + ".");
                    } else {
                        encontrado.exibirDados();
                    }
                    break;

                case 3:
                    if (robos.isEmpty()) {
                        System.out.println("--------------------------");
                        System.out.println("Nenhum robô cadastrado ainda.");
                    } else {
                        for (Robo r : robos) {
                            r.exibirDados();
                        }
                    }
                    break;

                case 4:
                    System.out.println("--------------------------");
                    System.out.println("Digite o código do primeiro robô: ");
                    int codigo1 = buscarOperacao(s);

                    System.out.println("--------------------------");
                    System.out.println("Digite o código do segundo robô: ");
                    int codigo2 = buscarOperacao(s);

                    Robo robo1 = buscarRobo(robos, codigo1);
                    Robo robo2 = buscarRobo(robos, codigo2);

                    if (codigo1 == codigo2) {
                        System.out.println("--------------------------");
                        System.out.println("Os robôs do combate devem ser diferentes.");
                    } else if (robo1 == null || robo2 == null) {
                        System.out.println("--------------------------");
                        System.out.println("Um dos códigos informados não existe.");
                    } else if (!robo1.estaDisponivel() || !robo2.estaDisponivel()) {
                        System.out.println("--------------------------");
                        System.out.println("Os dois robôs precisam ter pelo menos 30 de energia.");
                    } else {
                        Robo primeiro;
                        Robo segundo;

                        if (robo1.getPontos() < robo2.getPontos()) {
                            primeiro = robo1;
                            segundo = robo2;
                        } else if (robo2.getPontos() < robo1.getPontos()) {
                            primeiro = robo2;
                            segundo = robo1;
                        } else if (robo1.getCodigo() < robo2.getCodigo()) {
                            primeiro = robo1;
                            segundo = robo2;
                        } else {
                            primeiro = robo2;
                            segundo = robo1;
                        }

                        System.out.println("--------------------------");
                        System.out.println("Começa atacando: " + primeiro.getNome());

                        int dano;

                        for (int rodada = 1; rodada <= 5; rodada++) {
                            System.out.println("--------------------------");
                            System.out.println("--- Rodada " + rodada + " ---");
                            dano = primeiro.calcularDano(segundo, rodada);
                            segundo.receberDano(dano);

                            System.out.println("--------------------------");
                            System.out.println(primeiro.getNome() + " ataca " + segundo.getNome() + " e causa " + dano + " de dano. Energia de " + segundo.getNome() + ": " + segundo.getEnergia());

                            if (segundo.getEnergia() == 0) {
                                break;
                            }

                            dano = segundo.calcularDano(primeiro, rodada);
                            primeiro.receberDano(dano);

                            System.out.println("--------------------------");
                            System.out.println(segundo.getNome() + " ataca " + primeiro.getNome() + " e causa " + dano + " de dano. Energia de " + primeiro.getNome() + ": " + primeiro.getEnergia());

                            if (primeiro.getEnergia() == 0) {
                                break;
                            }
                        }

                        System.out.println("--------------------------");
                        System.out.println("=== Resultado final ===");

                        if (primeiro.getEnergia() == 0) {
                            segundo.registrarVitoria();
                            primeiro.registrarDerrota();
                            System.out.println("--------------------------");
                            System.out.println("Vencedor: " + segundo.getNome());

                        } else if (segundo.getEnergia() == 0) {
                            primeiro.registrarVitoria();
                            segundo.registrarDerrota();
                            System.out.println("--------------------------");
                            System.out.println("Vencedor: " + primeiro.getNome());

                        } else if (primeiro.getEnergia() > segundo.getEnergia()) {
                            primeiro.registrarVitoria();
                            segundo.registrarDerrota();
                            System.out.println("--------------------------");
                            System.out.println("Vencedor: " + primeiro.getNome());

                        } else if (segundo.getEnergia() > primeiro.getEnergia()) {
                            segundo.registrarVitoria();
                            primeiro.registrarDerrota();
                            System.out.println("--------------------------");
                            System.out.println("Vencedor: " + segundo.getNome());

                        } else {
                            primeiro.registrarEmpate();
                            segundo.registrarEmpate();
                            System.out.println("--------------------------");
                            System.out.println("Empate!");
                        }

                        System.out.println("--------------------------");
                        System.out.println(primeiro.getNome() + " - Energia: " + primeiro.getEnergia() + " | Pontos: " + primeiro.getPontos());
                        System.out.println(segundo.getNome() + " - Energia: " + segundo.getEnergia() + " | Pontos: " + segundo.getPontos());
                    }
                    break;

                case 5:
                    System.out.println("--------------------------");
                    System.out.println("Digite o código do Robo: ");
                    int codigoRec = buscarOperacao(s);
                    Robo recuperando = buscarRobo(robos, codigoRec);

                    if (recuperando == null) {
                        System.out.println("--------------------------");
                        System.out.println("Não existe robô com o código " + codigoRec + ".");
                    } else {
                        System.out.println("--------------------------");
                        System.out.println("Digite a quantidade de energia a recuperar: ");
                        int quantidade = buscarOperacao(s);
                        int custo = quantidade / 10;

                        if (recuperando.recuperarEnergia(quantidade)) {
                            System.out.println("--------------------------");
                            System.out.println("Energia recuperada");
                            System.out.println(recuperando.getNome() + " - Energia: " + recuperando.getEnergia() + " | Pontos: " + recuperando.getPontos());
                        } else if (quantidade == 0 || quantidade % 10 != 0) {
                            System.out.println("--------------------------");
                            System.out.println("A quantidade deve ser positiva e múltipla de 10.");
                        } else if (custo > recuperando.getPontos()) {
                            System.out.println("--------------------------");
                            System.out.println("Pontos insuficientes. Custo: " + custo + " | Pontos do robô: " + recuperando.getPontos());
                        } else {
                            System.out.println("--------------------------");
                            System.out.println("A energia final não pode ultrapassar 100.");
                        }
                    }
                    break;

                default:
                    System.out.println("--------------------------");
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
                System.out.println("--------------------------");
                System.out.println("Operação inválida");
                System.out.println("Digite novamente a informação!");
                opt = -1;
            }
        } while (opt < 0);

        return opt;
    }

    public static Robo buscarRobo(ArrayList<Robo> robos, int codigo) {
        for (Robo r : robos) {
            if (r.getCodigo() == codigo) {
                return r;
            }
        }
        return null;
    }
}