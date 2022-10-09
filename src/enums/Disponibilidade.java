package enums;

public enum Disponibilidade {
    ALUGADO(1),
    DISPONIVEL(2);

    private int s;

    Disponibilidade(int s){
        this.s = s;
    }

    public int getS() {
        return s;
    }
}


