/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.view.equipamentos;

import br.com.vitorgreati.model.CategoriaEquipamento;
import br.com.vitorgreati.model.Equipamento;
import br.com.vitorgreati.persistence.CategoriaEquipamentoDAO;
import br.com.vitorgreati.persistence.EquipamentoDAO;
import br.com.vitorgreati.persistence.UsuarioDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vitor
 */
public class TelaSelecionarEquipamento extends javax.swing.JFrame {

    public static final int EXCLUIR = 0;
    public static final int ATUALIZAR = 1;
    public static final int RESERVAR = 2;
    private CategoriaEquipamentoDAO categoriaDAO;
    private EquipamentoDAO equipamentoDAO;
    private UsuarioDAO usuarioDAO;
    private int modo = 0;
    private Equipamento equipamento = null;
    private JFrame frameInvocador;

    /**
     * Creates new form TelaBuscarEquipamento
     */
    public TelaSelecionarEquipamento(int modo, JFrame frameInvocador) {
        initComponents();

        setLocationRelativeTo(null);

        categoriaDAO = new CategoriaEquipamentoDAO();
        usuarioDAO = new UsuarioDAO();
        equipamentoDAO = new EquipamentoDAO();

        preencherCategorias();
        preencherTabela(equipamentoDAO.listar());
        
        this.frameInvocador = frameInvocador;

        cbDataCadastro.setVisible(false);
        snDataCadastro.setVisible(false);
        
        this.modo = modo;

        switch (modo) {
            case EXCLUIR:
                btSelecionar.setText("Excluir");
                setTitle("Excluir equipamento");
                break;
            case ATUALIZAR:
                btSelecionar.setText("Atualizar...");
                setTitle("Selecionar equipamento para atualizar");
                break;
            case RESERVAR:
                btSelecionar.setText("Reservar...");
                setTitle("Selecionar equipamento para reservar");
                break;
        }
    }

    private void preencherCategorias() {
        DefaultListModel model = new DefaultListModel();
        for (CategoriaEquipamento c : categoriaDAO.listar()) {
            model.addElement(c);
        }
        ltCategorias.setModel(model);
    }

