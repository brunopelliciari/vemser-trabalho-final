//import java.io.*;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//
//        FuncionarioManipulacao funcionarioManipulacao = new FuncionarioManipulacao();
//        VeiculoManipulacao veiculoManipulacao = new VeiculoManipulacao();
//        BufferedReader brVeiculos = new BufferedReader(new FileReader("veiculos.txt"));
//        BufferedReader brFuncionarios = new BufferedReader(new FileReader("funcionarios.txt"));
//        try {
//            String line = brVeiculos.readLine();
//            String line2 = brFuncionarios.readLine();
//
//            while (line != null) {
//                String[] valores = line.split(",");
//                int ano = Integer.parseInt(valores[3].strip());
//                double km = Double.parseDouble(valores[4].strip());
//                double preco = Double.parseDouble(valores[5].strip());
//                Disponibilidade disp = Disponibilidade.valueOf(valores[6].trim().replaceAll("Disponibilidade.",""));
//                Veiculo v = new Veiculo(valores[0].trim(), valores[1].trim(), valores[2].trim(), ano, km, preco, disp, valores[7].trim());
//                veiculoManipulacao.adicionarVeiculo(v);
//                line = brVeiculos.readLine();
//            }
//
//            while (line2 != null) {
//                String[] valores = line2.split(",");
//                int matricula = Integer.parseInt(valores[2].trim());
//                Funcionario f = new Funcionario(valores[0].trim(), valores[1].trim(), matricula);
//                funcionarioManipulacao.adicionarFuncionario(f);
//                line2 = brFuncionarios.readLine();
//            }
//        }
//        catch (FileNotFoundException e ){
//            System.out.println("Deu rui2m");
//        }
//        catch (IOException e ){
//            System.out.println("Deu ruim");
//        }
//        int primeiroMenu = 0;
//        int segundoMenu = 0;
//        int terceiroMenu = 0;
//        while (primeiroMenu != 9) {
//            try {
//                System.out.println("Digite 1 para entrar no menu de veículos");
//                System.out.println("Digite 2 para entrar no menu de clientes");
//                System.out.println("Digite 3 para entrar no menu de locação");
//                System.out.println("Digite 4 para entrar no menu de funcionários");
//                System.out.println("Digite 9 para sair");
//                primeiroMenu = scanner.nextInt();
//                scanner.nextLine();
//                switch (primeiroMenu) {
//                    case 1:
//                        segundoMenu = 0;
//                        while (segundoMenu != 9) {
//                            System.out.println("Digite 1 para registrar um veículo");
//                            System.out.println("Digite 2 para listar veículos disponíveis");
//                            System.out.println("Digite 3 para editar um veículo disponível");
//                            System.out.println("Digite 4 para excluir um veículo");
//                            System.out.println("Digite 9 para voltar ao menu anterior");
//                            segundoMenu = scanner.nextInt();
//                            scanner.nextLine();
//                            switch (segundoMenu) {
//                                case 1:
//                                    Veiculo veiculo = new Veiculo();
//                                    System.out.println("Digite a marca do veiculo");
//                                    veiculo.setMarca(scanner.nextLine());
//                                    System.out.println("Digite o modelo do veiculo");
//                                    veiculo.setModelo(scanner.nextLine());
//                                    System.out.println("Digite a cor do veiculo");
//                                    veiculo.setCor(scanner.nextLine());
//                                    System.out.println("Digite o ano do veiculo");
//                                    veiculo.setAno(scanner.nextInt());
//                                    scanner.nextLine();
//                                    System.out.println("Digite a quilometragem do veiculo");
//                                    veiculo.setQuilometragem(scanner.nextDouble());
//                                    scanner.nextLine();
//                                    System.out.println("Digite o valor de locação diário do veiculo");
//                                    veiculo.setValorLocacao(scanner.nextDouble());
//                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível");
//                                    veiculo.setDisponibilidade(scanner.nextInt());
//                                    scanner.nextLine();
//                                    System.out.println("Digite a placa do veículo");
//                                    veiculo.setPlaca(scanner.nextLine());
//                                    veiculoManipulacao.adicionarVeiculo(veiculo);
//                                    Main.salvarVeiculos(veiculoManipulacao);
//                                    break;
//                                case 2:
//                                    veiculoManipulacao.listarVeiculos();
//                                    break;
//                                case 3:
//                                    System.out.println("Qual veiculo você deseja editar?");
//                                    veiculoManipulacao.listarVeiculos();
//                                    int index = scanner.nextInt();
//                                    scanner.nextLine();
//
//                                    Veiculo novoVeiculo = new Veiculo();
//                                    System.out.println("Digite a nova marca do veiculo");
//                                    novoVeiculo.setMarca(scanner.nextLine());
//                                    System.out.println("Digite o novo modelo do veiculo");
//                                    novoVeiculo.setModelo(scanner.nextLine());
//                                    System.out.println("Digite a nova cor do veiculo");
//                                    novoVeiculo.setCor(scanner.nextLine());
//                                    System.out.println("Digite o novo ano do veiculo");
//                                    novoVeiculo.setAno(scanner.nextInt());
//                                    scanner.nextLine();
//                                    System.out.println("Digite a nova quilometragem do veiculo");
//                                    novoVeiculo.setQuilometragem(scanner.nextDouble());
//                                    scanner.nextLine();
//                                    System.out.println("Digite o novo valor de locação diária do veiculo");
//                                    novoVeiculo.setValorLocacao(scanner.nextDouble());
//                                    scanner.nextLine();
//                                    System.out.println("Digite a disponibilidade do veiculo(1- Alugado 2- Disponível");
//                                    novoVeiculo.setDisponibilidade(scanner.nextInt());
//                                    scanner.nextLine();
//                                    System.out.println("Digite a nova placa do veículo");
//                                    novoVeiculo.setPlaca(scanner.nextLine());
//                                    veiculoManipulacao.editarVeiculo(index, novoVeiculo);
//                                    Main.salvarVeiculos(veiculoManipulacao);
//                                    break;
//                                case 4:
//                                    System.out.println("Qual veiculo você deseja excluir?");
//                                    veiculoManipulacao.listarVeiculos();
//                                    int id = scanner.nextInt();
//                                    veiculoManipulacao.removerVeiculoPorIndice(id);
//                                    Main.salvarVeiculos(veiculoManipulacao);
//                                    break;
//                                case 9:
//                                    break;
//                                default:
//                                    System.err.println("Opção inválida");
//                                    break;
//                            }
//                        }
//                        break;
//                    case 2:
//                    case 3:
//                    case 4:
//                        terceiroMenu = 0;
//                        while (terceiroMenu != 9) {
//                            System.out.println("Digite 1 para registrar um funcionário");
//                            System.out.println("Digite 2 para listar funcionários disponíveis");
//                            System.out.println("Digite 3 para editar um funcionário disponível");
//                            System.out.println("Digite 4 para excluir um funcionário");
//                            System.out.println("Digite 9 para voltar ao menu anterior");
//                            terceiroMenu = scanner.nextInt();
//                            scanner.nextLine();
//                            switch (terceiroMenu) {
//                                case 1:
//                                    Funcionario funcionario = new Funcionario();
//                                    System.out.println("Digite o nome do funcionário");
//                                    funcionario.setNome(scanner.nextLine());
//                                    System.out.println("Digite o cpf do funcionario");
//                                    funcionario.setCpf(scanner.nextLine());
//                                    System.out.println("Digite o número de matrícula do funcionario");
//                                    funcionario.setMatricula(scanner.nextInt());
//                                    scanner.nextLine();
//                                    funcionarioManipulacao.adicionarFuncionario(funcionario);
//                                    Main.salvarFuncionarios(funcionarioManipulacao);
//                                    break;
//                                case 2:
//                                    funcionarioManipulacao.listarFuncionarios();
//                                    break;
//                                case 3:
//                                    System.out.println("Qual funcionário você deseja editar?");
//                                    funcionarioManipulacao.listarFuncionarios();
//                                    int index = scanner.nextInt();
//                                    scanner.nextLine();
//
//                                    Funcionario novoFuncionario = new Funcionario();
//                                    System.out.println("Digite o nova nome do funcionário");
//                                    novoFuncionario.setNome(scanner.nextLine());
//                                    System.out.println("Digite o novo cpf do funcionário");
//                                    novoFuncionario.setCpf(scanner.nextLine());
//                                    System.out.println("Digite o novo número de matrícula do funcionário");
//                                    novoFuncionario.setMatricula(scanner.nextInt());
//                                    scanner.nextLine();
//                                    funcionarioManipulacao.editarFuncionario(index, novoFuncionario);
//                                    Main.salvarFuncionarios(funcionarioManipulacao);
//                                    break;
//                                case 4:
//                                    System.out.println("Qual funcionário você deseja excluir?");
//                                    funcionarioManipulacao.listarFuncionarios();
//                                    int id = scanner.nextInt();
//                                    scanner.nextLine();
//                                    funcionarioManipulacao.removerFuncionarioPorIndice(id);
//                                    Main.salvarFuncionarios(funcionarioManipulacao);
//                                    break;
//                                case 9:
//                                    break;
//                                default:
//                                    System.err.println("Opção inválida");
//                                    break;
//                            }
//                        }
//                        break;
//                }
//            }
//            catch(InputMismatchException e){
//                System.err.println("Mandou mal");
//                scanner.nextLine();
//            }
//        }
//    }
//    public static void salvarFuncionarios(FuncionarioManipulacao v){
//        try{
//            PrintWriter writer = new PrintWriter("funcionarios.txt");
//            for (int i = 0; i < v.retornarLista().size();i++){
//                writer.println(v.retornarLista().get(i).toStringParaLista());
//            }
//            writer.close();
//        } catch (FileNotFoundException e){
//            System.out.println("File not found.");
//        }
//    }
//    public static void salvarVeiculos(VeiculoManipulacao v){
//        try{
//            PrintWriter writer = new PrintWriter("veiculos.txt");
//            for (int i = 0; i < v.retornarLista().size();i++){
//                writer.println(v.retornarLista().get(i).toStringParaLista());
//            }
//            writer.close();
//        } catch (FileNotFoundException e){
//            System.out.println("File not found.");
//        }
//    }
//}
