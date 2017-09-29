package io.lunes.temp.util;

import com.ecwid.maleorang.MailchimpClient;
import com.ecwid.maleorang.MailchimpException;
import com.ecwid.maleorang.MailchimpObject;
import com.ecwid.maleorang.method.v3_0.lists.members.EditMemberMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wandyersilva
 */

@Component
@PropertySource(value = "classpath:mailchimp.properties", ignoreResourceNotFound = true)

public class MailchimpUtil {

    @Value( "${mailchimp.apikey}" )
    private String apikey;
    @Value( "${mailchimp.listId}" )
    private String listId;

    /**
     * Subscribes a user to list.
     * @param email email to subscribe to list
     * @param language language of the webpage being accessed
     *
     * @return returns a status message if successful or failure
     */
    public String subscribe(String email, String language) {
        MailchimpClient client = new MailchimpClient(apikey);
        try {
            EditMemberMethod.Create method = new EditMemberMethod.Create(listId, email);
            method.status = "subscribed";
            method.merge_fields = new MailchimpObject();

            client.execute(method);
            return "ok";
        } catch (MailchimpException e) {
//            e.printStackTrace();
            return MailChimpErrorUtil.checkError(e.getMessage(), language);
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return MailChimpErrorUtil.checkError("", language);
    }

}
