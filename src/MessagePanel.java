import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel{

	/**
	 * 记录和显示消息覆盖情况的面板
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[][] box;  //对应每个人的JLabel数组
	private boolean[][] know;  //记录消息覆盖情况的数组，true表示知道消息
	private int x,y;  //数组长宽
	
	//初始化布局
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
	
	//恢复初始状态
	public void init()
	{
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
			{
				know[i][j] = false;  //所有人都不知道消息
				box[i][j].setBackground(new Color(1f,1f,0f,0f));  //对应界面上无颜色
			}
	}
	
	//设置(i,j)处的人知道消息
	public void setKnow(int i,int j)
	{
		if(know[i][j])
			return;
		know[i][j] = true;
		box[i][j].setBackground(new Color(1f,1f,0f,0.4f));  //对应界面上浅色
		this.updateUI();
	}
	
	//将已知道消息的人对应处颜色加重，返回是否所有人都知道了该消息
	public boolean setAlreadyKnow()
	{
		boolean allKnow = true;
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
				if(know[i][j])
					box[i][j].setBackground(new Color(1f,1f,0f,1f));  //对应界面上深色
				else
					allKnow = false;
		this.updateUI();
		return allKnow;
	}
	
	//获得(i,j)处消息的状态
	public boolean getKnow(int i,int j)
	{
		return know[i][j];
	}
	
	//(i,j)处周围有多少人已知消息
	public int getSurroundNum(int i, int j)
	{
		int n=0;
		if(know[(i+x-1)%x][j])  //上边
			n++;
		if(know[i][(j+y-1)%y])  //左边
			n++;
		if(know[(i+1)%x][j])  //下边
			n++;
		if(know[i][(j+1)%y])  //右边
			n++;
		return n;
	}
}
