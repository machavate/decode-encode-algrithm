package com.aervel.encode;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
public class Main {
    public static void main(String[] args) {
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAyrOP7fgXIJgJyp6nP/Vtlu8kW94Qu+gJjfMaTNOSd/mQJChqXiMWsZPH8uOoZGeR/9m7Y8vAU83D96usXUaKoDYiVmxoMBkfmw8DJAtHHt/8LWDdoAS/kpXyZJ5dt19Pv+rTApcjg7AoGczT+yIU7xp4Ku23EqQz70V5Rud+Qgerf6So28Pt3qZ9hxgUA6lgF7OjoYOIAKPqg07pHp2eOp4P6oQW8oXsS+cQkaPVo3nM1f+fctFGQtgLJ0y5VG61ZiWWWFMOjYFkBSbNOyJpQVcMKPcfdDRKq+9r5DFLtFGztPYIAovBm3a1Q6XYDkGYZWtnD8mDJxgEiHWCzog0wZqJtfNREnLf1g2ZOanTDcrEFzsnP2MQwIatV8M6q/fYrh5WejlNm4ujnKUVbnPMYH0wcbXQifSDhg2jcnRLHh9CF9iabkxAzjbYkaG1qa4zG+bCidLCRe0cEQvt0+/lQ40yESvpWF60omTy1dLSd10gl2//0v4IMjLMn9tgxhPp9c+C2Aw7x2Yjx3GquSYhU6IL41lrURwDuCQpg3F30QwIHgy1D8xIfQzno3XywiiUvoq4YfCkN9WiyKz0btD6ZX02RRK6DrXTFefeKjWf0RHREHlfwkhesZ4X168Lxe9iCWjP2d0xUB+lr10835ZUpYYIr4Gon9NTjkoOGwFyS5ECAwEAAQ==";
        String apiKey = "4uhkahrfajgrcdazin7dx6kp1cnioc3n";

        System.out.println(getBearerToken(publicKey,apiKey));
    }
    public static String getBearerToken(String publicKey, String apitKey){
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                Cipher cipher = Cipher.getInstance("RSA");
                byte[] encodedPublicKey = Base64.getDecoder().decode(publicKey);

                // Testing the result of encodedPublicKey decoded
                System.out.println(encodedPublicKey);

                X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
                PublicKey pk = keyFactory.generatePublic(publicKeySpec);

                cipher.init(Cipher.ENCRYPT_MODE, pk);
                byte[] encryptedApiKey =  Base64.getEncoder().encode(cipher.doFinal(apitKey.getBytes("UTF-8")));

                // Testing the result of encryptedApi encoded
                System.out.println(encryptedApiKey);
                return new String(encryptedApiKey, "UTF-8");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
    }
}