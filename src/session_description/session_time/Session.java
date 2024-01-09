package session_description.session_time;

import logger.Logger;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public final class Session {

    private String accessToken;
    private Date expDate;

    public Session() {
        setAccessToken();
        setExpDate();
    }

    public boolean sessionAlive() {

        if (this.accessToken.length() == 20 && this.expDate.after(new Date())) {
            Logger.executionLogger(new Date(), "Session is alive");
            return true;
        } else if (!(this.accessToken.length() == 20)){
            Logger.executionLogger(new Date(), "Session token error");
            return false;
        } else if (!this.expDate.after(new Date())){
            Logger.executionLogger(new Date(), "Session time has expired");
            return false;
        } else {
            return false;
        }

    }

    private void setAccessToken() {

        String symbols = "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=";

        this.accessToken = new Random().ints (20, 0, symbols. length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

    }

    private void setExpDate() {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 30);

        this.expDate = calendar.getTime();

    }

}
