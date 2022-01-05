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
//    private static final String URL = "http://ftp.sofcograha.co.id:3005/gajiid-API-new-test";
    private static final String URL = "http://localhost:8080/gaji-v2";
    private static final String APIKEY = "AFF2762EC99D4D1FB66FA679A19F4614";
    private static final String APIID = "dfdc06db-8090-41b6-b72d-9afa9903ce03";

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
                    getDatakaryawan(reader);
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

    private static void getDatakaryawan(BufferedReader reader) {
        try {
            String params = "";


            System.out.print("Id karyawan: ");
            String idKaryawan = reader.readLine();
            System.out.print("Nama karyawan: ");
            String namaKaryawan = reader.readLine();
            System.out.print("Nama lokasi kerja: ");
            String namaLokasiKerja = reader.readLine();
            System.out.print("Nama status kepegawaian: ");
            String namaStatusKepegawaian = reader.readLine();
            System.out.print("Nama departemen: ");
            String namaDepartemen = reader.readLine();
            System.out.print("Nama jabatan: ");
            String namaJabatan = reader.readLine();
            System.out.print("Nama level jabatan: ");
            String namaLeveljabatan = reader.readLine();
            System.out.print("Nama kelompok payroll: ");
            String namaKelompokPayroll = reader.readLine();
            System.out.print("Status bekerja: ");
            String statusBekerja = reader.readLine();
            System.out.print("Nilai atribut tambahan 1: ");
            String nilaiAtributTambahan1 = reader.readLine();
            System.out.print("Nilai atribut tambahan 2: ");
            String nilaiAtributTambahan2 = reader.readLine();
            System.out.print("Nilai atribut tambahan 3: ");
            String nilaiAtributTambahan3 = reader.readLine();
            System.out.print("Nilai atribut tambahan 4: ");
            String nilaiAtributTambahan4 = reader.readLine();
            System.out.print("Nilai atribut tambahan 5: ");
            String nilaiAtributTambahan5 = reader.readLine();
            System.out.print("Nilai atribut tambahan 6: ");
            String nilaiAtributTambahan6 = reader.readLine();
            System.out.print("Nilai atribut tambahan 7: ");
            String nilaiAtributTambahan7 = reader.readLine();
            System.out.print("Nilai atribut tambahan 8: ");
            String nilaiAtributTambahan8 = reader.readLine();
            System.out.print("Nilai atribut tambahan 9: ");
            String nilaiAtributTambahan9 = reader.readLine();
            System.out.print("Nilai atribut tambahan 10: ");
            String nilaiAtributTambahan10 = reader.readLine();

            if (!idKaryawan.equals("")) {
                params = params + "idKaryawan=" + idKaryawan + "&";
            }
            if (!namaKaryawan.equals("")) {
                params = params + "namaKaryawan=" + namaKaryawan + "&";
            }
            if (!namaLokasiKerja.equals("")) {
                params = params + "namaLokasiKerja=" + namaLokasiKerja + "&";
            }
            if (!namaStatusKepegawaian.equals("")) {
                params = params + "namaStatusKepegawaian=" + namaStatusKepegawaian + "&";
            }
            if (!namaDepartemen.equals("")) {
                params = params + "namaDepartemen=" + namaDepartemen + "&";
            }
            if (!namaJabatan.equals("")) {
                params = params + "namaJabatan=" + namaJabatan + "&";
            }
            if (!namaLeveljabatan.equals("")) {
                params = params + "namaLevelJabatan=" + namaLeveljabatan + "&";
            }
            if (!namaKelompokPayroll.equals("")) {
                params = params + "namaKelompokPayroll=" + namaKelompokPayroll + "&";
            }
            if (!statusBekerja.equals("")) {
                params = params + "statusBekerja=" + statusBekerja + "&";
            }
            if (!nilaiAtributTambahan1.equals("")) {
                params = params + "nilaiAtributTambahan1=" + nilaiAtributTambahan1 + "&";
            }
            if (!nilaiAtributTambahan2.equals("")) {
                params = params + "nilaiAtributTambahan2=" + nilaiAtributTambahan2 + "&";
            }
            if (!nilaiAtributTambahan3.equals("")) {
                params = params + "nilaiAtributTambahan3=" + nilaiAtributTambahan3 + "&";
            }
            if (!nilaiAtributTambahan4.equals("")) {
                params = params + "nilaiAtributTambahan4=" + nilaiAtributTambahan4 + "&";
            }
            if (!nilaiAtributTambahan5.equals("")) {
                params = params + "nilaiAtributTambahan5=" + nilaiAtributTambahan5 + "&";
            }
            if (!nilaiAtributTambahan6.equals("")) {
                params = params + "nilaiAtributTambahan6=" + nilaiAtributTambahan6 + "&";
            }
            if (!nilaiAtributTambahan7.equals("")) {
                params = params + "nilaiAtributTambahan7=" + nilaiAtributTambahan7 + "&";
            }
            if (!nilaiAtributTambahan8.equals("")) {
                params = params + "nilaiAtributTambahan8=" + nilaiAtributTambahan8 + "&";
            }
            if (!nilaiAtributTambahan9.equals("")) {
                params = params + "nilaiAtributTambahan9=" + nilaiAtributTambahan9 + "&";
            }
            if (!nilaiAtributTambahan10.equals("")) {
                params = params + "nilaiAtributTambahan10=" + nilaiAtributTambahan10 + "&";
            }

            Date date = new Date();
            final String uri = URL + "/open-api/karyawan/search?" + params;
            final String apiKey = APIKEY;
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
            headers.set("Api-ID", APIID);
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
            final String uri = URL + "/open-api/pendapatanPotongan/rekapBulanan?"+params;
            final String apiKey = APIKEY;
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
            headers.set("Api-ID", APIID);
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
            final String uri = URL + "/open-api/pendapatanPotongan";
            final String apiKey = APIKEY;
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
            headers.set("Api-ID", APIID);
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
