package view;

import exceptions.BancoDeDadosException;
import exceptions.DatasInvalidasException;
import model.*;
import repository.*;
import service.LocacaoService;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BancoDeDadosException, DatasInvalidasException {
        Scanner scanner = new Scanner(System.in);
        LocacaoService locacaoService = new LocacaoService();
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
                                    System.out.println("Digite o modelo do veiculo");
                                    System.out.println("Digite a cor do veiculo");
                                    System.out.println("Digite o ano do veiculo");
                                    System.out.println("Digite a quilometragem do veiculo");
                                    System.out.println("Digite o valor de locação diário do veiculo");
                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível");
                                    System.out.println("Digite a placa do veículo");
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    System.out.println("Qual veiculo você deseja editar? \n");

                                    System.out.println("Digite a nova marca do veiculo");
                                    System.out.println("Digite o novo modelo do veiculo");
                                    System.out.println("Digite a nova cor do veiculo");
                                    System.out.println("Digite o novo ano do veiculo");
                                    System.out.println("Digite a nova quilometragem do veiculo");
                                    System.out.println("Digite o novo valor de locação diária do veiculo");
                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível");
                                    System.out.println("Digite a nova placa do veículo");
                                    break;
                                case 4:
                                    System.out.println("Qual veiculo você deseja excluir?\n");
                                    break;
                                case 5:
                                    System.out.println("Qual o id do veículo que deseja alterar a disponibilidade?\n");
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
                                    if (index == 999) {
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
                                    if (id == 999) {
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
                                    System.out.print("Digite a data da locação do veículo(dd/MM/yyyy): \n");
                                    LocalDate dataLocacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                    System.out.print("Digite a data da devolucao do veículo(dd/MM/yyyy): \n");
                                    LocalDate dataDevolucao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                    System.out.print("Digite a quilometragem adicionada \n");
                                    double quilometragemAdicao = scanner.nextDouble();

                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    Cliente cliente = new ClienteRepository().getPorId(scanner.nextInt());

                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    Veiculo veiculo = new VeiculoRepository().getPorId(scanner.nextInt());
                                    veiculo.setQuilometragem(veiculo.getQuilometragem() + quilometragemAdicao);
                                    Duration d2 = Duration.between(dataLocacao.atStartOfDay(), dataDevolucao.atStartOfDay());
                                    double valorLocacao = d2.toDays() * veiculo.getValorLocacao();

                                    //System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                    //System.out.println("Informe a bandeira do cartão: 1- Visa 2- Mastercard ");
                                    //System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                    //System.out.println("Informe o limite do cartão: ");
                                    System.out.println("Informe o id do cartão: ");
                                    CartaoCredito cartaoCredito = new CartaoCredito(scanner.nextInt());

                                    System.out.print("Digite o id de um funcionário cadastrado: \n");
                                    Funcionario funcionario = new FuncionarioRepository().getPorId(scanner.nextInt());


                                    Locacao locacao = new Locacao(dataLocacao, dataDevolucao,valorLocacao,cliente, veiculo, cartaoCredito, funcionario);
                                    locacaoService.adicionarLocacao(locacao);
                                    break;
                                case 2:
                                    locacaoService.listar();
                                    break;
                                case 3:
                                    System.out.println("Qual registro de locação você deseja editar?Digite 999 para sair.\n");
                                    int index = scanner.nextInt();
                                    if (index == 999) {
                                        break;
                                    }
                                    scanner.nextLine();
                                    System.out.print("Digite a data da locação do veículo(dd/MM/yyyy): \n");
                                    LocalDate dl = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                    System.out.print("Digite a data da devolucao do veículo(dd/MM/yyyy): \n");
                                    LocalDate dd = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                    System.out.print("Digite a quilometragem adicionada \n");
                                    double quilometragemAdicaoEdicao = scanner.nextDouble();

                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    Cliente idCliente = new ClienteRepository().getPorId(scanner.nextInt());

                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    Veiculo idVeiculo = new VeiculoRepository().getPorId(scanner.nextInt());
                                    idVeiculo.setQuilometragem(idVeiculo.getQuilometragem() + quilometragemAdicaoEdicao);
                                    Duration d3 = Duration.between(dl.atStartOfDay(), dd.atStartOfDay());
                                    double valorLocacaoEdicao = d3.toDays() * idVeiculo.getValorLocacao();


                                    System.out.print("Digite o id de um funcionário cadastrado: \n");
                                    Funcionario idFuncionario = new FuncionarioRepository().getPorId(scanner.nextInt());

                                    //System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                    System.out.println("Informe o id do cartão: ");
                                    CartaoCredito idCartaoCredito = new CartaoCredito(scanner.nextInt());

                                    Locacao locacao1 = new Locacao( dl, dd,valorLocacaoEdicao,idCliente,idVeiculo,idCartaoCredito,idFuncionario);
                                    locacaoService.editar(index,locacao1);
                                    //System.out.println("Informe o numero do cartão: ");
                                    //System.out.println("Informe a bandeira do cartão: 1- Visa 2- Mastercard ");
                                    //System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                    //System.out.println("Informe o limite do cartão: ");
                                    break;
                                case 4:
                                    System.out.println("Qual registro de locação você deseja excluir? Digite 999 para voltar ");
                                    int id = scanner.nextInt();
                                    if (id == 999) {
                                        break;
                                    }
                                    locacaoService.remover(id);
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
                                    System.out.println("Digite o cpf do funcionario");
                                    System.out.println("Digite o número de matrícula do funcionario");
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    int index = scanner.nextInt();
                                    if (index == 999) {
                                        break;
                                    }
                                    scanner.nextLine();
                                    System.out.println("Digite o nova nome do funcionário");
                                    System.out.println("Digite o novo cpf do funcionário");
                                    System.out.println("Digite o novo número de matrícula do funcionário");
                                    break;
                                case 4:
                                    System.out.println("Qual funcionário você deseja excluir?");
                                    int id = scanner.nextInt();
                                    if (id == 999) {
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
                }
            } catch (InputMismatchException e) {
                System.err.println("Tipo de dado digitado está incorreto " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

}