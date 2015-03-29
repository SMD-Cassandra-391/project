package cass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.cassandra.service.StorageServiceMBean;

public class BulkLoader {

	private JMXConnector connector;
	private StorageServiceMBean storageBean;

	public BulkLoader(String host, int port) {
		try {
			connect(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connect(String host, int port) throws IOException,
			MalformedObjectNameException {
		// TODO Auto-generated method stub
		JMXServiceURL jmxUrl = new JMXServiceURL(String.format(
				"service:jmx:rmi:///jndi/rmi://%s:%d/jmxrmi", host, port));

		Map<String, Object> env = new HashMap<String, Object>();
		connector = JMXConnectorFactory.connect(jmxUrl, env);
		MBeanServerConnection mbeanServerConn = connector
				.getMBeanServerConnection();

		ObjectName name = new ObjectName(
				"org.apache.cassandra.db:type=StorageService");
		storageBean = JMX.newMBeanProxy(mbeanServerConn, name,
				StorageServiceMBean.class);
	}

	public void close() throws IOException {
		connector.close();
	}

	public void bulkLoad(String path) {

		storageBean.bulkLoad(path);

	}

	public void purgeDirectory(File dir) {

		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				purgeDirectory(file);
			file.delete();
		}
	}

	public void execute(String path) {
		long start = System.currentTimeMillis();
		final File pathToTable = new File(path);
		Thread t = new Thread(){
			public void run(){
				bulkLoad(pathToTable.getAbsolutePath());
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("load took " + (end - start)/1000 + " seconds.");
		purgeDirectory(pathToTable);
		try {
			close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