    private void preencherTabela(ArrayList<Equipamento> equipamentos) {
        DefaultTableModel model = (DefaultTableModel) tbEquipamentos.getModel();
        model.setRowCount(0);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Equipamento e : equipamentos) {
            model.addRow(new String[]{String.valueOf(e.getId()), e.getNome(), e.getCategoria()!=null ? e.getCategoria().getNome() : "-",
                        format.format(e.getDataCadastro()), e.getCadastradoPor().getNome(), String.valueOf(e.getQuantidade())});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        pnTudo = new javax.swing.JPanel();
        pnParametros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ltCategorias = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        tfFabricante = new javax.swing.JTextField();
        snDataCadastro = new javax.swing.JSpinner();
        cbDataCadastro = new javax.swing.JCheckBox();
        btLimpar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        pnResultados = new javax.swing.JPanel();
        btCancelar = new javax.swing.JButton();
        btDetalhes = new javax.swing.JButton();
        btSelecionar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEquipamentos = new javax.swing.JTable();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar equipamento");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnParametros.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por"));
        pnParametros.setPreferredSize(new java.awt.Dimension(800, 189));

        jLabel1.setText("Nome:");

        tfNome.setPreferredSize(new java.awt.Dimension(609, 20));

        jLabel2.setText("Categorias:");

        jScrollPane2.setViewportView(ltCategorias);

        jLabel3.setText("Fabricante:");

        snDataCadastro.setModel(new javax.swing.SpinnerDateModel());
        snDataCadastro.setEnabled(false);
        snDataCadastro.setPreferredSize(new java.awt.Dimension(108, 20));

        cbDataCadastro.setText("Cadastrado após:");
        cbDataCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDataCadastroActionPerformed(evt);
            }
        });

        btLimpar.setText("Limpar");
        btLimpar.setPreferredSize(new java.awt.Dimension(71, 23));
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        btBuscar.setText("Buscar");
        btBuscar.setPreferredSize(new java.awt.Dimension(89, 23));
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnParametrosLayout = new javax.swing.GroupLayout(pnParametros);
        pnParametros.setLayout(pnParametrosLayout);
        pnParametrosLayout.setHorizontalGroup(
            pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParametrosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParametrosLayout.createSequentialGroup()
                        .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnParametrosLayout.createSequentialGroup()
                                .addComponent(tfFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(cbDataCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(snDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnParametrosLayout.setVerticalGroup(
            pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(snDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDataCadastro))
                .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParametrosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnParametrosLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(pnParametrosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(pnParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pnParametrosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {snDataCadastro, tfFabricante, tfNome});

        pnResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamentos"));
        pnResultados.setPreferredSize(new java.awt.Dimension(800, 342));

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btDetalhes.setText("Detalhes");
        btDetalhes.setEnabled(false);
        btDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDetalhesActionPerformed(evt);
            }
        });

        btSelecionar.setText("Selecionar");
        btSelecionar.setEnabled(false);
        btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarActionPerformed(evt);
            }
        });

        tbEquipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Categoria", "Cadastrado em", "Cadastrado por", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEquipamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEquipamentosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbEquipamentos);

        javax.swing.GroupLayout pnResultadosLayout = new javax.swing.GroupLayout(pnResultados);
        pnResultados.setLayout(pnResultadosLayout);
        pnResultadosLayout.setHorizontalGroup(
            pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnResultadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btSelecionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDetalhes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        pnResultadosLayout.setVerticalGroup(
            pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btCancelar)
                        .addComponent(btDetalhes))
                    .addComponent(btSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnResultadosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btDetalhes, btSelecionar});

        javax.swing.GroupLayout pnTudoLayout = new javax.swing.GroupLayout(pnTudo);
        pnTudo.setLayout(pnTudoLayout);
        pnTudoLayout.setHorizontalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnParametros, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                    .addComponent(pnResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pnTudoLayout.setVerticalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarActionPerformed
        switch (modo) {
            case EXCLUIR:
                int linha = tbEquipamentos.getSelectedRow();
                int id = Integer.parseInt((String) tbEquipamentos.getValueAt(linha, 0));
                int opcao = JOptionPane.showConfirmDialog(null, "Excluir permanentemente este equipamento?", "Confirmar exclusão", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcao == 0) {
                    AvatarTools.excluirAvatar(AvatarTools.AVATAR_USUARIO, id);
                    equipamentoDAO.excluir(new Equipamento(id));
                    JOptionPane.showMessageDialog(null, "O equipamento foi excluído.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    this.frameInvocador.setEnabled(true);
                    dispose();
                }
                break;
            case ATUALIZAR:
                linha = tbEquipamentos.getSelectedRow();
                id = Integer.parseInt((String) tbEquipamentos.getValueAt(linha, 0));
                new TelaEditorEquipamento(equipamentoDAO.buscarPorId(id),this.frameInvocador).setVisible(true);
                //setEnabled(false);
                dispose();
                break;
            case RESERVAR:
                linha = tbEquipamentos.getSelectedRow();
                id = Integer.parseInt((String) tbEquipamentos.getValueAt(linha, 0));
                new TelaReservarEquipamento(equipamentoDAO.buscarPorId(id),this.frameInvocador).setVisible(true);
                //setEnabled(false);
                dispose();
                break;

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btSelecionarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        String nome = tfNome.getText();
        String fabricante = tfFabricante.getText();

        Date dataCadastro = null;
        if (cbDataCadastro.isSelected()) {
            dataCadastro = (Date) snDataCadastro.getModel().getValue();
        }

        //fazer ele poder selecionar mais de uma
        ArrayList<CategoriaEquipamento> categorias = null; //= new ArrayList<CategoriaEquipamento>();
        if (!ltCategorias.getSelectedValuesList().isEmpty()) {
            categorias = new ArrayList<CategoriaEquipamento>();
            for (CategoriaEquipamento c : (ArrayList<CategoriaEquipamento>) ltCategorias.getSelectedValuesList()) {
                categorias.add(categoriaDAO.buscarPorNome(c.getNome()));
            }
        }

        ArrayList<Equipamento> equipamentos = equipamentoDAO.buscarOtimizado(nome, fabricante, dataCadastro, categorias);
        if(equipamentos.isEmpty()) ativarDesativarBotoesPelaTabela();
        //System.out.println(equipamentos.size());
        
        DefaultTableModel model = (DefaultTableModel) tbEquipamentos.getModel();
        model.setRowCount(0);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Equipamento e : equipamentos) {
            System.out.println(e.getNome());
            model.addRow(new String[]{String.valueOf(e.getId()), e.getNome(), e.getCategoria().getNome(),
                        format.format(e.getDataCadastro()), e.getCadastradoPor().getNome(), String.valueOf(e.getQuantidade())});
            System.out.println(model.getRowCount());
        }
        tbEquipamentos.setModel(model);
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscarActionPerformed

    private void cbDataCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDataCadastroActionPerformed
        if (!cbDataCadastro.isSelected()) {
            snDataCadastro.setEnabled(false);
        } else {
            snDataCadastro.setEnabled(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cbDataCadastroActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.frameInvocador.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDetalhesActionPerformed
        int linha = tbEquipamentos.getSelectedRow();
        int id = Integer.parseInt((String) tbEquipamentos.getValueAt(linha, 0));
        new TelaDetalhesEquipamento(equipamentoDAO.buscarPorId(id),this).setVisible(true);        
        setEnabled(false);        
        // TODO add your handling code here:
    }//GEN-LAST:event_btDetalhesActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.frameInvocador.setEnabled(true);
        dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        tfNome.setText("");
        tfFabricante.setText("");
        cbDataCadastro.setEnabled(false);
        ltCategorias.clearSelection();
// TODO add your handling code here:
    }//GEN-LAST:event_btLimparActionPerformed

    private void ativarDesativarBotoesPelaTabela(){
        if(tbEquipamentos.getSelectedRow() != -1){
            btSelecionar.setEnabled(true);
            btDetalhes.setEnabled(true);
        }else{
            btSelecionar.setEnabled(false);
            btDetalhes.setEnabled(false);
        }
    }
    
    private void tbEquipamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEquipamentosMouseClicked

        ativarDesativarBotoesPelaTabela();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEquipamentosMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSelecionarEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSelecionarEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSelecionarEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSelecionarEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSelecionarEquipamento(0,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btDetalhes;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btSelecionar;
    private javax.swing.JCheckBox cbDataCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList ltCategorias;
    private javax.swing.JPanel pnParametros;
    private javax.swing.JPanel pnResultados;
    private javax.swing.JPanel pnTudo;
    private javax.swing.JSpinner snDataCadastro;
    private javax.swing.JTable tbEquipamentos;
    private javax.swing.JTextField tfFabricante;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}