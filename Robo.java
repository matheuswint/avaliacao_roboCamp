public class Robo {
    public int codigo;
    public String nome;
    public int ataque;
    public int defesa;
    public int energia;
    public int vitorias;
    public int derrotas;
    public int empate;
    public int pontos;

    Robo(int codigo, String nome, int ataque, int defesa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empate = 0;
        this.pontos = 0;
    }

    int calcularDano(Robo adversario, int rodada) {
        int dano = ataque - adversario.defesa;
        if (dano < 5) {
            dano = 5;
        }
        if (rodada % 2 == 0) {
            dano += 5;
        }
        return dano;
    }

    void exibirDados() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Energia: " + energia);
        System.out.println("Vitorias: " + vitorias);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Empates: " + empate);
        System.out.println("Pontos: " + pontos);
        if (energia >= 30) {
            System.out.println("Disponivel");
        } else {
            System.out.println("Em recuperacao");
        }
        System.out.println();
    }

    void receberDano(int dano) {
        energia = energia - dano;
        if (energia < 0) {
            energia = 0;
        }
    }

    void registrarVitoria() {
        vitorias++;
        pontos = pontos + 3;
    }

    void registrarDerrota() {
        derrotas++;
    }

    void registrarEmpate() {
        empate++;
        pontos = pontos + 1;
    }

        void recuperarEnergia(int quantidade) {
        energia = energia + quantidade;
        pontos = pontos - quantidade / 10;
    }
}