package com.example.BookMyShowApplication.Controllers;


import com.example.BookMyShowApplication.EntryDtos.TicketEntryDto;
import com.example.BookMyShowApplication.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto){

        try{
            String result=ticketService.addTicket(ticketEntryDto);
            return result;
        }
        catch(Exception e){
            return "Ticket is not available";
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getTicket(@PathVariable Integer id){
        return ticketService.getTicket(id);
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity getAllTickets(){
        return ticketService.getAllTickets();
    }

    @DeleteMapping("/delete/{id}")
    public void cancelTicket(@PathVariable Integer id){
        ticketService.cancelTicket(id);
    }


}
