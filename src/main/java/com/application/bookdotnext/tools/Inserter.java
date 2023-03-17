package com.application.bookdotnext.tools;

import com.application.bookdotnext.dal.BookInfoDao;
import com.application.bookdotnext.model.BookInfo;
import com.application.bookdotnext.model.BookInfo.Categories;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Inserter {

  public static void main(String[] args) throws SQLException {
    // DAO instances.
    BookInfoDao bookInfoDao = BookInfoDao.getInstance();

    // INSERT objects from our model.

    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    Date tDate1 = null;
    Date tDate2 = null;
    Date tDate3 = null;
    try {
      tDate1 = dateFormat1.parse("1995-06-01");
      tDate2 = dateFormat1.parse("2004-03-16");
      tDate3 = dateFormat1.parse("2021-03-16");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    LocalDate localDate = tDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    BookInfo bookInfo = new BookInfo("Henry Purcell",	1995	,"Henry Purcell set to music an entire age. Endlessly resourceful and dazzlingly innovatory",
        "http://books.google.com/books?id=RFEZAQAAIAAJ&dq=Henry+Purcell&hl=&source=gbs_api", Categories.Fiction, "Lion Books", "Maureen Duffy",
        "http://books.google.com/books/content?id=RFEZAQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api");
    BookInfo bookInfo1 = new BookInfo("Life on a Pig Farm", 2004, "Discover what life is like on five different types of farms",
        "http://books.google.com/books?id=FCJa1BWkMMAC&dq=Life+on+a+Pig+Farm+(Carolrhoda+Photo+Books)&hl=&source=gbs_api", Categories.Art,
        "Willowisp Press", "Judy Wolfman",
        "http://books.google.com/books/content?id=FCJa1BWkMMAC&printsec=frontcover&img=1&zoom=1&source=gbs_api");
    BookInfo bookInfo2 = new BookInfo("Life on a Pig Farm", 2021, "Discover what life is like on five different types of farms",
        "http://books.google.com/books?id=FCJa1BWkMMAC&dq=Life+on+a+Pig+Farm+(Carolrhoda+Photo+Books)&hl=&source=gbs_api", Categories.Fiction,
        "Willowisp Press", "Judy Wolfman",
        "http://books.google.com/books/content?id=FCJa1BWkMMAC&printsec=frontcover&img=1&zoom=1&source=gbs_api");

    bookInfo = bookInfoDao.create(bookInfo);
    bookInfo1 = bookInfoDao.create(bookInfo1);
    bookInfo2 = bookInfoDao.create(bookInfo2);


  }
}
