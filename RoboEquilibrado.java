public class RoboEquilibrado extends Robo {

    RoboEquilibrado(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Equilibrado";
    }
}