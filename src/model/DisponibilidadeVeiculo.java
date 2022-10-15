package model;

public enum DisponibilidadeVeiculo {
    ALUGADO(1),
    DISPONIVEL(2);

    private Integer disponibilidade;

    DisponibilidadeVeiculo(Integer disponibilidade){
        this.disponibilidade = disponibilidade;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }
}


