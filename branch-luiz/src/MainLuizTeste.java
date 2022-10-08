import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainLuizTeste {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CartaoCredito cartao = null;
        boolean teste = false;

        do {
            System.out.println("Cadastro do Cartão de crédito:");
            System.out.print("Digite o número do cartão de crédito: ");
            String numeroCartao = sc.nextLine();
            System.out.print("Digite a bandeira do cartão de crédito: ");
            String bandeiraCartao = sc.nextLine();
            try {
                System.out.print("Digite a data de validade do cartão de crédito (MM/yyyy): ");
                String diaValidade = "01/";
                LocalDate validadeCartao;
                validadeCartao = LocalDate.parse(diaValidade += sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                cartao = new CartaoCredito(numeroCartao, bandeiraCartao, validadeCartao);
                System.out.print("Digite o limite do cartão de crédito: ");
                cartao.setLimite(sc.nextDouble());
                teste = true;
            }catch (DatasInvalidasException e) {
                System.err.println(e.getMessage() + "A cartão está com a data de validade vencida. Tente outro cartão!");
            }finally {
                if(teste) {
                    System.out.println("Cartão de crédito cadastrado!");
                }
            }
        }while(!teste);

        cartao.consultarCartao();
        Locacao locacao = null;
        teste = false;

        do {
            try {
                System.out.print("Digite a data da locação do veículo: ");
                LocalDate dataLocacao = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.print("Digite a data da devolucao do veículo: ");
                LocalDate dataDevolucao = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                locacao = new Locacao(dataLocacao, dataDevolucao);
                teste = true;
            }catch (DatasInvalidasException e) {
                System.err.println(e.getMessage() + "A data de devolução não pode ser inferior a data de locacção. Tente novamente!");
            }finally {
                if(teste) {
                    System.out.println("Locação realizada!");
                }
            }
        }while(!teste);

        locacao.consultarLocacao();

        //DateTimeException // Exception de valor de meses maior que 12;
        // ValidacaoCartaoException // Exception se a data de validade do cartão for menor quer a data atual

//        Duration d2 = Duration.between(pastWeekLocalDateTime, nextWeekLocalDateTime);

//        System.out.println("d04 Dia = " + d04.getDayOfMonth());
//        System.out.println("d04 Mês = " + d04.getMonthValue());
//        System.out.println("d04 Ano = " + d04.getYear());
//
//        System.out.println("d05 Hora = " + d05.getHour());
//        System.out.println("d05 Minuto = " + d05.getMinute());
    }
}