import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.* ;

class OTP {
    private  String SMTP_HOST_NAME = "smtp.gmail.com" , SMTP_PORT = "465" ,
            SSL_FACTORY = "javax.net.ssl.SSLSocketFactory" , recipient   ,
            from = "esla889900@gmail.com" , OTPCode  ;
    private double initialTime ;

    private String makeOTPCode (int lengthOfTheCode) {
        String code = "" ;
        String choseFrom = "0123456789" ;
        Random rand = new Random() ;
        for (int i = 0 ; i < lengthOfTheCode ; i ++){
            code += choseFrom.charAt(rand.nextInt(10)) ;
        }
        return code ;
    }
    OTP (String recipient) {
        this.recipient = recipient ;
    }
    OTP () {}  // Empty constructor
    void setRecipients (String recipient ) {
        this.recipient = recipient ;
    }
    public void sendMessage(String message , String subject) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from , "smwizebedkhdnluu");
                    }
                });
        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);
        InternetAddress [] addressTo = new InternetAddress [1];
        addressTo[0] = new InternetAddress(recipient) ;
        msg.setRecipients(Message.RecipientType.TO, addressTo);
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
        initialTime = Calendar.getInstance().getTimeInMillis() ;
    }
    public void sendOTP (String operation) {
        OTPCode = makeOTPCode(5) ;
        try {
            sendMessage(OTPCode , "OTP code for " + operation);
            System.out.println("Check Your mail an OTP is send please enter in in about 1 minute after that it will be not valid");
        }catch (Exception ex) {

        }
    }
    public boolean valid (String test) {
        if (!test.equals(OTPCode)){
            System.out.println("Not matched OTP");
            return false ;
        }else if (!(Math.abs(Calendar.getInstance().getTimeInMillis() - initialTime) < 60000)) {
            System.out.println("out Time to enter the otp Please enter 0 to get another one");
            return false ;
        }else {
            return true ;
        }
    }
}