import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ClienteManipulacao clienteManipulacao = new ClienteManipulacao();
        FuncionarioManipulacao funcionarioManipulacao = new FuncionarioManipulacao();
        VeiculoManipulacao veiculoManipulacao = new VeiculoManipulacao();
        LocacaoManipulacao locacaoManipulacao = new LocacaoManipulacao();

        try {
            BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"));
            String line = br.readLine();

            while (line != null) {
                String[] valores = line.split(",");
                if(valores.length>=8) {
                    veiculoManipulacao.realizarCadastro(MetodosAuxiliares.retornarVeiculoAPartirDeListaDeStrings(valores));
                    line = br.readLine();
                }
                else{
                    break;
                }
            }

            br = new BufferedReader(new FileReader("funcionarios.txt"));
            line = br.readLine();
            while (line != null) {
                String[] valores = line.split(",");
                if(valores.length>=3) {
                    funcionarioManipulacao.realizarCadastro(MetodosAuxiliares.retornarFuncionarioAPartirDeListaDeStrings(valores));
                    line = br.readLine();
                }
                else{
                    break;
                }
            }

            br = new BufferedReader(new FileReader("clientes.txt"));
            line = br.readLine();
            while (line != null) {
                String[] valores = line.split(",");
                if(valores.length>=10){
                    clienteManipulacao.realizarCadastro(MetodosAuxiliares.retornarClienteAPartirDeListaDeStrings(valores));
                    line = br.readLine();
                }
                else{
                    break;
                }
            }

            br = new BufferedReader(new FileReader("locacoes.txt"));
            line = br.readLine();
            while (line != null) {
                String[] valores = line.split(",");
                if(valores.length>=8) {
                    locacaoManipulacao.realizarCadastro(MetodosAuxiliares.retornarLocacaoAPartirDeListaDeStrings(valores, clienteManipulacao, veiculoManipulacao));
                    line = br.readLine();
                }
                else{
                    break;
                }
            }

        }
        catch (FileNotFoundException e ){
            System.out.println("Arquivo não encontrado no caminho designado " + e.getMessage());
        }
        catch (IOException e ){
            System.out.println(e.getMessage());
        }
        catch (DatasInvalidasException e) {
            e.printStackTrace();
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
                                    System.out.println("Qual veiculo você deseja editar? \n");
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
                                    System.out.println("Qual veiculo você deseja excluir?\n");
                                    veiculoManipulacao.consultarCadastro();
                                    int id = scanner.nextInt();
                                    veiculoManipulacao.removerCadastro(id);
                                    MetodosAuxiliares.salvarVeiculos(veiculoManipulacao);
                                    break;
                                case 5:
                                    System.out.println("Qual o id do veículo que deseja alterar a disponibilidade?\n");
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
                                    clienteManipulacao.realizarCadastro(cliente);
                                    MetodosAuxiliares.salvarClientes(clienteManipulacao);
                                    break;
                                case 2:
                                    clienteManipulacao.consultarCadastro();
                                    break;
                                case 3:
                                    System.out.println("Qual cliente você deseja editar?\n");
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
                                    System.out.println("Qual cliente você deseja excluir?\n");
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
                        break;
                    case 3:
                        quintoMenu = 0;
                        while (quintoMenu != 9) {
                            MetodosAuxiliares.menuLocacao();
                            quintoMenu = scanner.nextInt();
                            scanner.nextLine();
                            switch (quintoMenu) {
                                case 1:
                                    boolean teste = false;
                                    Locacao locacao = new Locacao();
                                    CartaoCredito cartao = new CartaoCredito();
                                    do {
                                        try {
                                            System.out.print("\nDigite a data da locação do veículo(dd/MM/yyyy): ");
                                            LocalDate dl = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                            System.out.print("\nDigite a data da devolucao do veículo(dd/MM/yyyy): ");
                                            LocalDate dd = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                            MetodosAuxiliares.validarDatasLocacao(dl, dd);
                                            teste = true;
                                            locacao.setDataLocacao(dl);
                                            locacao.setDataDevolucao(dd);
                                        }catch(DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);
                                    teste = false;

                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    clienteManipulacao.consultarCadastro();
                                    locacao.setCliente(clienteManipulacao.retornarClientePorIndice(scanner.nextInt()));
                                    scanner.nextLine();
                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    veiculoManipulacao.consultarCadastroDisponivel();
                                    int vi = scanner.nextInt();
                                    scanner.nextLine();
                                    locacao.setVeiculo(veiculoManipulacao.retornarVeiculoPorIndice(vi));
                                    veiculoManipulacao.retornarVeiculoPorIndice(vi).alterarDisponibilidade();
                                    do {
                                        try {
                                            System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                            System.out.println("Informe o numero do cartão: ");
                                            String numeroCartao = scanner.nextLine();

                                            System.out.println("Informe a bandeira do cartão: 1- Visa 2- Mastercard ");
                                            int bandeiraCartao = scanner.nextInt();

                                            scanner.nextLine();
                                            System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                            String validadeCartao = scanner.nextLine();
                                            MetodosAuxiliares.validarDataValidadeCartao(validadeCartao);
                                            teste = true;
                                            cartao.setNumero(numeroCartao);
                                            cartao.setBandeira(bandeiraCartao);
                                            cartao.setValidade(validadeCartao);

                                        }catch(DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);

                                    System.out.println("Informe o limite do cartão: ");
                                    cartao.setLimite(scanner.nextDouble());
                                    scanner.nextLine();
                                    locacao.setCartaoCredito(cartao);
                                    locacaoManipulacao.realizarCadastro(locacao);
                                    MetodosAuxiliares.salvarLocacao(locacaoManipulacao);
                                    break;
                                case 2:
                                    locacaoManipulacao.consultarCadastro();
                                    break;
                                case 3:
                                    System.out.println("Qual registro de locação você deseja editar?\n");
                                    locacaoManipulacao.consultarCadastro();
                                    int index = scanner.nextInt();
                                    scanner.nextLine();

                                    teste = false;
                                    Locacao novaLocacao = new Locacao();
                                    CartaoCredito novoCartao = new CartaoCredito();
                                    do {
                                        try {
                                            System.out.print("\nDigite a data da locação do veículo(dd/MM/yyyy): ");
                                            LocalDate dl = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                            System.out.print("\nDigite a data da devolucao do veículo(dd/MM/yyyy): ");
                                            LocalDate dd = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                            MetodosAuxiliares.validarDatasLocacao(dl, dd);
                                            teste = true;
                                            novaLocacao.setDataLocacao(dl);
                                            novaLocacao.setDataDevolucao(dd);
                                        }catch(DatasInvalidasException e) {
                                            System.out.println();
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);
                                    teste = false;

                                    System.out.print("Digite o id de um cliente cadastrado: \n");
                                    clienteManipulacao.consultarCadastro();
                                    novaLocacao.setCliente(clienteManipulacao.retornarClientePorIndice(scanner.nextInt()));
                                    scanner.nextLine();
                                    System.out.print("Digite o id de um veículo cadastrado: \n");
                                    veiculoManipulacao.consultarCadastroDisponivel();
                                    vi = scanner.nextInt();
                                    scanner.nextLine();
                                    novaLocacao.setVeiculo(veiculoManipulacao.retornarVeiculoPorIndice(vi));
                                    veiculoManipulacao.retornarVeiculoPorIndice(vi).alterarDisponibilidade();
                                    do {
                                        try {
                                            System.out.print("Informe os dados do cartão de crédito que deseja utilizar para o pagamento: \n");
                                            System.out.println("Informe o numero do cartão: ");
                                            String numeroCartao = scanner.nextLine();

                                            System.out.println("Informe a bandeira do cartão: 1- Visa 2- Mastercard ");
                                            int bandeiraCartao = scanner.nextInt();

                                            scanner.nextLine();
                                            System.out.println("Informe a validade do cartão(MM/yyyy): ");
                                            String validadeCartao = scanner.nextLine();
                                            MetodosAuxiliares.validarDataValidadeCartao(validadeCartao);
                                            teste = true;
                                            novoCartao.setNumero(numeroCartao);
                                            novoCartao.setBandeira(bandeiraCartao);
                                            novoCartao.setValidade(validadeCartao);

                                        }catch(DatasInvalidasException e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }while(!teste);

                                    System.out.println("Informe o limite do cartão: ");
                                    novoCartao.setLimite(scanner.nextDouble());
                                    scanner.nextLine();
                                    novaLocacao.setCartaoCredito(novoCartao);
                                    locacaoManipulacao.realizarCadastro(novaLocacao);
                                    MetodosAuxiliares.salvarLocacao(locacaoManipulacao);
                                    break;
                                case 4:
                                    System.out.println("Qual registro de locação você deseja excluir?\n");
                                    locacaoManipulacao.consultarCadastro();
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    locacaoManipulacao.removerCadastro(id);
                                    MetodosAuxiliares.salvarLocacao(locacaoManipulacao);
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                        break;
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
        }
        scanner.close();
    }

}
