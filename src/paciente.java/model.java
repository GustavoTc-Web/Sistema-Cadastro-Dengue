public class RegiaoDengue {
    private String nome;
    private int casos;
    private int obitos;

    public RegiaoDengue(String nome, int casos, int obitos) {
        this.nome = nome;
        this.casos = casos;
        this.obitos = obitos;
    }

    public String getNome() {
        return nome;
    }

    public int getCasos() {
        return casos;
    }

    public int getObitos() {
        return obitos;
    }

    public double getTaxaMortalidade() {
        if (casos > 0) {
            return (double) obitos / casos * 100;
        } else {
            return 0.0;
        }
    }
}
