package boot.stream;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = boot.stream.KafkaStreamApplication.class)
public class KafkaStreamApplicationTest {
    @Autowired
    private Source source;
    
    public void send(){
        source.output().send(MessageBuilder.withPayload(new KafkaStreamApplication.Person("wanggan")).build());
    }
    
}