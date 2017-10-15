package com.jgos.hotelbooker;

import com.jgos.hotelbooker.entity.endpoint.ReservationRequest;
import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.RoomRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import com.jgos.hotelbooker.service.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class jakisnowyTest {


	private static final Logger log = LoggerFactory.getLogger(jakisnowyTest.class);


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
	UserDb userDb;

	@Mock
	private
	UserDb userDb2;

	@Mock
    Hotel hotel;

	@MockBean
    private
    UserRepository userRepository;

	@MockBean
    private
    RoomRepository roomRepository;

    @MockBean
    private
    ReservationRepository reservationRepository;

	private DateFormat df = new SimpleDateFormat("mm/dd/yyyy");

    @Autowired
    private ReservationService reservationService;

	@Before
	public void setUp() {

	}

	public void newone() throws ParseException {

		Date date1 = df.parse("06/01/2007");
		Date date2 = df.parse("06/02/2007");
		Date date3 = df.parse("06/03/2007");
		Date date4 = df.parse("06/04/2007");
		Date date5 = df.parse("06/05/2007");
		Date date6 = df.parse("06/06/2007");
		Date date7 = df.parse("06/07/2007");

		//log.error("dzialam");
		//ReservationRequest reservationRequest = new ReservationRequest(date1.getTime(),date2.getTime(),1);


        //reservationService.reserve(reservationRequest,"test");
    }

}
