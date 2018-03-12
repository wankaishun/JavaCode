package wankaishun.github.p2p;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	private final String selector="receiver='B'";
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageConsumer messageConsumer;
	public Consumer()  {
	 	connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
		ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://127.0.0.1:61616");
		try {
			connection = (Connection) connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
			destination=session.createQueue("firstqueue");
			messageConsumer=session.createConsumer(destination,selector);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	public void receive() {
		try {
			this.messageConsumer.setMessageListener(new Listenner());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class Listenner implements MessageListener {
		ArrayBlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(1000);
		ExecutorService service=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
				20, 
				120L,
				TimeUnit.SECONDS,
				queue);
		@Override
		public void onMessage(Message message) {
			// TODO Auto-generated method stub
			if(message instanceof MapMessage) {
				service.execute(new MessageTask((MapMessage)message));
			}
		}
	}
	public static void main(String[] args) {
		Consumer consumer=new Consumer();
		consumer.receive();
	}
}
