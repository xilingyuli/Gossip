import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingItem extends JPanel{

	/**
	 * һ��С����������ͼ
	 */
	private static final long serialVersionUID = 1L;
	private JTextField mTextField;  //�����
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
	
	//��ȡ�û���������е�int�������͵�����
	public int getInt()
	{
		try{
			return Integer.valueOf(mTextField.getText());
		}catch(Exception e){
			return 0;
		}
	}
	
	//��ȡ�û���������е�float�������͵�����
	public float getFloat()
	{
		try{
			return Float.valueOf(mTextField.getText());
		}catch(Exception e){
			return 0;
		}
	}
}
