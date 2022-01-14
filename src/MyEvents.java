

/**
 *
 * @author zaafr
 */
import com.mysql.cj.util.StringUtils;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MyEvents implements ActionListener  {
    MyForm myForm;
    MyConnection myConnection ;

    public MyEvents(MyForm myForm) {
        this.myForm=myForm;
        this.myConnection = new MyConnection();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.myForm.ajouter && !StringUtils.isNullOrEmpty(myForm.nomT.getText()) ) {
            try {
                if (this.myConnection.addUser(this.myForm.nomT.getText(), this.myForm.bg.getSelection().getActionCommand()) > 0) {
                    JOptionPane.showMessageDialog((Component)null, "insertion done successfully");
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Insertion error");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog((Component)null, "Please fill user's name");
        }

    }
}