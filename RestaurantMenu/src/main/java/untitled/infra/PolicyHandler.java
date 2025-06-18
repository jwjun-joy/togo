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
    RestaurantRepository restaurantRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderRequested'"
    )
    public void wheneverOrderRequested_NotifyRestaurantOnSpecialOrderPolicy(
        @Payload OrderRequested orderRequested
    ) {
        OrderRequested event = orderRequested;
        System.out.println(
            "\n\n##### listener NotifyRestaurantOnSpecialOrderPolicy : " +
            orderRequested +
            "\n\n"
        );
        // Comments //
        //고객이 주문 시 특이사항(specialRequest) 또는 알러지 정보가 입력된 경우 식당에 즉시 알림을 전송하여 주문 대응 및 식품 안전을 보장하기 위함

        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
