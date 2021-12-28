import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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
                    rekapPenghasilanKaryawan(reader);
                    break;
                case "3":
                    addPendapatanPotongan(reader);
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
            final String uri = "http://localhost:8080/gaji-v2/open-api/karyawan/search";
            final String apiKey = "F0D0F4AC734A42208567DA68B5575E1E"; //DB Dev - C0000000
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
            headers.set("Api-ID", "d78efc7b-d30a-472b-b6b0-7ace47940900");
            headers.set("Api-Key", resultToken);
            headers.set("Api-Auth-Time", String.valueOf(currentMillisecond));

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void rekapPenghasilanKaryawan(BufferedReader reader) {
        try {
            System.out.print("Tahun bulan: ");
            String tahunBulan = reader.readLine();
            System.out.print("Pengelompokan 1: ");
            String pengelompokan1 = reader.readLine();
            System.out.print("Nilai pengelompokan 1: ");
            String nilaiPengelompokan1 = reader.readLine();
            System.out.print("Pengelompokan 2: ");
            String pengelompokan2 = reader.readLine();
            System.out.print("Nilai pengelompokan 2: ");
            String nilaiPengelompokan2 = reader.readLine();

            String params = "tahunBulan="+tahunBulan+"&pengelompokan1="+pengelompokan1+"&nilaiPengelompokan1="+nilaiPengelompokan1+"&pengelompokan2="+pengelompokan2+"&nilaiPengelompokan2="+nilaiPengelompokan2;

            Date date = new Date();
            final String uri = "http://localhost:8080/gaji-v2/open-api/pendapatanPotongan/rekapBulanan?"+params;
            final String apiKey = "CA4C4E6C550E41059BAB2AD020D2A79A"; //DB Dev - C0000014
//            final String apiKey = "F0D0F4AC734A42208567DA68B5575E1E"; //DB Dev - C0000000
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
            headers.set("Api-ID", "f989a3fd-599b-4746-acc0-c413af0f808c"); //DB Dev - C0000014
//            headers.set("Api-ID", "d78efc7b-d30a-472b-b6b0-7ace47940900"); //DB Dev - C0000000
            headers.set("Api-Key", resultToken);
            headers.set("Api-Auth-Time", String.valueOf(currentMillisecond));

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addPendapatanPotongan(BufferedReader reader) {
        try {
            System.out.print("Id karyawan: ");
            String idKaryawan = reader.readLine();
            System.out.print("Nama pendapatan potongan: ");
            String namaPendapatanPotongan = reader.readLine();
            System.out.print("Nilai: ");
            String nilai = reader.readLine();
            System.out.print("Tipe pembayaran: ");
            String tipePembayaran = reader.readLine();
            System.out.print("Bulan awal terima: ");
            String bulanAwalTerima = reader.readLine();
            System.out.print("Bulan akhir terima: ");
            String bulanAkhirTerima = reader.readLine();
            System.out.print("Tanggal bayar: ");
            String tanggalBayar = reader.readLine();
            System.out.print("Potong pajak: ");
            String potongPajak = reader.readLine();
            System.out.print("Keterangan: ");
            String keterangan = reader.readLine();

            List<Map<String, Object>> bodyList = new ArrayList<>();
            Map<String, Object> body = new HashMap<>();
            body.put("idKaryawan", idKaryawan);
            body.put("namaPendapatanPotongan", namaPendapatanPotongan);
            body.put("nilai", nilai);
            body.put("tipePembayaran", tipePembayaran);
            body.put("bulanAwalTerima", bulanAwalTerima);
            body.put("bulanAkhirTerima", bulanAkhirTerima);
            body.put("tanggalBayar", tanggalBayar);
            body.put("potongPajak", potongPajak);
            body.put("keterangan", keterangan);
            bodyList.add(body);

            Date date = new Date();
            final String uri = "http://localhost:8080/gaji-v2/open-api/pendapatanPotongan";
            final String apiKey = "F0D0F4AC734A42208567DA68B5575E1E"; //DB Dev - C0000000
            final String method = "POST";
            final String contentType = "application/json";
            final long currentMillisecond = date.getTime();
            final String path = "/open-api/pendapatanPotongan";
            final String token = method.concat(contentType).concat(String.valueOf(currentMillisecond)).concat(path);

            SecretKeySpec secretKeySpec = new SecretKeySpec(apiKey.getBytes(), HMAC_SHA512);
            Mac mac = Mac.getInstance(HMAC_SHA512);
            mac.init(secretKeySpec);
            byte[] macBytes = bytesToHex(mac.doFinal(token.getBytes())).getBytes();
            String resultToken = Base64.getEncoder().encodeToString(macBytes);

            RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Api-ID", "d78efc7b-d30a-472b-b6b0-7ace47940900");
            headers.set("Api-Key", resultToken);
            headers.set("Api-Auth-Time", String.valueOf(currentMillisecond));

            HttpEntity<List<Map<String, Object>>> entity = new HttpEntity<>(bodyList, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

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
