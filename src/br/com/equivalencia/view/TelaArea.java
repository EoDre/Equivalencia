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
public class TelaArea extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void adicionar(){
        String sql = "insert into tb_area_tecnologica(nome_area) values (?)";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtNomeArea.getText());
            
            if(txtNomeArea.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campo de preechimento obrigatório não foi preenchido.");
            }else{
                int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"Área Tecnologica cadastrada com sucesso!.");
                    txtNomeArea.setText(null);
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
     private void consultar() {
        String sql = "select id_area as Id, nome_area as Área from tb_area_tecnologica where nome_area like ?";

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
    String sql="update tb_area_tecnologica set nome_area=? where id_area=?";
    
        try {
            pst=conexao.prepareStatement(sql);
            
            pst.setString(1,txtNomeArea.getText());
            pst.setString(2,txtIdArea.getText());

            // validação dos campos obrigatórios
            if ((txtIdArea.getText().isEmpty()) || (txtNomeArea.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Campo de preenchimento obrigatório está em branco!");

            } else {

                int adicionado = pst.executeUpdate();
                // a linha abaixo serve de apoio ao entendimento da lógica
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Informações de Área Tecnológica alteradas com sucesso!");
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
        String sql =  "delete from tb_area_tecnologica where id_area=?" ;
        
        int confirm = JOptionPane.showConfirmDialog(null, "Você tem certeza?","Atenção",JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION){
            try {
                
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdArea.getText());
                
                int apagado = pst.executeUpdate();

                if(apagado > 0 ){
                    JOptionPane.showMessageDialog(null, "Area Tecnologica apagada com sucesso.");
                    txtIdArea.setText(null);
                    txtNomeArea.setText(null);
                    btnAdicionarArea.setEnabled(true);
                    btnExcluirArea.setEnabled(false);
                    btnEditarArea.setEnabled(false);
                    
                }
            } catch(java.sql.SQLIntegrityConstraintViolationException e){
                JOptionPane.showMessageDialog(null,"A area não pode ser deletada.\nTente deletar os cursos vinculados a ela antes de apagar a mesma.");
            }catch(Exception e1){
                 JOptionPane.showMessageDialog(null, e1);
            }
        }
    }
    public void setar_campos(){
    tblAreaConsulta.setVisible(true);
    int setar = tblAreaConsulta.getSelectedRow();
    txtIdArea.setText(tblAreaConsulta.getModel().getValueAt(setar,0).toString());
    txtNomeArea.setText(tblAreaConsulta.getModel().getValueAt(setar,1).toString());
    btnAdicionarArea.setEnabled(false);
    btnEditarArea.setEnabled(true);
    btnExcluirArea.setEnabled(true);
}
    
    /**
     * Creates new form TelaArea
     */
    public TelaArea() {
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

        jLabel1 = new javax.swing.JLabel();
        txtIdArea = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeArea = new javax.swing.JTextField();
        btnAdicionarArea = new javax.swing.JButton();
        btnEditarArea = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnExcluirArea = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAreaConsulta = new javax.swing.JTable();
        txtConsultaArea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Equivâlencia - Área Tecnologica");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        txtIdArea.setEditable(false);
        txtIdArea.setBackground(new java.awt.Color(204, 204, 204));
        txtIdArea.setEnabled(false);
        txtIdArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAreaActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nome:");

        btnAdicionarArea.setBackground(new java.awt.Color(51, 255, 51));
        btnAdicionarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/add-area-icon.png"))); // NOI18N
        btnAdicionarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarArea.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAdicionarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarAreaActionPerformed(evt);
            }
        });

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

        btnVoltar.setBackground(new java.awt.Color(255, 51, 102));
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/back.png"))); // NOI18N
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

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

        tblAreaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Área", "Nome Area"
            }
        ));
        tblAreaConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAreaConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAreaConsulta);

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

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pesquisar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluirArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomeArea, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtConsultaArea, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtConsultaArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluirArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(45, Short.MAX_VALUE))))
        );

        btnAdicionarArea.getAccessibleContext().setAccessibleDescription("");
        btnEditarArea.getAccessibleContext().setAccessibleDescription("");
        btnVoltar.getAccessibleContext().setAccessibleDescription("");
        btnExcluirArea.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAreaActionPerformed

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
            Logger.getLogger(TelaArea.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(TelaArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaArea().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAreaConsulta;
    private javax.swing.JTextField txtConsultaArea;
    private javax.swing.JTextField txtIdArea;
    private javax.swing.JTextField txtNomeArea;
    // End of variables declaration//GEN-END:variables
}
