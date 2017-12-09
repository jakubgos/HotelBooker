package com.jgos.hotelbooker;

import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.hotel.Feedback;
import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.service.ReservationParserService;
import com.jgos.hotelbooker.service.ReservationParserServiceImpl;
import com.jgos.hotelbooker.service.ReservationService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationParserServiceImplTests {


	private static final Logger log = LoggerFactory.getLogger(ReservationParserServiceImplTests.class);

	@Autowired
	ReservationParserService reservationParserService;

	@Mock
	private Hotel hotel;
	@Mock
	private HotelDetail hotelDetail;
	@Mock
	private
	Room room1;
	@Mock
	private
	Room room2;
	@Mock
	private
	Room room3;

	@Mock
	private
	UserDb userDb;

	@Mock
	private
	UserDb userDb2;

	@Mock
	private
	UserDb userOwnerDb;



	DateFormat df = new SimpleDateFormat("mm/dd/yyyy");

	@Before
	public void setUp() {
		when(room1.getHotel()).thenReturn(hotel);
		when(room1.getName()).thenReturn("room1");
		when(room2.getHotel()).thenReturn(hotel);
		when(room2.getName()).thenReturn("room2");
		when(room3.getHotel()).thenReturn(hotel);
		when(room3.getName()).thenReturn("room3");

		when(hotel.getHotelDetail()).thenReturn(hotelDetail);
		when(hotelDetail.getName()).thenReturn("mockName");
	}

	@Test
	public void test1() throws ParseException {
		List<Reservation> reservationList = new ArrayList<Reservation>();



		when(hotel.getHotelDetail()).thenReturn(hotelDetail);
		when(hotelDetail.getName()).thenReturn("mockName");
		//    public Reservation(Room room, UserDb user, Date date, ReservationStatus reservationStatus, UserDb owner) {

		Date date1 = df.parse("06/01/2007");
		Date date2 = df.parse("06/02/2007");
		Date date3 = df.parse("06/03/2007");
		Date date4 = df.parse("06/04/2007");
		Date date5 = df.parse("06/05/2007");
		Date date6 = df.parse("06/06/2007");
		Date date7 = df.parse("06/07/2007");

		reservationList.add(new Reservation(userDb,date1 ,date3, room1,userOwnerDb,  ReservationStatus.UNKNOWN,new Feedback()));

		reservationList.add(new Reservation(userDb,date1 ,date5, room2,userOwnerDb,  ReservationStatus.UNKNOWN,new Feedback()));

		reservationList.add(new Reservation(userDb2,date1 ,date5, room2,userOwnerDb,  ReservationStatus.UNKNOWN,new Feedback()));


		UserReservationResponse result = reservationParserService.parseReservation(reservationList, new UserReservationResponse());

		log.info("TEST RESULT: + " + result);
		Assert.assertTrue(result.getReservationDataArrayList().size() == 3 );
		Assert.assertTrue(result.getReservationDataArrayList().get(0).getRoomName().equals("room1"));
		Assert.assertTrue(result.getReservationDataArrayList().get(1).getRoomName().equals("room2"));
		Assert.assertTrue(result.getReservationDataArrayList().get(2).getRoomName().equals("room2"));

		Assert.assertTrue(result.getReservationDataArrayList().get(0).getFromDate().equals(date1));
		Assert.assertTrue(result.getReservationDataArrayList().get(0).getToDate().equals(date4));


		Assert.assertTrue(result.getReservationDataArrayList().get(1).getFromDate().equals(date1));
		Assert.assertTrue(result.getReservationDataArrayList().get(1).getToDate().equals(date6));

		Assert.assertTrue(result.getReservationDataArrayList().get(2).getFromDate().equals(date1));
		Assert.assertTrue(result.getReservationDataArrayList().get(2).getToDate().equals(date6));



	}

	@Test
	public void test2() throws ParseException {
		UserReservationResponse result = reservationParserService.parseReservation(null, new UserReservationResponse());

		Assert.assertTrue(result.getReservationDataArrayList().size()==0);
	}

}
