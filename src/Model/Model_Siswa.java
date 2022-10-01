/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.tampilan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Koneksi.Koneksi_Database;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author LAB 2 PC 36
 */
public class Model_Siswa implements Controller.Interface_Controller {
 
    String jk;
    
    @Override
    public void Simpan(tampilan tampil) throws SQLException {
    if (tampil.rbLaki.isSelected()) {
                jk="Laki-laki";
        } else {
jk = "Perempuan";
        }
try {
Connection con = Koneksi_Database.getcon();
String sql = "Insert Into siswa Values (?,?,?,?) ";
PreparedStatement prepare = con.prepareStatement (sql);
prepare.setString (1, tampil.txtNIS.getText()); 
prepare.setString (2, tampil.txtNama.getText());
prepare.setString(3, jk);
prepare.setString (4, (String) tampil.cbJurusan.getSelectedItem());
prepare.executeUpdate();
JOptionPane.showMessageDialog(null, "Data Berhasil disimpan");
prepare.close();
}catch (Exception e) {
System.out.println(e);
} finally {
Tampilan (tampil);
    }
    } 

    @Override
    public void Reset(tampilan tampil) throws SQLException {
       tampil.txtNIS.setText("");
       tampil.txtNama.setText("");
    }

    @Override
    public void Ubah(tampilan tampil) throws SQLException {
        if (tampil.rbLaki.isSelected()) {
            jk = "Laki-Laki";
        }else {
            jk = "Perempuan";
        }
try {
    Connection con = Koneksi_Database.getcon();
    String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?,"+"jurusan=? WHERE NIS=?";
    PreparedStatement prepare = con.prepareStatement (sql);
    prepare.setString (1, tampil.txtNIS.getText()); 
    prepare.setString (2, tampil.txtNama.getText());
    prepare.setString(3, jk);
    prepare.setString (4, (String) tampil.cbJurusan.getSelectedItem());
    prepare.executeUpdate();
    JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
    prepare.close();
    } catch (Exception e){ 
        System.out.println(e);
    } finally {
  //tampil (tampil);
//tampil.setLebarKolom ();
//baru(tampil);
}
}

    @Override
    public void Tampilan(tampilan tampil) throws SQLException {
       tampil.tblmodel.getDataVector().removeAllElements();
       tampil.tblmodel.fireTableDataChanged();
       try {
           Connection con = Koneksi_Database.getcon();
           Statement stt = con.createStatement();
           
           
           String sql = "SELECT * FROM siswa ORDER BY NIS ASC";
           ResultSet res =stt.executeQuery(sql);
           while (res.next()){
               Object [] ob = new Object[8];
               ob [0] = res.getString(1);
               ob [1] = res.getString(2);
               ob [2] = res.getString(3);
               ob [3] = res.getString(4);
               tampil.tblmodel.addRow(ob);
           }
       } catch (Exception e) {
           System.out.println(e);
       }finally {


    }
        }

    @Override
    public void Hapus(tampilan tampil) throws SQLException {
        try{
        Connection con = Koneksi_Database.getcon ();
String sql = "DELETE FROM siswa WHERE NIS =?";
PreparedStatement prepare = con.prepareStatement (sql);
prepare.setString (1, tampil.txtNIS.getText());
prepare.executeUpdate();
JOptionPane.showMessageDialog (null, "Data berhasil dihapus");
prepare.close();
} catch (Exception e) {
System.out.println(e);
} finally {
//tampil (tampil);
//tampil.setLebarKolom ();
//baru(tampil);
    }
    }

    @Override
    public void kliktable(tampilan tampil) throws SQLException {
        try {

int pilih = tampil.table.getSelectedRow(); if (pilih == -1) {
return;
}
tampil.txtNIS.setText(tampil.tblmodel.getValueAt (pilih, 0).toString()); 
tampil.txtNama.setText(tampil.tblmodel.getValueAt (pilih, 1).toString()); 
tampil.cbJurusan.setSelectedItem(tampil.tblmodel.getValueAt (pilih, 3).toString());
jk = String.valueOf(tampil.tblmodel.getValueAt (pilih, 2));

} catch (Exception e) {
    }    
if (tampil.rbLaki.getText().equals (jk)) {
tampil.rbLaki.setSelected(true);
} else {
tampil.rbPerempuan.setSelected(true);
      }    
    }
}


