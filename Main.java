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
            System.out.println("4. Realizar Combate");
            System.out.println("5. Recuperar Energia");
            System.out.println("Digite a operação: ");
            opt = buscarOperacao(s);
            switch (opt) {
                case 0:

                    System.out.println("Adeus, arrogantchi!");
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

                        } 
                        
                        else if (buscarRobo(robos, codigo) != null) {
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

                    System.out.println("Robô cadastrado");
                    break;

                case 2:

                    System.out.println("Digite o código do Robo: ");
                    int codigoBusca = buscarOperacao(s);
                    Robo encontrado = buscarRobo(robos, codigoBusca);

                    if (encontrado == null) {
                        System.out.println("Não existe robô com o código " + codigoBusca + ".");

                    } 
                    
                    else {
                        encontrado.exibirDados();
                    }
                    break;

                case 3:

                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robô cadastrado ainda.");
                    } 

                    else {
                        for (Robo r : robos) {
                            r.exibirDados();
                        }
                    }
                    break;

                case 4:

                    System.out.println("Digite o código do primeiro robô: ");
                    int codigo1 = buscarOperacao(s);

                    System.out.println("Digite o código do segundo robô: ");
                    int codigo2 = buscarOperacao(s);

                    Robo robo1 = buscarRobo(robos, codigo1);
                    Robo robo2 = buscarRobo(robos, codigo2);

                    if (codigo1 == codigo2) {
                        System.out.println("Os robôs do combate devem ser diferentes.");

                    } 

                    else if (robo1 == null || robo2 == null) {
                        System.out.println("Um dos códigos informados não existe.");

                    } 

                    else if (robo1.energia < 30 || robo2.energia < 30) {
                        System.out.println("Os dois robôs precisam ter pelo menos 30 de energia.");
                    } 

                    else {
                        Robo primeiro;
                        Robo segundo;

                        if (robo1.pontos < robo2.pontos) {
                            primeiro = robo1;
                            segundo = robo2;

                        } 

                        else if (robo2.pontos < robo1.pontos) {
                            primeiro = robo2;
                            segundo = robo1;

                        } 

                        else if (robo1.codigo < robo2.codigo) {
                            primeiro = robo1;
                            segundo = robo2;

                        } 

                        else {
                            primeiro = robo2;
                            segundo = robo1;
                        }

                        System.out.println("Começa atacando: " + primeiro.nome);

                        int dano;

                        for (int rodada = 1; rodada <= 5; rodada++) {
                            System.out.println("--- Rodada " + rodada + " ---");
                            dano = primeiro.calcularDano(segundo, rodada);
                            segundo.receberDano(dano);

                            System.out.println(primeiro.nome + " ataca " + segundo.nome + " e causa " + dano + " de dano. Energia de " + segundo.nome + ": " + segundo.energia);

                            if (segundo.energia == 0) {
                                break;
                            }

                            dano = segundo.calcularDano(primeiro, rodada);
                            primeiro.receberDano(dano);

                            System.out.println(segundo.nome + " ataca " + primeiro.nome + " e causa " + dano + " de dano. Energia de " + primeiro.nome + ": " + primeiro.energia);

                            if (primeiro.energia == 0) {
                                break;
                            }
                        }

                        System.out.println("=== Resultado final ===");

                        if (primeiro.energia == 0) {
                            segundo.registrarVitoria();
                            primeiro.registrarDerrota();

                            System.out.println("Vencedor: " + segundo.nome);
                        } 

                        else if (segundo.energia == 0) {
                            primeiro.registrarVitoria();
                            segundo.registrarDerrota();

                            System.out.println("Vencedor: " + primeiro.nome);
                        } 

                        else if (primeiro.energia > segundo.energia) {
                            primeiro.registrarVitoria();
                            segundo.registrarDerrota();

                            System.out.println("Vencedor: " + primeiro.nome);
                        } 

                        else if (segundo.energia > primeiro.energia) {
                            segundo.registrarVitoria();
                            primeiro.registrarDerrota();

                            System.out.println("Vencedor: " + segundo.nome);
                        } 

                        else {
                            primeiro.registrarEmpate();
                            segundo.registrarEmpate();

                            System.out.println("Empate!");
                        }
                        System.out.println(primeiro.nome + " - Energia: " + primeiro.energia + " | Pontos: " + primeiro.pontos);
                        System.out.println(segundo.nome + " - Energia: " + segundo.energia + " | Pontos: " + segundo.pontos);
                    }
                    break;

                case 5:

                    System.out.println("Digite o código do Robo: ");
                    int codigoRec = buscarOperacao(s);
                    Robo recuperando = buscarRobo(robos, codigoRec);

                    if (recuperando == null) {
                        System.out.println("Não existe robô com o código " + codigoRec + ".");

                    } 
                    
                    else {
                        System.out.println("Digite a quantidade de energia a recuperar: ");
                        int quantidade = buscarOperacao(s);
                        int custo = quantidade / 10;

                        if (quantidade == 0 || quantidade % 10 != 0) {
                            System.out.println("A quantidade deve ser positiva e múltipla de 10.");
                        } 

                        else if (custo > recuperando.pontos) {
                            System.out.println("Pontos insuficientes. Custo: " + custo + " | Pontos do robô: " + recuperando.pontos);
                        } 
                        
                        else if (recuperando.energia + quantidade > 100) {
                            System.out.println("A energia final não pode ultrapassar 100.");
                        } 
                        
                        else {
                            recuperando.recuperarEnergia(quantidade);
                            System.out.println("Energia recuperada");
                            System.out.println(recuperando.nome + " - Energia: " + recuperando.energia + " | Pontos: " + recuperando.pontos);
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