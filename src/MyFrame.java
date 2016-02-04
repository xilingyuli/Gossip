
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	/**
	 * 窗口总框架
	 */
	private static final long serialVersionUID = 1L;
	MyFrame()
	{
		super();
		this.setLayout(new FlowLayout());  //设置窗口布局
		this.add(new SettingsPanel(this));  //添加设置面板
		this.pack();
		setLocation(500, 200);  //设置窗口位置
		setResizable(false);  //不可改变大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置点击右上角x关闭
	}

}
