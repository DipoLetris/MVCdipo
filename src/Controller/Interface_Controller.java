/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.tampilan;
import java.sql.SQLException;


/**
 *
 * @author LAB 2 PC 36
 */
public interface Interface_Controller {
    public void Simpan (tampilan tampil) throws SQLException;
    public void Reset (tampilan tampil) throws SQLException;
    public void Ubah (tampilan tampil) throws SQLException;
    public void Hapus (tampilan tampil) throws SQLException;
    public void Tampilan (tampilan tampil) throws SQLException;
     public void kliktable (tampilan tampil) throws SQLException;
}
