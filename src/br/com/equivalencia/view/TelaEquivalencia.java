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
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author asilva
 */
public class TelaEquivalencia extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private void pesquisar_area() {
        String sql = "select * from tb_area_tecnologica order by nome_area";
        txtIdArea.setText(null);
        txtIdCurso.setText(null);
        txtIdPpc.setText(null);
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            cboArea.removeAllItems();
            while (rs.next()) {
                cboArea.addItem(rs.getString(2));
                //cboArea.updateUI();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
        
    private void setar_id_area() {

        String sql = "select id_area from tb_area_tecnologica where nome_area=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboArea.getSelectedItem().toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtIdArea.setText(rs.getString(1));
            }
            cboCurso.removeAllItems();
            pesquisar_curso();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
    private void pesquisar_curso() {
        String sql = "select * from tb_cursos where id_area=?";
        txtIdCurso.setText(null);
        txtIdPpc.setText(null);
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdArea.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                cboCurso.addItem(rs.getString(2));
                //cboCurso.updateUI();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } 
    
    private void setar_id_curso() {

        String sql = "select id_curso from tb_cursos where nome_curso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboCurso.getSelectedItem().toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtIdCurso.setText(rs.getString(1));
                pesquisar_ppc();
            } else {
                txtIdCurso.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem cursos cadastrados para a área selecionada!");
        }
    }
    
    private void pesquisar_ppc() {
        String sql = "select * from tb_ppc where id_curso=? order by desc_ano";
        cboPpc.removeAllItems();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdCurso.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                cboPpc.addItem(rs.getString(2));
            }
            txtIdPpc.setText(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem planos de curso cadastrados para o curso selecionado!");
        }
    }
    
