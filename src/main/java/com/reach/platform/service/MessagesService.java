package com.reach.platform.service;

import com.amazonaws.services.workmail.AmazonWorkMail;
import com.amazonaws.services.workmail.AmazonWorkMailAsyncClient;
import com.amazonaws.services.workmail.AmazonWorkMailClient;
import com.amazonaws.services.workmail.model.UpdatePrimaryEmailAddressRequest;
import com.reach.platform.models.MessageResponse;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.amazonaws.services.workmail.model.CreateUserRequest;
import com.amazonaws.services.workmail.model.CreateUserResult;

@Service
public class MessagesService {
    public static final String ACCOUNT_SID = "AC8a5e6b710a728fb96f18eb4bcbc98605";
    public static final String AUTH_TOKEN = "32771b4faca43a18ae62fb6dae6c8f01";
    private static final String ORG_ID = "m-6bc2e5f029c940a89d872fa2598597d2";

    @Autowired
    AmazonWorkMail amazonWorkMail;

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

    public boolean createUser(String displayName, String username, String password) {
        CreateUserRequest createUserRequest = new CreateUserRequest().withDisplayName(displayName).withName(username).withPassword(password).withOrganizationId(ORG_ID);
        CreateUserResult createUserResult = amazonWorkMail.createUser(createUserRequest);

        String entityId = createUserResult.getUserId();
        UpdatePrimaryEmailAddressRequest updatePrimaryEmailAddressRequest = new UpdatePrimaryEmailAddressRequest().withEmail(username+ "@reach-apps.awsapps.com").withEntityId(entityId).withOrganizationId(ORG_ID);
        amazonWorkMail.updatePrimaryEmailAddress(updatePrimaryEmailAddressRequest);
        return true;
    }
}
