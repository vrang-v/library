package chapter1.improved;

import lombok.Getter;

@Getter
public class Audience
{
    private final Bag bag;
    
    public Audience(Bag bag)
    {
        this.bag = bag;
    }
    
    public Long getAmount( )
    {
        return bag.getAmount( );
    }
    
    public Long processPayment(PaymentRequest paymentRequest)
    {
        Long fee = paymentRequest.getFee( );
        bag.minusAmount(fee);
        return fee;
    }
    
    public void buy( )
    {
        Ticket ticket = TicketSeller.sellTicket(this);
        bag.setTicket(ticket);
    }
    
    public Long buy(Ticket ticket)
    {
        return bag.hold(ticket);
    }
}
