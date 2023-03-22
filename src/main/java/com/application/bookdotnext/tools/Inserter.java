package com.application.bookdotnext.tools;

import com.application.bookdotnext.dal.*;
import com.application.bookdotnext.dal.*;


import com.application.bookdotnext.model.*;



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
    PersonsDao personsDao = PersonsDao.getInstance();
    UsersDao usersDao = UsersDao.getInstance();
    AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
    BookReviewDao bookReviewDao = BookReviewDao.getInstance();
    AuthorDao authorDao = AuthorDao.getInstance();
    PublishersDao publishersDao = PublishersDao.getInstance();
    TopTenListsDao topTenListsDao = TopTenListsDao.getInstance();

    BookInfoDao bookInfoDao = BookInfoDao.getInstance();
    SearchHistoryDao searchHistoryDao = SearchHistoryDao.getInstance();
    VotesDao votesDao = VotesDao.getInstance();

    // INSERT objects from our model.
    //Date
    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    Date tDate1 = null;
    Date tDate2 = null;
    try {
      tDate1 = dateFormat1.parse("1997-06-16"); // userId1
      tDate2 = dateFormat1.parse("2005-11-15"); // userId2
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    //TimeStamp
    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    Timestamp t1 = null;
    Timestamp t2 = null;
    Timestamp t3 = null;
    try {
      Date parsedDate1 = dateFormat2.parse("2023-02-24 17:46:13.000"); //Admin1
      t1 = new Timestamp(parsedDate1.getTime());

      Date parsedDate2 = dateFormat2.parse("2022-01-10 18:53:17.000");
      t2 = new Timestamp(parsedDate2.getTime());

      Date parsedDate3 = dateFormat2.parse("2022-02-10 18:53:17.000");
      t3 = new Timestamp(parsedDate3.getTime());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

//    LocalDate localDate = tDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


    Publishers publishers1 = new Publishers("Lion Books", "US");
    Publishers publishers2 = new Publishers("Willowisp Press", "FR");
    publishersDao.create(publishers1);
    publishersDao.create(publishers2);

    Author author = new Author("Rose Arny", "US");
    Author author1 = new Author("John Napiery", "US");
    Author author2 = new Author("JMichael Joosten", "UK");
    Author author3 = new Author("Sian Tucker", "JP");
    Author author4 = new Author("Charles Hodge", "KR");
    Author author5 = new Author("Donald J. Wiseman", "US");
    Author author6 = new Author("Maureen Duffy", "US");
    Author author7 = new Author("Judy Wolfman", "UK");
    Author author8 = new Author("Henry Purcell", "US");

    authorDao.create(author);
    authorDao.create(author1);
    authorDao.create(author2);
    authorDao.create(author3);
    authorDao.create(author4);
    authorDao.create(author5);
    authorDao.create(author6);
    authorDao.create(author7);
    authorDao.create(author8);

    //BookInfo
    BookInfo bookInfo = new BookInfo("Henry Purcell",	1995	,"Henry Purcell set to music an entire age. Endlessly resourceful and dazzlingly innovatory",
        "http://books.google.com/books?id=RFEZAQAAIAAJ&dq=Henry+Purcell&hl=&source=gbs_api", "Fiction", "Lion Books", "Maureen Duffy",
        "http://books.google.com/books/content?id=RFEZAQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api");
    BookInfo bookInfo1 = new BookInfo("Life on a Pig Farm", 2004, "Discover what life is like on five different types of farms",
        "http://books.google.com/books?id=FCJa1BWkMMAC&dq=Life+on+a+Pig+Farm+(Carolrhoda+Photo+Books)&hl=&source=gbs_api", "Art",
        "Willowisp Press", "Judy Wolfman",
        "http://books.google.com/books/content?id=FCJa1BWkMMAC&printsec=frontcover&img=1&zoom=1&source=gbs_api");
    BookInfo bookInfo2 = new BookInfo("Life on a Pig Farm", 2021, "Discover what life is like on five different types of farms",
        "http://books.google.com/books?id=FCJa1BWkMMAC&dq=Life+on+a+Pig+Farm+(Carolrhoda+Photo+Books)&hl=&source=gbs_api", "Fiction",
        "Willowisp Press", "Judy Wolfman",
        "http://books.google.com/books/content?id=FCJa1BWkMMAC&printsec=frontcover&img=1&zoom=1&source=gbs_api");

    bookInfo = bookInfoDao.create(bookInfo);
    bookInfo1 = bookInfoDao.create(bookInfo1);
    bookInfo2 = bookInfoDao.create(bookInfo2);

    //Persons
    Persons p1 = new Persons("aspedroni7", "Alverta","Spedroni", "nh4FPTqzeWdG",true);
    Persons p2  = new Persons("mpotbury8","Modesta","Potbury","ioPcQyVVfO",false);
    Persons p3 = new Persons("oosmonda", "Olga","Osmond", "ZtaRY8uAWHr",false);


    //Users

    Users user1 = new Users(1,"mpotbury8","Modesta","Potbury",
        "ioPcQyVVfO",false,tDate1);

    Users user2 = new Users(2,"oosmonda", "Olga","Osmond",
        "ZtaRY8uAWHr",false,tDate2);


    user1 = UsersDao.getInstance().create(user1);
    user2 = UsersDao.getInstance().create(user2);

    //Admin
    Administrators admin1 = new Administrators(3,"aspedroni7", "Alverta",
        "Spedroni", "nh4FPTqzeWdG",true,t1);
    admin1 = AdministratorsDao.getInstance().create(admin1);

    //SearchHistory
    SearchHistory searchHistory1 = new SearchHistory(1,1,t1);
    SearchHistory searchHistory2 = new SearchHistory(2,2,t2);

    searchHistory1 =searchHistoryDao.create(searchHistory1);
    searchHistory2 =searchHistoryDao.create(searchHistory2);

    //Vote
    Votes vote1 = new Votes(t1,1,1);
    Votes vote2 = new Votes(t2,2,2);

    vote1 = votesDao.create(vote1);
    vote2 = votesDao.create(vote2);

    //BookReview
    BookReview bookReview1 = new BookReview(1,5.0,"Great!",t2,user1,bookInfo1);
    bookReview1 = bookReviewDao.create(bookReview1);

    BookReview bookReview2 = new BookReview(2,4.0,"Great!",t2,user1,bookInfo2);
    bookReview2 = bookReviewDao.create(bookReview2);


    // TopTenList
    TopTenLists topTenList1 = new TopTenLists(user1,bookInfo1,t1);
    topTenListsDao.create(topTenList1);




  }
}
