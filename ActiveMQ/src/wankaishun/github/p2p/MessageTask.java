package wankaishun.github.p2p;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

public class MessageTask implements Runnable {
	private MapMessage message;
	public MessageTask(MapMessage message) {
		// TODO Auto-generated constructor stub
		this.message=message;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 try {
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"��������"+message.getString("id"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
