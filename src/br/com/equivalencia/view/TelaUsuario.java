/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.equivalencia.view;

import br.com.equivalencia.dao.ModuloConexao;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author asilva
 */
public class TelaUsuario extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void adicionar(){
        String sql = "insert into tb_usuarios(nome,fone,login,senha,perfil) values (?,?,?,sha2(?,512),?)";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtNomeUsuario.getText());
            pst.setString(2,txtCpf.getText());
            pst.setString(3,txtLogin.getText());
            pst.setString(4,txtSenha.getText());
            pst.setString(5,cmoPerfil.getSelectedItem().toString());
            
            
            if((txtNomeUsuario.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtLogin.getText().isEmpty()) || (txtSenha.getText().isEmpty())){
                JOptionPane.showMessageDialog(null,"Campo de preechimento obrigatório não foi preenchido.");
           
            }else{
                int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"Usuario Cadastrado com sucesso!.");
                    txtNomeUsuario.setText(null);
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
     private void consultar() {
        String sql = "select id as 'ID Usuário', nome as 'Nome Usuário', fone as 'Cpf Usuário', login as Login, senha as Senha, perfil as Perfil from tb_usuarios where nome like?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtConsultaUsuario.getText() + "%");
            rs = pst.executeQuery();
            tblUsuarioConsulta.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
    String sql="update tb_usuarios set nome=?, cpf=?, login=?,senha=sha2(?,512), perfil=? where id=?";
    
        try {
            pst=conexao.prepareStatement(sql);
            
            pst.setString(1,txtNomeUsuario.getText());
            pst.setString(2,txtCpf.getText());
            pst.setString(3,txtLogin.getText());
            pst.setString(4,txtSenha.getText());
            pst.setString(5,cmoPerfil.getSelectedItem().toString());
            pst.setString(6,txtIdUsuario.getText());

            // validação dos campos obrigatórios
            if((txtNomeUsuario.getText().isEmpty()) || (txtCpf.getText().isEmpty()) || (txtLogin.getText().isEmpty()) || (txtSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Campo de preenchimento obrigatório está em branco!");

            } else {

                int adicionado = pst.executeUpdate();
                // a linha abaixo serve de apoio ao entendimento da lógica
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Informações de Usuario alteradas com sucesso!");
                    // as linhas abaixo limpam os campos para que o usuario possa cadastrar um novo
                    //txtIdArea.setText(null);
                    //txtNomeArea.setText(null);

                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void excluir(){
        String sql =  "delete from tb_usuarios where id=?" ;
        
        int confirm = JOptionPane.showConfirmDialog(null, "Você tem certeza?","Atenção",JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION){
            try {
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdUsuario.getText());
                
                int apagado = pst.executeUpdate();

                if(apagado > 0 ){
                    JOptionPane.showMessageDialog(null, "Usuario apagado com sucesso.");
                    btnAdicionarArea.setEnabled(true);
                    btnExcluirArea.setEnabled(false);
                    btnEditarArea.setEnabled(false);
                    
                }
            }catch(Exception e1){
                 JOptionPane.showMessageDialog(null, e1);
            }
        }
    }
    public void setar_campos(){
    tblUsuarioConsulta.setVisible(true);
    int setar = tblUsuarioConsulta.getSelectedRow();
    txtIdUsuario.setText(tblUsuarioConsulta.getModel().getValueAt(setar,0).toString());
    txtNomeUsuario.setText(tblUsuarioConsulta.getModel().getValueAt(setar,1).toString());
    txtCpf.setText(tblUsuarioConsulta.getModel().getValueAt(setar,2).toString());
    txtLogin.setText(tblUsuarioConsulta.getModel().getValueAt(setar,3).toString());
    txtSenha.setText(tblUsuarioConsulta.getModel().getValueAt(setar,4).toString());
    btnAdicionarArea.setEnabled(false);
    btnEditarArea.setEnabled(true);
    btnExcluirArea.setEnabled(true);
}
    public void limpar_campos(){
        txtNomeUsuario.setText(null);
        txtCpf.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtIdUsuario.setText(null);
        
    }
    /**
     * Creates new form TelaArea
     */
    public TelaUsuario() {
        initComponents();
        tblUsuarioConsulta.setVisible(false);
        Color nova = new Color(0,0,0);
        getContentPane().setBackground(nova);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/equivalencia/imagens/tela-icon.png")));
        conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        btnAdicionarArea = new javax.swing.JButton();
        btnEditarArea = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnExcluirArea = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarioConsulta = new javax.swing.JTable();
        txtConsultaUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmoPerfil = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Equivalencia - Tela Area tecnologica");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 22));

        txtIdUsuario.setBackground(new java.awt.Color(204, 204, 204));
        txtIdUsuario.setEnabled(false);
        txtIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nome :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 22));

        txtNomeUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeUsuarioKeyPressed(evt);
            }
        });
        getContentPane().add(txtNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 105, -1));

        btnAdicionarArea.setBackground(new java.awt.Color(51, 255, 51));
        btnAdicionarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/add-area-icon.png"))); // NOI18N
        btnAdicionarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarArea.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAdicionarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarAreaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 48, 48));
        btnAdicionarArea.getAccessibleContext().setAccessibleDescription("");

        btnEditarArea.setBackground(new java.awt.Color(255, 255, 51));
        btnEditarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/editar-area-icon.png"))); // NOI18N
        btnEditarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarArea.setEnabled(false);
        btnEditarArea.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEditarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAreaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 48, 48));
        btnEditarArea.getAccessibleContext().setAccessibleDescription("");

        btnVoltar.setBackground(new java.awt.Color(255, 51, 102));
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/back.png"))); // NOI18N
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, 48, 48));
        btnVoltar.getAccessibleContext().setAccessibleDescription("");

        btnExcluirArea.setBackground(new java.awt.Color(255, 51, 51));
        btnExcluirArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/exluir-area-icon.png"))); // NOI18N
        btnExcluirArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirArea.setEnabled(false);
        btnExcluirArea.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnExcluirArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAreaActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluirArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 48, 48));
        btnExcluirArea.getAccessibleContext().setAccessibleDescription("");

        tblUsuarioConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Usuario", "Nome Usuario", "fone", "Login", "Perfil"
            }
        ));
        tblUsuarioConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarioConsulta);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 360, 230));

        txtConsultaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtConsultaUsuarioMouseClicked(evt);
            }
        });
        txtConsultaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaUsuarioActionPerformed(evt);
            }
        });
        txtConsultaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsultaUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtConsultaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 290, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pesquisar:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, 22));

        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCpfKeyPressed(evt);
            }
        });
        getContentPane().add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 100, -1));

        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });
        getContentPane().add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 105, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("fone :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, 22));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Login :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 22));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Senha");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 22));

        cmoPerfil.setForeground(new java.awt.Color(255, 255, 255));
        cmoPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Admin", " " }));
        getContentPane().add(cmoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 105, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Perfil");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 22));

        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, 320));

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, -1));

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdUsuarioActionPerformed

    private void btnAdicionarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarAreaActionPerformed
        adicionar();
        consultar();
        limpar_campos();
    }//GEN-LAST:event_btnAdicionarAreaActionPerformed

    private void btnEditarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAreaActionPerformed
        alterar();
        consultar();
        limpar_campos();
    }//GEN-LAST:event_btnEditarAreaActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
        TelaPrincipal principal = new TelaPrincipal();
        principal.setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnExcluirAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAreaActionPerformed
        excluir();
        consultar();
        limpar_campos();
    }//GEN-LAST:event_btnExcluirAreaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    private void txtConsultaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaUsuarioActionPerformed
        tblUsuarioConsulta.setVisible(true);
        consultar();
     
    }//GEN-LAST:event_txtConsultaUsuarioActionPerformed

    private void txtConsultaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultaUsuarioKeyReleased
        consultar();
    }//GEN-LAST:event_txtConsultaUsuarioKeyReleased

    private void tblUsuarioConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioConsultaMouseClicked
        setar_campos();
        consultar();
        
    }//GEN-LAST:event_tblUsuarioConsultaMouseClicked

    private void txtConsultaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtConsultaUsuarioMouseClicked
        tblUsuarioConsulta.setVisible(true);
        consultar();
    }//GEN-LAST:event_txtConsultaUsuarioMouseClicked

    private void txtNomeUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtCpf.requestFocus();
        }
    }//GEN-LAST:event_txtNomeUsuarioKeyPressed

    private void txtCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtLogin.requestFocus();
        }
    }//GEN-LAST:event_txtCpfKeyPressed

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtSenha.requestFocus();
        }
    }//GEN-LAST:event_txtLoginKeyPressed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarArea;
    private javax.swing.JButton btnEditarArea;
    private javax.swing.JButton btnExcluirArea;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmoPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarioConsulta;
    private javax.swing.JTextField txtConsultaUsuario;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
