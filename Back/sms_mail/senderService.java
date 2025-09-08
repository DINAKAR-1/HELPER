package in.gov.cgg.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.gov.cgg.common.model.SmsAndMail;
import in.gov.cgg.common.model.SmsAndMailDto;
import in.gov.cgg.common.repository.SmsAndMailRepository;

@Component
public class SmsAndMailService {

	@Autowired
	private SmsAndMailRepository smsAndMailRepository;

//	@Autowired
//	private SimpleMailSender simpleMailSender;
//	
	@Value("${application.sms.service.enable}")
	private String smsEnable;
	
	@Value("${application.email.service.enable}")
	private String emailEnable;
	
	@Value("${application.officer.sms.service.enable}")
	private String officerSmsEnable;
	
	
	public static final Long applicantRegistration = 1L;
	public static final Long applicationSubmit = 2L;
	public static final Long officerLoginQueryRaised = 3L;
	public static final Long applicantLoginRepliedQuery = 4L;
	public static final Long officerLoginPaymentRaised = 5L;
	public static final Long applicantLoginPaymentMade = 6L;
	public static final Long officerLoginPermissionLetterIssued = 7L;
	public static final Long forgotPassword = 8L;
	public static final Long applicationOfficerSubmit = 9L;
	public static final Long officerResponseQuery = 10L;
	public static final Long officerApplicationPayment = 11L;

