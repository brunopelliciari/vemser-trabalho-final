package repository;

import exceptions.BancoDeDadosException;
import model.Cliente;
import model.Contato;
import model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteRepository implements Repositorio<Integer, Cliente> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_CLIENTE.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
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

            int resultado = stmt.executeUpdate();
            System.out.println("removerClientePorID.res=" + resultado);

            sql = "DELETE FROM CONTATO WHERE ID_CONTATO = ?";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContato);
            resultado = stmt.executeUpdate();
            System.out.println("removerContato.res=" + resultado);


            sql = "DELETE FROM ENDERECO_CLIENTE WHERE ID_ENDERECO = ?";

            stmt = con.prepareStatement(sql);

            stmt.setInt(1, idEndereco);

            resultado = stmt.executeUpdate();
            System.out.println("removerEnderecoPorId.res=" + resultado);

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
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CLIENTE CL\n" +
                    "FULL OUTER JOIN CONTATO C\n" +
                    "ON CL.ID_CONTATO = C.ID_CONTATO \n" +
                    "FULL OUTER JOIN ENDERECO_CLIENTE E \n" +
                    "ON CL.ID_ENDERECO = E.ID_ENDERECO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setContato(getContatoFromResultSet(res));
                cliente.setEndereco(getEnderecoResultSet(res));
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

    public Cliente getPorId(Integer chave) throws BancoDeDadosException {
        Cliente cliente = new Cliente();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM CLIENTE CL\n" +
                    "FULL OUTER JOIN CONTATO C\n" +
                    "ON CL.ID_CONTATO = C.ID_CONTATO \n" +
                    "FULL OUTER JOIN ENDERECO_CLIENTE E \n" +
                    "ON CL.ID_ENDERECO = E.ID_ENDERECO WHERE CL.ID_CLIENTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql.toString());


            stmt.setInt(1, chave);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {

                cliente.setId_cliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setEndereco(getEnderecoResultSet(res));
                cliente.setContato(getContatoFromResultSet(res));
            }
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

    private Contato getContatoFromResultSet(ResultSet res) throws SQLException {
        Contato contato = new Contato();
        contato.setId_contato(res.getInt("id_contato"));
        contato.setTelefone(res.getString("telefone"));
        contato.setEmail(res.getString("email"));
        return contato;
    }

    private Endereco getEnderecoResultSet(ResultSet res) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId_endereco(res.getInt("id_endereco"));
        endereco.setRua(res.getString("rua"));
        endereco.setNumero(res.getString("numero"));
        endereco.setBairro(res.getString("bairro"));
        endereco.setCidade(res.getString("cidade"));
        endereco.setEstado(res.getString("estado"));
        endereco.setCep(res.getString("cep"));
        endereco.setComplemento(res.getString("complemento"));
        return endereco;
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


