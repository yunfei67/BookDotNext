package com.application.bookdotnext.tools;

import com.application.bookdotnext.dal.BookInfoDao;
import com.application.bookdotnext.dal.SearchHistoryDao;
import com.application.bookdotnext.dal.UsersDao;
import com.application.bookdotnext.dal.VotesDao;
import com.application.bookdotnext.model.BookInfo;
import com.application.bookdotnext.model.BookInfo.Categories;
import com.application.bookdotnext.model.Persons;
import com.application.bookdotnext.model.SearchHistory;
import com.application.bookdotnext.model.Users;
import com.application.bookdotnext.model.Votes;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    SearchHistoryDao searchHistoryDao = SearchHistoryDao.getInstance();
    VotesDao votesDao = VotesDao.getInstance();

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

    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    Timestamp t1 = null;
    Timestamp t2 = null;
    Timestamp t3 = null;
    try {
      Date parsedDate1 = dateFormat2.parse("2022-01-01 18:53:17.000");
      t1 = new Timestamp(parsedDate1.getTime());

      Date parsedDate2 = dateFormat2.parse("2022-01-10 18:53:17.000");
      t2 = new Timestamp(parsedDate2.getTime());
      Date parsedDate3 = dateFormat2.parse("2022-02-10 18:53:17.000");
      t3 = new Timestamp(parsedDate3.getTime());



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

    Persons p1 = new Persons(1,"yc","yang","chen","a",false);
    Persons p2 = new Persons(2,"hcw","h","c","b", false);
    Persons p3 = new Persons(3, "test","t","s","c",false);

    Users user1 = new Users(1,"yc","yang","chen","a",false,tDate1);
    Users user2 = new Users(2,"hcw","h","c","b", false,tDate2);
    Users user3 = new Users(3, "test","t","s","c",false,tDate3);

    user1 = UsersDao.getInstance().create(user1);
    user2 = UsersDao.getInstance().create(user2);
    user3 = UsersDao.getInstance().create(user3);


    SearchHistory searchHistory1 = new SearchHistory(1,1, t1);
    SearchHistory searchHistory2 = new SearchHistory(2,2,t2);
    SearchHistory searchHistory3 = new SearchHistory(3,3, t3);

    searchHistory1 =searchHistoryDao.create(searchHistory1);
    searchHistory2 =searchHistoryDao.create(searchHistory2);
    searchHistory3 =searchHistoryDao.create(searchHistory3);



    Votes vote1 = new Votes(t1,1,1);
    Votes vote2 = new Votes(t2,2,2);
    Votes vote3 = new Votes(t3,3,3);

    vote1 = votesDao.create(vote1);
    vote2 = votesDao.create(vote2);
    vote3 = votesDao.create(vote3);






  }
}
