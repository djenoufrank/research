package g55301.webg6.pae.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Info {
   

    private String message;
    private Date date;
    
    public Info(String message2, java.util.Date date2) {
    }
}
