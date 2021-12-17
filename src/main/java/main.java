import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Date;

public class main {
    private static final String HMAC_SHA512 = "HmacSHA512";

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        System.out.println("1. Get data karyawan");
        System.out.println("2. Rekap penghasilan");
        System.out.println("3. Input pendapatan/potongan");
        System.out.print("Pilih: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String pilih = reader.readLine();
            switch(pilih) {
                case "1":
                    getDatakaryawan();
                    break;
                case "2":
                    rekapPenghasilanKaryawan();
                    break;
                default:
                    System.out.println("");
                    System.out.println("Menu tidak tersedia");
                    System.out.println("");
                    break;
            }
            System.out.println("");
            run();
        } catch (Exception e) {

        }
    }

    private static void getDatakaryawan() {
        try {
            Date date = new Date();
            final String uri = "http://localhost:8080/gaji-v2/open-api/karyawan/search?namaJabatan=stafasdas";
            final String apiKey = "FC2E7CD517E84D5FAEC99EEA4A15E700"; //DB Dev - C0000000
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

    private static void rekapPenghasilanKaryawan() {
        try {
            Date date = new Date();
            final String uri = "http://localhost:8080/gaji-v2/open-api/pendapatanPotongan/rekapBulanan?tahunBulan=202111";
            final String apiKey = "CA4C4E6C550E41059BAB2AD020D2A79A"; //DB Dev - C0000014
            final String method = "GET";
            final String contentType = "application/json";
            final long currentMillisecond = date.getTime();
            final String path = "/open-api/pendapatanPotongan/rekapBulanan";
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

    private static String bytesToHex(byte[] bytes) {
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
