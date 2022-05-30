package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@ToString

public class ChatLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int logIndex;
    private int chatRoomIndex;
    private Date uploadDate;
    private String writer;
    private String chatContent;
}
