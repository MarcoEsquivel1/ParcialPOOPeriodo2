/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Ordenar.OrdPreAsc;
import Ordenar.OrdPreDes;
import Ordenar.OrdTipoAsc;
import Ordenar.OrdTipoDes;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class frmInventario extends javax.swing.JFrame {

    /**
     * Creates new form frmInventario
     */
    DefaultTableModel mdlTabla;
    public frmInventario() {
        initComponents();
        this.cmbTipo.setSelectedIndex(-1);
        this.mdlTabla= (DefaultTableModel) this.jTable1.getModel();
        cargarTabla();
        this.jLabel3.setText(Double.toString(Parcial2POO.dineroFarmacia));
        active("desactivar");
        aprox();
    }
    
    private void cargarTabla(){
        this.mdlTabla.setRowCount(0);
        for(int j=0; j< Parcial2POO.lFar.size(); j++)
        {
            String[] registro = { 
                Parcial2POO.lFar.get(j).getNombre(),
                Double.toString(Parcial2POO.lFar.get(j).getPrecio()),
                Parcial2POO.lFar.get(j).getTipo(),
                Parcial2POO.df.format(Parcial2POO.lFar.get(j).caducidad()),
                Parcial2POO.lFar.get(j).getCod(),
                Integer.toString(Parcial2POO.lFar.get(j).getUnidades())
            };
            this.mdlTabla.addRow(registro); 
        }
    }
    
    private void agre1(int i, int cant){
        if (Parcial2POO.dineroFarmacia>=(Parcial2POO.lFar.get(i).getPrecio()*0.85)*cant) {
            Parcial2POO.lFar.get(i).setUnidades(Parcial2POO.lFar.get(i).getUnidades()+cant);
            Parcial2POO.dineroFarmacia -= (Parcial2POO.lFar.get(i).getPrecio()*0.85)*cant;
            aprox();
        }else{
            JOptionPane.showMessageDialog(null, "LA farmacia no cuenta con los fondos para realizar el pedido");
        }
        
    }
    
    private void agre2(String t) {
        if (Parcial2POO.dineroFarmacia >= (Double.parseDouble(this.txtPrecio.getText()) * 0.85) * Integer.parseInt(this.spnUnidades.getValue().toString())) {
            try {
                String txt;
                switch (t) {
                    case "Analgesico":
                        txt = this.txtCaducidad.getText();
                        Date d = Parcial2POO.parser.parse(txt);
                        Parcial2POO.agregarAnalgesico(this.txtNom.getText(),
                                Double.parseDouble(this.txtPrecio.getText()),
                                d, genCod("AG", Parcial2POO.nAg),
                                Integer.parseInt(this.spnUnidades.getValue().toString()),
                                Parcial2POO.lFar);
                        Parcial2POO.nAg++;
                        break;
                    case "Antialergico":
                        txt = this.txtCaducidad.getText();
                        Date a = Parcial2POO.parser.parse(txt);
                        Parcial2POO.agregarAntialergico(this.txtNom.getText(),
                                Double.parseDouble(this.txtPrecio.getText()),
                                a, genCod("AA", Parcial2POO.nAa),
                                Integer.parseInt(this.spnUnidades.getValue().toString()),
                                Parcial2POO.lFar);
                        Parcial2POO.nAa++;
                        break;
                    case "Antiinflamatorio":
                        txt = this.txtCaducidad.getText();
                        Date s = Parcial2POO.parser.parse(txt);
                        Parcial2POO.agregarAntiinflamatorio(this.txtNom.getText(),
                                Double.parseDouble(this.txtPrecio.getText()),
                                s,
                                genCod("AI", Parcial2POO.nAi),
                                Integer.parseInt(this.spnUnidades.getValue().toString()),
                                Parcial2POO.lFar);
                        Parcial2POO.nAi++;
                        break;
                    case "Antidepresivo":
                        txt = this.txtCaducidad.getText();
                        Date w = Parcial2POO.parser.parse(txt);
                        Parcial2POO.agregarAntidepresivo(this.txtNom.getText(),
                                Double.parseDouble(this.txtPrecio.getText()),
                                w, genCod("AD", Parcial2POO.nAd),
                                Integer.parseInt(this.spnUnidades.getValue().toString()),
                                Parcial2POO.lFar);
                        Parcial2POO.nAd++;
                        break;
                }
                Parcial2POO.dineroFarmacia -= (Double.parseDouble(this.txtPrecio.getText()) * 0.85) * Integer.parseInt(this.spnUnidades.getValue().toString());
                aprox();
            } catch (ParseException ex) {
                Logger.getLogger(frmInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "LA farmacia no cuenta con los fondos para realizar el pedido");
        } 
    }
    
    private void active(String x){
        if (x.equals("activar")) {
            this.lbl1.setEnabled(true);
            this.lbl2.setEnabled(true);
            this.lbl3.setEnabled(true);
            this.lbl4.setEnabled(true);
            this.lbl5.setEnabled(true);
            this.jLabel5.setEnabled(true);
            this.txtNom.setEnabled(true);
            this.txtPrecio.setEnabled(true);
            this.txtCaducidad.setEnabled(true);
            this.cmbTipo.setEnabled(true);
            this.spnUnidades.setEnabled(true);
            this.btnAgregar.setEnabled(true);
            this.btnInf2.setEnabled(true);
        }
        if (x.equals("desactivar")) {
            this.lbl1.setEnabled(false);
            this.lbl2.setEnabled(false);
            this.lbl3.setEnabled(false);
            this.lbl4.setEnabled(false);
            this.lbl5.setEnabled(false);
            this.jLabel5.setEnabled(false);
            this.txtNom.setEnabled(false);
            this.txtPrecio.setEnabled(false);
            this.txtCaducidad.setEnabled(false);
            this.cmbTipo.setEnabled(false);
            this.spnUnidades.setEnabled(false);
            this.btnAgregar.setEnabled(false);
            this.btnInf2.setEnabled(false);
        }
    }
    
    public String genCod(String x, int n){
        n = (n+1);
        return String.format(x+"%03d", n);
    }
    
    public static boolean validarFormatoFecha(String f){
        return f.matches("^[0-3]{1}"+"[0-9]{1}"+"-"+"[0-1]{1}"+"[0-9]{1}"+"-"+"[2]{1}"+"[0]{1}"+"[2-4]{1}"+"[0-9]{1}$");
    }
    
    public static boolean verificar(String x){
        boolean coincide= true;
        
        for (int i = 0; i < Parcial2POO.lFar.size(); i++) {
            if (Parcial2POO.lFar.get(i).getNombre().equals(x)) {
                coincide = false;
                break;
            }
        }
        return coincide;
    }
    
    private void aprox(){
        Parcial2POO.dineroFarmacia *= 100;
        Parcial2POO.dineroFarmacia = Math.round(Parcial2POO.dineroFarmacia);
        Parcial2POO.dineroFarmacia /= 100;
        this.jLabel3.setText(Double.toString(Parcial2POO.dineroFarmacia));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnAscTipo = new javax.swing.JButton();
        btnDesTipo = new javax.swing.JButton();
        btnAscPre = new javax.swing.JButton();
        btnDesPre = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        lbl2 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JFormattedTextField();
        lbl3 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        spnUnidades = new javax.swing.JSpinner();
        txtCaducidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnInf2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(788, 613));
        jPanel1.setMinimumSize(new java.awt.Dimension(788, 613));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Tipo", "Caducidad", "Codigo", "Cantidad"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Inventario");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/info1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Agregar existente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar nuevo ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Dinero: $");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("00.00");

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAscTipo.setBackground(new java.awt.Color(111, 198, 255));
        btnAscTipo.setText("Tipo ascendente");
        btnAscTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscTipoActionPerformed(evt);
            }
        });

        btnDesTipo.setBackground(new java.awt.Color(255, 111, 111));
        btnDesTipo.setText("Tipo descendente");
        btnDesTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesTipoActionPerformed(evt);
            }
        });

        btnAscPre.setBackground(new java.awt.Color(111, 198, 255));
        btnAscPre.setText("Precio ascendente");
        btnAscPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscPreActionPerformed(evt);
            }
        });

        btnDesPre.setBackground(new java.awt.Color(255, 111, 111));
        btnDesPre.setText("Precio descendente");
        btnDesPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesPreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDesTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAscTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAscPre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDesPre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAscTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAscPre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesPre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Informacion del producto.");

        lbl1.setText("Nombre:");

        lbl2.setText("Precio:");

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));

        lbl3.setText("Tipo:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Analgesico", "Antialergico", "Antiinflamatorio", "Antidepresivo" }));

        lbl4.setText("Caducidad:");

        lbl5.setText("Unidades:");

        spnUnidades.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel5.setText("dd-MM-yyyy");

        btnInf2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/info1.png"))); // NOI18N
        btnInf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInf2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl4)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(lbl1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNom))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(lbl2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnInf2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl5)))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(lbl3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl2)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl3)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(btnInf2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(lbl5)
                    .addComponent(spnUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(btnAgregar)
                .addContainerGap())
        );

        jButton5.setText("Regresar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        Parcial2POO p = new Parcial2POO();
        p.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "[Agregar existente]: Elije un producto en la tabla para agregar unidades. \n[Agregar nuevo]: Agrega un producto nuevo al inventario.");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (this.jTable1.getSelectedRow() >= 0) {
           int i = this.jTable1.getSelectedRow();
           int c;
            do {
                c = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de unidades que quiere agregar"));
            } while (c<0);
            agre1(i, c);
            cargarTabla(); 
            this.jTable1.clearSelection();
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un producto para agregar");
        }      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        active("activar");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (validarFormatoFecha(this.txtCaducidad.getText()) && verificar(this.txtNom.getText()) && Double.parseDouble(this.txtPrecio.getText())>0 && this.cmbTipo.getSelectedIndex()> -1) {
            agre2(this.cmbTipo.getSelectedItem().toString());
            cargarTabla();
            active("desactivar");
            
            this.txtNom.setText("");
            this.txtPrecio.setText("");
            this.txtCaducidad.setText("00-00-00");
            this.cmbTipo.setSelectedIndex(-1);
            this.spnUnidades.setValue(1);
            this.spnUnidades.setEnabled(false);
        }else{JOptionPane.showMessageDialog(null, "Ingrese datos validos:"
                + "\nNo puede agregar un producto con el mismo nombre que otro ya existente"
                + "\nLa fecha debe ser ingresada en el formato correcto"
                + "\nEl precio debe ser mayor que 0");}
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnInf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInf2ActionPerformed
        JOptionPane.showMessageDialog(null, "Ingrese la fecha en formato dd-MM-yyyy "
                + "\nSi los dias exeden el limite del mes, será desplazado al mes siguiente."
                + "\nSi el numero de mes exede el 12 sera enviado al año siguiente.");
    }//GEN-LAST:event_btnInf2ActionPerformed

    private void btnAscTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscTipoActionPerformed
        OrdTipoAsc oTA = new OrdTipoAsc();
        Parcial2POO.lFar.sort(oTA);
        this.cargarTabla();
    }//GEN-LAST:event_btnAscTipoActionPerformed

    private void btnDesTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesTipoActionPerformed
        OrdTipoDes oTD = new OrdTipoDes();
        Parcial2POO.lFar.sort(oTD);
        this.cargarTabla();
    }//GEN-LAST:event_btnDesTipoActionPerformed

    private void btnAscPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscPreActionPerformed
        OrdPreAsc oPA = new OrdPreAsc();
        Parcial2POO.lFar.sort(oPA);
        this.cargarTabla();
    }//GEN-LAST:event_btnAscPreActionPerformed

    private void btnDesPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesPreActionPerformed
        OrdPreDes oPD = new OrdPreDes();
        Parcial2POO.lFar.sort(oPD);
        this.cargarTabla();
    }//GEN-LAST:event_btnDesPreActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAscPre;
    private javax.swing.JButton btnAscTipo;
    private javax.swing.JButton btnDesPre;
    private javax.swing.JButton btnDesTipo;
    private javax.swing.JButton btnInf2;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JSpinner spnUnidades;
    private javax.swing.JTextField txtCaducidad;
    private javax.swing.JTextField txtNom;
    private javax.swing.JFormattedTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
