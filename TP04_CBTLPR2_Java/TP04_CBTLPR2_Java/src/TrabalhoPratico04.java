import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * CBTLPR2 (Java) - ADS 471
 * Professor: Wellington Tuler Moraes
 * Trabalho Prático 04 - Consulta e Navegação de Funcionários via JDBC
 */
public class TrabalhoPratico04 extends JFrame {

    private JTextField txtPesquisa;
    private JButton btnPesquisar;

    private JTextField txtNome;
    private JTextField txtSalario;
    private JTextField txtCargo;

    private JButton btnAnterior;
    private JButton btnProximo;

    private Connection conexao;
    private PreparedStatement stmt;
    private ResultSet rs;

    public TrabalhoPratico04() {
        super("TRABALHO PRATICO 04");
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 15));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Painel Superior: Campo de pesquisa + botão Pesquisar
        JPanel painelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSup = new GridBagConstraints();
        gbcSup.insets = new Insets(4, 4, 6, 4);

        JLabel lblPesquisa = new JLabel("Nome:");
        gbcSup.gridx = 0;
        gbcSup.gridy = 0;
        gbcSup.anchor = GridBagConstraints.EAST;
        painelSuperior.add(lblPesquisa, gbcSup);

        txtPesquisa = new JTextField(22);
        gbcSup.gridx = 1;
        gbcSup.gridy = 0;
        gbcSup.fill = GridBagConstraints.HORIZONTAL;
        painelSuperior.add(txtPesquisa, gbcSup);

        btnPesquisar = new JButton("Pesquisar");
        JPanel painelBotaoPesquisar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelBotaoPesquisar.add(btnPesquisar);

        gbcSup.gridx = 0;
        gbcSup.gridy = 1;
        gbcSup.gridwidth = 2;
        gbcSup.fill = GridBagConstraints.HORIZONTAL;
        painelSuperior.add(painelBotaoPesquisar, gbcSup);

        // Painel Central: Exibição dos dados do registro atual
        JPanel painelDados = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setPreferredSize(new Dimension(110, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelDados.add(lblNome, gbc);

        txtNome = new JTextField(18);
        txtNome.setEditable(false);
        txtNome.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(txtNome, gbc);

        JLabel lblSalario = new JLabel("Salário:");
        lblSalario.setPreferredSize(new Dimension(110, 22));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        painelDados.add(lblSalario, gbc);

        txtSalario = new JTextField(18);
        txtSalario.setEditable(false);
        txtSalario.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(txtSalario, gbc);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setPreferredSize(new Dimension(110, 22));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        painelDados.add(lblCargo, gbc);

        txtCargo = new JTextField(18);
        txtCargo.setEditable(false);
        txtCargo.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(txtCargo, gbc);

        // Painel Inferior: Botões Anterior e Próximo
        JPanel painelNavegacao = new JPanel(new GridLayout(1, 2, 4, 0));
        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);

        painelNavegacao.add(btnAnterior);
        painelNavegacao.add(btnProximo);

        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelDados, BorderLayout.CENTER);
        painelPrincipal.add(painelNavegacao, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnPesquisar.addActionListener(e -> pesquisarFuncionarios());
        txtPesquisa.addActionListener(e -> pesquisarFuncionarios());

        btnAnterior.addActionListener(e -> irParaAnterior());
        btnProximo.addActionListener(e -> irParaProximo());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fecharRecursos();
            }
        });
    }

    private void pesquisarFuncionarios() {
        String termo = txtPesquisa.getText().trim();

        String sql = "SELECT f.cod_func, f.nome_func, f.sal_func, c.ds_cargo "
                   + "FROM tbfuncs f "
                   + "LEFT JOIN tbcargos c ON f.cod_cargo = c.cod_cargo "
                   + "WHERE f.nome_func LIKE ? "
                   + "ORDER BY f.cod_func";

        try {
            fecharConsultaAnterior();

            if (conexao == null || conexao.isClosed()) {
                conexao = Conexao.getConexao();
            }

            // ResultSet rolável (TYPE_SCROLL_INSENSITIVE) para navegação Anterior/Próximo
            stmt = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            stmt.setString(1, "%" + termo + "%");

            rs = stmt.executeQuery();

            if (rs.first()) {
                exibirRegistroAtual();
            } else {
                limparCampos();
                btnAnterior.setEnabled(false);
                btnProximo.setEnabled(false);
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum funcionário encontrado para a pesquisa.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Driver JDBC do SQL Server não encontrado no Classpath.\n" + ex.getMessage(),
                    "Erro de Driver",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao consultar o banco de dados:\n" + ex.getMessage(),
                    "Erro SQL",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void irParaAnterior() {
        try {
            if (rs != null && !rs.isFirst()) {
                if (rs.previous()) {
                    exibirRegistroAtual();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao navegar para o registro anterior:\n" + ex.getMessage(),
                    "Erro de Navegação",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void irParaProximo() {
        try {
            if (rs != null && !rs.isLast()) {
                if (rs.next()) {
                    exibirRegistroAtual();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao navegar para o próximo registro:\n" + ex.getMessage(),
                    "Erro de Navegação",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void exibirRegistroAtual() throws SQLException {
        String nome = rs.getString("nome_func");
        double salario = rs.getDouble("sal_func");
        String cargo = rs.getString("ds_cargo");

        txtNome.setText(nome != null ? nome.trim() : "");
        txtSalario.setText(String.format(Locale.US, "%.2f", salario));
        txtCargo.setText(cargo != null ? cargo.trim() : "");

        // Controla os limites inicial e final do recordset
        btnAnterior.setEnabled(!rs.isFirst());
        btnProximo.setEnabled(!rs.isLast());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtSalario.setText("");
        txtCargo.setText("");
    }

    private void fecharConsultaAnterior() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException ignored) {
        }
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException ignored) {
        }
    }

    private void fecharRecursos() {
        fecharConsultaAnterior();
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException ignored) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrabalhoPratico04().setVisible(true);
        });
    }
}
