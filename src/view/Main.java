package view;

import exceptions.BancoDeDadosException;
import model.Cliente;
import model.Contato;
import model.Endereco;
import service.ClienteService;
import service.ContatoService;
import service.EnderecoService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ContatoService contatoService = new ContatoService();
        EnderecoService enderecoService = new EnderecoService();
        ClienteService clienteService = new ClienteService();

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
                                    if(index==999){
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
                                    if(id==999){
                                        break;
                                    }
                                    scanner.nextLine();
                                    clienteService.remover(id);
                                    break;
                                case 5:
                                    System.out.println("Qual endereço você deseja excluir?Digite 999 para voltar\n");
                                    enderecoService.listarSemVinculo();
                                    enderecoService.removerEnderecosOciosos();
                                    enderecoService.listarSemVinculo();
                                    break;
                                case 6:
                                    System.out.println("Qual contato você deseja excluir?Digite 999 para voltar\n");
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
                                    System.out.println("Digite o cpf do funcionario");
                                    System.out.println("Digite o número de matrícula do funcionario");
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    int index = scanner.nextInt();
                                    if(index==999){
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
                }
            }
            catch(InputMismatchException e){
                System.err.println("Tipo de dado digitado está incorreto " + e.getMessage());
                scanner.nextLine();
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }

}
