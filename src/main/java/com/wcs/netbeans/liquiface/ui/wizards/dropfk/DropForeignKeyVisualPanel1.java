/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.netbeans.liquiface.ui.wizards.dropfk;

/*
 * #%L
 * Liquiface - GUI for Liquibase
 * %%
 * Copyright (C) 2013 Webstar Csoport Kft.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.wcs.netbeans.liquiface.facade.ModelFacade;
import com.wcs.netbeans.liquiface.model.Column;
import com.wcs.netbeans.liquiface.model.ForeignKeyConstraint;
import com.wcs.netbeans.liquiface.model.Table;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public final class DropForeignKeyVisualPanel1 extends JPanel {
    
    private Table table;

    /**
     * Creates new form DropForeignKeyVisualPanel1
     */
    public DropForeignKeyVisualPanel1(String tableName) {
        initComponents();
        table = ModelFacade.getInstance().getTableByName(tableName);
        List<ForeignKeyConstraint> fkList = table.getForeignKeyConstraints();
        List<String> fkNames = new ArrayList<String>();
        fkNames.add("");
        for (ForeignKeyConstraint fk : fkList) {
            fkNames.add(fk.getName());
        }
        selectedFkField.setModel(new DefaultComboBoxModel(fkNames.toArray()));
        selectedFkField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForeignKeyConstraint foreignKey = table.getForeignConstraintByName((String) selectedFkField.getSelectedItem());
                
                Object[][] rows;
                if (foreignKey != null) {
                    referencedTableNameField.setText(foreignKey.getReferencedTable().getName());
                    List<Column> baseColumns = foreignKey.getBaseColumns();
                    List<Column> referencedColumns = foreignKey.getReferencedColumns();

                    rows = new Object[baseColumns.size()][2];
                    for (int i = 0; i < baseColumns.size(); i++) {
                        rows[i] = new Object[]{
                            referencedColumns.get(i).getName(),
                            baseColumns.get(i).getName()
                        };
                    }
                } else {
                    referencedTableNameField.setText("");
                    rows = new Object[0][0];
                }
                
                columnsTable.setModel(new DropForeignKeyTableModel(rows, new String[] {"Referenced Column", "Locale Column"}));
            }
        });
    }

    @Override
    public String getName() {
        return "Drop Foreign Key";
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        selectedFkField = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        referencedTableNameField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        columnsTable = new javax.swing.JTable();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DropForeignKeyVisualPanel1.class, "DropForeignKeyVisualPanel1.jLabel1.text")); // NOI18N

        selectedFkField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DropForeignKeyVisualPanel1.class, "DropForeignKeyVisualPanel1.jLabel2.text")); // NOI18N

        referencedTableNameField.setEditable(false);
        referencedTableNameField.setText(org.openide.util.NbBundle.getMessage(DropForeignKeyVisualPanel1.class, "DropForeignKeyVisualPanel1.referencedTableNameField.text")); // NOI18N

        columnsTable.setBackground(getBackground());
        columnsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Referenced Column", "Locale Column"}
        ));
        columnsTable.setEnabled(false);
        columnsTable.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(columnsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedFkField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(referencedTableNameField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(selectedFkField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(referencedTableNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable columnsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField referencedTableNameField;
    private javax.swing.JComboBox selectedFkField;
    // End of variables declaration//GEN-END:variables

    public JComboBox getSelectedFkField() {
        return selectedFkField;
    }
}
