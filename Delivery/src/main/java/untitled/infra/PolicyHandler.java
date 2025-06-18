package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    RiderRepository riderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPrepared'"
    )
    public void wheneverOrderPrepared_TriggerDeliveryRequestOnOrderPreparedPolicy(
        @Payload OrderPrepared orderPrepared
    ) {
        OrderPrepared event = orderPrepared;
        System.out.println(
            "\n\n##### listener TriggerDeliveryRequestOnOrderPreparedPolicy : " +
            orderPrepared +
            "\n\n"
        );
        // Comments //
        //주문이 준비 완료되면 자동으로 배달을 요청하여 고객과 배달원 모두에게 신속하게 상태를 알림

        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
