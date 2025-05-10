to send a sms or email we need 


1)url which is a service , to send the messages
2)username
3)password
4)senderId
5)security key

and hit the send sms method 

and make sure to have 
# email and sms services
application.sms.email.service.enable=false
application.sms.service.enable=false
application.email.service.enable=false
application.officer.sms.service.enable=true

set to true in prod
/////////////////////////
sms sender has a limit to send sms so it should be recharged frequently  to run 