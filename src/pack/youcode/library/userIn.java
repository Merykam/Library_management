package pack.youcode.library;

import java.time.LocalDate;
import java.util.Date;

public interface userIn {

    public int addUser(User user, int idOfBorrowedISBN, Date Bdate, Date Rdate);
}
