/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufu.sd.client.ui;

import br.ufu.sd.client.HashTableApi;
import br.ufu.sd.grpc.Saida;
import com.google.protobuf.ByteString;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Giullia
 */
public class Editar extends javax.swing.JFrame {

    private HashTableApi client;
    private Menu menu;
    /**
     * Creates new form Editar
     */
    public Editar(HashTableApi client, Menu menu) {
        initComponents();
        this.menu = menu;
        this.client = client;
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        chaveTexto = new javax.swing.JTextField();
        dadoTexto = new javax.swing.JTextField();
        botaoAlterar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        versaoTexto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("VOLTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("CHAVE");

        jLabel2.setText("NOVO DADO");

        chaveTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chaveTextoActionPerformed(evt);
            }
        });

        dadoTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dadoTextoActionPerformed(evt);
            }
        });

        botaoAlterar.setText("EDITAR");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        versaoTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                versaoTextoActionPerformed(evt);
            }
        });

        jLabel4.setText("VERSÃO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chaveTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(versaoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoAlterar))
                        .addComponent(dadoTexto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chaveTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(versaoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dadoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(botaoAlterar))))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chaveTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chaveTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chaveTextoActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        String texto;
        long chave = (long) Integer.parseInt(chaveTexto.getText());
        long versao = (long) Integer.parseInt(versaoTexto.getText());
        ByteString dado = ByteString.copyFrom(dadoTexto.getText().getBytes());
        
        Saida saida = this.client.testAndSet(chave, versao, dado);
        if(saida == null){
            texto = "Um erro insesperado aconteceu";
        }
        else if(saida.getValue().getTimeSt() == 0){
            texto = "A chave " + chave + " nao existe no banco";
        }else if(saida.getError().equals("ERROR_WV")){
            texto = "A chave " + chave + " na versao " + versao + " nao existe no banco";
        }else{
            texto = "Atualizado, os novos valores sao:\n"
                    + "Versão: " + saida.getValue().getVersion() + "\n"
                    + "TimeStamp: " + new Date(saida.getValue().getTimeSt()) + "\n"
                    + "Dado: " + saida.getValue().getData().toStringUtf8();
        }
        JOptionPane.showMessageDialog(this, texto);        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void versaoTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_versaoTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_versaoTextoActionPerformed

    private void dadoTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dadoTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dadoTextoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JTextField chaveTexto;
    private javax.swing.JTextField dadoTexto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField versaoTexto;
    // End of variables declaration//GEN-END:variables
}
