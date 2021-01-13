package chapter1.improved;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bag
{
    private Long       amount;
    private Ticket     ticket;
    private Invitation invitation;
    
    public Bag(Long amount)
    {
        this.amount = amount;
    }
    
    public Bag(Long amount, Invitation invitation)
    {
        this.amount     = amount;
        this.invitation = invitation;
    }
    
    public Long hold(Ticket ticket)
    {
        if (hasInvitation( )) {
            setTicket(ticket);
            return 0L;
        }
        else {
            setTicket(ticket);
            minusAmount(ticket.getFee( ));
            return ticket.getFee( );
        }
    }
    
    public boolean hasInvitation( )
    {
        return invitation != null;
    }
    
    public boolean hasTicket( )
    {
        return ticket != null;
    }
    
    public void minusAmount(Long amount)
    {
        this.amount -= amount;
    }
    
    public void plusAmount(Long amount)
    {
        this.amount += amount;
    }
}
