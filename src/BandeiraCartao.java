public enum BandeiraCartao {
    VISA(1),
    MASTERCARD(2);

    private int s;

    BandeiraCartao(int s){
        this.s = s;
    }

    public int getS() {
        return s;
    }
}
