/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.view.equipamentos;

import br.com.vitorgreati.model.CategoriaEquipamento;
import br.com.vitorgreati.model.Equipamento;
import br.com.vitorgreati.model.SessaoUsuario;
import br.com.vitorgreati.persistence.CategoriaEquipamentoDAO;
import br.com.vitorgreati.persistence.EquipamentoDAO;
import br.com.vitorgreati.persistence.UsuarioDAO;
import java.awt.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Vitor
 */
public class TelaEditorEquipamento extends javax.swing.JFrame {

    private EquipamentoDAO equipamentoDAO;
    private UsuarioDAO usuarioDAO;
    private CategoriaEquipamentoDAO categoriaDAO;
    
    private static final int CADASTRAR_NOVO = 0;
    private static final int EDITAR_EXISTENTE = 1;
    
    private Equipamento equipamentoEditavel = null;
    
    private int modo = 0;
    
    private String pathNovaImagem;
    
    private JFrame frameInvocador;
    
    public TelaEditorEquipamento(JFrame frameInvocador){
        initComponents();
        setLocationRelativeTo(null);
        
        this.frameInvocador = frameInvocador;
        
    }
    /**
     * Creates new form TelaNovoEquipamento
     */
    public TelaEditorEquipamento(Equipamento equipamento, JFrame frameInvocador) {
        initComponents();
        
        equipamentoDAO = new EquipamentoDAO();
        usuarioDAO = new UsuarioDAO();
        categoriaDAO = new CategoriaEquipamentoDAO();
        this.frameInvocador = frameInvocador;
        this.equipamentoEditavel = equipamento;
        
        preencherCategorias();
        // setando o modo de trabalho
        if(equipamento == null) {
            modo = 0;
        }else {
            modo = 1;
            btLimpar.setText("Cancelar");
            preencherCamposParaAtualizar(equipamentoEditavel);
        }
        
        setLocationRelativeTo(null);
    }
    
    public void preencherCategorias(){
        ArrayList<CategoriaEquipamento> categorias = categoriaDAO.listar();
        categorias.add(0, new CategoriaEquipamento("",""));
        ComboBoxModel model = new DefaultComboBoxModel(categoriaDAO.listar().toArray());
        cbCategoria.setModel(model);
    }
    
    private void preencherCamposParaAtualizar(Equipamento equipamento){
        tfEquipamento.setText(equipamento.getNome());
        tfFabricante.setText(equipamento.getFabricante());
        tfTombamento.setText(equipamento.getTombamento());
        if(equipamento.getCategoria()!=null) 
            cbCategoria.setSelectedItem(equipamento.getCategoria());
        else
            cbCategoria.setSelectedIndex(0);
        taDescricao.setText(equipamento.getDescricao());
        taInstrucoes.setText(equipamento.getInstrucoes());
        taPrecaucoes.setText(equipamento.getPrecaucoes());
        snQuantidade.setValue(equipamento.getQuantidade());
        
        try{
            lbImagem.setIcon(new ImageIcon(AvatarTools.getAvatar(AvatarTools.AVATAR_EQUIPAMENTO, equipamentoEditavel.getId(),lbImagem.getWidth(), lbImagem.getHeight())));
        }catch(IOException e){}        
        setTitle("Atualizar equipamento");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTudo = new javax.swing.JPanel();
        pnCamposCadastro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfEquipamento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFabricante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescricao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taInstrucoes = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taPrecaucoes = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        snQuantidade = new javax.swing.JSpinner();
        btLimpar = new javax.swing.JButton();
        btCadastrar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox();
        lbImagem = new javax.swing.JLabel();
        btBuscarImagem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tfTombamento = new javax.swing.JTextField();
        btNovaCategoria = new javax.swing.JButton();
        btNovaCategoria1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Equipamento");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Equipamento:");

        jLabel2.setText("Fabricante:");

        jLabel3.setText("Descrição:");

        taDescricao.setColumns(20);
        taDescricao.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        taDescricao.setRows(5);
        jScrollPane1.setViewportView(taDescricao);