    private void setar_id_ppc() {

        String sql = "select id_ppc from tb_ppc where desc_ano=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboPpc.getSelectedItem().toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtIdPpc.setText(rs.getString(1));
            } else {
                txtIdPpc.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem cursos cadastrados para a área selecionada!");
        }
    }
    
    private void listar_disciplinas() {
        setar_id_ppc();
        String sql = "select id_disciplina as 'Id U.C.', nome_disciplina as 'Disciplina', ch_disciplina as 'C.H. da U.C.', ch_presencial as 'C.H. Presencial', ch_ead as 'C.H. EAD', id_grupo_equivalencia as 'Grupo Equivalência', id_ppc as 'PPC' from tb_disciplinas where id_ppc=?";
        tblDisciplinas.setEnabled(true);

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdPpc.getText());
            rs = pst.executeQuery();
            tblDisciplinas.setVisible(true);
            tblDisciplinas.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void pesquisar_area1() {
        String sql = "select * from tb_area_tecnologica order by nome_area";
        txtIdArea1.setText(null);
        txtIdCurso1.setText(null);
        txtIdPpc1.setText(null);
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            cboArea1.removeAllItems();
            while (rs.next()) {
                cboArea1.addItem(rs.getString(2));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    private void setar_id_area1() {

        String sql = "select id_area from tb_area_tecnologica where nome_area=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboArea1.getSelectedItem().toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtIdArea1.setText(rs.getString(1));
            }
            cboCurso1.removeAllItems();
            pesquisar_curso1();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } 
    
    private void pesquisar_curso1() {
        String sql = "select * from tb_cursos where id_area=?";
        txtIdCurso1.setText(null);
        txtIdPpc1.setText(null);
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdArea1.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                cboCurso1.addItem(rs.getString(2));
                //cboCurso.updateUI();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setar_id_curso1() {

        String sql = "select id_curso from tb_cursos where nome_curso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboCurso1.getSelectedItem().toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtIdCurso1.setText(rs.getString(1));
                pesquisar_ppc1();
            } else {
                txtIdCurso1.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem cursos cadastrados para a área selecionada!");
        }
    }
    
    private void pesquisar_ppc1() {
        String sql = "select * from tb_ppc where id_curso=? order by desc_ano";
        cboPpc1.removeAllItems();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdCurso1.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                cboPpc1.addItem(rs.getString(2));
            }
            txtIdPpc1.setText(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem planos de curso cadastrados para o curso selecionado!");
        }
    }
    
     private void setar_id_ppc1() {

        String sql = "select id_ppc from tb_ppc where desc_ano=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboPpc1.getSelectedItem().toString());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtIdPpc1.setText(rs.getString(1));
            } else {
                txtIdPpc1.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem planos de curso cadastrados para o curso selecionado!");
        }
    }
     
    private void listar_disciplinas1() {
        setar_id_ppc1();
        String sql = "select id_disciplina as 'Id U.C.', nome_disciplina as 'Disciplina', ch_disciplina as 'C.H. da U.C.', ch_presencial as 'C.H. Presencial', ch_ead as 'C.H. EAD', id_grupo_equivalencia as 'Grupo Equivalência', id_ppc as 'PPC' from tb_disciplinas where id_ppc=?";
        tblDisciplinas1.setEnabled(true);

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdPpc1.getText());
            rs = pst.executeQuery();
            tblDisciplinas1.setVisible(true);
            tblDisciplinas1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     public void compararListas() {

        DefaultTableModel tdm = (DefaultTableModel) tblDisciplinas.getModel();

        DefaultTableModel tdm2 = (DefaultTableModel) tblDisciplinas1.getModel();

        DefaultTableModel tdm3 = (DefaultTableModel) tblDisciplinas2.getModel();

        tdm3.setRowCount(0);

        tdm.getDataVector().forEach(l -> {
           
            tdm2.getDataVector().forEach(m -> {
               
                if (l.get(5).equals(m.get(5))) {
                    tdm3.addRow(new Object[]{
                        l.get(0),
                        l.get(1).toString().toUpperCase(),
                        l.get(2).toString(),
                        l.get(3).toString(),
                        l.get(4).toString(),
                        l.get(5).toString(),
                        l.get(6).toString()
                    });
                }
            });
        });
    }
    
    
    /**
         * Creates new form TelaArea
         */
    public TelaEquivalencia() {
        initComponents();
        tblDisciplinas.setVisible(false);
        Color nova = new Color(0, 0, 0);
        getContentPane().setBackground(nova);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/equivalencia/imagens/tela-icon.png")));
        conexao = ModuloConexao.conector();
        pesquisar_area();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisciplinas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDisciplinas1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDisciplinas2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cboArea = new javax.swing.JComboBox<>();
        cboPpc = new javax.swing.JComboBox<>();
        cboCurso = new javax.swing.JComboBox<>();
        cboCurso1 = new javax.swing.JComboBox<>();
        cboArea1 = new javax.swing.JComboBox<>();
        cboPpc1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtIdCurso = new javax.swing.JTextField();
        txtIdArea = new javax.swing.JTextField();
        txtIdPpc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIdArea1 = new javax.swing.JTextField();
        txtIdCurso1 = new javax.swing.JTextField();
        txtIdPpc1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

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

        tblDisciplinas.setModel(new javax.swing.table.DefaultTableModel(
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
                "Id ", "Nome", "Carga Horaria", "C.H Presencial", "C.H EAD"
            }
        ));
        tblDisciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisciplinasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDisciplinas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 440, 230));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 30, 270));

        tblDisciplinas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Carga Horária", "C.H Presencial", "C.H EAD"
            }
        ));
        jScrollPane2.setViewportView(tblDisciplinas1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, 230));

        tblDisciplinas2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "U.C", "C.H Total", "C.H EAD", "ID Grupo Equiv"
            }
        ));
        jScrollPane3.setViewportView(tblDisciplinas2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 780, 230));

        jButton1.setText("Verificar Equivalencia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 730, 150, -1));

        cboArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboAreaFocusLost(evt);
            }
        });
        getContentPane().add(cboArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 314, -1));

        cboPpc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPpcFocusLost(evt);
            }
        });
        getContentPane().add(cboPpc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 314, -1));

        cboCurso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboCursoFocusLost(evt);
            }
        });
        getContentPane().add(cboCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 314, -1));

        cboCurso1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboCurso1FocusLost(evt);
            }
        });
        getContentPane().add(cboCurso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 331, -1));

        cboArea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboArea1FocusLost(evt);
            }
        });
        getContentPane().add(cboArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 331, -1));

        cboPpc1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPpc1FocusLost(evt);
            }
        });
        getContentPane().add(cboPpc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 140, 331, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Id Area Tecnologica :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtIdCurso.setEnabled(false);
        getContentPane().add(txtIdCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 40, -1));

        txtIdArea.setEnabled(false);
        txtIdArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAreaActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 40, -1));

        txtIdPpc.setEnabled(false);
        getContentPane().add(txtIdPpc, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 40, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Id Plano de Cursos :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id Curso Técnico :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Id Área Técnologica :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Id Curso Técnico :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Id Planos de Cursos :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PPC Cursado");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 80, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PPC a Cursar");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, -1));

        txtIdArea1.setEnabled(false);
        getContentPane().add(txtIdArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 40, -1));

        txtIdCurso1.setEnabled(false);
        getContentPane().add(txtIdCurso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 40, -1));

        txtIdPpc1.setEnabled(false);
        txtIdPpc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPpc1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdPpc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 40, -1));

        jButton2.setText("Listar PPC");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 40, 40, 670));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 740, 1150, 30));

        jButton3.setText("Listar PCC");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 190, -1, -1));

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(TelaEquivalencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    private void tblDisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisciplinasMouseClicked
   
    }//GEN-LAST:event_tblDisciplinasMouseClicked

    private void txtIdAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAreaActionPerformed

    private void txtIdPpc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPpc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPpc1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        listar_disciplinas();
        pesquisar_area1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        listar_disciplinas1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       compararListas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboAreaFocusLost
        setar_id_area();
    }//GEN-LAST:event_cboAreaFocusLost

    private void cboCursoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboCursoFocusLost
        setar_id_curso();
    }//GEN-LAST:event_cboCursoFocusLost

    private void cboPpcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPpcFocusLost
        setar_id_ppc();
    }//GEN-LAST:event_cboPpcFocusLost

    private void cboArea1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboArea1FocusLost
        setar_id_area1();
    }//GEN-LAST:event_cboArea1FocusLost

    private void cboCurso1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboCurso1FocusLost
        setar_id_curso1();
    }//GEN-LAST:event_cboCurso1FocusLost

    private void cboPpc1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPpc1FocusLost
        setar_id_ppc1();
    }//GEN-LAST:event_cboPpc1FocusLost

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
            java.util.logging.Logger.getLogger(TelaEquivalencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEquivalencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEquivalencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEquivalencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEquivalencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboArea;
    private javax.swing.JComboBox<String> cboArea1;
    private javax.swing.JComboBox<String> cboCurso;
    private javax.swing.JComboBox<String> cboCurso1;
    private javax.swing.JComboBox<String> cboPpc;
    private javax.swing.JComboBox<String> cboPpc1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblDisciplinas;
    private javax.swing.JTable tblDisciplinas1;
    private javax.swing.JTable tblDisciplinas2;
    private javax.swing.JTextField txtIdArea;
    private javax.swing.JTextField txtIdArea1;
    private javax.swing.JTextField txtIdCurso;
    private javax.swing.JTextField txtIdCurso1;
    private javax.swing.JTextField txtIdPpc;
    private javax.swing.JTextField txtIdPpc1;
    // End of variables declaration//GEN-END:variables
}
