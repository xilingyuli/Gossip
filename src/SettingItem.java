import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingItem extends JPanel{

	/**
	 * 一个小的设置项视图
	 */
	private static final long serialVersionUID = 1L;
	private JTextField mTextField;  //输入框
	SettingItem(String text)
	{
		setLayout(new FlowLayout());
		JLabel mLabel = new JLabel(text+":");
		mLabel.setPreferredSize(new Dimension(60,25));
		mTextField = new JTextField();
		mTextField.setPreferredSize(new Dimension(100,25));
		add(mLabel);
		add(mTextField);
	}
	
	//获取用户在输入框中的int数据类型的输入
	public int getInt()
	{
		try{
			return Integer.valueOf(mTextField.getText());
		}catch(Exception e){
			return 0;
		}
	}
	
	//获取用户在输入框中的float数据类型的输入
	public float getFloat()
	{
		try{
			return Float.valueOf(mTextField.getText());
		}catch(Exception e){
			return 0;
		}
	}
}
