import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTeste {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ClienteManipulacao clienteManipulacao = new ClienteManipulacao();
        FuncionarioManipulacao funcionarioManipulacao = new FuncionarioManipulacao();
        VeiculoManipulacao veiculoManipulacao = new VeiculoManipulacao();
        LocacaoManipulacao locacaoManipulacao = new LocacaoManipulacao();
        try {
            BufferedReader brVeiculos = new BufferedReader(new FileReader("veiculos.txt"));
            BufferedReader brFuncionarios = new BufferedReader(new FileReader("funcionarios.txt"));
            BufferedReader brClientes = new BufferedReader(new FileReader("clientes.txt"));
            String line = brVeiculos.readLine();
            String line2 = brFuncionarios.readLine();
            String line3 = brClientes.readLine();

            while (line != null) {
                String[] valores = line.split(",");
                veiculoManipulacao.realizarCadastro(MetodosAuxiliares.retornarVeiculoAPartirDeListaDeStrings(valores));
                line = brVeiculos.readLine();
            }

            while (line2 != null) {
                String[] valores = line2.split(",");
                funcionarioManipulacao.realizarCadastro(MetodosAuxiliares.retornarFuncionarioAPartirDeListaDeStrings(valores));
                line2 = brFuncionarios.readLine();
            }

            while (line3 != null) {
                String[] valores = line3.split(",");
                clienteManipulacao.realizarCadastro(MetodosAuxiliares.retornarClienteAPartirDeListaDeStrings(valores));
                line3 = brClientes.readLine();
            }
        }
        catch (FileNotFoundException e ){
            System.out.println("Arquivo não encontrado no caminho designado " + e.getMessage());
        }
        catch (IOException e ){
            System.out.println(e.getMessage());
        }
        int primeiroMenu = 0;
        int segundoMenu = 0;
        int terceiroMenu = 0;
        int quartoMenu = 0;
        int quintoMenu = 0;
        while (primeiroMenu != 9) {
            try {
                MetodosAuxiliares.menuInicial();
                primeiroMenu = scanner.nextInt();
                scanner.nextLine();
                switch (primeiroMenu) {
                    case 1:
                        segundoMenu = 0;
                        while (segundoMenu != 9) {
                            MetodosAuxiliares.menuVeiculos();
                            segundoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (segundoMenu) {
                                case 1:
                                    Veiculo veiculo = new Veiculo();
                                    System.out.println("Digite a marca do veiculo");
                                    veiculo.setMarca(scanner.nextLine());
                                    System.out.println("Digite o modelo do veiculo");
                                    veiculo.setModelo(scanner.nextLine());
                                    System.out.println("Digite a cor do veiculo");
                                    veiculo.setCor(scanner.nextLine());
                                    System.out.println("Digite o ano do veiculo");
                                    veiculo.setAno(scanner.nextInt());
                                    scanner.nextLine();
                                    System.out.println("Digite a quilometragem do veiculo");
                                    veiculo.setQuilometragem(scanner.nextDouble());
                                    scanner.nextLine();
                                    System.out.println("Digite o valor de locação diário do veiculo");
                                    veiculo.setValorLocacao(scanner.nextDouble());
                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível");
                                    veiculo.setDisponibilidade(scanner.nextInt());
                                    scanner.nextLine();
                                    System.out.println("Digite a placa do veículo");
                                    veiculo.setPlaca(scanner.nextLine());
                                    veiculoManipulacao.realizarCadastro(veiculo);
                                    MetodosAuxiliares.salvarVeiculos(veiculoManipulacao);
                                    break;
                                case 2:
                                    veiculoManipulacao.consultarCadastro();
                                    break;
                                case 3:
                                    System.out.println("Qual veiculo você deseja editar?");
                                    veiculoManipulacao.consultarCadastro();
                                    int index = scanner.nextInt();
                                    scanner.nextLine();

                                    Veiculo novoVeiculo = new Veiculo();
                                    System.out.println("Digite a nova marca do veiculo");
                                    novoVeiculo.setMarca(scanner.nextLine());
                                    System.out.println("Digite o novo modelo do veiculo");
                                    novoVeiculo.setModelo(scanner.nextLine());
                                    System.out.println("Digite a nova cor do veiculo");
                                    novoVeiculo.setCor(scanner.nextLine());
                                    System.out.println("Digite o novo ano do veiculo");
                                    novoVeiculo.setAno(scanner.nextInt());
                                    scanner.nextLine();
                                    System.out.println("Digite a nova quilometragem do veiculo");
                                    novoVeiculo.setQuilometragem(scanner.nextDouble());
                                    scanner.nextLine();
                                    System.out.println("Digite o novo valor de locação diária do veiculo");
                                    novoVeiculo.setValorLocacao(scanner.nextDouble());
                                    scanner.nextLine();
                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível");
                                    novoVeiculo.setDisponibilidade(scanner.nextInt());
                                    scanner.nextLine();
                                    System.out.println("Digite a nova placa do veículo");
                                    novoVeiculo.setPlaca(scanner.nextLine());
                                    veiculoManipulacao.editarCadastro(index, novoVeiculo);
                                    MetodosAuxiliares.salvarVeiculos(veiculoManipulacao);
                                    break;
                                case 4:
                                    System.out.println("Qual veiculo você deseja excluir?");
                                    veiculoManipulacao.consultarCadastro();
                                    int id = scanner.nextInt();
                                    veiculoManipulacao.removerCadastro(id);
                                    MetodosAuxiliares.salvarVeiculos(veiculoManipulacao);
                                    break;
                                case 5:
                                    System.out.println("Qual o id do veículo que deseja alterar a disponibilidade?");
                                    veiculoManipulacao.consultarCadastro();
                                    id = scanner.nextInt();
                                    veiculoManipulacao.retornarVeiculoPorIndice(id).alterarDisponibilidade();
                                    System.out.println(veiculoManipulacao.retornarVeiculoPorIndice(id).toString());
                                    MetodosAuxiliares.salvarVeiculos(veiculoManipulacao);
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        quartoMenu = 0;
                        while (quartoMenu != 9) {
                            MetodosAuxiliares.menuClientes();
                            quartoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (quartoMenu) {
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
                                    scanner.nextLine();
                                    cliente.setContato(new Contato(aux[0], aux[1]));
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
                                    cliente.setEndereco(new Endereco(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5],
                                            aux[6]));
                                    clienteManipulacao.realizarCadastro(cliente);
                                    MetodosAuxiliares.salvarClientes(clienteManipulacao);
                                    break;
                                case 2:
                                    clienteManipulacao.consultarCadastro();
                                    break;
                                case 3:
                                    System.out.println("Qual cliente você deseja editar?");
                                    clienteManipulacao.consultarCadastro();
                                    int index = scanner.nextInt();
                                    scanner.nextLine();

                                    Cliente novoCliente = new Cliente();
                                    System.out.println("Digite o novo nome do cliente");
                                    novoCliente.setNome(scanner.nextLine());
                                    System.out.println("Digite o novo cpf do cliente");
                                    novoCliente.setCpf(scanner.nextLine());
                                    aux = new String[2];
                                    System.out.println("Digite o telefone de contato do cliente");
                                    aux[0] = scanner.nextLine();
                                    System.out.println("Digite o email de contato do cliente");
                                    aux[1] = scanner.nextLine();
                                    novoCliente.setContato(new Contato(aux[0], aux[1]));
                                    scanner.nextLine();
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
                                    clienteManipulacao.editarCadastro(index, novoCliente);
                                    MetodosAuxiliares.salvarClientes(clienteManipulacao);
                                    break;
                                case 4:
                                    System.out.println("Qual cliente você deseja excluir?");
                                    clienteManipulacao.consultarCadastro();
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    clienteManipulacao.removerCadastro(id);
                                   MetodosAuxiliares.salvarClientes(clienteManipulacao);
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    case 3:
                        quintoMenu = 0;
                        while (quintoMenu != 9) {
                            MetodosAuxiliares.menuLocacao();
                            quintoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (quintoMenu) {
                                case 1:
                                    Locacao locacao = new Locacao();
                                    System.out.print("Digite a data da locação do veículo(dd/MM/yyyy): ");
                                    locacao.setDataLocacao(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                    System.out.print("Digite a data da devolucao do veículo(dd/MM/yyyy): ");
                                    locacao.setDataDevolucao(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                    System.out.print("Digite o id de um cliente cadastrado: ");
                                    clienteManipulacao.consultarCadastro();
                                    locacao.setCliente(clienteManipulacao.retornarClientePorIndice(scanner.nextInt()));
                                    scanner.nextLine();
                                    System.out.print("Digite o id de um cliente cadastrado: ");
                                    veiculoManipulacao.consultarCadastro();
                                    locacao.setVeiculo(veiculoManipulacao.retornarVeiculoPorIndice(scanner.nextInt()));
                                    scanner.nextLine();
                                    System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: ");
                                    System.out.println("Informe o numero do cartão: ");
                                    String[] temp = new String[2];
                                    temp[0] = scanner.nextLine();
                                    System.out.println("Informe a bandeira do cartão: ");
                                    temp[1] = scanner.nextLine();
                                    System.out.println("Informe a validade do cartão: ");
                                    LocalDate ld = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    System.out.println("Informe o limite do cartão: ");
                                    locacao.setCartaoCredito(new CartaoCredito(temp[0], temp[1], ld, scanner.nextDouble()));
                                    locacaoManipulacao.realizarCadastro(locacao);
                                    break;
                                case 2:
                                    locacaoManipulacao.consultarCadastro();
                                    break;
                                case 3:
                                    System.out.println("Qual funcionário você deseja editar?");
                                    funcionarioManipulacao.consultarCadastro();
                                    int index = scanner.nextInt();
                                    scanner.nextLine();

                                    Funcionario novoFuncionario = new Funcionario();
                                    System.out.println("Digite o nova nome do funcionário");
                                    novoFuncionario.setNome(scanner.nextLine());
                                    System.out.println("Digite o novo cpf do funcionário");
                                    novoFuncionario.setCpf(scanner.nextLine());
                                    System.out.println("Digite o novo número de matrícula do funcionário");
                                    novoFuncionario.setMatricula(scanner.nextInt());
                                    scanner.nextLine();
                                    funcionarioManipulacao.editarCadastro(index, novoFuncionario);
                                    MetodosAuxiliares.salvarFuncionarios(funcionarioManipulacao);
                                    break;
                                case 4:
                                    System.out.println("Qual funcionário você deseja excluir?");
                                    funcionarioManipulacao.consultarCadastro();
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    funcionarioManipulacao.removerCadastro(id);
                                    MetodosAuxiliares.salvarFuncionarios(funcionarioManipulacao);
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    case 4:
                        terceiroMenu = 0;
                        while (terceiroMenu != 9) {
                           MetodosAuxiliares.menuFuncionarios();
                            terceiroMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (terceiroMenu) {
                                case 1:
                                    Funcionario funcionario = new Funcionario();
                                    System.out.println("Digite o nome do funcionário");
                                    funcionario.setNome(scanner.nextLine());
                                    System.out.println("Digite o cpf do funcionario");
                                    funcionario.setCpf(scanner.nextLine());
                                    System.out.println("Digite o número de matrícula do funcionario");
                                    funcionario.setMatricula(scanner.nextInt());
                                    scanner.nextLine();
                                    funcionarioManipulacao.realizarCadastro(funcionario);
                                    MetodosAuxiliares.salvarFuncionarios(funcionarioManipulacao);
                                    break;
                                case 2:
                                    funcionarioManipulacao.consultarCadastro();
                                    break;
                                case 3:
                                    System.out.println("Qual funcionário você deseja editar?");
                                    funcionarioManipulacao.consultarCadastro();
                                    int index = scanner.nextInt();
                                    scanner.nextLine();

                                    Funcionario novoFuncionario = new Funcionario();
                                    System.out.println("Digite o nova nome do funcionário");
                                    novoFuncionario.setNome(scanner.nextLine());
                                    System.out.println("Digite o novo cpf do funcionário");
                                    novoFuncionario.setCpf(scanner.nextLine());
                                    System.out.println("Digite o novo número de matrícula do funcionário");
                                    novoFuncionario.setMatricula(scanner.nextInt());
                                    scanner.nextLine();
                                    funcionarioManipulacao.editarCadastro(index, novoFuncionario);
                                    MetodosAuxiliares.salvarFuncionarios(funcionarioManipulacao);
                                    break;
                                case 4:
                                    System.out.println("Qual funcionário você deseja excluir?");
                                    funcionarioManipulacao.consultarCadastro();
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    funcionarioManipulacao.removerCadastro(id);
                                    MetodosAuxiliares.salvarFuncionarios(funcionarioManipulacao);
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                        break;
                }
            }
            catch(InputMismatchException e){
                System.err.println("Tipo de dado digitado está incorreto " + e.getMessage());
                scanner.nextLine();
            }
            catch (DatasInvalidasException e) {
                System.err.println(e.getMessage() + "A data de devolução não pode ser inferior a data de locacção. Tente novamente!");
            }
        }
        scanner.close();
    }

}