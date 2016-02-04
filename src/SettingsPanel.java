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
	 * Gossipģ�͵����ð���
	 */
	private static final long serialVersionUID = 1L;
	
	//Gossipģ�͵ĳ�����Ϣ�������ʡ���ʼ����������
	private SettingItem width,height,probability,number;
	
	//���button1�������ó�ʼ�������button2����һ�������ݻ������button3�Զ��ݻ�
	private JButton button1,button2,button3;
	Timer timer;
	
	//�㷨�ؼ����֣�һ��������Ϣ�����Ŀ�������
	private GossipController gossip;
	
	SettingsPanel(final JFrame parent)
	{
		//�����������Ĳ���
		setLayout(new FlowLayout());
		
		//��Ӳ���������ʾ
		add(new JLabel(("������������д�ٷ���ֵ0-100")));
		add(new JLabel(("��ʼ����Ӧ����0")));
		
		//��Ӹ���������
		height = new SettingItem("����");
		width = new SettingItem("����");
		probability = new SettingItem("��������");
		number = new SettingItem("��ʼ����");
		add(width);
		add(height);
		add(probability);
		add(number);
		
		//�½�button1����Ӽ�����
		button1 = new JButton("��ʼ");
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//����û�����Ĳ���
				int x = height.getInt();
				int y = width.getInt();
				int p = probability.getInt();
				int num = number.getInt();
				
				if(x>0 && y>0 && num>0 && p>0 && p<=100 && num<=x*y )  //��������Ҫ��
				{
					//��ʼ��Gossipģ�Ϳ��ӻ����沢��ӹ�����
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
					
					//���ݲ�����ʼ����һ��������
					gossip = new GossipController(x,y,p,msgPan);
					gossip.init(num);  //Ϊ���ӻ���������num����Ϣ�����Դ
					 
					//��"��ʼ"��ť�滻��"�ݻ�"��"�Զ��ݻ�"��ť
					SettingsPanel.this.remove(button1);
					SettingsPanel.this.add(button2);
					SettingsPanel.this.add(button3);
					SettingsPanel.this.updateUI();
				}
				else  //����������Ҫ��
				{
					JOptionPane.showMessageDialog(null, "��ȷ����������������ʼ��������0������������0-100֮�䣬��ʼ����С��������", "���Ϸ�������", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(button1);
		
		//�½�button2����Ӽ�����
		button2 = new JButton("�ݻ�");
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gossip.next();  //������Ϣ��һ������
			}
		});
		
		//��ʱ��
		timer = new Timer(500,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(gossip.next())  //������Ϣ��һ������
				{
					timer.stop();  //������ȫ�ݻ�����ֹͣ
					button3.setEnabled(false);
				}
			}
		});
		
		button3 = new JButton("�Զ��ݻ�");
		button3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(button3.getText().equals("�Զ��ݻ�")){
					timer.start();
					button3.setText("��ͣ");
					button2.setEnabled(false);
				}
				else
				{
					timer.stop();
					button3.setText("�Զ��ݻ�");
					button2.setEnabled(true);
				}
			}
		});

		//��������С
		this.setPreferredSize(new Dimension(200,250));
		updateUI();
	}
}
