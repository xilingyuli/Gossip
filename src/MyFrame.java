
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	/**
	 * �����ܿ��
	 */
	private static final long serialVersionUID = 1L;
	MyFrame()
	{
		super();
		this.setLayout(new FlowLayout());  //���ô��ڲ���
		this.add(new SettingsPanel(this));  //����������
		this.pack();
		setLocation(500, 200);  //���ô���λ��
		setResizable(false);  //���ɸı��С
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //���õ�����Ͻ�x�ر�
	}

}
