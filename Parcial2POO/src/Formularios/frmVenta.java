/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import javax.swing.table.DefaultTableModel;
import Entidades.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco René Esquivel Juárez
 */
public class frmVenta extends javax.swing.JFrame {

    /**
     * Creates new form frmVenta
     */
    public ArrayList<Farmaco> lF = new ArrayList<>();
    public DefaultTableModel mdlTabla;
    public DefaultTableModel mdlTabla2;

    public frmVenta() {
        initComponents();
        this.lF.clear();
        this.mdlTabla = (DefaultTableModel) this.jTable1.getModel();
        this.mdlTabla2 = (DefaultTableModel) this.jTable2.getModel();
        cargarTabla();
    }

    private void cargarTabla() {
        this.mdlTabla.setRowCount(0);
        for (int j = 0; j < Parcial2POO.lFar.size(); j++) {
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

    private void cargarTabla2() {
        this.mdlTabla2.setRowCount(0);
        for (int j = 0; j < this.lF.size(); j++) {
            String[] registro = {
                this.lF.get(j).getNombre(),
                Double.toString(this.lF.get(j).getPrecio()),
                this.lF.get(j).getTipo(),
                Parcial2POO.df.format(this.lF.get(j).caducidad()),
                this.lF.get(j).getCod(),
                Integer.toString(this.lF.get(j).getUnidades())
            };
            this.mdlTabla2.addRow(registro);
        }
    }

    private void crear(String t, int i) {
        switch (t) {
            case "Analgesico":
                this.lF = Parcial2POO.agregarAnalgesico(Parcial2POO.lFar.get(i).getNombre(),
                        Parcial2POO.lFar.get(i).getPrecio(),
                        Parcial2POO.lFar.get(i).caducidad,
                        Parcial2POO.lFar.get(i).getCod(),
                        Integer.parseInt(this.jSpinner1.getValue().toString()), lF);
                Parcial2POO.lFar.get(i).setUnidades(Parcial2POO.lFar.get(i).getUnidades() - Integer.parseInt(this.jSpinner1.getValue().toString()));
                break;
            case "Antialergico":
                this.lF = Parcial2POO.agregarAntialergico(Parcial2POO.lFar.get(i).getNombre(),
                        Parcial2POO.lFar.get(i).getPrecio(),
                        Parcial2POO.lFar.get(i).caducidad,
                        Parcial2POO.lFar.get(i).getCod(),
                        Integer.parseInt(this.jSpinner1.getValue().toString()), lF);
                Parcial2POO.lFar.get(i).setUnidades(Parcial2POO.lFar.get(i).getUnidades() - Integer.parseInt(this.jSpinner1.getValue().toString()));
                break;
            case "Antiinflamatorio":
                this.lF = Parcial2POO.agregarAntiinflamatorio(Parcial2POO.lFar.get(i).getNombre(),
                        Parcial2POO.lFar.get(i).getPrecio(),
                        Parcial2POO.lFar.get(i).caducidad,
                        Parcial2POO.lFar.get(i).getCod(),
                        Integer.parseInt(this.jSpinner1.getValue().toString()), lF);
                Parcial2POO.lFar.get(i).setUnidades(Parcial2POO.lFar.get(i).getUnidades() - Integer.parseInt(this.jSpinner1.getValue().toString()));
                break;
            case "Antidepresivo":
                this.lF = Parcial2POO.agregarAntidepresivo(Parcial2POO.lFar.get(i).getNombre(),
                        Parcial2POO.lFar.get(i).getPrecio(),
                        Parcial2POO.lFar.get(i).caducidad,
                        Parcial2POO.lFar.get(i).getCod(),
                        Integer.parseInt(this.jSpinner1.getValue().toString()), lF);
                Parcial2POO.lFar.get(i).setUnidades(Parcial2POO.lFar.get(i).getUnidades() - Integer.parseInt(this.jSpinner1.getValue().toString()));
                break;
        }
        Parcial2POO.lFar.get(i).comprar();
    }

    private void eliminar() {

        int iProd = this.jTable2.getSelectedRow();
        String nombre = this.lF.get(iProd).getNombre();
        int cant = this.lF.get(iProd).getUnidades();
        for (int j = 0; j < Parcial2POO.lFar.size(); j++) {
            if (nombre.equals(Parcial2POO.lFar.get(j).getNombre())) {
                Parcial2POO.lFar.get(j).setUnidades(Parcial2POO.lFar.get(j).getUnidades() + cant);
            }
        }
        this.lF.remove(iProd);
        cargarTabla();
        cargarTabla2();
        JOptionPane.showMessageDialog(null, "Se ha eliminado el objeto correctamente");

    }

    private void eliminarTodo() {
        for (int i = 0; i < this.lF.size(); i++) {
            String nombre = this.lF.get(i).getNombre();
            int cant = this.lF.get(i).getUnidades();
            for (int j = 0; j < Parcial2POO.lFar.size(); j++) {
                if (nombre.equals(Parcial2POO.lFar.get(j).getNombre())) {
                    Parcial2POO.lFar.get(j).setUnidades(Parcial2POO.lFar.get(j).getUnidades() + cant);
                }
            }
        }
        this.lF.clear();
    }

    private double mont() {
        double m = 0;
        for (int i = 0; i < lF.size(); i++) {
            m += lF.get(i).getPrecio() * lF.get(i).getUnidades();
            m *= 100;
            m = Math.round(m);
            m /= 100;
        }

        return m;
    }

    public static boolean validarDUI(String DUI) {
        return DUI.matches("^[0-9]{8}" + "-" + "[0-9]{1}$");
    }

    private boolean verificarDUI(String x) {
        boolean noCoincide = true;

        for (int i = 0; i < Parcial2POO.lR.size(); i++) {
            if (Parcial2POO.lR.get(i).getCliente().getDUI().equals(x)) {
                noCoincide = false;
                break;
            }
        }
        return noCoincide;
    }

    private boolean verificarPersona(String x, String y) {
        boolean coincide = true;

        for (int i = 0; i < Parcial2POO.lR.size(); i++) {
            if ((Parcial2POO.lR.get(i).getCliente().getNombre() + " " + Parcial2POO.lR.get(i).getCliente().getApellido()).equals(x) && Parcial2POO.lR.get(i).getCliente().getDUI().equals(y)) {
                coincide = false;
                break;
            }
        }
        return coincide;
    }

    private void addCom() {
        Parcial2POO.lR.add(new Relaciones(
                new Persona(this.txtNom.getText().toUpperCase().trim(),
                        this.txtApe.getText().toUpperCase().trim(),
                        this.txtDUI.getText()),
                new Venta(mont(),
                        this.lF,
                        new Date())));
        Parcial2POO.dineroFarmacia += mont();

        JOptionPane.showMessageDialog(null, "Se ha relizado la compra");
        String x;
        do {
            x = JOptionPane.showInputDialog("¿Quiere salir? \n[1]Si \n[2]Nueva compra");
            switch (x) {
                case "1":
                    this.setVisible(false);
                    Parcial2POO p = new Parcial2POO();
                    p.setVisible(true);
                    break;
                case "2":
                    this.setVisible(false);
                    frmVenta fV = new frmVenta();
                    fV.setVisible(true);
                }
        } while (!"1".equals(x) && !"2".equals(x));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDUI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtApe = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(788, 613));
        jPanel1.setMinimumSize(new java.awt.Dimension(788, 613));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Tipo", "Caducidad", "Codigo", "Unidades"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Selecciona el producto, la cantidad y agregar!!! ");

        jLabel4.setText("Cantidad Producto");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Tipo", "Caducidad", "Codigo", "Unidades"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Productos Agregados");

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Realizar compra");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Total: ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("00.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(108, 108, 108))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(250, 250, 146));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nombre Cliente:");

        jLabel3.setText("DUI:");

        jLabel6.setText("Apellido Cliente:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(txtDUI)
                    .addComponent(txtApe))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton4.setText("Mostrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Informacion");
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
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
            .addGap(0, 613, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int op;
        if (this.lF.size() < 1) {
            this.setVisible(false);
            Parcial2POO p = new Parcial2POO();
            p.setVisible(true);
        } else {
            do {
                op = Integer.parseInt(JOptionPane.showInputDialog("¿Desea salir?"
                        + "\nSe eliminaran los productos de la lista de compra."
                        + "\n[1]Si\n[2]No"));
                if (op == 1) {
                    eliminarTodo();
                    this.setVisible(false);
                    Parcial2POO p = new Parcial2POO();
                    p.setVisible(true);

                }
            } while (op != 1 && op != 2);

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.jTable1.getSelectedRow() >= 0) {
            int i = this.jTable1.getSelectedRow();
            String t = Parcial2POO.lFar.get(i).getTipo();
            if (Parcial2POO.lFar.get(i).getUnidades() >= Integer.parseInt(this.jSpinner1.getValue().toString())) {
                crear(t, i);
                cargarTabla2();
                this.jSpinner1.setValue(1);
                this.jLabel8.setText(Double.toString(mont()));
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad de productos no es suficiente para realizar la compra");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el producto que desea agregar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!"".equals(this.txtApe.getText()) && !"".equals(this.txtNom.getText()) && validarDUI(this.txtDUI.getText()) && lF.isEmpty() == false) {
            if (Parcial2POO.lR.isEmpty()) {
                addCom();
            } else {
                if (verificarDUI(this.txtDUI.getText()) == false) {
                    if (verificarPersona((this.txtNom.getText().toUpperCase().trim() + " " + this.txtApe.getText().toUpperCase().trim()), this.txtDUI.getText())) {
                        JOptionPane.showMessageDialog(null, "El DUI ya existe con el nombre de otra persona");
                    } else {
                        addCom();
                    }
                } else {
                    addCom();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese los datos del cliente y al menos un producto"
                    + "\n(El dui debe tener un formato correcto 8 digitos - 1 digito)");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (this.jTable2.getSelectedRow() >= 0) {
            int op;
            do {
                op = Integer.parseInt(JOptionPane.showInputDialog("¿Desea eliminar este producto de la compra? \n[1]Si\n[2]No"));
                if (op == 1) {
                    eliminar();
                    this.jLabel8.setText(Double.toString(mont()));
                } else {
                }
            } while (op != 1 && op != 2);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (this.jTable1.getSelectedRow() >= 0) {
            int i = this.jTable1.getSelectedRow();
            Parcial2POO.lFar.get(i).mostrar();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el producto que desea mostrar");
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (this.jTable1.getSelectedRow() >= 0) {
            int i = this.jTable1.getSelectedRow();
            Parcial2POO.lFar.get(i).infoFarmaco();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el producto del que desea ver la información");
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtDUI;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
