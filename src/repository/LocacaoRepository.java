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
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public Locacao adicionar(Locacao locacao) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Integer proximoId = this.getProximoId(con);
            locacao.setIdlocacao(proximoId);

            String sql = "INSERT INTO LOCACAO\n" +
                    "(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)\n" +
                    "VALUES(?,?,?,?,?,?,?,?,?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setInt(1, locacao.getIdlocacao());
            stmt.setDate(2, Date.valueOf(locacao.getDataLocacao()));
            stmt.setDate(3, Date.valueOf(locacao.getDataDevolucao()));
            stmt.setDouble(4, locacao.getVeiculo().getQuilometragem());
            stmt.setDouble(5, locacao.getValorLocacao());
            stmt.setInt(6, locacao.getCliente().getId_cliente());
            stmt.setInt(7, locacao.getVeiculo().getIdVeiculo());
            stmt.setInt(8, locacao.getFuncionario().getIdFuncionario());
            stmt.setInt(9, locacao.getCartaoCredito().getIdCartaoCredito());


            int res = stmt.executeUpdate();
            //System.out.println("adicionarLocacao.res=" + res);
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
        int idCartao = 0;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql2 = "SELECT L.ID_CARTAO FROM LOCACAO L WHERE L.ID_LOCACAO = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, id);
            ResultSet res = stmt2.executeQuery();
            while (res.next()) {
                idCartao = res.getInt("id_cartao");
            }
            //System.out.println("removerCartaoPorId.res=" + res);


            String sql = "DELETE FROM LOCACAO WHERE id_locacao = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int resultado = stmt.executeUpdate();
            //System.out.println("removerLocacaoPorId.res=" + resultado);
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
    public boolean editar(Integer id, Locacao locacao) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE LOCACAO SET \n");

            if (locacao.getDataLocacao() != null) {
                sql.append(" data_locacao = ?,");
            }
            if (locacao.getDataDevolucao() != null) {
                sql.append(" data_devolucao = ?,");
            }
            if (locacao.getValorLocacao() != null) {
                sql.append(" valor_locacao_total = ?,");
            }
            Veiculo veiculo = locacao.getVeiculo();
            if (veiculo.getQuilometragem() != null) {
                sql.append("quilometragem_adicao = ?,");
            }
            Cliente cliente = locacao.getCliente();
            if (cliente != null) {
                sql.append("id_cliente = ?,");
            }
            if (veiculo.getIdVeiculo() != null) {
                sql.append(" id_veiculo = ?,");
            }
            Funcionario funcionario = locacao.getFuncionario();
            if (funcionario.getIdFuncionario() != null) {
                sql.append(" id_funcionario = ?,");
            }
            CartaoCredito cartaoCredito = locacao.getCartaoCredito();
            if (cartaoCredito != null) {
                sql.append(" id_cartao = ?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id_locacao = ? ");
            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (locacao.getDataLocacao() != null) {
                stmt.setDate(index++, Date.valueOf(locacao.getDataLocacao()));
            }
            if (locacao.getDataDevolucao() != null) {
                stmt.setDate(index++, Date.valueOf(locacao.getDataDevolucao()));
            }
            if (veiculo.getQuilometragem() != null) {
                stmt.setDouble(index++, locacao.getVeiculo().getQuilometragem());
            }
            if (locacao.getValorLocacao() != null) {
                stmt.setDouble(index++, locacao.getValorLocacao());
            }
            if (cliente != null) {
                stmt.setInt(index++, cliente.getId_cliente());
            }
            if (veiculo.getIdVeiculo() != null) {
                stmt.setInt(index++, veiculo.getIdVeiculo());
            }
            if (funcionario.getIdFuncionario() != null) {
                stmt.setInt(index++, funcionario.getIdFuncionario());
            }
            if (cartaoCredito != null) {
                stmt.setInt(index++, cartaoCredito.getIdCartaoCredito());
            }
            stmt.setInt(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarLocacao.res=" + res);

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
    public List<Locacao> listar() throws BancoDeDadosException {
        List<Locacao> locacoes = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "select * from LOCACAO L\n" +
                    "left join CLIENTE C2 on C2.ID_CLIENTE = L.ID_CLIENTE\n" +
                    "left join VEICULO V on V.ID_VEICULO = L.ID_VEICULO\n" +
                    "left join CONTATO C3 on C3.ID_CONTATO = C2.ID_CONTATO\n" +
                    "left join FUNCIONARIO F on F.ID_FUNCIONARIO = L.ID_FUNCIONARIO\n" +
                    "left join ENDERECO_CLIENTE EC on EC.ID_ENDERECO = C2.ID_ENDERECO\n" +
                    "left join CARTAO_CREDITO CC on L.ID_CARTAO = CC.ID_CARTAO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Locacao locacao = getLocacaoFromResultset(res);
                locacoes.add(locacao);
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


    private Locacao getLocacaoFromResultset(ResultSet res) throws SQLException {
        Locacao locacao = new Locacao();
        locacao.setIdlocacao(res.getInt("id_locacao"));
        locacao.setDataLocacao(res.getDate("data_locacao").toLocalDate());
        locacao.setDataDevolucao(res.getDate("data_devolucao").toLocalDate());
        locacao.setValorLocacao(res.getDouble("valor_locacao_total"));
        locacao.setCliente(getClientFromResultSet(res));
        locacao.setVeiculo(getVeiculoFromResultSet(res));
        locacao.setFuncionario(getFuncionarioResultSet(res));
        locacao.setCartaoCredito(getFromResultSetCartaoCredito(res));
        return locacao;
    }

    private Funcionario getFuncionarioResultSet(ResultSet res) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(res.getInt("id_funcionario"));
        funcionario.setNome(res.getString("nome_funcionario"));
        funcionario.setCpf(res.getString("cpf_funcionario"));
        funcionario.setMatricula(res.getInt("matricula"));
        return funcionario;
    }

    private CartaoCredito getFromResultSetCartaoCredito(ResultSet res) throws SQLException {
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setIdCartaoCredito(res.getInt("id_cartao"));
        cartaoCredito.setNumero(res.getString("numero_cartao"));
        cartaoCredito.setBandeira(BandeiraCartao.valueOf(res.getString("bandeira_cartao")));
        cartaoCredito.setValidade(res.getString("validade"));
        cartaoCredito.setLimite(res.getDouble("limite"));
        return cartaoCredito;
    }

    private Cliente getClientFromResultSet(ResultSet res) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(res.getInt("id_cliente"));
        cliente.setNome(res.getString("nome"));
        cliente.setCpf(res.getString("cpf"));
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
        veiculo.setIdVeiculo(res.getInt("id_veiculo"));
        veiculo.setMarca(res.getString("marca"));
        veiculo.setModelo(res.getString("modelo"));
        veiculo.setCor(res.getString("cor"));
        veiculo.setAno(res.getInt("ano"));
        veiculo.setQuilometragem(res.getDouble("quilometragem"));
        veiculo.setValorLocacao(res.getDouble("valor_locacao_diario"));
        veiculo.setDisponibilidadeVeiculo(DisponibilidadeVeiculo.getByValue(res.getInt("disponibilidade")));
        veiculo.setPlaca(res.getString("placa"));
        return veiculo;
    }
}
