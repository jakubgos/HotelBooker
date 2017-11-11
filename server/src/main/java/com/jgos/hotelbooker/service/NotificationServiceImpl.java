package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.hotel.Notification;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.repository.NotificationRepository;
import com.jgos.hotelbooker.service.data.EmailToSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-11-10.
 */


@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class NotificationServiceImpl implements  NotificationService{
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    NotificationRepository notificationRepository;

    private ArrayList<Reservation> reservationList;


    public NotificationServiceImpl() {
        reservationList = new ArrayList<Reservation>();
    }

    @Override
    public void addReservation(Reservation reservation)
    {
        log.info("add Reservation " + reservation);
        reservationList.add(reservation);
    }
    @Override
    public void submitNotification()
    {
        if(reservationList.size()==0) {
            log.info("submitNotification list is empty, no email sent");
            return;
        }

        Notification notification = notificationRepository.findByOwner(reservationList.get(0).getOwner());
        log.info("submitNotification : notification.isActive() "+notification.isActive() + " LIST: " + reservationList);

        if(notification.isActive()) {
            ArrayList<EmailToSend> emailList = new ArrayList<EmailToSend>();
            for (Reservation re :
                    reservationList) {
                if( notification.getMessage(re.getReservationStatus()).length() < 1)
                {
                    log.info("submitNotification message not present, continue");
                    continue;
                }
                emailList.add(prepareEmail(re, notification));
            }

            Thread t = new Thread(new MailSenderThread(emailList));
            t.start();

        }
        reservationList = new ArrayList<Reservation>();
    }

    private EmailToSend prepareEmail(Reservation reservation, Notification notification) {

        EmailToSend emailToSend = new EmailToSend(reservation.getUser().getEmail(),notification.getSubject(), notification.getMessage(reservation.getReservationStatus()));
        return emailToSend;
    }

    public class MailSenderThread implements Runnable
    {
        ArrayList<EmailToSend> emailList = new ArrayList<EmailToSend>();

        public MailSenderThread(ArrayList<EmailToSend> emailList) {
            this.emailList=emailList;
        }

        @Override
        public void run() {

            for (EmailToSend em: emailList
                 ) {
                log.info("MailSenderThread sending email " + em);
                SimpleMailMessage message = new SimpleMailMessage();
                //message.setTo(em.getTo());
                message.setTo("jackobxy@gmail.com");
                message.setSubject(em.getSubject());
                message.setText(em.getContent());
                emailSender.send(message);
            }

        }
    }


}
