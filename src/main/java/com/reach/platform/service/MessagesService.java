package com.reach.platform.service;

import com.amazonaws.services.workmail.AmazonWorkMail;
import com.amazonaws.services.workmail.AmazonWorkMailAsyncClient;
import com.amazonaws.services.workmail.AmazonWorkMailClient;
import com.amazonaws.services.workmail.model.*;
import com.reach.platform.models.MessageResponse;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

@Service
public class MessagesService {
    public static final String ACCOUNT_SID = "AC8a5e6b710a728fb96f18eb4bcbc98605";
    public static final String AUTH_TOKEN = "32771b4faca43a18ae62fb6dae6c8f01";
    private static  String ORG_ID = "m-6bc2e5f029c940a89d872fa2598597d2";

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

    public String createUser(String displayName, String username, String password) {
        try{
//            CreateUserRequest createUserRequest = new CreateUserRequest().withDisplayName(displayName).withName(username).withPassword(password).withOrganizationId(ORG_ID);
//            CreateUserResult createUserResult = amazonWorkMail.createUser(createUserRequest);

//        String entityId = createUserResult.getUserId();
            ORG_ID = "m-6bc2e5f029c940a89d872fa2598597d2";
//        String entityId = "bbef253a-eed7-4428-aaaf-cc664773758a";
//            String entityId = "6395098b-77b0-4586-b740-ab158365a026";
            Random random = new Random();
            int x = random.nextInt(21) + 1;
            String mailbox = String.format("mailbox_%d@reachmsg.com" ,
                    x);
            String entityId = getEntityId(mailbox,true);
            //username ="asdf@reach-apps.awsapps.com";
            CreateAliasRequest createAliasRequest = new CreateAliasRequest().withAlias(username).withEntityId(entityId).withOrganizationId(ORG_ID);
            amazonWorkMail.createAlias(createAliasRequest);
//        UpdatePrimaryEmailAddressRequest updatePrimaryEmailAddressRequest = new UpdatePrimaryEmailAddressRequest().withEmail(username+ "@reach-apps.awsapps.com").withEntityId(entityId).withOrganizationId(ORG_ID);
//        amazonWorkMail.updatePrimaryEmailAddress(updatePrimaryEmailAddressRequest);
            return mailbox;
        }
        catch(Exception ex){
            System.out.println("Exception :" + ex.toString());
            return "Exception :" + ex.toString();
        }

    }

    public String deleteAlias(String alias, String username) {
        try {
//        String entityId = "bbef253a-eed7-4428-aaaf-cc664773758a";
//            String entityId = "6395098b-77b0-4586-b740-ab158365a026";
            String entityId = getEntityId(username, false);
            DeleteAliasRequest deleteAliasRequest = new DeleteAliasRequest().withAlias(alias).withEntityId(entityId).withOrganizationId(ORG_ID);
            DeleteAliasResult deleteAliasResult = amazonWorkMail.deleteAlias(deleteAliasRequest);
            ORG_ID = "m-6bc2e5f029c940a89d872fa2598597d2";

            return "Done";
        }
        catch(Exception ex){
            return "Exception :" + ex.toString();
        }
    }



    private String getEntityId(String username, boolean isCreate){

        Map<String, String> entityMap = new HashMap<>();
        entityMap.put("mailbox_1@reachmsg.com", "6395098b-77b0-4586-b740-ab158365a026");
        entityMap.put("mailbox_2@reachmsg.com", "7e0575a8-b447-4c25-9983-82dec89a2145");
        entityMap.put("mailbox_3@reachmsg.com", "a41ec676-e97a-4f29-b9d5-d5704b180b72");
        entityMap.put("mailbox_4@reachmsg.com", "4296f88e-10be-4e63-b3ee-1c59a7311c75");
        entityMap.put("mailbox_5@reachmsg.com", "07405e77-47a3-4344-ae31-f00bce962fa9");
        entityMap.put("mailbox_6@reachmsg.com", "cfad2a14-393b-454b-86ce-6f7b36af2d3c");
        entityMap.put("mailbox_7@reachmsg.com", "d50e327b-934a-4eda-a532-8bd73445c779");
        entityMap.put("mailbox_8@reachmsg.com", "605bb757-065d-460d-81de-2e5a3701be43");
        entityMap.put("mailbox_9@reachmsg.com", "58c7f8b3-ab78-4b4b-906b-d83d5f27e400");
        entityMap.put("mailbox_10@reachmsg.com", "bb2828b4-97c0-4b6b-9de7-81b5e6e57191");
        entityMap.put("mailbox_11@reachmsg.com", "7b89218a-f29f-491e-8aa0-afeb0dd16327");
        entityMap.put("mailbox_12@reachmsg.com", "c2d5dbe4-7216-416e-a553-09e650a36265");
        entityMap.put("mailbox_13@reachmsg.com", "3fb0cc36-303b-4fda-a044-0a56aa1eb13a");
        entityMap.put("mailbox_14@reachmsg.com", "3a7e97ac-367c-48cc-a3f8-d6011bce17a3");
        entityMap.put("mailbox_15@reachmsg.com", "b388e52d-1a80-4136-a885-a6fcfc959bfc");
        entityMap.put("mailbox_16@reachmsg.com", "65bf0e73-5fae-43e5-a549-b4b33f042d98");
        entityMap.put("mailbox_17@reachmsg.com", "b18a4b86-1eab-49e2-8ce0-1d4f15adebaf");
        entityMap.put("mailbox_18@reachmsg.com", "be2aa26b-facf-491d-8247-ce66dddc2340");
        entityMap.put("mailbox_19@reachmsg.com", "bd823f93-6113-4ce5-ac81-784ba0680a57");
        entityMap.put("mailbox_20@reachmsg.com", "8963aeee-b7a0-4e77-94c7-80d6535d7729");
        entityMap.put("mailbox_21@reachmsg.com", "25a6a18a-d8e0-41b2-83e3-f189f60cf527");
//        entityMap.put("mailbox_22", "");
//        entityMap.put("mailbox_23", "");
//        entityMap.put("mailbox_24", "");
//        entityMap.put("mailbox_25", "");
        return entityMap.get(username);
    }
}
