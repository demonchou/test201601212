package excise.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


/**
 * @author sars
 */
public class KafkaProducerTest
{
	public static void main(String[] args)
	{
		String submit = "{\"msgContent\":{\"notifyType\":\"submit_maintain\",\"messageType\":\"platform_id\",\"messageList\":[{\"maintainList\":[{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【变大】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":100000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【变小】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":10000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":500000,\"bankName\":\"【不变】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":50000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【临时无备份】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":false,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":100000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【计划】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":false,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":100000,\"backupChannelNote\":\"DEFAULT\"}],\"keyList\":[\"2014082822PT79963677\",\"2008122815PT00000025\",\"2013010715PT15651940\",\"2013070216PT16730911\",\"2017030917PT01597391\"]},]},\"businessKey\": \"GATE_MAINTAIN_NOTIFY\",\"accessible\": true}";
		String cancel = "{\"msgContent\":{\"notifyType\":\"cancel_maintain\",\"messageType\":\"platform_id\",\"messageList\":[{\"maintainList\":[{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【变大】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":100000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【变小】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":10000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":500000,\"bankName\":\"【不变】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":50000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【临时无备份】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":true,\"hasBackupChannel\":false,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":100000,\"backupChannelNote\":\"DEFAULT\"},{\"maintainEnd\":\"2017-06-24 18:20:00\",\"gateId\":\"0150\",\"backupChannelCode\":\"AbcQuickPay\",\"backupBankDayLimit\":100000,\"bankName\":\"【计划】\",\"originBankDayLimit\":500000,\"gateTypeName\":\"代扣\",\"maintainStart\":\"2017-06-24 18:00:00\",\"gateCode\":\"AbcDebitQuickPay\",\"tmpMaintain\":false,\"hasBackupChannel\":true,\"originBankOrderLimit\":50000,\"backupBankOrderLimit\":100000,\"backupChannelNote\":\"DEFAULT\"}],\"keyList\":[\"2014082822PT79963677\",\"2008122815PT00000025\",\"2013010715PT15651940\",\"2013070216PT16730911\",\"2017030917PT01597391\"]}]},\"businessKey\": \"GATE_MAINTAIN_NOTIFY\",\"accessible\": true}";

		Properties props = new Properties();
		props.put("bootstrap.servers", "223.252.220.247:29092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 1; i++)
		{
			producer.send(new ProducerRecord<>("gateservice.maintain.dev", Integer.toString(i),
					cancel));
			try {
				System.err.println("====>>> sleep for " + Integer.toString(i));
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		producer.close();
	}
}
