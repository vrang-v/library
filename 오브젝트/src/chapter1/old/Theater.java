package chapter1.old;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Theater
{
    private TicketSeller ticketSeller;
    
    public void enter(Audience audience)
    {
        Ticket ticket = ticketSeller.getTicketOffice( ).getTicket( );
        if (!audience.getBag( ).hasInvitation( )) {
            ticketSeller.getTicketOffice( ).plusAmount(ticket.getFee( ));
            audience.getBag( ).minusAmount(ticket.getFee( ));
        }
        audience.getBag( ).setTicket(ticket);
    }
}
