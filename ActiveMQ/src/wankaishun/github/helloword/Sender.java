package wankaishun.github.helloword;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	public static void main(String[] args) throws JMSException, InterruptedException {
		ConnectionFactory factory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://127.0.0.1:61616");
		Connection connection=factory.createConnection();
		connection.start();
		Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination=session.createQueue("first");
		MessageProducer producer=session.createProducer(destination);
		for (int i = 0; i < 100; i++) {
			producer.send(session.createTextMessage("我是消息内容"+i));
			TimeUnit.SECONDS.sleep(1);
		}
		if(connection!=null) {
			connection.close();
		}
	}
}
