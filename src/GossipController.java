import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class GossipController {
	private int x,y,prob;  //����������
	private MessagePanel msgPan;  //���ӻ�����
	private Random random;  //�����������
	private int count;  //��¼�ݻ��Ĵ���
	
	//��ʼ��һ������Ϊx��y������Ϊp��Gossipģ�Ϳ�������Ч����ʾ��msgPan�����
	GossipController(int x, int y, int p, MessagePanel msgPan)
	{
		this.x = x;
		this.y = y;
		this.prob = p;
		this.msgPan = msgPan;
		random = new Random();
	}
	
	//������ϳ�ʼ������Ϊnum����ϢԴ
	public void init(int num)
	{
		msgPan.init();
		for(int i=0;i<num;i++)
		{
			int x = random.nextInt(this.x);
			int y = random.nextInt(this.y);
			if(!msgPan.getKnow(x, y))  //��֤��֮ǰ����ϢԴ���ظ�
				msgPan.setKnow(x, y);
			else  //���ظ������ѭ��һ��
				i--;
		}
		count = 0;
	}
	
	//����һ�������ݻ�
	public boolean next()
	{
		Stack<int[]> stack = new Stack<int[]>();
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				//��Χ��ϢԴ����*��������(�ٷֱȱ�ʾ)=�����򱻴������ĸ���(�ٷֱȱ�ʾ)
				int p = msgPan.getSurroundNum(i, j)*prob;
				//����һ��0-100������������������С��p����ʹ��Ϣ�������˴�
				if(random.nextFloat()*100<p)
					stack.push(new int[]{i,j});  //����Ϣ�������˴�����λ����Ϣ����ջ
			}
		}
		
		//���Ѵ������ĵط���ɫ����
		boolean alreadyKnow = msgPan.setAlreadyKnow();
		if(alreadyKnow)  //����ȫ��������ʾ
			JOptionPane.showMessageDialog(null, "��"+count+"�δ�����Ϣ��ȫ������", "�ݻ�����", JOptionPane.INFORMATION_MESSAGE);
		else
			count++;  //�ݻ�������һ
		for(int[] s : stack)  //���������д������ĵط�������ɫ
		{
			msgPan.setKnow(s[0], s[1]);
		}
		return alreadyKnow;
	}

}
