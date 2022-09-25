package com.reach.platform.service;

import com.reach.platform.models.MessageResponse;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    public static final String ACCOUNT_SID = "AC8a5e6b710a728fb96f18eb4bcbc98605";
    public static final String AUTH_TOKEN = "32771b4faca43a18ae62fb6dae6c8f01";

    public MessageResponse getMessages() {
        List<String> result = new ArrayList<>();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Message> messages = Message.reader()
                //.setFrom(new PhoneNumber("+19797398135"))
                .setTo("whatsapp:+14155238886")
                .limit(20).read();
        System.out.println( "   koffffkok  "  + messages.toString() + "  mk  " + messages.getLimit());
        for(Message record : messages) {
            result.add(record.getFrom() + "   kokok  "  + record.getBody()) ;
        }

        MessageResponse response = new MessageResponse();
        response.setMessages(result);
        return response;
    }
}
