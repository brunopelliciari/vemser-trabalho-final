package view;

import exceptions.BancoDeDadosException;
import exceptions.DatasInvalidasException;
import model.*;
import repository.*;
import service.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BancoDeDadosException, DatasInvalidasException {
        Scanner scanner = new Scanner(System.in);
        CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();
        VeiculoService veiculoService = new VeiculoService();
        FuncionarioService funcionarioService = new FuncionarioService();
        ContatoService contatoService = new ContatoService();
        EnderecoService enderecoService = new EnderecoService();
        ClienteService clienteService = new ClienteService();
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
                                    Cliente cliente = new Cliente();
                                    System.out.println("Digite o nome do cliente");
                                    cliente.setNome(scanner.nextLine());
                                    System.out.println("Digite o cpf do cliente");
                                    cliente.setCpf(scanner.nextLine());
                                    String[] aux = new String[2];
                                    System.out.println("Digite o telefone de contato do cliente");
                                    aux[0] = scanner.nextLine();
                                    System.out.println("Digite o email de contato do cliente");
                                    aux[1] = scanner.nextLine();
                                    cliente.setContato(new Contato(aux[0], aux[1]));
                                    aux = new String[7];
                                    System.out.println("Digite a rua do cliente");
                                    aux[0] = scanner.nextLine();
                                    System.out.println("Digite o número do endereço do cliente");
                                    aux[1] = scanner.nextLine();
                                    System.out.println("Digite o bairro do cliente");
                                    aux[2] = scanner.nextLine();
                                    System.out.println("Digite a cidade do cliente");
                                    aux[3] = scanner.nextLine();
                                    System.out.println("Digite o estado de residência do cliente");
                                    aux[4] = scanner.nextLine();
                                    System.out.println("Digite o CEP do cliente");
                                    aux[5] = scanner.nextLine();
                                    System.out.println("Digite o complemento do cliente");
                                    aux[6] = scanner.nextLine();
                                    cliente.setEndereco(new Endereco(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5],
                                            aux[6]));
                                    enderecoService.adicionarEndereco(cliente.getEndereco());
                                    cliente.getEndereco().setId_endereco(enderecoService.retornarId());
                                    contatoService.adicionarContato(cliente.getContato());
                                    cliente.getContato().setId_contato(contatoService.retornarId());
                                    clienteService.adicionarCliente(cliente);
                                    break;
                                case 2:
                                    clienteService.listar();
                                    break;
                                case 3:
                                    System.out.println("Qual cliente você deseja editar? Digite 999 para voltar ao menu anterior\n");
                                    clienteService.listar();
                                    int index = scanner.nextInt();
                                    if (index == 999) {
                                        break;
                                    }
                                    scanner.nextLine();

                                    Cliente novoCliente = new Cliente();
                                    System.out.println("Digite o novo nome do cliente");
                                    novoCliente.setNome(scanner.nextLine());
                                    System.out.println("Digite o novo cpf do cliente");
                                    novoCliente.setCpf(scanner.nextLine());
                                    System.out.println("Deseja editar o contato deste cliente?");
                                    aux = new String[2];
                                    System.out.println("Digite o telefone de contato do cliente");
                                    aux[0] = scanner.nextLine();
                                    System.out.println("Digite o email de contato do cliente");
                                    aux[1] = scanner.nextLine();
                                    novoCliente.setContato(new Contato(aux[0], aux[1]));
                                    novoCliente.getContato().setId_contato(clienteService.retornarIdContato(index));
                                    contatoService.editar(clienteService.retornarIdContato(index), novoCliente.getContato());
                                    aux = new String[7];
                                    System.out.println("Digite a rua do cliente");
                                    aux[0] = scanner.nextLine();
                                    System.out.println("Digite o numero da endereço do cliente");
                                    aux[1] = scanner.nextLine();
                                    System.out.println("Digite o bairro do cliente");
                                    aux[2] = scanner.nextLine();
                                    System.out.println("Digite a cidade do cliente");
                                    aux[3] = scanner.nextLine();
                                    System.out.println("Digite o estado de residência do cliente");
                                    aux[4] = scanner.nextLine();
                                    System.out.println("Digite o CEP do cliente");
                                    aux[5] = scanner.nextLine();
                                    System.out.println("Digite o complemento do cliente");
                                    aux[6] = scanner.nextLine();
                                    novoCliente.setEndereco(new Endereco(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5],
                                            aux[6]));
                                    novoCliente.getEndereco().setId_endereco(clienteService.retornarIdEndereco(index));
                                    enderecoService.editar(clienteService.retornarIdEndereco(index), novoCliente.getEndereco());
                                    clienteService.editar(index, novoCliente);
                                    break;
                                case 4:
                                    System.out.println("Qual cliente você deseja excluir?Digite 999 para voltar\n");
                                    clienteService.listar();
                                    int id = scanner.nextInt();
                                    if (id == 999) {
                                        break;
                                    }
                                    scanner.nextLine();
                                    clienteService.remover(id);
                                    break;
                                case 5:
                                    System.out.println("Excluindo endereços ociosos. \n");
                                    enderecoService.listarSemVinculo();
                                    enderecoService.removerEnderecosOciosos();
                                    enderecoService.listarSemVinculo();
                                    break;
                                case 6:
                                    System.out.println("Excluindo contatos ociosos\n");
                                    contatoService.listarSemVinculo();
                                    contatoService.removerContatosOciosos();
                                    contatoService.listarSemVinculo();
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
                                    boolean teste = false;
                                    LocalDate dataLocacao = null;
                                    LocalDate dataDevolucao = null;
                                    do {
                                        try {
                                            System.out.print("Digite a data da locação do veículo(dd/MM/yyyy): \n");
                                            dataLocacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                            System.out.print("Digite a data da devolucao do veículo(dd/MM/yyyy): \n");
                                            dataDevolucao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                            Locacao.validarDatasLocacao(dataLocacao, dataDevolucao);

                                            teste = true;
                                        }catch (DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);

                                    System.out.print("Digite a quilometragem  \n");
                                    double quilometragemAdicao = scanner.nextDouble();

                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    clienteService.listar();
                                    Cliente cliente = new ClienteRepository().getPorId(scanner.nextInt());

                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    veiculoService.listarVeiculosDisponiveis();
                                    Veiculo veiculo = new VeiculoRepository().getPorId(scanner.nextInt());
                                    veiculo.setQuilometragem(quilometragemAdicao);
                                    Duration d2 = Duration.between(dataLocacao.atStartOfDay(), dataDevolucao.atStartOfDay());
                                    double valorLocacao = d2.toDays() * veiculo.getValorLocacao();
                                    scanner.nextLine();
                                    String validade = "";
                                    teste = false;
                                    String numero = "";
                                    do {
                                        try {
                                            System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                            System.out.println("Informe o numero do cartão: ");
                                            numero = scanner.nextLine();

                                            System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                            validade = scanner.nextLine();

                                            CartaoCredito.validarDataValidadeCartao(validade);
                                            teste = true;
                                        } catch (DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);

                                    System.out.println("Informe o limite do cartão: ");
                                    double limite = scanner.nextDouble();

                                    System.out.println("Bandeira Cartão 1- MasterCard 2-Visa");
                                    int bandeiraCartao = scanner.nextInt();

                                    CartaoCredito cartaoCredito = null;
                                    if (bandeiraCartao == 1) {
                                        cartaoCredito = new CartaoCredito(numero, BandeiraCartao.MASTERCARD, validade, limite);

                                    } else {
                                        cartaoCredito = new CartaoCredito(numero, BandeiraCartao.VISA, validade, limite);
                                    }
                                    CartaoCreditoRepository cartaoCreditoRepository = new CartaoCreditoRepository();
                                    CartaoCredito cartaoAdicionado = cartaoCreditoRepository.adicionar(cartaoCredito);

                                    System.out.print("Digite o id de um funcionário cadastrado: \n");
                                    funcionarioService.listarFuncionarios();
                                    Funcionario funcionario = new FuncionarioRepository().getPorId(scanner.nextInt());

                                    veiculo.alterarDisponibilidadeVeiculo();
                                    Locacao locacao = new Locacao(dataLocacao, dataDevolucao, valorLocacao, cliente, veiculo, cartaoAdicionado, funcionario);
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
                                    teste = false;
                                    dataLocacao = null;
                                    dataDevolucao = null;
                                    do {
                                        try {
                                            System.out.print("Digite a data da locação do veículo(dd/MM/yyyy): \n");
                                            dataLocacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                            System.out.print("Digite a data da devolucao do veículo(dd/MM/yyyy): \n");
                                            dataDevolucao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                                            Locacao.validarDatasLocacao(dataLocacao, dataDevolucao);

                                            teste = true;
                                        }catch (DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);

                                    System.out.print("Digite a quilometragem  \n");
                                    double quilometragemAdicaoEdicao = scanner.nextDouble();

                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    clienteService.listar();
                                    Cliente idCliente = new ClienteRepository().getPorId(scanner.nextInt());

                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    veiculoService.listarVeiculosDisponiveis();
                                    Veiculo idVeiculo = new VeiculoRepository().getPorId(scanner.nextInt());
                                    idVeiculo.setQuilometragem(idVeiculo.getQuilometragem() + quilometragemAdicaoEdicao);
                                    Duration d3 = Duration.between(dataLocacao.atStartOfDay(), dataDevolucao.atStartOfDay());
                                    double valorLocacaoEdicao = d3.toDays() * idVeiculo.getValorLocacao();

                                    System.out.print("Digite o id de um funcionário cadastrado: \n");
                                    funcionarioService.listarFuncionarios();
                                    Funcionario idFuncionario = new FuncionarioRepository().getPorId(scanner.nextInt());
                                    scanner.nextLine();
                                    validade = "";
                                    teste = false;
                                    numero = "";
                                    do {
                                        try {
                                            System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                            System.out.println("Informe o numero do cartão: ");
                                            numero = scanner.nextLine();

                                            System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                            validade = scanner.nextLine();

                                            CartaoCredito.validarDataValidadeCartao(validade);
                                            teste = true;
                                        } catch (DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);

                                    System.out.println("Informe o limite do cartão: ");
                                    double limite1 = scanner.nextDouble();

                                    System.out.println("Bandeira Cartão 1- MasterCard 2-Visa");
                                    int bandeiraCartao1 = scanner.nextInt();

                                    CartaoCredito cartaoCredito1 = null;
                                    if (bandeiraCartao1 == 1) {
                                        cartaoCredito1 = new CartaoCredito(numero, BandeiraCartao.MASTERCARD, validade, limite1);

                                    } else {
                                        cartaoCredito1 = new CartaoCredito(numero, BandeiraCartao.VISA, validade, limite1);
                                    }
                                    System.out.println("Informe o id do cartão: ");
                                    cartaoCreditoService.listarCartoes();
                                    CartaoCreditoRepository cartaoCreditoRepository1 = new CartaoCreditoRepository();
                                    CartaoCredito cartaoAdicionado1 = cartaoCreditoRepository1.adicionar(cartaoCredito1);
                                    idVeiculo.alterarDisponibilidadeVeiculo();
                                    Locacao locacao1 = new Locacao(dataLocacao, dataDevolucao, valorLocacaoEdicao, idCliente, idVeiculo, cartaoAdicionado1, idFuncionario);
                                    locacaoService.editar(index, locacao1);
                                    break;
                                case 4:
                                    System.out.println("Qual registro de locação você deseja excluir? Digite 999 para voltar ");
                                    locacaoService.listar();
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
                                    int index = scanner.nextInt();
                                    if (index == 999) {
                                        break;
                                    }
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
