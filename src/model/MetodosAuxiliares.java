package model;

import exceptions.DatasInvalidasException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MetodosAuxiliares {

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
        System.out.println("Digite 5 para excluir enderecos ociosos");
        System.out.println("Digite 6 para excluir contatos ociosos");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void menuLocacao(){
        System.out.println("Digite 1 para registrar uma locação");
        System.out.println("Digite 2 para listar locação realizadas");
        System.out.println("Digite 3 para editar um registro de locação");
        System.out.println("Digite 4 para excluir um registro de locação");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void validarDatasLocacao(LocalDate dl, LocalDate dd) throws DatasInvalidasException{
        if(dl.isBefore(LocalDate.now())) {
            throw new DatasInvalidasException("A data da locação não pode ser inferior a data atual. Tente novamente!");
        }else if(dd.isBefore(dl)) {
            throw new DatasInvalidasException("A data da devolução não pode ser inferior a data de locação. Tente novamente!");
        }
    }

    public static void validarDataValidadeCartao(String validade) throws DatasInvalidasException{
        if(Integer.parseInt(validade.substring(0,2)) > 12){
            throw new DatasInvalidasException("Mês inválido. Tente novamente!");
        }else if(Integer.parseInt(validade.substring(3)) < LocalDate.now().getYear()){
            throw new DatasInvalidasException("Cartão inválido, Data de vencimento do cartão inferior ao ano atual. Tente outro cartão!");
        }else if(Integer.parseInt(validade.substring(3)) == LocalDate.now().getYear()) {
            if(Integer.parseInt(validade.substring(0,2)) <= LocalDate.now().getMonthValue()) {
                throw new DatasInvalidasException("Cartão inválido, Data de vencimento do cartão inferior ao ano atual. Tente outro cartão!");
            }
        }
    }
}
