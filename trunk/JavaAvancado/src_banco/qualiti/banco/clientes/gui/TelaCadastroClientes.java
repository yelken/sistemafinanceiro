package qualiti.banco.clientes.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import qualiti.banco.fachada.Fachada;
import qualiti.banco.clientes.Cliente;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class TelaCadastroClientes extends JFrame {
  JPanel pnBotoes = new JPanel();
  JButton btIncluir = new JButton();
  JButton btAlterar = new JButton();
  JButton btExcluir = new JButton();
  JButton btCancelar = new JButton();
  JPanel pnPrinc = new JPanel();
  JLabel laCpf = new JLabel();
  JTextField txCpf = new JTextField();
  JLabel laNome = new JLabel();
  JTextField txNome = new JTextField();
  JButton btNovo = new JButton();
  JButton btAcesso = new JButton();
  private Fachada fac = Fachada.obterInstancia();
  private Cliente clienteAcessado;

  public TelaCadastroClientes() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    TelaCadastroClientes telaCadastroClientes1 = new TelaCadastroClientes();
    telaCadastroClientes1.setBounds(200,200,400,300);
    telaCadastroClientes1.setVisible(true);
  }
  private void jbInit() throws Exception {
    btIncluir.setEnabled(false);
    btIncluir.setText("Incluir");
    btIncluir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btIncluir_actionPerformed(e);
      }
    });
    btAlterar.setEnabled(false);
    btAlterar.setText("Alterar");
    btAlterar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btAlterar_actionPerformed(e);
      }
    });
    btExcluir.setEnabled(false);
    btExcluir.setText("Excluir");
    btExcluir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btExcluir_actionPerformed(e);
      }
    });
    btCancelar.setText("Cancelar");
    btCancelar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btCancelar_actionPerformed(e);
      }
    });
    pnPrinc.setLayout(null);
    laCpf.setText("CPF:");
    laCpf.setBounds(new Rectangle(51, 40, 41, 17));
    txCpf.setBounds(new Rectangle(105, 37, 119, 21));
    txCpf.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txCpf_actionPerformed(e);
      }
    });
    laNome.setEnabled(false);
    laNome.setText("Nome:");
    laNome.setBounds(new Rectangle(51, 80, 41, 17));
    txNome.setEnabled(false);
    txNome.setBounds(new Rectangle(105, 77, 254, 21));
    this.setDefaultCloseOperation(3);
    this.setResizable(false);
    this.setTitle("Cadastro de Clientes");
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(WindowEvent e) {
        this_windowOpened(e);
      }
    });
    btNovo.setBounds(new Rectangle(231, 34, 44, 27));
    btNovo.setText("N");
    btNovo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btNovo_actionPerformed(e);
      }
    });
    btAcesso.setText("A");
    btAcesso.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btAcesso_actionPerformed(e);
      }
    });
    btAcesso.setBounds(new Rectangle(282, 34, 44, 27));
    this.getContentPane().add(pnBotoes, BorderLayout.SOUTH);
    pnBotoes.add(btIncluir, null);
    pnBotoes.add(btAlterar, null);
    pnBotoes.add(btExcluir, null);
    pnBotoes.add(btCancelar, null);
    this.getContentPane().add(pnPrinc, BorderLayout.CENTER);
    pnPrinc.add(laCpf, null);
    pnPrinc.add(laNome, null);
    pnPrinc.add(txCpf, null);
    pnPrinc.add(txNome, null);
    pnPrinc.add(btNovo, null);
    pnPrinc.add(btAcesso, null);
  }

  void txCpf_actionPerformed(ActionEvent e) {

  }

  void btNovo_actionPerformed(ActionEvent e) {
    if (txCpf.getText().trim().equals("")) {
      JOptionPane.showMessageDialog(this,"Digite um CPF","Mensagem de erro",JOptionPane.ERROR_MESSAGE);
      txCpf.requestFocus();
    } else {
      mudaEstadoParaInclusao();
    }
  }
  private void mudaEstadoParaInclusao() {
      laCpf.setEnabled(false);
      txCpf.setEnabled(false);
      btNovo.setEnabled(false);
      btAcesso.setEnabled(false);
      laNome.setEnabled(true);
      txNome.setEnabled(true);
      btIncluir.setEnabled(true);
      txNome.requestFocus();
  }
  private void mudaEstadoParaAltExc() {
      laCpf.setEnabled(false);
      txCpf.setEnabled(false);
      btNovo.setEnabled(false);
      btAcesso.setEnabled(false);
      laNome.setEnabled(true);
      txNome.setEnabled(true);
      btAlterar.setEnabled(true);
      btExcluir.setEnabled(true);
      txNome.requestFocus();
  }
  private void limpaCamposChave() {
      txCpf.setText("");
  }
  private void limpaCamposDados() {
      txNome.setText("");
  }
  private void mudaEstadoParaInicial() {
      laCpf.setEnabled(true);
      txCpf.setEnabled(true);
      btNovo.setEnabled(true);
      btAcesso.setEnabled(true);
      laNome.setEnabled(false);
      txNome.setEnabled(false);
      btIncluir.setEnabled(false);
      btAlterar.setEnabled(false);
      btExcluir.setEnabled(false);
      clienteAcessado = null;
      txCpf.requestFocus();
  }
  void btAcesso_actionPerformed(ActionEvent e) {
    if (txCpf.getText().trim().equals("")) {
      JOptionPane.showMessageDialog(this,"Digite um CPF","Mensagem de erro",JOptionPane.ERROR_MESSAGE);
      txCpf.requestFocus();
    } else {
      try {
        Cliente cli = fac.procurarCliente(txCpf.getText().trim());
        clienteAcessado = cli;
        txNome.setText(cli.getNome());
        mudaEstadoParaAltExc();
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,ex.getMessage(),"Mensagem de erro",JOptionPane.ERROR_MESSAGE);
        txCpf.requestFocus();
      }
    }
  }

  void btCancelar_actionPerformed(ActionEvent e) {
    if (txNome.isEnabled()) {
      limpaCamposChave();
      limpaCamposDados();
      mudaEstadoParaInicial();
    } else {
      txCpf.setText("");
      txCpf.requestFocus();
    }
  }

  void btIncluir_actionPerformed(ActionEvent e) {
    try {
      Cliente cli = new Cliente(txCpf.getText(),txNome.getText());
      fac.cadastrar(cli);
      JOptionPane.showMessageDialog(this,"Inclusão realizada com sucesso","Mensagem de confirmação",JOptionPane.PLAIN_MESSAGE);
      btCancelar_actionPerformed(null);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,ex.getMessage(),"Mensagem de erro",JOptionPane.ERROR_MESSAGE);
      txNome.requestFocus();
    }
  }

  void btAlterar_actionPerformed(ActionEvent e) {
    try {
      clienteAcessado.setCpf(txCpf.getText());
      clienteAcessado.setNome(txNome.getText());
      fac.atualizar(clienteAcessado);
      JOptionPane.showMessageDialog(this,"Alteração realizada com sucesso","Mensagem de confirmação",JOptionPane.PLAIN_MESSAGE);
      btCancelar_actionPerformed(null);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,ex.getMessage(),"Mensagem de erro",JOptionPane.ERROR_MESSAGE);
      txNome.requestFocus();
    }
  }

  void btExcluir_actionPerformed(ActionEvent e) {
    try {
      fac.descadastrarCliente(txCpf.getText());
      JOptionPane.showMessageDialog(this,"Exclusão realizada com sucesso","Mensagem de confirmação",JOptionPane.PLAIN_MESSAGE);
      btCancelar_actionPerformed(null);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,ex.getMessage(),"Mensagem de erro",JOptionPane.ERROR_MESSAGE);
      txNome.requestFocus();
    }
  }

  void this_windowOpened(WindowEvent e) {
    txCpf.requestFocus();
  }
}
