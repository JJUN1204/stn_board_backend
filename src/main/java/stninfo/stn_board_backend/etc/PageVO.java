package stninfo.stn_board_backend.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@ToString
@Getter
public class PageVO<T> {
    private int totalData;
    private List<T> data;

}