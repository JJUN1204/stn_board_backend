package stninfo.stn_board_backend.etc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Result {
    private String result;

    public Result(String result) {
        this.result = result;
    }
}
