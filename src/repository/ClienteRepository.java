package repository;


import exceptions.BancoDeDadosException;
import model.Cliente;
import model.Contato;
import model.Endereco;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import service.ContatoService;
import service.EnderecoService;

public class ClienteRepository implements Repositorio<Integer, Cliente> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_CLIENTE.nextval id_cliente from CLIENTE";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("id_cliente");
        }

        return null;
    }

    public Cliente adicionar(Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            cliente.setId_cliente(proximoId);

            String sql = "INSERT INTO CLIENTE\n" +
                    "(ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, cliente.getId_cliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setInt(4, cliente.getContato().getId_contato());
            stmt.setInt(5, cliente.getEndereco().getId_endereco());

            int res = stmt.executeUpdate();
            System.out.println("adicionarCliente.res=" + res);
            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        int idEndereco = 0;
        int idContato = 0;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql2 = "SELECT C.ID_ENDERECO FROM CLIENTE C WHERE C.ID_CLIENTE = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, id);
            ResultSet res = stmt2.executeQuery();
            while (res.next()) {
                idEndereco = res.getInt("id_endereco");
            }


            String sql3 = "SELECT C.ID_CONTATO FROM CLIENTE C WHERE C.ID_CLIENTE = ?";
            PreparedStatement stmt3 = con.prepareStatement(sql3);
            stmt3.setInt(1, id);
            res = stmt3.executeQuery();
            while (res.next()) {
                idContato = res.getInt("id_contato");
            }

            String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int resultado = stmt.executeUpdate();
            System.out.println("removerClientePorID.res=" + res);

            sql = "DELETE FROM CONTATO WHERE ID_CONTATO = ?";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContato);
            int resultado2 = stmt.executeUpdate();
            System.out.println("removerContato.res=" + res);


            sql = "DELETE FROM ENDERECO_CLIENTE WHERE ID_ENDERECO = ?";

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, idEndereco);

            int resultado3 = stmt.executeUpdate();
            System.out.println("removerEnderecoPorId.res=" + res);

            return resultado > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editar(Integer id, Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        ContatoService cs = new ContatoService();
        EnderecoService es = new EnderecoService();
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTE SET ");
            sql.append(" nome = ?,");
            sql.append(" cpf = ?,");
            sql.append(" id_contato = ?,");
            sql.append(" id_endereco = ?");
            sql.append(" WHERE id_cliente = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getContato().getId_contato());
            stmt.setInt(4, cliente.getEndereco().getId_endereco());
            stmt.setInt(5, id);

            int res = stmt.executeUpdate();
            System.out.println("editarCliente.res=" + res);
            es.removerEnderecosOciosos();
            cs.removerContatosOciosos();

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Cliente> listar() throws BancoDeDadosException {
        List<Cliente> clientes = new ArrayList<>();
        List<Contato> contatos = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sqlContato = "SELECT *\n" +
                    "FROM CONTATO C\n" +
                    "WHERE EXISTS (SELECT CL.ID_CONTATO FROM CLIENTE CL\n" +
                    "WHERE CL.ID_CONTATO = C.ID_CONTATO)";

            ResultSet resContato = stmt.executeQuery(sqlContato);

            while (resContato.next()) {
                Contato contato = new Contato();
                contato.setId_contato(resContato.getInt("id_contato"));
                contato.setTelefone(resContato.getString("telefone"));
                contato.setEmail(resContato.getString("email"));
                contatos.add(contato);
            }

            String sqlEndereco = "SELECT *\n" +
                    "FROM ENDERECO_CLIENTE E\n" +
                    "WHERE EXISTS (SELECT CL.ID_ENDERECO  FROM CLIENTE CL\n" +
                    "WHERE CL.ID_ENDERECO = E.ID_ENDERECO)";

            ResultSet resEndereco = stmt.executeQuery(sqlEndereco);

            while (resEndereco.next()) {
                Endereco endereco = new Endereco();
                endereco.setId_endereco(resEndereco.getInt("id_endereco"));
                endereco.setRua(resEndereco.getString("rua"));
                endereco.setNumero(resEndereco.getString("numero"));
                endereco.setBairro(resEndereco.getString("bairro"));
                endereco.setCidade(resEndereco.getString("cidade"));
                endereco.setEstado(resEndereco.getString("estado"));
                endereco.setCep(resEndereco.getString("cep"));
                endereco.setComplemento(resEndereco.getString("complemento"));
                enderecos.add(endereco);
            }

            String sql = "SELECT * FROM CLIENTE";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setContato(retonarIndiceContatoPorIdTabela(contatos, res.getInt("id_contato")));
                cliente.setEndereco(retonarIndiceEnderecoPorIdTabela(enderecos, res.getInt("id_endereco")));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    public Contato retonarIndiceContatoPorIdTabela(List<Contato> contatos, int id) throws SQLException {
        Optional<Contato> contato = contatos.stream()
                .filter(c -> c.getId_contato() == id)
                .findFirst();
        if (contato.isPresent()) {
            return contato.get();
        } else {
            throw new SQLException();
        }
    }

    public Endereco retonarIndiceEnderecoPorIdTabela(List<Endereco> enderecos, int id) throws SQLException {
        Optional<Endereco> endereco = enderecos.stream()
                .filter(e -> e.getId_endereco() == id)
                .findFirst();
        if (endereco.isPresent()) {
            return endereco.get();
        } else {
            throw new SQLException();
        }
    }

    public int retornarIndiceContatoPorIdCliente(int id) {
        int idContato = 0;
        try {
            Connection con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT C.ID_CONTATO FROM CLIENTE C WHERE C.ID_CLIENTE = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                idContato = res.getInt("id_contato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idContato;
    }

    public int retornarIndiceEnderecoPorIdCliente(int id) {
        int idEndereco = 0;
        try {
            Connection con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT C.ID_ENDERECO FROM CLIENTE C WHERE C.ID_CLIENTE = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                idEndereco = res.getInt("id_endereco");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEndereco;
    }
}


