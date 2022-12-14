package repository;

import exceptions.BancoDeDadosException;
import model.DisponibilidadeVeiculo;
import model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository implements Repositorio<Integer, Veiculo> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_VEICULO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Veiculo adicionar(Veiculo veiculo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            veiculo.setIdVeiculo(proximoId);

            String sql = "INSERT INTO VEICULO\n" +
                    "(id_veiculo, marca, modelo, cor, ano, quilometragem, valor_locacao_diario, disponibilidade, placa)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, veiculo.getIdVeiculo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getCor());
            stmt.setInt(5, veiculo.getAno());
            stmt.setDouble(6, veiculo.getQuilometragem());
            stmt.setDouble(7, veiculo.getValorLocacao());
            stmt.setInt(8, veiculo.getDisponibilidadeVeiculo().getDisponibilidade());
            stmt.setString(9, veiculo.getPlaca());

            int res = stmt.executeUpdate();
            System.out.println("adicionarVeiculo.res=" + res);
            return veiculo;
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
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM VEICULO WHERE id_veiculo = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerVeiculoPorId.res=" + res);

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
    public boolean editar(Integer id, Veiculo veiculo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE VEICULO SET");
            sql.append(" marca = ?");
            sql.append(", modelo = ?");
            sql.append(", cor = ?");
            sql.append(", ano = ?");
            sql.append(", quilometragem = ?");
            sql.append(", valor_locacao_diario = ?");
            sql.append(", disponibilidade = ?");
            sql.append(", placa = ?");
            sql.append(" WHERE id_veiculo = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getCor());
            stmt.setInt(4, veiculo.getAno());
            stmt.setDouble(5, veiculo.getQuilometragem());
            stmt.setDouble(6, veiculo.getValorLocacao());
            stmt.setInt(7, veiculo.getDisponibilidadeVeiculo().getDisponibilidade());
            stmt.setString(8, veiculo.getPlaca());
            stmt.setInt(9, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarVeiculo.res=" + res);

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
    public List<Veiculo> listar() throws BancoDeDadosException {
        List<Veiculo> veiculos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VEICULO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
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
                veiculos.add(veiculo);
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
        return veiculos;
    }

    public List<Veiculo> listarVeiculosDisponiveis() throws BancoDeDadosException {
        List<Veiculo> veiculos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VEICULO WHERE DISPONIBILIDADE = 2";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
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
                veiculos.add(veiculo);
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
        return veiculos;
    }

    private Veiculo getVeiculoFRomResultSEt(ResultSet res) {
        return null;
    }

    public Veiculo getPorId(Integer chave) throws BancoDeDadosException {
        Veiculo veiculo = new Veiculo();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM VEICULO\n" +
                    "WHERE ID_VEICULO = ?";

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1,chave);

            ResultSet res = stmt.executeQuery();

            while (res.next()){
                veiculo.setIdVeiculo(res.getInt("id_veiculo"));
                veiculo.setMarca(res.getString("marca"));
                veiculo.setModelo(res.getString("modelo"));
                veiculo.setCor(res.getString("cor"));
                veiculo.setAno(res.getInt("ano"));
                veiculo.setQuilometragem(res.getDouble("quilometragem"));
                veiculo.setValorLocacao(res.getDouble("valor_locacao_diario"));
                veiculo.setDisponibilidadeVeiculo(DisponibilidadeVeiculo.getByValue(res.getInt("disponibilidade")));
                veiculo.setPlaca(res.getString("placa"));
            }
            //System.out.println("buscarVeiculo.res=" + res);
            return veiculo;
        }catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }finally {
            try {
                if(con != null){
                    con.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
