package wayc.backend.member.infrastructure;

import java.util.Random;

public class AwsSesUtils {

    public static String getSubject(){
        return "[넌왜코] 인증 번호 발송 메일입니다.";
    }


    public static String getEmailVerificationHtml(String authKey) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<!DOCTYPE html>");
        emailContent.append("<html>");
        emailContent.append("<head>");
        emailContent.append("</head>");
        emailContent.append("<body>");
        emailContent.append(
                " <div" +
                        "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 700px; height: 900px; border-top: 4px solid #29ABE2; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                        "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                        "		<span style=\"font-size: 50px; margin: 0 0 10px 3px;\">넌왜코</span><br/>" +
                        "		<span style=\"color: #29ABE2\">메일인증</span> 안내입니다." +
                        "	</h1>\n" +
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
                        "	    메일 인증을 위한 인증번호를 알려드립니다.<br/>" +
                        "		아래 인증 번호를 " + "<strong style=\"color:#29ABE2\">5분이내</strong>에 입력하시면 인증이 완료됩니다. <br/>" +
                        "   <div style=\"width: 576px;height: 90px; margin-top: 50px; padding: 0 27px;color: #242424;font-size: 16px;font-weight: bold;background-color: #F9F9F9;vertical-align: middle;line-height: 90px;\">인증번호 : <strong style=\"font-style: normal;font-weight: bold;color: #29ABE2\">" + authKey + "</strong></div>" +
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
                        "	    발급 받으신 인증번호를 입력하시면 이메일 인증을 완료하실 수 있습니다.<br/> <br/> <br/>" +
                        "		감사합니다.<br/>" +
                        "	<div style=\"border-top: 4px solid #29ABE2; margin: 40px auto; padding: 30px 0;\"></div>" +
                        " </div>");
        emailContent.append("</body>");
        emailContent.append("</html>");
        String content = emailContent.toString();
        return content;
    }
}
