package view;

public class ExibeMenu {

    public static void menuInicial(){
        System.out.println("Digite 1 para entrar no menu de veículos");
        System.out.println("Digite 2 para entrar no menu de clientes");
        System.out.println("Digite 3 para entrar no menu de locação");
        System.out.println("Digite 4 para entrar no menu de funcionários");
        System.out.println("Digite 9 para sair");
    }
    public static void menuVeiculos(){
        System.out.println("Digite 1 para registrar um veículo");
        System.out.println("Digite 2 para listar veículos disponíveis");
        System.out.println("Digite 3 para editar um veículo disponível");
        System.out.println("Digite 4 para excluir um veículo");
        System.out.println("Digite 5 para alterar a disponibilidade de um veículo cadastrado");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }
    public static void menuFuncionarios(){
        System.out.println("Digite 1 para registrar um funcionário");
        System.out.println("Digite 2 para listar funcionários disponíveis");
        System.out.println("Digite 3 para editar um funcionário disponível");
        System.out.println("Digite 4 para excluir um funcionário");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void menuClientes(){
        System.out.println("Digite 1 para registrar um cliente");
        System.out.println("Digite 2 para listar clientes disponíveis");
        System.out.println("Digite 3 para editar um cliente disponível");
        System.out.println("Digite 4 para excluir um cliente");
        System.out.println("Digite 5 para excluir endereços ociosos");
        System.out.println("Digite 6 para excluir contatos ociosos");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void menuLocacao(){
        System.out.println("Digite 1 para registrar uma locação");
        System.out.println("Digite 2 para listar locações realizadas");
        System.out.println("Digite 3 para editar um registro de locação");
        System.out.println("Digite 4 para excluir um registro de locação");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }
}
