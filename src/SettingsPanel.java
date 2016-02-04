import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class SettingsPanel extends JPanel{

	/**
	 * Gossip模型的设置版面
	 */
	private static final long serialVersionUID = 1L;
	
	//Gossip模型的长宽、消息传播概率、初始数的设置项
	private SettingItem width,height,probability,number;
	
	//点击button1根据设置初始化，点击button2进行一步传播演化，点击button3自动演化
	private JButton button1,button2,button3;
	Timer timer;
	
	//算法关键部分，一个控制消息传播的控制器类
	private GossipController gossip;
	
	SettingsPanel(final JFrame parent)
	{
		//设置设置面板的布局
		setLayout(new FlowLayout());
		
		//添加参数设置提示
		add(new JLabel(("传播概率请填写百分数值0-100")));
		add(new JLabel(("初始数量应大于0")));
		
		//添加各种设置项
		height = new SettingItem("行数");
		width = new SettingItem("列数");
		probability = new SettingItem("传播概率");
		number = new SettingItem("初始数量");
		add(width);
		add(height);
		add(probability);
		add(number);
		
		//新建button1并添加监听器
		button1 = new JButton("开始");
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//获得用户输入的参数
				int x = height.getInt();
				int y = width.getInt();
				int p = probability.getInt();
				int num = number.getInt();
				
				if(x>0 && y>0 && num>0 && p>0 && p<=100 && num<=x*y )  //参数符合要求
				{
					//初始化Gossip模型可视化界面并添加滚动框
					MessagePanel msgPan = new MessagePanel(x,y);
					JScrollPane jsp = new JScrollPane(msgPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					if(x>=12&&y>=12)
						jsp.setPreferredSize(new Dimension(500,500));
					else if(x>=12)
						jsp.setPreferredSize(new Dimension(jsp.getPreferredSize().width,500));
					else if(y>=12)
						jsp.setPreferredSize(new Dimension(500,jsp.getPreferredSize().height));
					parent.add(jsp);
					parent.pack();
					
					//根据参数初始化出一个控制器
					gossip = new GossipController(x,y,p,msgPan);
					gossip.init(num);  //为可视化界面设置num个消息最初来源
					 
					//将"开始"按钮替换成"演化"和"自动演化"按钮
					SettingsPanel.this.remove(button1);
					SettingsPanel.this.add(button2);
					SettingsPanel.this.add(button3);
					SettingsPanel.this.updateUI();
				}
				else  //参数不符合要求
				{
					JOptionPane.showMessageDialog(null, "请确保行数、列数、初始数量大于0，传播概率在0-100之间，初始数量小于总数量", "不合法的输入", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(button1);
		
		//新建button2并添加监听器
		button2 = new JButton("演化");
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gossip.next();  //进行消息下一步传播
			}
		});
		
		//计时器
		timer = new Timer(500,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(gossip.next())  //进行消息下一步传播
				{
					timer.stop();  //若已完全演化，则停止
					button3.setEnabled(false);
				}
			}
		});
		
		button3 = new JButton("自动演化");
		button3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(button3.getText().equals("自动演化")){
					timer.start();
					button3.setText("暂停");
					button2.setEnabled(false);
				}
				else
				{
					timer.stop();
					button3.setText("自动演化");
					button2.setEnabled(true);
				}
			}
		});

		//设置面板大小
		this.setPreferredSize(new Dimension(200,250));
		updateUI();
	}
}
