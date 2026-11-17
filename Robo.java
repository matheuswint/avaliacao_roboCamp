public abstract class Robo {
    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energia;
    private int vitorias;
    private int derrotas;
    private int empate;
    private int pontos;

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

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getEnergia() {
        return energia;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpate() {
        return empate;
    }

    public int getPontos() {
        return pontos;
    }

    public abstract String getTipo();

    public int getAtaqueEfetivo() {
        return ataque;
    }

    public int ajustarDanoRecebido(int dano) {
        return dano;
    }

    public int calcularDano(Robo alvo, int rodada) {
        int dano = getAtaqueEfetivo() - alvo.getDefesa();
        if (dano < 5) {
            dano = 5;
        }
        if (rodada % 2 == 0) {
            dano += 5;
        }
        return alvo.ajustarDanoRecebido(dano);
    }

    public boolean estaDisponivel() {
        return energia >= 30;
    }

    public void receberDano(int dano) {
        energia = energia - dano;
        if (energia < 0) {
            energia = 0;
        }
    }

    public void registrarVitoria() {
        vitorias++;
        pontos = pontos + 3;
    }

    public void registrarDerrota() {
        derrotas++;
    }

    public void registrarEmpate() {
        empate++;
        pontos = pontos + 1;
    }

    public boolean recuperarEnergia(int quantidade) {
        if (quantidade <= 0 || quantidade % 10 != 0) {
            return false;
        }
        int custo = quantidade / 10;
        if (custo > pontos) {
            return false;
        }
        if (energia + quantidade > 100) {
            return false;
        }
        energia = energia + quantidade;
        pontos = pontos - custo;
        return true;
    }

    public void exibirDados() {
        System.out.println("--------------------------");
        System.out.println("Tipo: " + getTipo());
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Energia: " + energia);
        System.out.println("Vitorias: " + vitorias);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Empates: " + empate);
        System.out.println("Pontos: " + pontos);
        if (estaDisponivel()) {
            System.out.println("--------------------------");
            System.out.println("Disponivel");
        } else {
            System.out.println("--------------------------");
            System.out.println("Em recuperacao");
        }
        System.out.println();
    }
}