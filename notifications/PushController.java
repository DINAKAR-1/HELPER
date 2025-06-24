package in.gov.cgg.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.gov.cgg.PushNotificationService;
import in.gov.cgg.common.controller.PushRequest;

@RestController
@RequestMapping("/api/notifications")
public class PushController {

    @Autowired
    private PushNotificationService pushService;
	 @RequestMapping(value = "/api/notifications/send", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> sendPush(@RequestBody PushRequest request) {
        System.out.println("request==="+request);
        return ResponseEntity.ok(
            pushService.sendNotificationToToken(
                request.getToken(),
                request.getTitle(),
                request.getBody(),
                request.getLink()
            )
        );
    }
   
}
