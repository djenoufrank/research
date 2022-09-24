package g55301.webg6.pae.model;

import java.util.List;

import org.springframework.web.client.RestTemplate;

public class Client {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String Base_URL = "http://localhost:8080/api/helloList";
        var info = restTemplate.getForObject(Base_URL, List.class);
        // List<Course> info = restTemplate.getForObject(Base_URL, List.class);
        // String info = restTemplate.getForObject(Base_URL, String.class);
        System.out.println(info.toString());

    }
}
