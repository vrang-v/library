package chapter1.improved;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TicketSeller
{
    private static TicketOffice ticketOffice;
    
    public void sellTo(Audience audience)
    {
        Ticket ticket = ticketOffice.getTicket( );
        Long   fee    = audience.buy(ticket);
        ticketOffice.plusAmount(fee);
    }
    
    public static Ticket sellTicket(Audience audience)
    {
        Ticket ticket    = ticketOffice.getTicket( );
        Long   paidMoney = audience.processPayment(new PaymentRequest(ticket.getFee( )));
        ticketOffice.plusAmount(paidMoney);
        return ticket;
    }
}