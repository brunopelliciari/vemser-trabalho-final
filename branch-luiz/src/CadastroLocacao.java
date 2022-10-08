//import java.util.ArrayList;
//import java.util.List;
//
//public class CadastroLocacao implements Cadastro<Locacao> {
//
//    private List<Locacao> locacao = new ArrayList<>();
//
//    @Override
//    public void realizarCadastro(Locacao locacao) {
//        this.locacao.add(locacao);
//    }
//
//    @Override
//    public void removerCadastro(Locacao locacao) {
//        this.locacao.remove(locacao);
//        System.out.println("Cadastro de locação removido!");
//    }
//
//    @Override
//    public void editarCadastro(Locacao locacao) {
//        locacao.setDataLocacao();
//        locacao.setDataDevolucao();
//        locacao.setQuilometragem();
//        locacao.setValorLocacao();
//        locacao.setDevolvido();
////        locacao.setCliente();
////        locacao.setVeiculo();
//    }
//
//    @Override
//    public void consultarCadastro(Locacao locacao) {
//        System.out.println("\nDados da Locação:"
//                + "\nData e Hora da Locação: "
//                + locacao.getDataLocacao()
//                + "\nData e Hora da devolução: "
//                + locacao.getDataDevolucao()
//                + "\nQuilometragem do veículo: "
//                + locacao.getQuilometragem()
//                + "\nValor da locação: R$"
//                + locacao.getValorLocacao());
////                + "\nDevolvido: "
////                + locacao.getDevolvido()
////                + "\nCliente: "
////                + locacao.getCliente()
////                + "\nVeículo: "
////                + locacao.getVeiculo());
//    }
//}