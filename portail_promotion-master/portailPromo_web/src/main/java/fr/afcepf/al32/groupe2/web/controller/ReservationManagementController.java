package fr.afcepf.al32.groupe2.web.controller;

import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.Shopkeeper;
import fr.afcepf.al32.groupe2.service.IAuthenticationService;
import fr.afcepf.al32.groupe2.service.IServiceReservation;
import fr.afcepf.al32.groupe2.web.connexion.ConnectionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservationManagement")
public class ReservationManagementController {

	@Autowired
	private IAuthenticationService authenticationService;
	
    @Autowired
    private IServiceReservation serviceReservation;

    private List<Reservation> reservationList;

    private String withDrawalCode;
    
    @PostMapping("/reservation/{idReservation}/{withDrawalCode}")
    public Reservation validateReservation( @PathVariable Long idReservation, @PathVariable String withDrawalCode){
    	Reservation reservation = serviceReservation.rechercheReservationParIdentifiant(idReservation);    	
    	if(reservation.getWithdrawalCode().equals(withDrawalCode)){
            reservation.getReservationProduct().setWithdrawalDate(new Date());
            serviceReservation.update(reservation);
        }
        withDrawalCode = null;
        return reservation;
    }
    
    @GetMapping("/reservationList/{idCommercant}")
    public List<Reservation> getReservationList(@PathVariable Long idCommercant) {
        Shopkeeper shopkeeper = (Shopkeeper) authenticationService.findOneById(idCommercant);

        return serviceReservation.findAllByShopKeeper(shopkeeper).stream().sorted(Comparator.comparing(Reservation::getDateCreation)).collect(Collectors.toList());
    }
    

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public String getWithDrawalCode() {
        return withDrawalCode;
    }

    public void setWithDrawalCode(String withDrawalCode) {
        this.withDrawalCode = withDrawalCode;
    }
}

