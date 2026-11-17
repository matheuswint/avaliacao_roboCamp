public class RoboDefensivo extends Robo {

    RoboDefensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Defensivo";
    }

    @Override
    public int ajustarDanoRecebido(int dano) {
        int reduzido = dano - 3;
        if (reduzido < 2) {
            reduzido = 2;
        }
        return reduzido;
    }
}