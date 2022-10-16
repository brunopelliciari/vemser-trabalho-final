package view;

import exceptions.DatasInvalidasException;
import model.*;
import service.FuncionarioService;
import service.VeiculoService;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        VeiculoService veiculoService = new VeiculoService();
        FuncionarioService funcionarioService = new FuncionarioService();

        int primeiroMenu = 0;
        int segundoMenu;
        int terceiroMenu;
        int quartoMenu;
        int quintoMenu;
        while (primeiroMenu != 9) {
            try {
                ExibeMenu.menuInicial();
                primeiroMenu = scanner.nextInt();
                scanner.nextLine();
                switch (primeiroMenu) {
                    case 1 -> {
                        segundoMenu = 0;
                        while (segundoMenu != 9) {
                            ExibeMenu.menuVeiculos();
                            segundoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (segundoMenu) {
                                case 1:
                                    System.out.println("Digite a marca do veiculo");
                                    String marcaVeiculo = scanner.nextLine();
                                    System.out.println("Digite o modelo do veiculo");
                                    String maodeloVeiculo = scanner.nextLine();
                                    System.out.println("Digite a cor do veiculo");
                                    String corVeiculo = scanner.nextLine();
                                    System.out.println("Digite o ano do veiculo");
                                    int anoVeiculo = scanner.nextInt();
                                    System.out.println("Digite a quilometragem do veiculo");
                                    double quilometragemVeiculo = scanner.nextDouble();
                                    System.out.println("Digite o valor de locação diário do veiculo");
                                    double valorLocacaoVeiculo = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Digite a placa do veículo");
                                    String placaVeiculo = scanner.nextLine();

                                    Veiculo veiculo = new Veiculo(marcaVeiculo, maodeloVeiculo, corVeiculo, anoVeiculo
                                    , quilometragemVeiculo, valorLocacaoVeiculo, placaVeiculo);

                                    veiculoService.adicionarVeiculo(veiculo);

                                    break;
                                case 2:
                                    veiculoService.listarVeiculos();
                                    break;
                                case 3:
                                    Veiculo novoVeiculo = new Veiculo();
                                    System.out.println("Qual veiculo você deseja editar? \n");
                                    veiculoService.listarVeiculos();
                                    int idVeiculoParaEditar = scanner.nextInt();
                                    scanner.nextLine();

                                    System.out.println("Digite a nova marca do veiculo");
                                    novoVeiculo.setMarca(scanner.nextLine());
                                    System.out.println("Digite o novo modelo do veiculo");
                                    novoVeiculo.setModelo(scanner.nextLine());
                                    System.out.println("Digite a nova cor do veiculo");
                                    novoVeiculo.setCor(scanner.nextLine());
                                    System.out.println("Digite o novo ano do veiculo");
                                    novoVeiculo.setAno(scanner.nextInt());
                                    System.out.println("Digite a nova quilometragem do veiculo");
                                    novoVeiculo.setQuilometragem(scanner.nextDouble());
                                    System.out.println("Digite o novo valor de locação diária do veiculo");
                                    novoVeiculo.setValorLocacao(scanner.nextDouble());
                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível)");
                                    int disponibilidadeVeiculo = scanner.nextInt();
                                    novoVeiculo.setDisponibilidadeVeiculo(DisponibilidadeVeiculo.values()[disponibilidadeVeiculo]);
                                    scanner.nextLine();
                                    System.out.println("Digite a nova placa do veículo");
                                    novoVeiculo.setPlaca(scanner.nextLine());

                                    veiculoService.editarVeiculo(idVeiculoParaEditar, novoVeiculo);

                                    break;
                                case 4:
                                    System.out.println("Qual veiculo você deseja excluir?\n");
                                    veiculoService.listarVeiculos();
                                    int idVeiculoParaRemover = scanner.nextInt();

                                    veiculoService.removerVeiculo(idVeiculoParaRemover);

                                    break;
                                case 5:
                                    System.out.println("Qual o id do veículo que deseja alterar a disponibilidade?\n");
                                    veiculoService.listarVeiculos();
                                    int idVeiculoAlterarDisponibilidade = scanner.nextInt();

                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    }
                    case 2 -> {
                        terceiroMenu = 0;
                        while (terceiroMenu != 9) {
                            ExibeMenu.menuClientes();
                            terceiroMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (terceiroMenu) {
                                case 1:
                                    System.out.println("Digite o nome do cliente");
                                    System.out.println("Digite o cpf do cliente");
                                    System.out.println("Digite o telefone de contato do cliente");
                                    System.out.println("Digite o email de contato do cliente");
                                    System.out.println("Digite a rua do cliente");
                                    System.out.println("Digite o número do endereço do cliente");
                                    System.out.println("Digite o bairro do cliente");
                                    System.out.println("Digite a cidade do cliente");
                                    System.out.println("Digite o estado de residência do cliente");
                                    System.out.println("Digite o CEP do cliente");
                                    System.out.println("Digite o complemento do cliente");
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    System.out.println("Qual cliente você deseja editar?\n");
                                    int index = scanner.nextInt();
                                    if(index==999){
                                        break;
                                    }
                                    scanner.nextLine();
                                    System.out.println("Digite o novo nome do cliente");
                                    System.out.println("Digite o novo cpf do cliente");
                                    System.out.println("Digite o telefone de contato do cliente");
                                    System.out.println("Digite o email de contato do cliente");
                                    System.out.println("Digite a rua do cliente");
                                    System.out.println("Digite o numero da endereço do cliente");
                                    System.out.println("Digite o bairro do cliente");
                                    System.out.println("Digite a cidade do cliente");
                                    System.out.println("Digite o estado de residência do cliente");
                                    System.out.println("Digite o CEP do cliente");
                                    System.out.println("Digite o complemento do cliente");
                                    break;
                                case 4:
                                    System.out.println("Qual cliente você deseja excluir?Digite 999 para voltar\n");
                                    int id = scanner.nextInt();
                                    if(id==999){
                                        break;
                                    }
                                    scanner.nextLine();
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    }
                    case 3 -> {
                        quartoMenu = 0;
                        while (quartoMenu != 9) {
                            ExibeMenu.menuLocacao();
                            quartoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (quartoMenu) {
                                case 1:
                                    System.out.print("\nDigite a data da locação do veículo(dd/MM/yyyy): ");
                                    //LocalDate dataLocacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    System.out.print("\nDigite a data da devolucao do veículo(dd/MM/yyyy): ");
                                    //LocalDate dataDevolucao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                    System.out.println("Informe o numero do cartão: ");
                                    System.out.println("Informe a bandeira do cartão: 1- Visa 2- Mastercard ");
                                    System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                    System.out.println("Informe o limite do cartão: ");
                                    System.out.print("Digite o id de um funcionário cadastrado: \n");
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    System.out.println("Qual registro de locação você deseja editar?Digite 999 para sair.\n");
                                    int index = scanner.nextInt();
                                    if(index==999){
                                        break;
                                    }
                                    scanner.nextLine();
                                    System.out.print("\nDigite a data da locação do veículo(dd/MM/yyyy): ");
                                    //LocalDate dl = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    System.out.print("\nDigite a data da devolucao do veículo(dd/MM/yyyy): ");
                                    //LocalDate dd = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    System.out.print("Digite o id de um funcionário cadastrado: \n");
                                    System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                    System.out.println("Informe o numero do cartão: ");
                                    System.out.println("Informe a bandeira do cartão: 1- Visa 2- Mastercard ");
                                    System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                    System.out.println("Informe o limite do cartão: ");
                                    break;
                                case 4:
                                    System.out.println("Qual registro de locação você deseja excluir? Digite 999 para voltar.\n");
                                    int id = scanner.nextInt();
                                    if(id==999){
                                        break;
                                    }
                                    scanner.nextLine();
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    }
                    case 4 -> {
                        quintoMenu = 0;
                        while (quintoMenu != 9) {
                            ExibeMenu.menuFuncionarios();
                            quintoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (quintoMenu) {
                                case 1:
                                    System.out.println("Digite o nome do funcionário");
                                    String nomeFuncionario = scanner.nextLine();
                                    System.out.println("Digite o cpf do funcionario");
                                    String cpfFuncionario = scanner.nextLine();
                                    System.out.println("Digite o número de matrícula do funcionario");
                                    int matriculaFuncionario = scanner.nextInt();

                                    Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, matriculaFuncionario);

                                    funcionarioService.adicionarFuncionario(funcionario);

                                    break;
                                case 2:
                                    funcionarioService.listarFuncionarios();

                                    break;
                                case 3:
                                    Funcionario novoFuncionario = new Funcionario();
                                    System.out.println("Qual funcionário você deseja editar?");
                                    funcionarioService.listarFuncionarios();
                                    int idFuncionarioParaEditar = scanner.nextInt();
                                    scanner.nextLine();

                                    System.out.println("Digite o novo nome do funcionário");
                                    novoFuncionario.setNome(scanner.nextLine());
                                    System.out.println("Digite o novo cpf do funcionário");
                                    novoFuncionario.setCpf(scanner.nextLine());
                                    System.out.println("Digite o novo número de matrícula do funcionário");
                                    novoFuncionario.setMatricula(scanner.nextInt());

                                    funcionarioService.editarFuncionario(idFuncionarioParaEditar, novoFuncionario);

                                    break;
                                case 4:
                                    System.out.println("Qual funcionário você deseja excluir?");
                                    funcionarioService.listarFuncionarios();
                                    int idFuncionarioParaRemover = scanner.nextInt();

                                    funcionarioService.removerFuncionario(idFuncionarioParaRemover);

                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    }
                }
            }
            catch(InputMismatchException e){
                System.err.println("Tipo de dado digitado está incorreto " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

}
