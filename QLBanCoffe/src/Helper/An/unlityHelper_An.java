/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper.An;

import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jdk.nashorn.internal.ir.WhileNode;

/**
 *
 * @author ducmc
 */
public class unlityHelper_An {

    public static boolean checkName(JTextField txt) {
        txt.setBackground(Color.white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,50}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(Color.pink);
            Helper.An.dialogHelper_An.alert(txt.getRootPane(), txt.getText() + "Bạn nhập sai định dạng tên món ăn");
            return false;
        }
    }

    public static boolean checkMoney(JTextField txt) {
        txt.setBackground(white);
        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp >= 0) {
                return true;
            } else {
                txt.setBackground(pink);
                Helper.An.dialogHelper_An.alert(txt.getRootPane(), txt.getName() + " giá tiền phải là lớn hơn hoặc bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
            Helper.An.dialogHelper_An.alert(txt.getRootPane(), txt.getName() + " giá tiền phải là số thực.");
            return false;
        }
    }

    public static boolean checkNullTextBan(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            Helper.An.dialogHelper_An.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }

    }

    

    public static boolean checkNullTextKV(JOptionPane khuvuc) {
        khuvuc.setBackground(white);
        if (khuvuc.getName().trim().length() > 0) {
            return true;
        } else {
            khuvuc.setBackground(pink);
            Helper.An.dialogHelper_An.alert(khuvuc.getRootPane(), "Không được để trống " + khuvuc.getName());
            return false;
        }

    }

    
}
