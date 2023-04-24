/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.equivalencia.view;

import br.com.equivalencia.dao.ModuloConexao;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author asilva
 */
public class TelaPPC extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void adicionar(){
        String sql = "insert into tb_ppc(desc_ano,ch_curso,modalidade,id_curso) values (?,?,?,?)";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtAno.getText());
            pst.setString(2,txtCargaH.getText());
            pst.setString(3,txtModalidade.getText());
            pst.setString(4,txtIdCurso.getText());
            
            if(txtCargaH.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campo de preechimento obrigatório não foi preenchido.");
            }else{
                int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"PPC cadastrado com sucesso!.");
                    txtCargaH.setText(null);
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
     private void consultar() {
        String sql = "select id_ppc as 'Id PPC', ch as 'Carga Horaria', ano as ano from ppc where ano like?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtConsultaArea.getText() + "%");
            rs = pst.executeQuery();
            tblAreaConsulta.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void alterar(){
    String sql="update ppc set ch=?, ano=?  where id_ppc=?";
    
        try {
            pst=conexao.prepareStatement(sql);
            
            pst.setString(1,txtCargaH.getText());
            pst.setString(2,txtAno.getText());
            pst.setString(3,txtIdPpc.getText());

            // validação dos campos obrigatórios
            if ((txtIdPpc.getText().isEmpty()) || (txtCargaH.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Campo de preenchimento obrigatório está em branco!");

            } else {

                int adicionado = pst.executeUpdate();
                // a linha abaixo serve de apoio ao entendimento da lógica
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Informações do PPC foram alteradas com sucesso!");
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
        String sql =  "delete from ppc where id_ppc=?" ;
        
        int confirm = JOptionPane.showConfirmDialog(null, "Você tem certeza?","Atenção",JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION){
            try {
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdPpc.getText());
                
                int apagado = pst.executeUpdate();

                if(apagado > 0 ){
                    JOptionPane.showMessageDialog(null, "PPC apagado com sucesso.");
                    txtIdPpc.setText(null);
                    txtCargaH.setText(null);
                    btnAdicionarArea.setEnabled(true);
                    btnExcluirArea.setEnabled(false);
                    btnEditarArea.setEnabled(false);
                    
                }
            } catch(java.sql.SQLIntegrityConstraintViolationException e){
                JOptionPane.showMessageDialog(null,"O ppc não pode ser deletado.\nTente deletar os cursos vinculados a ele antes de apagar o mesmo.");
            }catch(Exception e1){
                 JOptionPane.showMessageDialog(null, e1);
            }
        }
    }
    public void setar_campos(){
    tblAreaConsulta.setVisible(true);
    int setar = tblAreaConsulta.getSelectedRow();
    txtIdPpc.setText(tblAreaConsulta.getModel().getValueAt(setar,0).toString());
    txtCargaH.setText(tblAreaConsulta.getModel().getValueAt(setar,1).toString());
    txtAno.setText(tblAreaConsulta.getModel().getValueAt(setar,2).toString());
    btnAdicionarArea.setEnabled(false);
    btnEditarArea.setEnabled(true);
    btnExcluirArea.setEnabled(true);
}
    
    /**
     * Creates new form TelaArea
     */
    public TelaPPC() {
        initComponents();
        tblAreaConsulta.setVisible(false);
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

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdPpc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCargaH = new javax.swing.JTextField();
        btnAdicionarArea = new javax.swing.JButton();
        btnEditarArea = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnExcluirArea = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAreaConsulta = new javax.swing.JTable();
        txtConsultaArea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtModalidade = new javax.swing.JTextField();
        txtIdCurso = new javax.swing.JTextField();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

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
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 30, 22));

        txtIdPpc.setBackground(new java.awt.Color(204, 204, 204));
        txtIdPpc.setEnabled(false);
        txtIdPpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPpcActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdPpc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 105, 22));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Carga Hr :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 22));
        getContentPane().add(txtCargaH, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 105, 22));

        btnAdicionarArea.setBackground(new java.awt.Color(51, 255, 51));
        btnAdicionarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/add-area-icon.png"))); // NOI18N
        btnAdicionarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarArea.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAdicionarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarAreaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 48, 48));
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
        getContentPane().add(btnEditarArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 48, 48));
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
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 48, 48));
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
        getContentPane().add(btnExcluirArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 48, 48));
        btnExcluirArea.getAccessibleContext().setAccessibleDescription("");

        tblAreaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id PPc", "Carga Horaria", "Ano"
            }
        ));
        tblAreaConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAreaConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAreaConsulta);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 300, 230));

        txtConsultaArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtConsultaAreaMouseClicked(evt);
            }
        });
        txtConsultaArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaAreaActionPerformed(evt);
            }
        });
        txtConsultaArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsultaAreaKeyReleased(evt);
            }
        });
        getContentPane().add(txtConsultaArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 220, 22));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pesquisar :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 80, 22));
        getContentPane().add(txtAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 105, 22));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ano :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 22));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 30, 310));

        txtModalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModalidadeActionPerformed(evt);
            }
        });
        getContentPane().add(txtModalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 110, -1));
        getContentPane().add(txtIdCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 110, -1));

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdPpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPpcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPpcActionPerformed

    private void btnAdicionarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarAreaActionPerformed
        adicionar();
        consultar();
    }//GEN-LAST:event_btnAdicionarAreaActionPerformed

    private void btnEditarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAreaActionPerformed
        alterar();
        consultar();
    }//GEN-LAST:event_btnEditarAreaActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
        TelaPrincipal principal = new TelaPrincipal();
        principal.setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnExcluirAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAreaActionPerformed
        excluir();
        consultar();
    }//GEN-LAST:event_btnExcluirAreaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    private void txtConsultaAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaAreaActionPerformed
        tblAreaConsulta.setVisible(true);
        consultar();
     
    }//GEN-LAST:event_txtConsultaAreaActionPerformed

    private void txtConsultaAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultaAreaKeyReleased
        consultar();
    }//GEN-LAST:event_txtConsultaAreaKeyReleased

    private void tblAreaConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAreaConsultaMouseClicked
        setar_campos();
        consultar();
    }//GEN-LAST:event_tblAreaConsultaMouseClicked

    private void txtConsultaAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtConsultaAreaMouseClicked
        tblAreaConsulta.setVisible(true);
        consultar();
    }//GEN-LAST:event_txtConsultaAreaMouseClicked

    private void txtModalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModalidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModalidadeActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPPC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarArea;
    private javax.swing.JButton btnEditarArea;
    private javax.swing.JButton btnExcluirArea;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAreaConsulta;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtCargaH;
    private javax.swing.JTextField txtConsultaArea;
    private javax.swing.JTextField txtIdCurso;
    private javax.swing.JTextField txtIdPpc;
    private javax.swing.JTextField txtModalidade;
    // End of variables declaration//GEN-END:variables
}
