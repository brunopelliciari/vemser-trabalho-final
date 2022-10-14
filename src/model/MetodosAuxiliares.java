package model;

import exceptions.DatasInvalidasException;
import service.ClienteManipulacao;
import service.FuncionarioManipulacao;
import service.LocacaoManipulacao;
import service.VeiculoManipulacao;

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
        System.out.println("Digite 2 para listar cliente disponíveis");
        System.out.println("Digite 3 para editar um cliente disponível");
        System.out.println("Digite 4 para excluir um cliente");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void menuLocacao(){
        System.out.println("Digite 1 para registrar uma locação");
        System.out.println("Digite 2 para listar locação realizadas");
        System.out.println("Digite 3 para editar um registro de locação");
        System.out.println("Digite 4 para excluir um registro de locação");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static Locacao retornarLocacaoAPartirDeListaDeStrings(String[] valores, ClienteManipulacao cliente, VeiculoManipulacao veiculo, FuncionarioManipulacao funcionario) throws DatasInvalidasException {
        LocalDate dataLocacao = (LocalDate.parse(valores[0].trim().replaceAll("-","/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        LocalDate dataDevolucao = (LocalDate.parse(valores[1].trim().replaceAll("-","/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        BandeiraCartao b = BandeiraCartao.valueOf(valores[7].trim().replaceAll("bandeira=",""));
        Double limite = Double.parseDouble(valores[9].replaceAll("limite=","").replace("]",""));
        CartaoCredito cartao = new CartaoCredito(valores[6].replaceAll("numero=",""), b,
                valores[8].trim().replaceAll("validade=",""), limite);
        return new Locacao(dataLocacao, dataDevolucao, cliente.retornarClientesPorCPF(valores[3].trim()),
                veiculo.retornarVeiculoPorPlaca(valores[5].trim()), cartao, funcionario.retornarFuncionariosPorCPF(valores[11].trim()));
    }

    public static Cliente retornarClienteAPartirDeListaDeStrings(String [] valores){
        Contato contato = new Contato(valores[2].replaceAll("telefone=",""), valores[3].replaceAll("email=",""));
        Endereco endereco = new Endereco(valores[4].replaceAll("rua=","")
        ,valores[5].replaceAll("numero=",""),valores[6].replaceAll("bairro=","")
                ,valores[7].replaceAll("cidade=",""),valores[8].replaceAll("estado=","")
        ,valores[9].replaceAll("cep=",""),valores[10].replaceAll("complemento=",""));
        return new Cliente(valores[0].trim(), valores[1].trim(), contato, endereco);
    }

    public static Funcionario retornarFuncionarioAPartirDeListaDeStrings(String[] valores){
            int matricula = Integer.parseInt(valores[2].trim());
            return new Funcionario(valores[0].trim(), valores[1].trim(), matricula);
        }


    public static Veiculo retornarVeiculoAPartirDeListaDeStrings(String[] valores){
            int ano = Integer.parseInt(valores[3].strip());
            double km = Double.parseDouble(valores[4].strip());
            double preco = Double.parseDouble(valores[5].strip());
            Disponibilidade disp = Disponibilidade.valueOf(valores[6].trim().replaceAll("Disponibilidade.", ""));
            return new Veiculo(valores[0].trim().replace("[",""), valores[1].trim(), valores[2].trim(), ano, km, preco, disp, valores[7].trim().replace("]",""));
    }

    public static void salvarFuncionarios(FuncionarioManipulacao v){
        try{
            PrintWriter writer = new PrintWriter("registro-de-dados/funcionarios.txt");
            v.retornarLista().stream()
                    .forEach(funcionario -> writer.println(funcionario.impressaoConversora()));
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
    }

    public static void salvarClientes(ClienteManipulacao c){
        try{
            PrintWriter writer = new PrintWriter("registro-de-dados/clientes.txt");
            c.retornarLista().stream()
                    .forEach(cliente -> writer.println(cliente.impressaoConversora()));
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
    }

    public static void salvarLocacao(LocacaoManipulacao l){
        try{
            PrintWriter writer = new PrintWriter("registro-de-dados/locacoes.txt");
            l.retornarLista().stream()
                    .forEach(locacao -> writer.println(locacao.impressaoConversora()));
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println("Cliente ou veículo contido no registro de locação não está mais no sistema. Exclua diretamente do banco de dados." + e.getMessage());
        }
    }

    public static void salvarVeiculos(VeiculoManipulacao v){
        try{
            PrintWriter writer = new PrintWriter("registro-de-dados/veiculos.txt");
            v.retornarLista().stream()
                    .forEach(veiculo -> writer.println(veiculo.impressaoConversora()));
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
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
