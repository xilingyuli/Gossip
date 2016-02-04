import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class GossipController {
	private int x,y,prob;  //长、宽、概率
	private MessagePanel msgPan;  //可视化界面
	private Random random;  //随机数产生器
	private int count;  //记录演化的次数
	
	//初始化一个长宽为x、y，概率为p的Gossip模型控制器，效果显示在msgPan面板上
	GossipController(int x, int y, int p, MessagePanel msgPan)
	{
		this.x = x;
		this.y = y;
		this.prob = p;
		this.msgPan = msgPan;
		random = new Random();
	}
	
	//在面板上初始化数量为num的消息源
	public void init(int num)
	{
		msgPan.init();
		for(int i=0;i<num;i++)
		{
			int x = random.nextInt(this.x);
			int y = random.nextInt(this.y);
			if(!msgPan.getKnow(x, y))  //保证与之前的消息源不重复
				msgPan.setKnow(x, y);
			else  //若重复，则多循环一次
				i--;
		}
		count = 0;
	}
	
	//进行一步传播演化
	public boolean next()
	{
		Stack<int[]> stack = new Stack<int[]>();
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				//周围信息源数量*传播概率(百分比表示)=该区域被传播到的概率(百分比表示)
				int p = msgPan.getSurroundNum(i, j)*prob;
				//产生一个0-100的随机数，若该随机数小于p，则使消息传播至此处
				if(random.nextFloat()*100<p)
					stack.push(new int[]{i,j});  //若消息传播至此处，将位置信息加入栈
			}
		}
		
		//将已传播过的地方颜色加重
		boolean alreadyKnow = msgPan.setAlreadyKnow();
		if(alreadyKnow)  //若已全覆盖则提示
			JOptionPane.showMessageDialog(null, "经"+count+"次传播消息已全部覆盖", "演化结束", JOptionPane.INFORMATION_MESSAGE);
		else
			count++;  //演化次数加一
		for(int[] s : stack)  //将本次所有传播到的地方进行着色
		{
			msgPan.setKnow(s[0], s[1]);
		}
		return alreadyKnow;
	}

}
