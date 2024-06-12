package stninfo.stn_board_backend.service.email;

import stninfo.stn_board_backend.dto.Email;
import stninfo.stn_board_backend.etc.Result;

import java.io.IOException;

public interface EmailService {

    Result sendEmail(Email email);

}
