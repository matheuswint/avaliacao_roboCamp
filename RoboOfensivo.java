public class RoboOfensivo extends Robo {

    RoboOfensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Ofensivo";
    }

    @Override
    public int getAtaqueEfetivo() {
        return getAtaque() + 5;
    }
}