package repository;

import exceptions.BancoDeDadosException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocacaoRepository implements Repositorio<Integer, Locacao> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_LOCACAO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("id_cliente");
        }
        return null;
    }

    @Override
    public Locacao adicionar(Locacao locacao) throws BancoDeDadosException {
        Connection con = null;
        try {
            Integer proximoId = this.getProximoId(con);
            locacao.setId_locacao(proximoId);

            String sql = "INSERT INTO LOCACAO\n" +
                    "(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)\n" +
                    "VALUES(?,?,?,?,?,?,?,?,?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, locacao.getId_locacao());
            stmt.setDate(2, Date.valueOf(locacao.getDataLocacao()));
            stmt.setDate(3, Date.valueOf(locacao.getDataDevolucao()));
            stmt.setDouble(4, locacao.getVeiculo().getQuilometragemAdicao());
            stmt.setDouble(5, locacao.getValorLocacao());
            stmt.setInt(6, locacao.getCliente().getId_cliente());
            stmt.setInt(7, locacao.getVeiculo().getId_veiculo());
            stmt.setInt(8, locacao.getFuncionario().getId_funcionario());
            stmt.setInt(9, locacao.getCartaoCredito().getId_cartao_credito());

            int res = stmt.executeUpdate();
            System.out.println("adicionarLocacao.res=" + res);
            return locacao;
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

        try {
            String sql = "DELETE FROM LOCACAO WHERE id_locacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerLocacaoPorId.res=" + res);
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
    public boolean editar(Integer id, Locacao locacao) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE LOCACAO SET ");
            sql.append(" dataLocacao = ?,");
            sql.append(" dataDevolucao = ?,");
            sql.append(" quilometragemAdicao = ?,");
            sql.append(" valorLocacao = ?,");
            sql.append(" id_Cliente = ?,");
            sql.append(" id_veiculo = ?,");
            sql.append(" id_funcionario = ?,");
            sql.append(" id_cartao = ? ");
            sql.append(" Where id_locacao = ? ");
            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setDate(1, Date.valueOf(locacao.getDataLocacao()));
            stmt.setDate(2, Date.valueOf(locacao.getDataDevolucao()));
            stmt.setDouble(3, locacao.getVeiculo().getQuilometragemAdicao());
            stmt.setDouble(4, locacao.getValorLocacao());
            stmt.setInt(5, locacao.getCliente().getId_cliente());
            stmt.setInt(6, locacao.getVeiculo().getId_veiculo());
            stmt.setInt(7, locacao.getFuncionario().getId_funcionario());
            stmt.setInt(8, locacao.getCartaoCredito().getId_cartao_credito());
            stmt.setInt(9, id);

            int res = stmt.executeUpdate();
            System.out.println("editar.res=" + res);

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
    public List<Locacao> listar() throws SQLException {
        List<Locacao> locacoes = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "select *\n" +
                    "from LOCACAO\n" +
                    "left join  VEICULO V on V.ID_VEICULO = LOCACAO.ID_VEICULO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Locacao locacao = new Locacao();
                locacao.setId_locacao(res.getInt("id_locacao"));
                locacao.setDataLocacao(res.getDate("data_locacao").toLocalDate());
                locacao.setDataDevolucao(res.getDate("data_devolucao").toLocalDate());
                locacao.setValorLocacao(res.getDouble("valor_locacao"));
                locacao.setCliente(getClientFromResultSet(res));
                locacao.setVeiculo(getVeiculoFromResultSet(res));
                locacao.setFuncionario(getFuncionarioResultSet(res));
                locacao.setCartaoCredito(getFromResultSet(res));
            }
            return locacoes;
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

    private Funcionario getFuncionarioResultSet(ResultSet res) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(res.getInt("id_funcionario"));
        funcionario.setNome(res.getString("nome"));
        funcionario.setCpf(res.getString("cpf"));
        funcionario.setMatricula(res.getInt("matricula"));
        return funcionario;
    }

    private CartaoCredito getFromResultSet(ResultSet res) throws SQLException {
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setId_cartao_credito(res.getInt("id_cartao_credito"));
        cartaoCredito.setNumero(res.getString("numero"));
        cartaoCredito.setBandeira(res.getInt("bandeira"));
        cartaoCredito.setValidade(res.getString("validade"));
        cartaoCredito.setLimite(res.getDouble("limite"));
        return cartaoCredito;
    }

    private Cliente getClientFromResultSet(ResultSet res) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(res.getInt("id_cliente"));
        cliente.setContato(getContatoFromResultSet(res));
        cliente.setEndereco(getEnderecoResultSet(res));
        return cliente;
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

    private Veiculo getVeiculoFromResultSet(ResultSet res) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId_veiculo(res.getInt("id_veiculo"));
        veiculo.setMarca(res.getString("marca"));
        veiculo.setModelo(res.getString("modelo"));
        veiculo.setCor(res.getString("cor"));
        veiculo.setAno(res.getInt("ano"));
        veiculo.setQuilometragemAdicao(res.getDouble("quilometragem"));
        veiculo.setValorLocacao(res.getDouble("valor_locacao_total"));
        veiculo.setDisponibilidade(res.getInt("disponibilidade"));
        //veiculo.setPlaca(res.getString("placa"));
        return veiculo;
    }
}
