package wankaishun.github.p2p;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageProducer messageProducer;
	public Producer()  {
		this.connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
			ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://127.0.0.1:61616");
			try {
				this.connection = (Connection) connectionFactory.createConnection();
				this.connection.start();
				this.session=connection.createSession(Boolean.FALSE,  Session.AUTO_ACKNOWLEDGE);
				this.destination=session.createQueue("firstqueue");
				this.messageProducer=session.createProducer(destination);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void send() {
		for (int i = 0; i < 100; i++) {
			
			try {
				MapMessage textMessage = this.session.createMapMessage();
				textMessage.setInt("id", i);
				textMessage.setString("name", "ÕÅ"+i);
				textMessage.setString("ÄêÁä", ""+i);
				String receiver=i%2==0?"A":"B";
				textMessage.setStringProperty("receiver", receiver);
				this.messageProducer.send(textMessage);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}
	public static void main(String[] args) {
		Producer producer=new Producer();
		producer.send();
	}
}