        jLabel4.setText("Como usar:");

        taInstrucoes.setColumns(20);
        taInstrucoes.setRows(5);
        jScrollPane2.setViewportView(taInstrucoes);

        jLabel5.setText("Precauções:");

        taPrecaucoes.setColumns(20);
        taPrecaucoes.setRows(5);
        jScrollPane3.setViewportView(taPrecaucoes);

        jLabel6.setText("Quantidade:");

        snQuantidade.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));

        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        btCadastrar.setText("Salvar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        jLabel7.setText("Categoria:");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vidraria" }));

        lbImagem.setBackground(new java.awt.Color(255, 255, 255));
        lbImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lbImagem.setOpaque(true);

        btBuscarImagem.setText("Buscar imagem...");
        btBuscarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarImagemActionPerformed(evt);
            }
        });

        jLabel8.setText("Tombamento:");

        btNovaCategoria.setText("Nova...");
        btNovaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovaCategoriaActionPerformed(evt);
            }
        });

        btNovaCategoria1.setText("Excluir...");
        btNovaCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovaCategoria1ActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCamposCadastroLayout = new javax.swing.GroupLayout(pnCamposCadastro);
        pnCamposCadastro.setLayout(pnCamposCadastroLayout);
        pnCamposCadastroLayout.setHorizontalGroup(
            pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(11, 11, 11)
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)))
                    .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btBuscarImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCamposCadastroLayout.createSequentialGroup()
                                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCamposCadastroLayout.createSequentialGroup()
                                                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jLabel7))
                                                .addGap(14, 14, 14))
                                            .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(20, 20, 20)))
                                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                                                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(cbCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                                                        .addComponent(snQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel8)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfTombamento)
                                                    .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                                                        .addComponent(btNovaCategoria)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btNovaCategoria1)
                                                        .addGap(0, 0, Short.MAX_VALUE))))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCamposCadastroLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCamposCadastroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar)))
                .addContainerGap())
        );
        pnCamposCadastroLayout.setVerticalGroup(
            pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCamposCadastroLayout.createSequentialGroup()
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(13, 13, 13)
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(snQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(tfTombamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscarImagem)
                    .addComponent(btNovaCategoria)
                    .addComponent(btNovaCategoria1))
                .addGap(27, 27, 27)
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(pnCamposCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        pnCamposCadastroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btNovaCategoria, cbCategoria, snQuantidade, tfEquipamento, tfFabricante, tfTombamento});

        pnCamposCadastroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3});

        javax.swing.GroupLayout pnTudoLayout = new javax.swing.GroupLayout(pnTudo);
        pnTudo.setLayout(pnTudoLayout);
        pnTudoLayout.setHorizontalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCamposCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTudoLayout.setVerticalGroup(
            pnTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCamposCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed

        String nome = tfEquipamento.getText();
        String fabricante = tfFabricante.getText();
        String tombamento = tfTombamento.getText();
        String categoria = cbCategoria.getSelectedItem().toString();
        String descricao = taDescricao.getText();
        String precaucoes = taPrecaucoes.getText();
        String instrucoes = taInstrucoes.getText();
        int quantidade = (int) snQuantidade.getModel().getValue();
        
        if(nome.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o nome do equipamento.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }else if(fabricante.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o fabricante do equipamento.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }else if(tombamento.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o tombamento do equipamento.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }else if(quantidade < 0){
            JOptionPane.showMessageDialog(null, "Quantidades negativas não são aceitas.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }else if(categoria.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Escolha uma categoria.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }else{
            switch(modo){
                case CADASTRAR_NOVO:
                    Equipamento equipamento = new Equipamento(nome,fabricante,descricao,instrucoes,precaucoes,
                            new Date(),quantidade,SessaoUsuario.getUsuario(),categoriaDAO.buscarPorNome(categoria),tombamento);
                    equipamentoDAO.cadastrar(equipamento);
                    if(this.pathNovaImagem != null){
                        AvatarTools.salvarAvatar(AvatarTools.AVATAR_EQUIPAMENTO,this.pathNovaImagem, equipamento.getId());
                    }                
                    JOptionPane.showMessageDialog(null, "O equipamento foi cadastrado.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    this.frameInvocador.setEnabled(true);
                    dispose();
                    break;

                case EDITAR_EXISTENTE:
                    equipamento = new Equipamento(equipamentoEditavel.getId(),nome,fabricante,descricao,instrucoes,precaucoes,
                            new Date(),quantidade,SessaoUsuario.getUsuario(),categoriaDAO.buscarPorNome(categoria),tombamento);
                    equipamentoDAO.atualizar(equipamento);
                    if(this.pathNovaImagem != null){
                        AvatarTools.salvarAvatar(AvatarTools.AVATAR_EQUIPAMENTO,this.pathNovaImagem, equipamentoEditavel.getId());
                    }
                    JOptionPane.showMessageDialog(null, "O equipamento foi atualizado.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    
                    if(this.frameInvocador instanceof TelaBuscarEquipamento)
                        ((TelaBuscarEquipamento)frameInvocador).preencherDepoisDeAtualizar(equipamento);
                    
                    this.frameInvocador.setEnabled(true);
                    dispose();
                    break;
            }
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btNovaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovaCategoriaActionPerformed
        new TelaNovaCategoriaEquipamento(this).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btNovaCategoriaActionPerformed

    private void btBuscarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarImagemActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        this.pathNovaImagem = chooser.getSelectedFile().getPath();
        try{
            lbImagem.setIcon(new ImageIcon(AvatarTools.getAvatarTemporario(pathNovaImagem, lbImagem.getWidth(), lbImagem.getHeight())));
        }catch(IOException e){}
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscarImagemActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        for(Component p : pnCamposCadastro.getComponents()){
            if(p instanceof JTextComponent){
                ((JTextComponent) p).setText("");
            }
            if(p instanceof JComboBox){
                ((JComboBox)p).setSelectedIndex(0);
            }
        }
        taDescricao.setText("");
        taInstrucoes.setText("");
        taPrecaucoes.setText("");
        snQuantidade.getModel().setValue(1);
        lbImagem.setIcon(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_btLimparActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.frameInvocador.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btNovaCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovaCategoria1ActionPerformed
        CategoriaEquipamento categoria = categoriaDAO.buscarPorNome(cbCategoria.getSelectedItem().toString());
        if(categoria!=null){
            int opcao = JOptionPane.showConfirmDialog(null, "Excluir categoria "+categoria.getNome()+"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(opcao == 0){
                categoriaDAO.excluir(categoria);
                cbCategoria.removeItemAt(cbCategoria.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "A categoria foi excluída.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma categoria.", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btNovaCategoria1ActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        //if(equipamentoEditavel!=null){
            if(this.pathNovaImagem != null){
                int opcao = JOptionPane.showConfirmDialog(null, "Sair sem salvar alterações?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(opcao == 0){
                    this.frameInvocador.setEnabled(true);
                    dispose();
                }else{
                    btCadastrar.requestFocus();
                }
            }else{
                this.frameInvocador.setEnabled(true);
                dispose();
            }
        //}
    }//GEN-LAST:event_btCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaEditorEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditorEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditorEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditorEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEditorEquipamento(null,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscarImagem;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btNovaCategoria;
    private javax.swing.JButton btNovaCategoria1;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbImagem;
    private javax.swing.JPanel pnCamposCadastro;
    private javax.swing.JPanel pnTudo;
    private javax.swing.JSpinner snQuantidade;
    private javax.swing.JTextArea taDescricao;
    private javax.swing.JTextArea taInstrucoes;
    private javax.swing.JTextArea taPrecaucoes;
    private javax.swing.JTextField tfEquipamento;
    private javax.swing.JTextField tfFabricante;
    private javax.swing.JTextField tfTombamento;
    // End of variables declaration//GEN-END:variables
}
