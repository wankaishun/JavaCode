package wankaishun.github.helloword;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory =new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		Connection connection=factory.createConnection();
		connection.start();
		Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination=session.createQueue("first");
		MessageConsumer consumer=session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				try {
					System.out.println("消费数据"+((TextMessage)message).getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
}
