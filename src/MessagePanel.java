import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel{

	/**
	 * ��¼����ʾ��Ϣ������������
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[][] box;  //��Ӧÿ���˵�JLabel����
	private boolean[][] know;  //��¼��Ϣ������������飬true��ʾ֪����Ϣ
	private int x,y;  //���鳤��
	
	//��ʼ������
	MessagePanel(int x,int y)
	{
		super();
		this.x = x;
		this.y = y;
		this.setLayout(new GridLayout(x,y));
		box = new JLabel[x][y];
		know = new boolean[x][y];
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
			{
				box[i][j] = new JLabel();
				box[i][j].setPreferredSize(new Dimension(40,40));
				box[i][j].setOpaque(true);
				this.add(box[i][j]);
			}
		init();
	}
	
	//�ָ���ʼ״̬
	public void init()
	{
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
			{
				know[i][j] = false;  //�����˶���֪����Ϣ
				box[i][j].setBackground(new Color(1f,1f,0f,0f));  //��Ӧ����������ɫ
			}
	}
	
	//����(i,j)������֪����Ϣ
	public void setKnow(int i,int j)
	{
		if(know[i][j])
			return;
		know[i][j] = true;
		box[i][j].setBackground(new Color(1f,1f,0f,0.4f));  //��Ӧ������ǳɫ
		this.updateUI();
	}
	
	//����֪����Ϣ���˶�Ӧ����ɫ���أ������Ƿ������˶�֪���˸���Ϣ
	public boolean setAlreadyKnow()
	{
		boolean allKnow = true;
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
				if(know[i][j])
					box[i][j].setBackground(new Color(1f,1f,0f,1f));  //��Ӧ��������ɫ
				else
					allKnow = false;
		this.updateUI();
		return allKnow;
	}
	
	//���(i,j)����Ϣ��״̬
	public boolean getKnow(int i,int j)
	{
		return know[i][j];
	}
	
	//(i,j)����Χ�ж�������֪��Ϣ
	public int getSurroundNum(int i, int j)
	{
		int n=0;
		if(know[(i+x-1)%x][j])  //�ϱ�
			n++;
		if(know[i][(j+y-1)%y])  //���
			n++;
		if(know[(i+1)%x][j])  //�±�
			n++;
		if(know[i][(j+1)%y])  //�ұ�
			n++;
		return n;
	}
}
