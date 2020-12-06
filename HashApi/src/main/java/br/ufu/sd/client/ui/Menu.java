/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufu.sd.client.ui;

import br.ufu.sd.client.HashTableApi;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Giullia
 */
public class Menu extends javax.swing.JFrame {
    
    private HashTableApi client;

    /** Creates new form Principal */
    public Menu(HashTableApi client) {
        initComponents();
        this.client = client;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_cadastrar = new javax.swing.JButton();
        jButton_editar = new javax.swing.JButton();
        jButton_excluir = new javax.swing.JButton();
        jButton_consultar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro SD");

        jButton_cadastrar.setText("CADASTRAR");
        jButton_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cadastrarActionPerformed(evt);
            }
        });

        jButton_editar.setText("EDITAR");
        jButton_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editarActionPerformed(evt);
            }
        });

        jButton_excluir.setText("EXCLUIR");
        jButton_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_excluirActionPerformed(evt);
            }
        });

        jButton_consultar.setText("CONSULTAR");
        jButton_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_consultarActionPerformed(evt);
            }
        });

        jLabel1.setText("Bem Vindo Novamente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(371, 371, 371)
                                .addComponent(jButton_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(372, 372, 372)
                                .addComponent(jButton_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(jLabel2)))
                .addContainerGap(398, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_editar)
                    .addComponent(jButton_cadastrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_consultar)
                    .addComponent(jButton_excluir))
                .addGap(229, 229, 229))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cadastrarActionPerformed
        new Cadastrar(this.client).setVisible(true);
    }//GEN-LAST:event_jButton_cadastrarActionPerformed

    private void jButton_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_consultarActionPerformed
        new Consultar(this.client).setVisible(true);
    }//GEN-LAST:event_jButton_consultarActionPerformed

    private void jButton_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_excluirActionPerformed
        new Excluir(this.client).setVisible(true);
    }//GEN-LAST:event_jButton_excluirActionPerformed

    private void jButton_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editarActionPerformed
        new Editar(this.client).setVisible(true);
    }//GEN-LAST:event_jButton_editarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
        String target = "localhost:50051";
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        
        HashTableApi client = new HashTableApi(channel);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(client).setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cadastrar;
    private javax.swing.JButton jButton_consultar;
    private javax.swing.JButton jButton_editar;
    private javax.swing.JButton jButton_excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}