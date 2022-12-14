package repository;

import exceptions.BancoDeDadosException;
import model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements Repositorio<Integer, Funcionario> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_FUNCIONARIO.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Funcionario adicionar(Funcionario funcionario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            funcionario.setIdFuncionario(proximoId);

            String sql = "INSERT INTO FUNCIONARIO\n" +
                    "(id_funcionario, nome_funcionario, cpf_funcionario, matricula)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, funcionario.getIdFuncionario());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getCpf());
            stmt.setInt(4, funcionario.getMatricula());

            int res = stmt.executeUpdate();
            System.out.println("adicionarFuncionario.res=" + res);
            return funcionario;
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

            String sql = "DELETE FROM FUNCIONARIO WHERE id_funcionario = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerFuncionarioPorId.res=" + res);

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
    public boolean editar(Integer id, Funcionario funcionario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE FUNCIONARIO SET ");
            sql.append(" nome_funcionario = ?,");
            sql.append(" cpf_funcionario = ?,");
            sql.append(" matricula = ? ");
            sql.append(" WHERE id_funcionario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setInt(3, funcionario.getMatricula());
            stmt.setInt(4, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarFuncionario.res=" + res);

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
    public List<Funcionario> listar() throws BancoDeDadosException {
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM FUNCIONARIO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(res.getInt("id_funcionario"));
                funcionario.setNome(res.getString("nome_funcionario"));
                funcionario.setCpf(res.getString("cpf_funcionario"));
                funcionario.setMatricula(res.getInt("matricula"));
                funcionarios.add(funcionario);
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
        return funcionarios;
    }

    public Funcionario getPorId(Integer chave) throws BancoDeDadosException {
        Funcionario funcionario = new Funcionario();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM FUNCIONARIO\n" +
                    "WHERE id_funcionario = ?";

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1,chave);

            ResultSet res = stmt.executeQuery();

            while (res.next()){
                funcionario.setIdFuncionario(res.getInt("id_funcionario"));
                funcionario.setNome(res.getString("nome_funcionario"));
                funcionario.setCpf(res.getString("cpf_funcionario"));
                funcionario.setMatricula(res.getInt("matricula"));
            }
            //System.out.println("buscarFuncionario.res="+ res);
            return funcionario;
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
