package com.jgos.hotelbooker;

import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.service.ReservationParserService;
import com.jgos.hotelbooker.service.ReservationParserServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationParserServiceImplTests {


	private static final Logger log = LoggerFactory.getLogger(ReservationParserServiceImplTests.class);

	@Autowired
	ReservationParserService reservationParserService;
	@Test
	public void contextLoads() throws ParseException {
		List<Reservation> reservationList = new ArrayList<Reservation>();
		//    public Reservation(Room room, UserDb user, Date date, ReservationStatus reservationStatus, UserDb owner) {
		Room room1 = new Room();
		room1.setId(1);
		room1.setName("room1");
		Room room2 = new Room();
		room2.setId(2);
		room2.setName("room2");
		Room room3 = new Room();
		room3.setName("room3");
		room3.setId(3);

		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		Date date1 = df.parse("06/01/2007");
		Date date2 = df.parse("06/02/2007");
		Date date3 = df.parse("06/03/2007");
		Date date4 = df.parse("06/04/2007");
		Date date5 = df.parse("06/05/2007");
		Date date6 = df.parse("06/06/2007");

		reservationList.add(new Reservation(room1,null ,date2, ReservationStatus.UNKNOWN,null));
		reservationList.add(new Reservation(room1,null ,date3, ReservationStatus.UNKNOWN,null));
		reservationList.add(new Reservation(room1,null ,date1, ReservationStatus.UNKNOWN,null));

		reservationList.add(new Reservation(room2,null ,date1, ReservationStatus.UNKNOWN,null));
		reservationList.add(new Reservation(room2,null ,date2, ReservationStatus.UNKNOWN,null));
		reservationList.add(new Reservation(room2,null ,date5, ReservationStatus.UNKNOWN,null));

		UserReservationResponse result = reservationParserService.parseReservation(reservationList, new UserReservationResponse());

		log.info("TEST RESULT: + " + result);
		Assert.assertTrue(result.getReservationDataArrayList().size() == 3 );
		Assert.assertTrue(result.getReservationDataArrayList().get(0).getRoomName().equals("room1"));
		Assert.assertTrue(result.getReservationDataArrayList().get(1).getRoomName().equals("room2"));
		Assert.assertTrue(result.getReservationDataArrayList().get(2).getRoomName().equals("room2"));

		Assert.assertTrue(result.getReservationDataArrayList().get(0).getFromDate().equals(date1));
		Assert.assertTrue(result.getReservationDataArrayList().get(0).getToDate().equals(date4));


		Assert.assertTrue(result.getReservationDataArrayList().get(1).getFromDate().equals(date1));
		Assert.assertTrue(result.getReservationDataArrayList().get(1).getToDate().equals(date3));

		Assert.assertTrue(result.getReservationDataArrayList().get(2).getFromDate().equals(date5));
		Assert.assertTrue(result.getReservationDataArrayList().get(2).getToDate().equals(date6));

	}



}
