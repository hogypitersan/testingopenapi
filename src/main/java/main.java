import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class main {
    private static final String HMAC_SHA512 = "HmacSHA512";

    public static void main(String[] args) {
        try {
            Date date = new Date();
            final String uri = "http://localhost:8080/gaji-v2/open-api/karyawan/search?namaJabatan=stafasdas";
            final String apiKey = "FC2E7CD517E84D5FAEC99EEA4A15E700";
            final String method = "GET";
            final String contentType = "application/json";
            final long currentMillisecond = date.getTime();
            final String path = "/open-api/karyawan/search";
            final String token = method.concat(contentType).concat(String.valueOf(currentMillisecond)).concat(path);

            SecretKeySpec secretKeySpec = new SecretKeySpec(apiKey.getBytes(), HMAC_SHA512);
            Mac mac = Mac.getInstance(HMAC_SHA512);
            mac.init(secretKeySpec);
            byte[] macBytes = bytesToHex(mac.doFinal(token.getBytes())).getBytes();
            String resultToken = Base64.getEncoder().encodeToString(macBytes);

            RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Api-ID", "f989a3fd-599b-4746-acc0-c413af0f808c");
            headers.set("Api-Key", resultToken);
            headers.set("Api-Auth-Time", String.valueOf(currentMillisecond));

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : bytes) {
            int intVal = 0xff & hashByte;
            if (intVal < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(intVal));
        }
        return sb.toString();
    }
}