	public boolean smsAndMailSender(SmsAndMailDto  smsAndMailDto) {
		
		
		 ExecutorService executorService = Executors.newCachedThreadPool();
		
		Optional<SmsAndMail> smsAndMailOptional = smsAndMailRepository.findById(smsAndMailDto.getServiceId());
		
		SmsAndMail smsAndMail = smsAndMailOptional.orElse(new SmsAndMail());
		
		String smsMessage="";
		String emailMessage="";
		
		boolean result = true;
		
		final String finalSmsMessage;
		final String finalMailMessage;
		
		if(smsAndMail.getId()!=0L) {
			
			smsMessage=smsAndMail.getSmsMessage();
			emailMessage=smsAndMail.getMailMessage();
			String replaceValue="{#var#}";
			
			
			if(smsAndMail.getId()==1L) {
				
			}else if(smsAndMail.getId()==2L ) {
				smsMessage = smsMessage.replace( replaceValue , smsAndMailDto.getReferenceId() );
				emailMessage = emailMessage.replace( replaceValue , smsAndMailDto.getReferenceId() );
				
			}else if( smsAndMail.getId()==3L || smsAndMail.getId()==4L || smsAndMail.getId()==5L || smsAndMail.getId()==7L) {
				smsMessage = smsMessage.replace( replaceValue , smsAndMailDto.getApplicationId() );
				emailMessage = emailMessage.replace( replaceValue , smsAndMailDto.getApplicationId());	
			}else if(smsAndMail.getId()==6L) {
			
				List<String> values = Arrays.asList(smsAndMailDto.getFeePaid(),smsAndMailDto.getApplicationId(),smsAndMailDto.getDate(),smsAndMailDto.getReferenceId());
				
				String  repalceString  ="\\{#var#\\}";
				
				for(String value: values) {
					
					smsMessage = smsMessage.replaceFirst( repalceString , value);
					emailMessage = emailMessage.replaceFirst( repalceString , value );
				}
			
			}else if( smsAndMail.getId()==8L) {
				smsMessage = smsMessage.replace( replaceValue , smsAndMailDto.getOtp() );
				emailMessage = emailMessage.replace( replaceValue , smsAndMailDto.getOtp() );
				
			}
			
			finalMailMessage=emailMessage;
			
			if (Boolean.parseBoolean(emailEnable)) {
				
				  Callable<Boolean> task = new Callable<Boolean>() {
                      public Boolean call() {
                          return SimpleMailSender.sendMail(smsAndMailDto.getEmailId(), finalMailMessage);
                      }
                   };
                   Future<Boolean> future = executorService.submit(task);
                   try {
                       future.get(30,TimeUnit.SECONDS);
                       if(future.isDone()) {
                    	   
                    	   result=true;
                       }else {
                    	   
                    	   result=false;
                       }
                   }catch (TimeoutException te) {
                       te.printStackTrace();
                       result=false;
                   } catch (InterruptedException e) {
                	   result=false;
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result=false;
				}
				
				
			}
			
			finalSmsMessage=smsMessage;
			
			if (Boolean.parseBoolean(smsEnable)) {
				
				
				
				
				  Callable<String> task = new Callable<String>() {
                      public String call() {
                          return SmsSender.sendSMS(smsAndMailDto.getMobileNumber(), finalSmsMessage,smsAndMail.getTemplateId());
                      }
                   };
                   Future<String> future = executorService.submit(task);
                   try {
                       future.get(30,TimeUnit.SECONDS);
                       
                       if(future.isDone()) {
                    	   
                    	   result=true;
                       }else {
                    	   
                    	   result=false;
                       }
                   }catch (TimeoutException te) {
                       te.printStackTrace();
                       result=false;
                   } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result=false;
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result=false;
				}
				
				
				
			}			
			
			
		}
		return result;
		
	}
	
	
	public boolean officerSmsSender(SmsAndMailDto smsAndMailDto) {
		
		 ExecutorService executorService = Executors.newCachedThreadPool();
		
		Optional<SmsAndMail> smsAndMailOptional = smsAndMailRepository.findById(smsAndMailDto.getServiceId());
		
		SmsAndMail smsAndMail = smsAndMailOptional.orElse(new SmsAndMail());
		
		String smsMessage="";
		
		boolean result = true;
		
		final String finalSmsMessage;
		
		if(smsAndMail.getId()!=0L) {
			
			smsMessage=smsAndMail.getSmsMessage();
//			String replaceValue1="{#var1#}";
//			String replaceValue2="{#var2#}";
			
			if(smsAndMail.getId()==9L) {
				
				List<String> values = Arrays.asList(smsAndMailDto.getReferenceId(),smsAndMailDto.getDate());
				
				String  repalceString  ="\\{#var#\\}";
				
				for(String value: values) {
					smsMessage = smsMessage.replaceFirst( repalceString , value);
				}
//				smsMessage = smsMessage.replace( replaceValue1 , smsAndMailDto.getReferenceId() ).replace(replaceValue2, smsAndMailDto.getDate());
			}else if(smsAndMail.getId()==11L ) {
				
				List<String> values = Arrays.asList(smsAndMailDto.getFeePaid(),smsAndMailDto.getReferenceId());
				
				String  repalceString  ="\\{#var#\\}";
				
				for(String value: values) {
					smsMessage = smsMessage.replaceFirst( repalceString , value);
				}
			}else if(smsAndMail.getId()==10L ) {
				
				List<String> values = Arrays.asList(smsAndMailDto.getReferenceId(),smsAndMailDto.getDate());
				
				String  repalceString  ="\\{#var#\\}";
				
				for(String value: values) {
					smsMessage = smsMessage.replaceFirst( repalceString , value);
				}
			}
			
			finalSmsMessage=smsMessage;
			
			if (Boolean.parseBoolean(officerSmsEnable)) {
				
				  Callable<String> task = new Callable<String>() {
                     public String call() {
                         return SmsSender.sendSMS(smsAndMailDto.getOfficerMobileNumber(), finalSmsMessage,smsAndMail.getTemplateId());
                     }
                  };
                  Future<String> future = executorService.submit(task);
                  try {
                      future.get(30,TimeUnit.SECONDS);
                      
                      if(future.isDone()) {
                   	   
                   	   result=true;
                      }else {
                   	   
                   	   result=false;
                      }
                  }catch (TimeoutException te) {
                      te.printStackTrace();
                      result=false;
                  } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result=false;
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result=false;
				}
			}			
		}
		return result;
		
	}

}
