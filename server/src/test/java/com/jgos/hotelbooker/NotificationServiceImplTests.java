package com.jgos.hotelbooker;

import com.jgos.hotelbooker.entity.endpoint.ReservationRequest;
import com.jgos.hotelbooker.entity.hotel.Feedback;
import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.hotel.Notification;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.repository.NotificationRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.RoomRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import com.jgos.hotelbooker.service.NotificationService;
import com.jgos.hotelbooker.service.NotificationServiceImpl;
import com.jgos.hotelbooker.service.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
		properties = {"management.health.mail.enabled=false"})
public class NotificationServiceImplTests {


	private static final Logger log = LoggerFactory.getLogger(NotificationServiceImplTests.class);

	@Mock
	private
	UserDb userDb;

	@MockBean
    private
	NotificationRepository notificationRepository;

	@Autowired
	private NotificationService notificationService;

	@MockBean
	private
	JavaMailSender emailSender;

	@MockBean
	private
	ReservationRepository reservationRepository;

	@Before
	public void setUp() {

	}

	@Test
	public void test1() throws ParseException {

		Notification notification = new Notification();
		notification.setActive(true);
		notification.setRESERVATION_DONE_change("asdasd");
		notification.setRESERVATION_FINISHED_change("asdasd");
		notification.setWAIT_FOR_CONFIRMATION_change("sdsdsd");
		notification.setRESERVATION_REJECTED_change("asdasd");
		when(notificationRepository.findByOwner(any())).thenReturn(notification);


		notificationService.addReservation(new Reservation(new UserDb(),new Date(),new Date(),new Room(),userDb, ReservationStatus.RESERVATION_DONE,new Feedback()));
		notificationService.addReservation(new Reservation(new UserDb(),new Date(),new Date(),new Room(),userDb, ReservationStatus.RESERVATION_FINISHED,new Feedback()));
		notificationService.addReservation(new Reservation(new UserDb(),new Date(),new Date(),new Room(),userDb, ReservationStatus.WAIT_FOR_CONFIRMATION,new Feedback()));
		notificationService.addReservation(new Reservation(new UserDb(),new Date(),new Date(),new Room(),userDb, ReservationStatus.RESERVATION_REJECTED,new Feedback()));

		doNothing().when(emailSender).send(any(SimpleMailMessage.class));
		notificationService.submitNotification();
		verify(emailSender,times(4)).send(any(SimpleMailMessage.class));
	}
}
